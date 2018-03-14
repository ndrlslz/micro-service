package com.test.vehicle.controller;

import io.restassured.RestAssured;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.when;
import static io.restassured.config.JsonConfig.jsonConfig;
import static org.hamcrest.Matchers.equalTo;

public class VehicleControllerTest extends ControllerTestBase {
    @Before
    public void setUp() throws Exception {
        RestAssured.config = RestAssured.config().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));
    }

    @After
    public void tearDown() throws Exception {
        RestAssured.reset();

    }

    @Test
    public void should_return_vehicles() throws Exception {
        when()
                .get(prefix + "vehicles?shopId=1")
                .then()
                .statusCode(200)
                .body("data[0].name", equalTo("benz"))
                .body("data[1].name", equalTo("mar"))
                .body("data[0].price", equalTo(new BigDecimal("1000.00")))
                .body("data[1].price", equalTo(new BigDecimal("2000.00")));
    }
}
