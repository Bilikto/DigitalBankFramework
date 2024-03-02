package com.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src//test//resources/features/database/accounts"},
        glue = {"com.automation.stepdefs"},
        tags = "@db",
        monochrome=true
)
public class DBRunner {
}
