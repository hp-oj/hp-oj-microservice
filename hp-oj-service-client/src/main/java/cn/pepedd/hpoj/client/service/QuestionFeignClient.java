package cn.pepedd.hpoj.client.service;

import cn.pepedd.hpoj.entity.pojo.question.BizQuestion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO
 *
 * @author pepedd864
 * @since 2024/6/15
 */
@FeignClient(name = "hp-oj-question-service", path = "/api/question/feign")
public interface QuestionFeignClient {
  @PostMapping("/getById")
  BizQuestion getById(@RequestParam("questionSubmitId") long questionId);
}
