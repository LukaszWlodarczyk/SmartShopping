package com.zzpj.smartshopping;

import com.zzpj.smartshopping.services.impl.OfferServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Timer;

@SpringBootApplication
public class SmartshoppingApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = (ApplicationContext) SpringApplication.run(SmartshoppingApplication.class, args);
        OfferServiceImpl service = applicationContext.getBean(OfferServiceImpl.class);
        Timer time = new Timer();
        time.schedule(service, 600000); //updating all offers every ten minutes


    }

}
