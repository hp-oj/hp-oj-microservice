package cn.pepedd.hpoj.judge.service.strategy;


import cn.pepedd.hpoj.entity.codesandbox.JudgeInfo;

/**
 * 判题策略
 *
 * @author pepedd864
 * @since 2024/6/10
 */
public interface JudgeStrategy {

  /**
   * 执行判题
   * @param judgeContext
   * @return
   */
  JudgeInfo doJudge(JudgeContext judgeContext);
}
