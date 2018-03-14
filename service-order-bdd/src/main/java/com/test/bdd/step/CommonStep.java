package com.test.bdd.step;

import com.test.bdd.common.SpringRunner;
import com.test.bdd.db.DBHelper;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CommonStep extends BaseStep {
    private SpringRunner springRunner = new SpringRunner();

    @Before
    public void before() {
        springRunner.runApplication();
        DBHelper.cleanDB();
    }

    @When("I sent the request")
    public void submit() {
        sendRequest();
    }

    @Then("status code is (\\d+)")
    public void receiveResponse(int statusCode) {
        getResponse().statusCode(statusCode);
    }
}
