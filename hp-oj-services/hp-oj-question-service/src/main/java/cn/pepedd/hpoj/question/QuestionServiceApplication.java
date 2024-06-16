package cn.pepedd.hpoj.question;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO
 *
 * @author pepedd864
 * @since 2024/6/15
 */
@SpringBootApplication
@ComponentScan("cn.pepedd.hpoj")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.pepedd.hpoj.client.service"})
public class QuestionServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(QuestionServiceApplication.class, args);
  }
}
