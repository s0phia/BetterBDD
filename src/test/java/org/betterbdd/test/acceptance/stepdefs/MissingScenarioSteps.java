package org.betterbdd.test.acceptance.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.betterbdd.model.enums.Rule.duplicate_feature_name;
import static org.betterbdd.model.enums.Rule.missing_scenario_steps;

public class MissingScenarioSteps extends TestBase {
    @Given("^I have a feature file with scenarios that do not have any steps$")
    public void i_have_duplicate_feature_name_in_my_BDD_test_suite() {
        featureFile = new File(baseFeaturePath + "/redundancy");
    }

    @When("^I run the Missing Scenario Steps rule$")
    public void i_run_the_Duplicate_Scenario_Name_rule() throws IOException {
        runRule(missing_scenario_steps, featureFile);
    }

    @Then("^I am notified of the scenarios with missing steps$")
    public void i_am_notified_of_the_duplicate_feature_names() throws Exception {
        verifyMessage(String.format("## %s ## - Scenarios with missing steps found", missing_scenario_steps.name()));

    }

}



