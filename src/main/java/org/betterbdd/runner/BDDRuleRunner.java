package org.betterbdd.runner;

import org.betterbdd.model.Feature;
import org.betterbdd.model.enums.RunLevel;
import org.betterbdd.model.result.BDDRunResult;
import org.betterbdd.model.result.impl.FeatureRunResult;
import org.betterbdd.model.result.impl.FeaturesRunResult;
import org.betterbdd.rules.IRule;
import org.betterbdd.rules.IRuleBatch;
import org.betterbdd.rules.IRuleSingle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BDDRuleRunner {

    public BDDRunResult runRules(List<Feature> features, Set<IRule> rules) {

        BDDRunResult bddRunResult = new BDDRunResult(features, rules);
        FeaturesRunResult featuresRunResult;
        FeatureRunResult featureRunResult;
        Set<IRule> failedRules = new HashSet<>();

        for (IRule rule : rules) {

            if (rule.getRule().runLevel().equals(RunLevel.FEATURE_GROUP)) {

                IRuleBatch ruleBatch = ((IRuleBatch) rule);
                featuresRunResult = (FeaturesRunResult) ruleBatch.applyRule(features);
                bddRunResult.addGlobalFeaturesRunResult(featuresRunResult);


            } else if (rule.getRule().runLevel().equals(RunLevel.FEATURE)||
                    rule.getRule().runLevel().equals(RunLevel.SCENARIO)) {

                for (Feature feature : features) {
                    IRuleSingle ruleSingle = ((IRuleSingle) rule);
                    featureRunResult = (FeatureRunResult) ruleSingle.applyRule(feature);
                    bddRunResult.addFeatureRunResult(featureRunResult);

                }

            }


        }

        for (FeaturesRunResult a:bddRunResult.getFailedFeatureGlobalResults()){
            if(!a.isSuccess())
                failedRules.add(a.getRule());

        }

        for (FeatureRunResult a:bddRunResult.getFailedFeatureResults()){
            if(!a.getSuccess())
                failedRules.add(a.getRule());

        }

        bddRunResult.setFailedRules(failedRules);
        return bddRunResult;
    }

}



