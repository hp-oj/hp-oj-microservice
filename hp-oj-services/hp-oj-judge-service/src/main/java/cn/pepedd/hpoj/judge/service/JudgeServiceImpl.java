package cn.pepedd.hpoj.judge.service;

import cn.hutool.json.JSONUtil;
import cn.pepedd.hpoj.client.service.QuestionFeignClient;
import cn.pepedd.hpoj.client.service.QuestionSubmitFeignClient;
import cn.pepedd.hpoj.comm.common.enums.ErrorCode;
import cn.pepedd.hpoj.entity.codesandbox.ExecuteCodeRequest;
import cn.pepedd.hpoj.entity.codesandbox.ExecuteCodeResponse;
import cn.pepedd.hpoj.entity.codesandbox.JudgeInfo;
import cn.pepedd.hpoj.entity.dto.question.JudgeCase;
import cn.pepedd.hpoj.entity.enums.QuestionSubmitStatusEnum;
import cn.pepedd.hpoj.comm.exception.ThrowUtils;
import cn.pepedd.hpoj.judge.service.codesandbox.CodeSandbox;
import cn.pepedd.hpoj.judge.service.codesandbox.CodeSandboxFactory;
import cn.pepedd.hpoj.judge.service.codesandbox.CodeSandboxProxy;
import cn.pepedd.hpoj.judge.service.strategy.JudgeContext;
import cn.pepedd.hpoj.entity.pojo.question.BizQuestion;
import cn.pepedd.hpoj.entity.pojo.question.Question;
import cn.pepedd.hpoj.entity.pojo.questionSubmit.BizQuestionSubmit;
import cn.pepedd.hpoj.entity.pojo.questionSubmit.QuestionSubmit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author pepedd864
 * @since 2024/6/10
 */
@Service
public class JudgeServiceImpl implements JudgeService {

  @Resource
  @Lazy
  private QuestionSubmitFeignClient questionSubmitFeignClient;
  @Resource
  @Lazy
  private QuestionFeignClient questionFeignClient;
  @Resource
  private JudgeManager judgeManager;

  @Value("${codesandbox.type:example}")
  private String type;

  /**
   * 判题
   *
   * @param questionSubmitId
   * @return
   */
  @Override
  public QuestionSubmit doJudge(long questionSubmitId) {
    // 1. 取出提交信息
    BizQuestionSubmit bizQuestionSubmit = questionSubmitFeignClient.getById(questionSubmitId);
    QuestionSubmit questionSubmit = QuestionSubmit.dbToObj(bizQuestionSubmit);
    BizQuestion bizQuestion = questionFeignClient.getById(bizQuestionSubmit.getQuestionId());
    Question question = Question.dbToObj(bizQuestion);
    // 2. 如果不为待判题 抛出异常
    ThrowUtils.throwIf(
        !bizQuestionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue()),
        ErrorCode.SYSTEM_ERROR,
        "题目正在判题中");
    // 3. 修改为判题中
    bizQuestionSubmit.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
    boolean update = questionSubmitFeignClient.updateById(bizQuestionSubmit);
    ThrowUtils.throwIf(!update,
        ErrorCode.SYSTEM_ERROR,
        "题目状态更新错误");
    // 4. 调用沙箱
    CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
    codeSandbox = new CodeSandboxProxy(codeSandbox);
    // 获取输入用例
    List<JudgeCase> judgeCaseList = question.getJudgeCase();
    List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
    // 构建请求参数
    ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
        .code(bizQuestionSubmit.getCode())
        .language(bizQuestionSubmit.getLanguage())
        .inputList(inputList)
        .build();
    // 执行代码
    ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
    // 5. 根据沙箱执行结果，设置判题状态和信息
    List<String> outputList = executeCodeResponse.getOutputList();
    JudgeContext judgeContext = JudgeContext.builder()
        .judgeInfo(executeCodeResponse.getJudgeInfo())
        .inputList(inputList)
        .outputList(outputList)
        .judgeCaseList(judgeCaseList)
        .question(question)
        .questionSubmit(questionSubmit)
        .build();
    JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
    // 6. 设置数据库中的判题结果
    bizQuestionSubmit.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
    bizQuestionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
    update = questionSubmitFeignClient.updateById(bizQuestionSubmit);
    ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
    // 7. 返回最终判题结果
    return QuestionSubmit.dbToObj(questionSubmitFeignClient.getById(bizQuestionSubmit.getId()));
  }
}
