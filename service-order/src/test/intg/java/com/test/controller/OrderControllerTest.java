package com.test.controller;

import io.restassured.RestAssured;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.when;
import static io.restassured.config.JsonConfig.jsonConfig;
import static org.hamcrest.core.IsEqual.equalTo;

public class OrderControllerTest extends ControllerTestBase {

    @Before
    public void setUp() throws Exception {
        RestAssured.config = RestAssured.config().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));
    }

    @After
    public void tearDown() throws Exception {
        RestAssured.reset();

    }

    @Test
    public void should_return_orders_of_specific_shop() {
        when()
                .get(prefix + "shops/1/orders")
                .then()
                .statusCode(200)
                .body("data[0].vehicle", equalTo("benz"))
                .body("data[0].price", equalTo(new BigDecimal("99.00")))
                .body("data[1].vehicle", equalTo("mar"))
                .body("data[1].price", equalTo(new BigDecimal("100.00")));
    }
}
