package com.jay.camelmicroservicea.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleLogProcessingComponent {

  private Logger logger = LoggerFactory.getLogger(SimpleLogProcessingComponent.class);

  public void process(String message) {
      logger.info("SimpleLogProcessingComponent: {}", message);
  }

}
