package org.bddaid.model.result.impl;

import org.bddaid.model.result.RunResult;
import org.bddaid.rules.IRule;

import java.util.ArrayList;
import java.util.List;

public class FeaturesRunResult implements RunResult {

    private boolean success;
    private IRule rule;
    private List<FeatureRunResult> featureRunResults;

    public FeaturesRunResult(boolean success, IRule rule, List<FeatureRunResult> featureRunResults) {
        this.success = success;
        this.rule = rule;
        this.featureRunResults = featureRunResults;
    }

    public boolean isSuccess() {
        return success;
    }

    public IRule getRule() {
        return rule;
    }

    public List<FeatureRunResult> getFeatureRunResults() {
        return featureRunResults;
    }

    public void setFeatureRunResults(List<FeatureRunResult> featureRunResults) {
        this.featureRunResults = featureRunResults;
    }


    public List<FeatureRunResult> getFailedFeatureRunResults() {

        List<FeatureRunResult> failedFeatureRunResults = new ArrayList<>();

            for (FeatureRunResult fr : featureRunResults) {
                if (!fr.getSuccess())
                    failedFeatureRunResults.add(fr);
            }

        return failedFeatureRunResults;
    }
}
