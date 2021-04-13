package com.jay.camelmicroservicea.routes;

import com.jay.camelmicroservicea.bean.CurrentTimerBean;
import com.jay.camelmicroservicea.bean.SimpleLogProcessingComponent;
import com.jay.camelmicroservicea.bean.SimpleLoggingProcessor;
import java.time.LocalDateTime;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class MyTimerRouter extends RouteBuilder {

  @Autowired
  CurrentTimerBean currentTimerBean;

  @Autowired
  SimpleLogProcessingComponent simpleLogProcessingComponent;

  @Autowired
  SimpleLoggingProcessor simpleLoggingProcessor;

  @Override
  public void configure() throws Exception {

    from("timer:jay-timer") // 來源
        .log("${body}") // null
        .bean(currentTimerBean, "getCurrentTime")// 將來源資訊轉成 bean
        .log("${body}")
        .bean(simpleLogProcessingComponent, "process")
        .log("${body}")
        .process(simpleLoggingProcessor)
        .to("log:jay-timer");// 去處
  }
}
