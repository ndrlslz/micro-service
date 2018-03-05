package com.test.step;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CommonStep extends BaseStep {

    @When("I sent the request")
    public void submit() {
        sendRequest();
    }

    @Then("status code is (\\d+)")
    public void receiveResponse(int statusCode) {
        getResponse().statusCode(statusCode);
    }
}
