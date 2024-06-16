package cn.pepedd.hpoj.judge.service;

import cn.pepedd.hpoj.entity.codesandbox.JudgeInfo;
import cn.pepedd.hpoj.judge.service.strategy.JavaJudgeStrategy;
import cn.pepedd.hpoj.judge.service.strategy.JudgeContext;
import cn.pepedd.hpoj.entity.pojo.questionSubmit.QuestionSubmit;
import cn.pepedd.hpoj.judge.service.strategy.DefaultJudgeStrategy;
import cn.pepedd.hpoj.judge.service.strategy.JudgeStrategy;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 *
 * @author pepedd864
 * @since 2024/6/10
 */
@Service
public class JudgeManager {

  /**
   * 执行判题
   *
   * @param judgeContext
   * @return
   */
  JudgeInfo doJudge(JudgeContext judgeContext) {
    QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
    String language = questionSubmit.getLanguage();
    JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
    if ("java".equals(language)) {
      judgeStrategy = new JavaJudgeStrategy();
    }
    return judgeStrategy.doJudge(judgeContext);
  }

}
