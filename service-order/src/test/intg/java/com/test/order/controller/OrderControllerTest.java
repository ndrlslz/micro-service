package com.test.order.controller;

import com.test.order.model.CreateOrderRequest;
import io.restassured.RestAssured;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.config.JsonConfig.jsonConfig;
import static org.hamcrest.core.IsEqual.equalTo;

public class OrderControllerTest extends ControllerTestBase {

    @Before
    public void setUp() throws Exception {
        RestAssured.config = RestAssured.config().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));
        RestAssured.baseURI = prefix;
    }

    @After
    public void tearDown() throws Exception {
        RestAssured.reset();

    }

    @Test
    public void should_return_orders_of_specific_shop() {
        when()
                .get("shops/1/orders")
                .then()
                .statusCode(200)
                .body("data[0].vehicle", equalTo("benz"))
                .body("data[0].price", equalTo(new BigDecimal("99.00")))
                .body("data[1].vehicle", equalTo("mar"))
                .body("data[1].price", equalTo(new BigDecimal("100.00")));
    }

    @Test
    public void should_throw_resource_not_found_exception_when_create_order() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setPrice(BigDecimal.valueOf(100));
        createOrderRequest.setVehicle("maz");
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("{\"price\": 123, \"vehicle\": \"123\"}")
                .when()
                .post("shops/100/order")
                .then()
                .body("errors[0].message", equalTo("Shop not found"));
    }
}
