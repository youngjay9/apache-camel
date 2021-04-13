package com.jay.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * 使用 ActiveMQ 需在 dependency 加入 camel-activemq-starter
 */
@Component
public class ActiveMQSenderRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    // from: 透過 timer 每 10s 發送訊息至 Queue
    from("timer:active-mq-timer?period=10000")
        // 製造固定的訊息
        .transform().constant("Jay Message For MQ")
        // 將訊息放至 queue
        .to("activemq:jay-queue");

  }
}
