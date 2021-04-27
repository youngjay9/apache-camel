package com.jay.camel.sender.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class FileRouter extends RouteBuilder {

  /**
   * 使用 simple language 來判斷副檔名及檔案內容
   * 可參考 https://camel.apache.org/components/3.9.x/languages/simple-language.html
   * @throws Exception
   */
  @Override
  public void configure() throws Exception {
      from("file:/Users/jay/Git_Repository/apache-camel/camel-sender/files/input")
          .routeId("Files-Input-Route")
          .transform().body(String.class) // 要判斷 body 的內容需先轉換成 String
          .choice()
            .when(simple("${file:ext} ends with 'xml'"))// 使用 simple language 的 operator 判斷副檔名是否為 xml
              .log("xml file")
            .when(simple("${body} contains 'USD'")) // 使用 simple language 判斷檔案內容是否包含 'USD'
               .log("not xml file bust contains 'USD'")
            .otherwise()
              .log("not xml file")
          .end()
          .to("direct://log-file-values") // route to a reusable writing log content endpoint
          .to("file:/Users/jay/Git_Repository/apache-camel/camel-sender/files/output");
  }
}
