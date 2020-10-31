package edu.miu.groupx.shop.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShoppingApp {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApp.class, args);
    }

}
