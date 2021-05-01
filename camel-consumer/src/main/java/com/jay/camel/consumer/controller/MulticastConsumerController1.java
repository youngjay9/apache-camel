package com.jay.camel.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MulticastConsumerController1 {

  Logger logger = LoggerFactory.getLogger(MulticastConsumerController1.class);

  @GetMapping("/multicast-consumer1/from/{from}/to/{to}")
  public void castConsumer1(@PathVariable String from, @PathVariable String to) {
    logger.info("MulticastConsumerController1 >> castConsumer1: from:{} to:{}", from, to);
  }
}
