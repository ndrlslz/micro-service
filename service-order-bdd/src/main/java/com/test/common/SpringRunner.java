package com.test.common;

import com.test.Application;
import io.restassured.RestAssured;
import org.springframework.boot.SpringApplication;

import java.time.Duration;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SpringRunner {
    public static void main(String[] args) {
        new SpringRunner().runApplication();
    }

    private void setAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8763;
    }
    
    public void runApplication() {
        setAssured();
        if (healthCheck()) {
            return;
        }

        System.out.println(">>>>>>>>>>>>>>>Start service order<<<<<<<<<<<<<<");
        System.setProperty("spring.profiles.active", "bdd");
        Thread thread = new Thread(() -> SpringApplication.run(Application.class));
        thread.setDaemon(true);
        thread.start();

        checkApplicationIsRunning();
    }

    private void checkApplicationIsRunning() {
        LocalDateTime start = LocalDateTime.now();
        while (true) {
            if (healthCheck()) {
                System.out.println(">>>>>>>>>>>>>>>Start service order successfully<<<<<<<<<<<<<<");
                break;
            } else {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Fail to start service order");
                }
                if (Duration.between(start, LocalDateTime.now()).getSeconds() > 60) {
                    throw new RuntimeException("Timeout to start service order");
                }
            }
        }
    }

    private boolean healthCheck() {
        try {
            given()
                    .when()
                    .get("/health")
                    .then()
                    .statusCode(200)
                    .body("status", equalTo("UP"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
