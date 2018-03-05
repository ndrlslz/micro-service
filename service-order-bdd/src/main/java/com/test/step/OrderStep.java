package com.test.step;

import com.test.common.CucumberRequest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import static org.hamcrest.Matchers.is;

public class OrderStep extends BaseStep {
    private final static String RETRIEVE_ORDERS_PATH = "shops/{id}/orders";

    @Given("I create a request to retrieve orders")
    public void retrieveOrders() {
        newRequest(new CucumberRequest()
                .withPath(RETRIEVE_ORDERS_PATH)
                .withPathParam("id", "1"));
    }

    @And("order count is (\\d+)")
    public void orderCountIs(int count) {
        getResponse().body("data.size()", is(count));
    }
}
