package com.jay.camel.consumer.controller;

import com.jay.camel.consumer.bean.CurrencyExchange;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

  Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyExchange findConversionValue(@PathVariable String from, @PathVariable String to) {
    logger.info("CurrencyExchangeController >> findConversionValue: from:{} to:{}", from, to);
    return new CurrencyExchange(1003L, from, to, BigDecimal.TEN);
  }

}
