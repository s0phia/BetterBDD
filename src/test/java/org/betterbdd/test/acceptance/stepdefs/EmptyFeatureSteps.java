package org.betterbdd.test.acceptance.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.io.IOException;

import static org.betterbdd.model.enums.Rule.empty_feature_file;

public class EmptyFeatureSteps extends TestBase {

    @Given("^I have an empty feature file$")
    public void i_have_an_empty_feature_file() {

        featureFile = new File(baseFeaturePath + "/redundancy/empty_file.feature");
    }

    @When("^I run the empty feature file rule$")
    public void i_run_the_empty_feature_file_rule() throws IOException {

        runRule(empty_feature_file, featureFile);
    }

    @Then("^I am notified that the feature file is empty$")
    public void i_am_notified_that_the_feature_file_is_empty() throws Exception {

        verifyMessage(String.format("## %s ## - Empty Feature files found", empty_feature_file.name()));
    }

}



