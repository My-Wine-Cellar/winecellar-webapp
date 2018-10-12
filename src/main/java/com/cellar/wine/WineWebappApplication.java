package com.cellar.wine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class WineWebappApplication {

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(WineWebappApplication.class, args);

    }
}
