package com.jay.camel.sender.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
/**
 * EIP RoutingSlip 模式：把 endpoint from 的內容傳至全部的 routing slip
 */
public class EipPatternRoutingSlip extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    String routingSlip = "direct:endpoint1,direct:endpoint2";

    from("timer:jay-routing-slip?period=10000") // endpoint from 由 timer 每 10s 發出訊息
        .transform().constant("My-message") // 傳送固定的訊息
        .routingSlip(simple(routingSlip)); // endpoint to routingSlip

    // 將 endpoint to 的內容都 log 出來
    from("direct:endpoint1")
        .log("message:${body}");

    from("direct:endpoint2")
        .log("message:${body}");


  }
}
