package com.jay.camelmicroserviceb.routes;

import org.springframework.stereotype.Component;
import org.apache.camel.builder.RouteBuilder;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder{

  @Override
  public void configure() throws Exception {
      from("activemq:jay-queue")
          .log("jay-log: received message from activeMQ");
  }
}
