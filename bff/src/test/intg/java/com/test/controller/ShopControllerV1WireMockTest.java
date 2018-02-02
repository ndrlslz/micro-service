package com.test.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.io.ByteStreams;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.google.common.io.ByteStreams.toByteArray;
import static com.test.util.WiremockStub.stubGet;
import static io.restassured.RestAssured.when;
import static java.lang.String.format;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShopControllerV1WireMockTest extends ControllerTestBase {
//    @Rule
//    public WireMockRule stubServer = new WireMockRule(10000);

    private WireMockServer stubServer = new WireMockServer(wireMockConfig().port(10000));
    @Before
    public void setUp() throws Exception {
        stubServer.start();
    }

    @Test
    public void should_return_shop() throws InterruptedException, IOException {
        InputStream OrderInputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("stub/order/OrdersOfShopOne.json");
        InputStream vehicleInputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("stub/vehicle/VehiclesOfShopOne.json");
//
        stubServer.stubFor(get(urlEqualTo("/service-order/shops/1/orders"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ByteStreams.toByteArray(OrderInputStream))));
//
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(format("stub/%s/%s", "order", "OrdersOfShopOne.json"));
//        stubServer.stubFor(get(urlEqualTo("/service-order/shops/1/orders"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withBody(toByteArray(is))));
//
//        InputStream is1 = ClassLoader.getSystemClassLoader().getResourceAsStream(format("stub/%s/%s", "vehicle", "VehiclesOfShopOne.json"));
//        stubServer.stubFor(get(urlEqualTo("/service-vehicle/vehicles?shopId=1"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withBody(toByteArray(is1))));
//
//
//        stubGet("/service-order/shops/1/orders", "order", 200, "OrdersOfShopOne.json");
//        stubGet("/service-vehicle/vehicles?shopId=1", "vehicle", 200, "VehiclesOfShopOne.json");
        stubServer.stubFor(get(urlEqualTo("/service-vehicle/vehicles?shopId=1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ByteStreams.toByteArray(vehicleInputStream))));
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
