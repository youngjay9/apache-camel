package com.jay.camel.sender.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


//@Component
public class RestAPIConsumerRouter extends RouteBuilder {

  /**
   * 用 timer 每 10s 將訊息送至 rest api
   * 此部份後續改寫可用參數判斷，動態尋找 register
   */
  @Override
  public void configure() throws Exception {
    // 定義 route to 的 IP:port
    restConfiguration().host("localhost").port(7001);

    from("timer:rest-api-comsumer?period=10000")
        .setHeader("from", () -> "USD")
        .setHeader("to", () -> "NTD")
        .to("rest:get:/currency-exchange/from/{from}/to/{to}")
        .log("${body}");//route to 後面接的是 URI
  }
}
