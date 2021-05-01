package com.jay.camel.sender.routes.patterns;

import com.jay.camel.sender.bean.DeciderBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class EipPatternContentBasedRouter extends RouteBuilder {

  @Autowired
  private DeciderBean deciderBean;

  @Override
  public void configure() throws Exception {
    from("file:/Users/jay/Git_Repository/apache-camel/camel-sender/files/input") // endpoint: from
        .routeId("Files-Input-Route") // 定義 routeId
        .transform().body(String.class) // 要判斷 body 的內容需先轉換成 String
        .choice() // 使用 content based routing
        /*
           可參考 https://camel.apache.org/components/3.9.x/languages/simple-language.html
           使用 simple language 的 operator 判斷副檔名是否為 xml
         */
        .when(simple("${file:ext} ends with 'xml'"))
            .log("xml file")
        .when(simple("${body} contains 'USD'")) // 使用 simple language 判斷檔案內容是否包含 'USD'
            .log("not xml file but contains 'USD'")
        .when(method(deciderBean)) // 將上面的 simple language 改成共用的 bean 去判斷
        .otherwise()
            .log("not xml file")
        .end()
        .to("direct://log-file-values") // route to a reusable writing log content endpoint
        .to("file:/Users/jay/Git_Repository/apache-camel/camel-sender/files/output"); // route file to another folder
  }
}
