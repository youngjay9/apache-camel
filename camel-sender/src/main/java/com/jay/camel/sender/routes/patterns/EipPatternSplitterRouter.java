package com.jay.camel.sender.routes.patterns;

import com.jay.camel.sender.bean.ContentSplitter;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * EIP splitter 模式：endpoint from 的內容 split 再 endpoint to anywhere
 */
public class EipPatternSplitterRouter extends RouteBuilder {

  @Autowired
  ContentSplitter contentSplitter;

  @Override
  public void configure() throws Exception {
    from("file:/Users/jay/Git_Repository/apache-camel/camel-sender/files/csv") // endpoint: from

        // 處理 body 內容可參考以下作法
        // 作法1: 將讀取的 csv 檔案內容整個轉成 csv 物件, 並將內容一行一行讀出送至 Queue
//        .unmarshal().csv() // 將讀取的 csv 檔轉成 csv 物件
//        .split(body()) // 將 body 的內容一行一行讀出
//        .log("${body}")
//        .to("activemq:split-queue"); // endpoint: to, 傳到 ActiveMQ

        // 作法2: 將讀取的 csv 檔案內容轉成 string, 並用 "," 分隔, 一個欄位一個欄位取出
        .convertBodyTo(String.class)
        .split(method(contentSplitter))
        .to("activemq:split-queue"); // endpoint: to: 傳到 ActiveMQ
  }
}
