package org.bddaid.model.result;

import org.bddaid.model.Feature;

import java.util.ArrayList;
import java.util.List;

public class FeaturesRunResult implements BDDRunResult {

    private List<Feature> features;
    private List<Failure> failures = new ArrayList<>();

    public FeaturesRunResult(List<Feature> features, List<Failure> failures) {
        this.features = features;
        this.failures = failures;
    }

    public List<Feature>  getFeatures() {
        return features;
    }

    public void setFeature(Feature feature) {
        this.features = features;
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
