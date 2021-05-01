package com.jay.camel.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MulticastConsumerController2 {
  Logger logger = LoggerFactory.getLogger(MulticastConsumerController2.class);

  @GetMapping("/multicast-consumer2/from/{from}/to/{to}")
  public void castConsumer2(@PathVariable String from, @PathVariable String to) {
    logger.info("MulticastConsumerController2 >> castConsumer2: from:{} to:{}", from, to);
  }
}
