package com.test.step;

import com.test.common.CucumberRequest;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.is;

public class ShopStep extends BaseStep {
    private final static String RETRIEVE_SHOPS_PATH = "/shops";

    @Given("^I create a request to retrieve shops")
    public void retrieveOrders() {
        newRequest(new CucumberRequest()
                .withPath(RETRIEVE_SHOPS_PATH));
    }

    @And("^shop list contains")
    public void retrieveShops(DataTable shops) {
        Map<String, String> orderMap = shops.asMap(String.class, String.class);
        ValidatableResponse root = getResponse().root("data.any { it == ['code': '%s', 'name': '%s'] }");
        orderMap.forEach((key, value) -> root.body(withArgs(key, value), is(true)));
    }
}
