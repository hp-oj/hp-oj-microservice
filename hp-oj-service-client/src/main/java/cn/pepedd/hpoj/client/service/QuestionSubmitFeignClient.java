package cn.pepedd.hpoj.client.service;

import cn.pepedd.hpoj.entity.pojo.questionSubmit.BizQuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO
 *
 * @author pepedd864
 * @since 2024/6/15
 */
@FeignClient(name = "hp-oj-question-submit-service", path = "/api/questionSubmit/feign")
public interface QuestionSubmitFeignClient {
  @PostMapping("/getById")
  BizQuestionSubmit getById(@RequestParam("questionSubmitId") long questionSubmitId);

  @PostMapping("/updateById")
  boolean updateById(@RequestBody BizQuestionSubmit bizQuestionSubmit);
}
