package com.jay.camel.sender.routes.patterns;

import com.jay.camel.sender.bean.DynamicRouterBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EipPatternDynamicRouting extends RouteBuilder {

  @Autowired
  DynamicRouterBean dynamicRouterBean;

  @Override
  public void configure() throws Exception {
    from("timer:dynamicRouting?period=10000") // endpoint from 由 timer 每 10s 發出訊息
        .transform().constant("My-message") // 傳送固定的訊息
        .dynamicRouter(method(dynamicRouterBean)); // 根據 dynamicRouterbean 決定 endpoint:to

    // dynamic router bean 決定的 endpoint to
    from("direct:endpoint1")
        .log("log:directEndpoint1");

    from("direct:endpoint2")
        .log("log:directEndpoint2");

    from("direct:endpoint3")
        .log("log:directEndpoint3");
  }
}
