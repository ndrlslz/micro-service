package com.test.bff.controller;


import com.test.bff.configuratioin.IntegrationTestConfiguration;
import com.test.bff.dao.StubOrderDaoV1;
import com.test.bff.dao.StubVehicleDaoV1;
import com.test.bff.exception.DaoException;
import com.test.bff.model.Order;
import com.test.bff.model.Vehicle;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class ShopControllerWithStubTest extends ControllerTestBase {
    @Autowired
    private StubOrderDaoV1 orderDaoV1;

    @Autowired
    private StubVehicleDaoV1 vehicleDaoV1;

    @After
    public void tearDown() throws Exception {
        stubVehicle().reset();
        stubOrder().reset();
    }

    @Test
    public void name() throws Exception {
        stubOrder()
                .givenShopId("1")
                .withOrders(new Order()
                        .withPrice("1000")
                        .withShopName("stub_shop_name")
                        .withVehicleName("stub_vehicle_name"))
                .andShopId("2")
                .withOrders(new Order()
                        .withPrice("2000")
                        .withShopName("stub_name2")
                        .withVehicleName("stub_vehicle2"));

        stubVehicle()
                .givenShopId("1")
                .withVehicles(new Vehicle()
                        .withName("stub_vehicle")
                        .withPrice("999"))
                .andShopId("3")
                .withException(new DaoException("Error!"));

        when()
                .get(prefix + "v1/shops/1")
                .then()
                .statusCode(200)
                .body("orders[0].price", equalTo("1000"))
                .body("vehicles[0].price", equalTo("999"));

        when()
                .get(prefix + "v1/shops/2")
                .then()
                .statusCode(200)
                .body("orders[0].shopName", equalTo("stub_name2"));

        when()
                .get(prefix + "v1/shops/3")
                .then()
                .statusCode(500);

    }

    public StubOrderDaoV1 stubOrder() {
        return orderDaoV1;
    }

    public StubVehicleDaoV1 stubVehicle() {
        return vehicleDaoV1;
    }
}
