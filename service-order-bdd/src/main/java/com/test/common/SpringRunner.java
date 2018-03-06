package com.test.common;

import com.test.Application;
import com.test.db.DBHelper;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.test.step.BaseStep.BASE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SpringRunner {
    public void runApplication() {
        if (healthCheck()) {
            return;
        }
        System.out.println(">>>>>>>>>>>>>>>Start service order<<<<<<<<<<<<<<");
        Thread thread = new Thread(() -> {
            Application.main(new String[] {});
            ConfigurableApplicationContext main = Application.configurableApplicationContext;
            DBHelper.jdbcTemplate = (JdbcTemplate) main.getBean("jdbcTemplate");
        });
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
                    System.out.println(">>>>>>>>>>>>Fail to start service order<<<<<<<<<<<<<<");
                }
                if (Duration.between(start, LocalDateTime.now()).getSeconds() > 60) {
                    System.out.println(">>>>>>>>>>>>Timeout to start service order<<<<<<<<<<<<<<");
                }
            }
        }
    }

    private boolean healthCheck() {
        try {
            given()
                    .when()
                    .baseUri(BASE_URI)
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
