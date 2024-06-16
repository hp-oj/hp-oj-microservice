package cn.pepedd.hpoj.question.feign;

import cn.pepedd.hpoj.client.service.QuestionFeignClient;
import cn.pepedd.hpoj.entity.pojo.question.BizQuestion;
import cn.pepedd.hpoj.question.service.IBizQuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author pepedd864
 * @since 2024/6/15
 */
@RestController
@RequestMapping("/question/feign")
@ApiIgnore
public class QuestionFeignController implements QuestionFeignClient {
  @Resource
  private IBizQuestionService bizQuestionService;

  @Override
  @PostMapping("/getById")
  public BizQuestion getById(long questionId) {
    return bizQuestionService.getById(questionId);
  }
}
