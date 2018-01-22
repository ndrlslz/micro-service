package com.test.controller;

import com.test.dao.OrderDaoV1;
import com.test.dao.VehicleDaoV1;
import com.test.model.Order;
import com.test.model.Orders;
import com.test.model.Vehicle;
import com.test.model.Vehicles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ShopControllerTest {
    @LocalServerPort
    private int port;

    @MockBean
    private OrderDaoV1 orderDaoV1;

    @MockBean
    private VehicleDaoV1 vehicleDaoV1;

    @Autowired
    private ApplicationContext context;

    @Test
    public void should_return_shop() throws Exception {
        Orders orders = new Orders();
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        order.setShopName("shop_name");
        order.setPrice("100");
        order.setVehicleName("benz");
        orderList.add(order);
        orders.setOrders(orderList);


        Vehicles vehicles = new Vehicles();
        List<Vehicle> vehicleList = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setName("marz");
        vehicle.setPrice("200");
        vehicleList.add(vehicle);
        vehicles.setVehicles(vehicleList);


        Mockito.when(orderDaoV1.retrieveOrdersForShop("1")).thenReturn(orders);
        Mockito.when(vehicleDaoV1.retrieveVehiclesForShop("1")).thenReturn(vehicles);

        when()
                .get("http://localhost:" + port + "/v1/shops/1")
                .then()
                .statusCode(200)
                .body("orders[0].price", equalTo("100"))
                .body("orders[0].vehicle", equalTo("benz"))
                .body("orders[0].shopName", equalTo("shop_name"))
                .body("vehicles[0].name", equalTo("marz"))
                .body("vehicles[0].price", equalTo("200"));

    }
}
