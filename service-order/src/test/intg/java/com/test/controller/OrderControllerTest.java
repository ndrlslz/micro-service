package com.test.controller;

import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

public class OrderControllerTest extends ControllerTestBase {
    @Test
    public void should_return_orders_of_specific_shop() {
        when()
                .get(prefix + "shops/1/orders")
                .then()
                .statusCode(200)
                .body("data[0].vehicle", equalTo("benz"))
                .body("data[0].price", equalTo("99"))
                .body("data[1].vehicle", equalTo("mar"))
                .body("data[1].price", equalTo("100"));
    }
}
