package com.test.step;

import com.test.common.SpringRunner;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

public class CommonStep extends BaseStep {
    private SpringRunner springRunner = new SpringRunner();

    @Before
    public void before() {
        springRunner.runApplication();
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
