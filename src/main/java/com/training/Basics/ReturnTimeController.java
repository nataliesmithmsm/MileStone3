package com.training.Basics;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnTimeController {

TimeServices timeServices = new TimeServices();

    @RequestMapping("/timeuk")
    public String getCurrentTime(){
        return timeServices.getCurrentTime();
    }

    @RequestMapping("/canadaTime")
    public String CanadaTime(){
        return timeServices.getCanadaTime();
    }
}
