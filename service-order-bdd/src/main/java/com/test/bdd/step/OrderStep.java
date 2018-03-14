package com.test.bdd.step;

import com.test.bdd.common.CucumberRequest;
import com.test.bdd.db.DBHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import static org.hamcrest.Matchers.is;

public class OrderStep extends BaseStep {
    private final static String RETRIEVE_ORDERS_PATH = "shops/{id}/orders";

    @Given("I have a order whose vehicle name is (.*), price is (\\d+) and shop id is (.*)")
    public void createOrder(String vehicleName, long price, String shopId) {
        DBHelper.createOrder(vehicleName, price, shopId);
    }

    @Given("I create a request to retrieve orders")
    public void retrieveOrders() {
        newRequest(new CucumberRequest()
                .withPath(RETRIEVE_ORDERS_PATH));
    }

    @And("Shop id is (.*)")
    public void shopIdIs(String shopId) {
        getRequest().addPathParam("id", shopId);
    }

    @And("order count is (\\d+)")
    public void orderCountIs(int count) {
        getResponse().body("data.size()", is(count));
    }
}
