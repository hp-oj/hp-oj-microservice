package cn.pepedd.hpoj.judge.service.strategy;

import cn.pepedd.hpoj.entity.codesandbox.JudgeInfo;
import cn.pepedd.hpoj.entity.dto.question.JudgeCase;
import cn.pepedd.hpoj.entity.pojo.question.Question;
import cn.pepedd.hpoj.entity.pojo.questionSubmit.QuestionSubmit;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
@Builder
public class JudgeContext {
  private JudgeInfo judgeInfo;
  private List<String> inputList;
  private List<String> outputList;
  private List<JudgeCase> judgeCaseList;
  private Question question;
  private QuestionSubmit questionSubmit;
}
