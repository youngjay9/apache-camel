package com.jay.camel.sender.routes.patterns;

import com.jay.camel.sender.bean.CurrencyExchange;
import com.jay.camel.sender.routes.strategy.ArrayListAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

//@Component
/**
 * EIP aggregation 模式：把 endpoint from 一次讀取的內容組成 List 再 endpoint to anywhere
 */
public class EipPatternAggregationRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from(
        "file:/Users/jay/Git_Repository/apache-camel/camel-sender/files/aggregate-json") // endpoint: from

        .unmarshal()
        .json(JsonLibrary.Jackson, CurrencyExchange.class) // 將讀取的內容轉成 CurrencyExchange 物件
        .aggregate(simple("${body.to}"),
            new ArrayListAggregationStrategy()) //根據自行定義的 ArrayListAggregationStrategy 將 CurrencyExchange 物件的 to 欄位收集至 list
        .completionSize(4) // list size 到 4 才會 route to endpoint: to
        .to("log:aggregate-json"); // 待 list size:4 再印出 log
  }
}
