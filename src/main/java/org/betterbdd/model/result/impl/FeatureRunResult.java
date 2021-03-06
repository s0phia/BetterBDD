package org.betterbdd.model.result.impl;

import org.betterbdd.model.Feature;
import org.betterbdd.model.result.RunResult;
import org.betterbdd.rules.IRule;

import java.util.ArrayList;
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


//    public boolean isSuccess() {
//        return success;
//    }

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


    public List<ScenarioRunResult> getFailedScenarios() {

        List<ScenarioRunResult> failedScenarios = new ArrayList<>();

        if (scenarioRunResultList != null) {
            for (ScenarioRunResult fs : scenarioRunResultList) {
                if (!fs.isSuccess())
                    failedScenarios.add(fs);
            }
        }
        return failedScenarios;
    }

    public boolean getSuccess() {
        return success;
    }

}
