package com.test.constraint;

import com.test.app.TestApplication;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConstraintTest {
    @LocalServerPort
    private String port;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    public void name() {
        when()
                .get("/test?shopId=abc")
                .then()
                .statusCode(400)
                .body("errors[0].message", equalTo("Invalid shop id"));


    }
}
