package com.test.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "com.test.bdd.step",
        strict = true,
        features = "classpath:features",
        plugin = { "pretty" })
public class CucumberRunner {
}
