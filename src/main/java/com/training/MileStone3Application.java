package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.training"})
@EnableScheduling
public class MileStone3Application {

    public static void main(String [] args)
    {
        SpringApplication.run(MileStone3Application.class, args);
    }
}
