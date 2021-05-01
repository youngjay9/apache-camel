package com.jay.camel.sender.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class EipPatternMulticastRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    // 定義 endpoint: to 的 IP:port
    restConfiguration().host("localhost").port(7001);

    from("timer:rest-api-comsumer?period=10000") // endpoint: from
        .setHeader("from", () -> "USD") // 設定傳到 rest 的參數
        .setHeader("to", () -> "INR") // 設定傳到 rest 的參數
        .multicast() // 將 body 的資訊 route 至多個 endpoint: to
        .to("rest:get:/currency-exchange/from/{from}/to/{to}",
            "rest:get:/multicast-consumer1/from/{from}/to/{to}",
            "rest:get:/multicast-consumer2/from/{from}/to/{to}");
  }
}
