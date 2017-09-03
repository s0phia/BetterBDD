package org.bddaid.model.result;

import org.bddaid.model.Feature;

import java.util.ArrayList;
import java.util.List;

public class FeatureRunResult implements BDDRunResult {

    private Feature feature;
    private List<Failure> failures = new ArrayList<>();

    public FeatureRunResult(Feature feature, List<Failure> failures) {
        this.feature = feature;
        this.failures = failures;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public boolean isSuccess() {
        return failures.size() > 0 ? false : true;
    }


    public List<Failure> getFailures() {
        return failures;
    }

    public void addFailures(List<Feature> errors) {
        this.failures.addAll(failures);
    }



}
