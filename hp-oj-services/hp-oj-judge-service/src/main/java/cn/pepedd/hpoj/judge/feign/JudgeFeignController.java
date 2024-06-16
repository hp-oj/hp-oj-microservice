package cn.pepedd.hpoj.judge.feign;

import cn.pepedd.hpoj.client.service.JudgeFeignClient;
import cn.pepedd.hpoj.entity.pojo.questionSubmit.QuestionSubmit;
import cn.pepedd.hpoj.judge.service.JudgeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/judge/feign")
@ApiIgnore
public class JudgeFeignController implements JudgeFeignClient {
  @Resource
  private JudgeService judgeService;

  /**
   * 判题
   *
   * @param questionSubmitId
   * @return
   */
  @Override
  @PostMapping("/do")
  public QuestionSubmit doJudge(@RequestParam("questionSubmitId") long questionSubmitId) {
    return judgeService.doJudge(questionSubmitId);
  }
}
