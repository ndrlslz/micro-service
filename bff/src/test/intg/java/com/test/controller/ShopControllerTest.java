package com.test.controller;

import com.test.dao.OrderDaoV1;
import com.test.dao.VehicleDaoV1;
import com.test.model.Order;
import com.test.model.Orders;
import com.test.model.Vehicle;
import com.test.model.Vehicles;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static io.restassured.RestAssured.when;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.IsEqual.equalTo;


public class ShopControllerTest extends ControllerTestBase {
    @MockBean
    private OrderDaoV1 orderDaoV1;

    @MockBean
    private VehicleDaoV1 vehicleDaoV1;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void should_return_shop() throws Exception {
        Mockito.when(orderDaoV1.retrieveOrdersForShop("1")).thenReturn(
                new Orders().withOrders(singletonList(
                        new Order()
                                .withPrice("100")
                                .withShopName("shop_name")
                                .withVehicleName("benz")))
        );

        Mockito.when(vehicleDaoV1.retrieveVehiclesForShop("1")).thenReturn(
                new Vehicles().withVehicles(singletonList(
                        new Vehicle()
                                .withPrice("200")
                                .withName("marz")))
        );

        when()
                .get(prefix + "v1/shops/1")
                .then()
                .statusCode(200)
                .body("orders[0].price", equalTo("100"))
                .body("orders[0].vehicle", equalTo("benz"))
                .body("orders[0].shopName", equalTo("shop_name"))
                .body("vehicles[0].name", equalTo("marz"))
                .body("vehicles[0].price", equalTo("200"));

    }
}
