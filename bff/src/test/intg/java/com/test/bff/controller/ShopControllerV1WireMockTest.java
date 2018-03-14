package com.test.bff.controller;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;

import static com.test.bff.util.WireMockStub.stubGet;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShopControllerV1WireMockTest extends ControllerTestBase {
    @ClassRule
    public static WireMockRule stubServer = new WireMockRule(10000);

    @Test
    public void should_return_shop() throws InterruptedException, IOException {
        stubGet("/service-order/shops/1/orders", "order", 200, "OrdersOfShopOne.json");
        stubGet("/service-vehicle/vehicles\\?.*", "shopId", "1", "vehicle", 200, "VehiclesOfShopOne.json");

        when()
                .get(prefix + "v1/shops/1")
                .then()
                .statusCode(200)
                .body("orders[0].price", equalTo("9999"))
                .body("orders[0].vehicle", equalTo("wire_mock_benz"))
                .body("orders[0].shopName", equalTo("name1"))
                .body("vehicles[0].name", equalTo("wire_mock_benz"))
                .body("vehicles[0].price", equalTo("200"));

    }
}
