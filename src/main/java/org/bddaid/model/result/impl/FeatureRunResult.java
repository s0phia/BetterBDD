package org.bddaid.model.result.impl;

import org.bddaid.model.Feature;
import org.bddaid.model.result.RunResult;
import org.bddaid.rules.IRule;

import java.util.List;

public class FeatureRunResult implements RunResult {

    private boolean success;
    private IRule rule;
    private Feature feature;
    private List<ScenarioRunResult> scenarioRunResultList;

    public FeatureRunResult(boolean success, IRule rule, Feature feature) {
        this.success = success;
        this.rule = rule;
        this.feature = feature;
    }

    public FeatureRunResult(boolean success, IRule rule, Feature feature, List<ScenarioRunResult> scenarioRunResultList) {
        this.success = success;
        this.rule = rule;
        this.feature = feature;
        this.scenarioRunResultList = scenarioRunResultList;
    }


    public boolean isSuccess() {
        return success;
    }

    public IRule getRule() {
        return rule;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public List<ScenarioRunResult> getScenarioRunResultList() {
        return scenarioRunResultList;
    }

    public void setScenarioRunResultList(List<ScenarioRunResult> scenarioRunResultList) {
        this.scenarioRunResultList = scenarioRunResultList;
    }
}
