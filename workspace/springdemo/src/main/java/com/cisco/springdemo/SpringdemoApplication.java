package com.cisco.springdemo;

import com.cisco.springdemo.service.AppService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringdemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringdemoApplication.class, args);

        AppService service = ctx.getBean("appService", AppService.class);
        service.doTask();

        System.out.println("******");
        String[] names = ctx.getBeanDefinitionNames();
        for(String name: names) {
            System.out.println(name);
        }
    }

}
