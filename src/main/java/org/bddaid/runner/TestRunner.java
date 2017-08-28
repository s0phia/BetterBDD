package org.bddaid.runner;

import org.bddaid.model.Feature;
import org.bddaid.model.RunResult;
import org.bddaid.rules.IRule;
import org.bddaid.rules.IRuleBatch;
import org.bddaid.rules.IRuleSingle;

import java.util.List;

public class TestRunner {

    public RunResult runRules(List<Feature> features, List<IRule> rules) {

        RunResult runResult = new RunResult();
        RunResult ruleRunResult = new RunResult();
        for (IRule rule : rules) {

            if (rule instanceof IRuleBatch) {
                ruleRunResult = ((IRuleBatch) rule).applyRule(features);
                if (!ruleRunResult.isSuccess()) {
                    runResult.addErrors(ruleRunResult.getErrors());
                }
            } else {

                for (Feature feature : features) {
                    ruleRunResult = ((IRuleSingle) rule).applyRule(feature);
                    if (!ruleRunResult.isSuccess()) {
                        runResult.addErrors(ruleRunResult.getErrors());

                    }
                }
            }
        }
        return ruleRunResult;
    }
}
