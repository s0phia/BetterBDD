package org.betterbdd.test.acceptance.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.io.IOException;

import static org.betterbdd.model.enums.Rule.duplicate_feature_name;
import static org.betterbdd.model.enums.Rule.duplicate_scenario_name;
import static org.betterbdd.model.enums.Rule.empty_feature_file;

public class DuplicateScenarioNameSteps extends TestBase {
    @Given("^I have duplicate feature name in my BDD test suite$")
    public void i_have_duplicate_feature_name_in_my_BDD_test_suite() throws Throwable {
        featureFile = new File(baseFeaturePath + "/duplication");
    }

    @When("^I run the Duplicate Scenario Name rule$")
    public void i_run_the_Duplicate_Scenario_Name_rule() throws Throwable {
        runRule(duplicate_scenario_name, featureFile);
    }

    @Then("^I am notified of the duplicate scenario names$")
    public void i_am_notified_of_the_duplicate_feature_names() throws Throwable {
        verifyMessage(String.format("## %s ## - Duplicate scenario names found", duplicate_scenario_name.name()));

    }

}



