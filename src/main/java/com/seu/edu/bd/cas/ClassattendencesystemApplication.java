package com.seu.edu.bd.cas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ClassattendencesystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassattendencesystemApplication.class, args);
    }
    @RestController
    static
    class TestController{
        @GetMapping("/")
        public String  hello(){
            return "Hello spring boot";
        }
    }

}
