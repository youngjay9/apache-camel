package com.jay.camelmicroservicea.bean;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class CurrentTimerBean {
  public String getCurrentTime(){
    return "Time now is: " + LocalDateTime.now();
  }
}
