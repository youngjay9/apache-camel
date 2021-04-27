package com.jay.camel.sender.routes.endpoints;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LogFileValues extends RouteBuilder {

  /**
   * define a reusable writing log content endpoint
   *
   * @throws Exception
   */
  @Override
  public void configure() throws Exception {
    from("direct:log-file-values") // 定義 endpoint 的名稱
        .routeId("Log-File-Value")
        .log("${messageHistory} ${file:absolute.path}")
        .log("${file:name} ${file:name.ext} ${file:name.noext} ${file:onlyname}")
        .log("${routeId} ${camelId} ${body}");
  }
}
