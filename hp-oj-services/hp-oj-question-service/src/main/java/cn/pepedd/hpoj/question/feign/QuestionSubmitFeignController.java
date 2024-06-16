package cn.pepedd.hpoj.question.feign;

import cn.pepedd.hpoj.client.service.QuestionSubmitFeignClient;
import cn.pepedd.hpoj.entity.pojo.questionSubmit.BizQuestionSubmit;
import cn.pepedd.hpoj.question.service.IBizQuestionSubmitService;
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
@RequestMapping("/questionSubmit/feign")
@ApiIgnore
public class QuestionSubmitFeignController implements QuestionSubmitFeignClient {
  @Resource
  private IBizQuestionSubmitService bizQuestionSubmitService;

  @Override
  @PostMapping("/getById")
  public BizQuestionSubmit getById(long questionSubmitId) {
    return bizQuestionSubmitService.getById(questionSubmitId);
  }

  @Override
  @PostMapping("/updateById")
  public boolean updateById(BizQuestionSubmit bizQuestionSubmit) {
    return bizQuestionSubmitService.updateById(bizQuestionSubmit);
  }
}
