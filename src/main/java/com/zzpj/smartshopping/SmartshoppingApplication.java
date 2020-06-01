package com.zzpj.smartshopping;

import com.zzpj.smartshopping.services.impl.OfferServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Timer;

@SpringBootApplication
@EnableScheduling
public class SmartshoppingApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SmartshoppingApplication.class, args);


    }

}
