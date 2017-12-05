package com.training.Basics;

import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Timer;

@RestController
public class ReturnTime {

TimeServices timeServices = new TimeServices();

    @RequestMapping("/timeuk")
    public String getCurrentTime(){
        return timeServices.getCurrentTime();
    }


    @RequestMapping("/canadaTime")
    public String CanadaTime(){
        return timeServices.getCanadaTime();
    }


//    public void run(){
//        Date currentTime;
//        currentTime = new Date();
//        System.out.println(currentTime);
//        ScheduledTask sc;
//
//    }
//
//    public void timer2(){
//        Timer time = new Timer();
//        ScheduledTask st = new ScheduledTask();
//        time.schedule(st, 0, 3000);
//    }

}
