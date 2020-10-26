package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication(scanBasePackages={"com.spring.boot"})
//@EnableOAuth2Client  //creates a filter bean 
//@EnableCaching
public class StudentApplication extends SpringBootServletInitializer {
    public static void main( String[] args )     {
        SpringApplication.run(StudentApplication.class, args);
    }
}
