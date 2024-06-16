package cn.pepedd.hpoj.judge;

import cn.pepedd.hpoj.judge.rabbitmq.InitRabbitMq;
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
public class JudgeServiceApplication {
  public static void main(String[] args) {
    InitRabbitMq.doInit();
    SpringApplication.run(JudgeServiceApplication.class, args);
  }
}
