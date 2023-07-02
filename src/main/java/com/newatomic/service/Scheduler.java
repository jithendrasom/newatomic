package com.newatomic.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Scheduled(cron = "2 40 20 * * *")  // seconds minutes hours
    public void checker920(){
        for(int i = 0; i < 10; i++){
            System.out.println("printing at 8.40 pm");
        }
    }
}
