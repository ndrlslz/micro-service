package com.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static java.lang.String.format;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShopControllerWithRestAssured {
    @LocalServerPort
    private int port;

    @Test
    public void should_return_all_shops() throws Exception {
        when().
                get(format("http://localhost:%s/v1/shops", port)).

        then().
                statusCode(200).
                body("data[0].name", equalTo("name1")).
                body("data[0].code", equalTo("code1"));
    }
}
