package org.bddaid.runner;

import org.bddaid.model.Feature;
import org.bddaid.model.enums.RunLevel;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.model.result.impl.FeaturesRunResult;
import org.bddaid.model.result.RunResult;
import org.bddaid.rules.IRule;
import org.bddaid.rules.IRuleBatch;
import org.bddaid.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public List<RunResult> runRules(List<Feature> features, List<IRule> rules) {

        List<RunResult> runResults= new ArrayList<>();

        FeaturesRunResult featuresRunResult;
        FeatureRunResult featureRunResult;

        for (IRule rule : rules) {

            if (rule.getRunLevel().equals(RunLevel.FEATURES)) {

                IRuleBatch ruleBatch = ((IRuleBatch) rule);
                featuresRunResult = (FeaturesRunResult) ruleBatch.applyRule(features);
                runResults.add(featuresRunResult);

            } else if (rule.getRunLevel().equals(RunLevel.FEATURE)){

                for (Feature feature : features) {
                    IRuleSingle ruleSingle = ((IRuleSingle) rule);
                    featureRunResult = (FeatureRunResult)ruleSingle.applyRule(feature);
                    runResults.add(featureRunResult);
                }
            }
        }
        return runResults;
    }




}
