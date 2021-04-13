package com.jay.camelmicroservicea.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleLoggingProcessor implements Processor {

  private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);

  @Override
  public void process(Exchange exchange) throws Exception {
      logger.info("SimpleLoggingProcessor: {}", exchange.getMessage().getBody());
  }
}
