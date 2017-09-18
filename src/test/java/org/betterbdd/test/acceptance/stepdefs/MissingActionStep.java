package org.betterbdd.test.acceptance.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.io.IOException;

import static org.betterbdd.model.enums.Rule.missing_action_step;
import static org.betterbdd.model.enums.Rule.missing_scenario_steps;
import static org.betterbdd.model.enums.Rule.missing_verification_step;

public class MissingActionStep extends TestBase {

    @Given("^I have a feature file with scenarios that do not have a action step$")
    public void i_have_a_feature_file_with_scenarios_that_do_not_have_a_action_step() {
        featureFile = new File(baseFeaturePath + "/nondeclaritive");
    }

    @When("^I run the Missing Action Step rule$")
    public void i_run_the_Missing_Action_Step_rule() throws Throwable {
        runRule(missing_action_step, featureFile);
    }

    @Then("^I am notified of the scenarios that are missing an action step$")
    public void i_am_notified_of_the_scenarios_that_are_missing_an_action_step() throws Throwable {
        verifyMessage(String.format("## %s ## - Scenarios with no 'When' action step found", missing_action_step.name()));

    }
}



