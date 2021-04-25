package com.jay.camel.sender.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaSenderRouter extends RouteBuilder {

  /**
   * 讀取檔案並 route 至 kafka 的 topic
   * @throws Exception
   */
  @Override
  public void configure() throws Exception {
    // 讀取檔案需用絕對路徑
    from("file:/Users/jay/Git_Repository/apache-camel/files/json")
        .log("${body}")
        .to("kafka:myKafkaTopic");
  }
}
