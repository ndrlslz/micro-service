package com.test.vehicle;

import com.test.utils.log.EnablePerformanceLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnablePerformanceLog
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}


