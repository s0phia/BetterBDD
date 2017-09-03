package org.bddaid.runner;

import org.bddaid.model.result.BDDRunResult;
import org.bddaid.model.Feature;
import org.bddaid.model.result.RunResult;
import org.bddaid.rules.IRule;
import org.bddaid.rules.IRuleBatch;
import org.bddaid.rules.IRuleSingle;

import java.util.List;

public class TestRunner {

    public RunResult runRules(List<Feature> features, List<IRule> rules) {

        RunResult runResult = new RunResult();
        BDDRunResult ruleRunResult;
        for (IRule rule : rules) {

            if (rule instanceof IRuleBatch) {
                ruleRunResult = ((IRuleBatch) rule).applyRule(features);
                runResult.addBDDRunResult(ruleRunResult);
            } else {
                for (Feature feature : features) {
                    ruleRunResult = ((IRuleSingle) rule).applyRule(feature);
                    runResult.addBDDRunResult(ruleRunResult);
                }
            }
        }
        return runResult;
    }
}
