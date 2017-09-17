package org.betterbdd.model.result.impl;

import org.betterbdd.model.result.RunResult;
import org.betterbdd.rules.IRule;

public class ScenarioRunResult implements RunResult {

    private boolean success;
    private IRule rule;
    private String scenario;

    public ScenarioRunResult(boolean success, IRule rule, String scenario) {
        this.success = success;
        this.rule = rule;
        this.scenario = scenario;
    }

    public boolean isSuccess() {
        return success;
    }

    public IRule getRule() {
        return rule;
    }

    public String getScenario() {
        return scenario;
    }


    public void setRule(IRule rule) {
        this.rule = rule;
    }
}
