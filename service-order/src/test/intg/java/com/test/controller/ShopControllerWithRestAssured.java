package com.test.controller;

import org.junit.Test;
import org.springframework.boot.context.embedded.LocalServerPort;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShopControllerWithRestAssured extends ControllerTestBase{
    @LocalServerPort
    private int port;

    @Test
    public void should_return_all_shops() throws Exception {
        when().
                get(prefix + "shops").

        then().
                statusCode(200).
                body("data[0].name", equalTo("name1")).
                body("data[0].code", equalTo("code1"));
    }
}
