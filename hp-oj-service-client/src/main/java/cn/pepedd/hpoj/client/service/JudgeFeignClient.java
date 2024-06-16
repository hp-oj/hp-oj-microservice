package cn.pepedd.hpoj.client.service;

import cn.pepedd.hpoj.entity.pojo.questionSubmit.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO
 *
 * @author pepedd864
 * @since 2024/6/15
 */
@FeignClient(name = "hp-oj-judge-service", path = "/api/judge/feign")
public interface JudgeFeignClient {
  /**
   * 判题
   *
   * @param questionSubmitId
   * @return
   */
  @PostMapping("/do")
  QuestionSubmit doJudge(@RequestParam("questionSubmitId") long questionSubmitId);
}
