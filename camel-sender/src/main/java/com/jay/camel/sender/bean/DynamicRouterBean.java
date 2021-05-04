package com.jay.camel.sender.bean;

import java.util.Map;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DynamicRouterBean {

  Logger logger = LoggerFactory.getLogger(DynamicRouterBean.class);

  int invocations;

  public String decideTheNextEndpoint(@ExchangeProperties Map<String, String> properties,
      @Headers Map<String, String> headers, @Body String body) {

    logger.info("properties:{}, headres:{}, body:{}", properties, headers, body);

    invocations++;

    logger.info("invocations: {}", invocations);

    if (invocations % 3 == 0) {
      return "direct:endpoint1";
    }

    if (invocations % 3 == 1) {
      return "direct:endpoint2, direct:endpoint3";
    }

    return null;
  }
}
