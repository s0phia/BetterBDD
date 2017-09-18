package org.betterbdd.test.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "./src/test/resources/features",
        glue = {"org.betterbdd.test.acceptance.stepdefs"}
)

public class AcceptanceTestRunner { }