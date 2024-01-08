package com.automation.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src//test//resources/features/api/accounts"},
        glue = {"com.automation.stepdefs.api"},
        tags = "@api"
)
public class APIRunner {
}
