package com.test;

import com.test.log.EnablePerformanceLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnablePerformanceLog
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
