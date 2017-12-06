package com.training.Basics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeServices {

    public String getCurrentTime(){
        Date date = new Date();
        String strdateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strdateFormat);
        String formattedDate = dateFormat.format(date);

        return formattedDate;
    }

    public String getCanadaTime(){
        System.out.println("-----Current time of different offset-----");
        ZoneOffset zoneOffset = ZoneOffset.of("-05:00");
        ZoneId zoneId=ZoneId.ofOffset("UTC", zoneOffset);
        LocalTime offsetTime = LocalTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime=offsetTime.format(formatter);

        return  formattedTime;
    }




}
