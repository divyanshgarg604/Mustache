package com.example.mustache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Restcontroller{
      public static final Logger Log = LoggerFactory.getLogger(Restcontroller.class);
      @GetMapping("/message")
            public String welcome(){
                  return "hello";
            }
            public static void main(String args[])
            {
            SpringApplication.run(Restcontroller.class, args);
      }
}