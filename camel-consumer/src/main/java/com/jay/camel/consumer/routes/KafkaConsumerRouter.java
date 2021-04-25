package com.jay.camel.consumer.routes;

import com.jay.camel.consumer.bean.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerRouter extends RouteBuilder {

  @Autowired
  GetCurrencyExchange getCurrencyExchange;

  @Override
  public void configure() throws Exception {
    from("kafka:myKafkaTopic")
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
        .bean(getCurrencyExchange)
        .to("log:camel consumer received-message-from-kafka");
  }
}
@Component
class GetCurrencyExchange{
  Logger logger= LoggerFactory.getLogger(GetCurrencyExchange.class);
  public void getData(CurrencyExchange currencyExchange){
    logger.info("GetCurrencyExchange data: "+currencyExchange.getId());
  }
}