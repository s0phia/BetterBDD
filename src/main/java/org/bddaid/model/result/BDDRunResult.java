package org.bddaid.model.result;

import org.bddaid.model.Feature;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.model.result.impl.FeaturesRunResult;
import org.bddaid.rules.IRule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BDDRunResult {

    private boolean isSuccess;

    private List<Feature> features;
    private Set<IRule> rules;
    private Set<IRule> failedRules;
    private List<FeatureRunResult> featureRunResults;
    private List<FeaturesRunResult> featureGlobalRunResults;

    public BDDRunResult(List<Feature> features, Set<IRule> rules) {
        this.featureRunResults = new ArrayList<>();
        this.featureGlobalRunResults = new ArrayList<>();
        this.features = new ArrayList<>();
        this.features.addAll(features);
        this.rules = rules;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }


    public Set<IRule> getRules() {
        return rules;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFailedRules(Set<IRule> failedRules) {
        this.failedRules = failedRules;
    }

    public Set<IRule> getFailedRules() {
        return this.failedRules;
    }

    public Set<IRule> getPassedRules() {

        Set<IRule> passedRules = new HashSet<>();
        passedRules.addAll(rules);
        passedRules.removeAll(getFailedRules());
        return passedRules;
    }

    public void addFeatureRunResult(FeatureRunResult featureRunResult) {
        this.featureRunResults.add(featureRunResult);
    }

    public void addGlobalFeaturesRunResult(FeaturesRunResult featuresRunResult) {
        this.featureGlobalRunResults.add(featuresRunResult);
    }

    public List<FeatureRunResult> getPassedFeatureResults() {

        List<FeatureRunResult> passedFeatures = new ArrayList<>();

        for (FeatureRunResult f : featureRunResults) {
            if (f != null && f.isSuccess())
                passedFeatures.add(f);
        }
        return passedFeatures;
    }

    public Set<Feature> getPassedFeatures() {


        Set<Feature> passedFeatures = new HashSet<>();
        passedFeatures.addAll(features);
        passedFeatures.removeAll(getFailedFeatures());


        return  passedFeatures;

    }

    public Set<Feature> getFailedFeatures() {

        Set<Feature> failedFeatures = new HashSet<>();

        for (Feature feature : features) {
            for (FeatureRunResult f : featureRunResults) {
                if (f != null) {
                    if (feature.getPath().equals(f.getFeature().getPath()) && !f.isSuccess()) {
                        failedFeatures.add(feature);
                        break;
                    }
                }
            }
        }
        return failedFeatures;
    }

    public List<FeatureRunResult> getFailedFeatureResults() {

        List<FeatureRunResult> failedFeatures = new ArrayList<>();

        for (FeatureRunResult f : featureRunResults) {
            if (f != null && !f.isSuccess())
                failedFeatures.add(f);
        }
        return failedFeatures;
    }

    public List<FeaturesRunResult> getFailedFeatureGlobalResults() {

        List<FeaturesRunResult> failedGlobalFeatures = new ArrayList<>();

        for (FeaturesRunResult f : featureGlobalRunResults) {
            if (!f.isSuccess())
                failedGlobalFeatures.add(f);
        }
        return failedGlobalFeatures;
    }

    public List<FeatureRunResult> getFeatureRunResults() {
        return featureRunResults;
    }


    public List<FeaturesRunResult> getFeatureGlobalRunResults() {
        return featureGlobalRunResults;
    }

   }
