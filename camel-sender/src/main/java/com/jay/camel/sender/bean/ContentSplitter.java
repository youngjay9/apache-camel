package com.jay.camel.sender.bean;

import java.util.Arrays;
import java.util.List;
import org.apache.camel.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContentSplitter {

  Logger logger = LoggerFactory.getLogger(ContentSplitter.class);

  public List<String> split(@Body String body) {
    logger.info("ContentSplitter >> split body: {}", body);
    String[] arr = body.split(",");
    return Arrays.asList(arr);
  }
}
