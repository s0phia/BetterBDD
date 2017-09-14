package org.bddaid.reports;

import org.bddaid.model.enums.RunLevel;
import org.bddaid.model.result.BDDRunResult;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.model.result.impl.FeaturesRunResult;
import org.bddaid.model.result.impl.ScenarioRunResult;
import org.bddaid.rules.IRule;
import org.bddaid.rules.IRuleBatch;
import org.bddaid.rules.IRuleSingle;

public class ConsoleReporter {

    public static void runReport(BDDRunResult runResult) {

        System.out.printf("***************************************************************************\n");
        System.out.printf("BDD Scan Results\n");
        System.out.printf("***************************************************************************\n");
        System.out.printf("\nSummary\n---------------------------------------------------------------------------\n");

        System.out.printf("%s Rules executed (%d passed, %s failed) \n", runResult.getRules().size(),
                runResult.getRules().size() - runResult.getFailedRules().size(),
                runResult.getFailedRules().size());

        System.out.printf("%s Feature file scanned (%d passed, %s failed) \n", runResult.getFeatures().size(),
                runResult.getPassedFeatures().size(),
                runResult.getFailedFeatures().size());
        System.out.printf("---------------------------------------------------------------------------\n");

        if (runResult.getFailedRules().isEmpty()) {
            System.out.printf("\nNo Rules Failed\n");

        } else {
            System.out.printf("\nFailed rules:\n");

            for (IRule rule : runResult.getFailedRules()) {
                System.out.println("\n\t" + rule.getRule().name() + " - " + rule.getErrorMessage());


                if (rule instanceof IRuleSingle) {
                    for (FeatureRunResult r : runResult.getFailedFeatureResults()) {
                        if (r.getRule().getRule().equals(rule.getRule())) {
                            System.out.println("\t\t" + r.getFeature().getPath());

                            if (r.getRule().getRule().runLevel().equals(RunLevel.SCENARIO)) {
                                for (ScenarioRunResult sr : r.getFailedScenarios()) {
                                    System.out.printf("\t\t\t[%s]\n", sr.getScenario());
                                }
                            }

                        }
                    }


                } else if (rule instanceof IRuleBatch) {
                    for (FeaturesRunResult fr : runResult.getFailedFeatureGlobalResults()) {
                        for (FeatureRunResult ffr : fr.getFailedFeatureRunResults()) {
                            System.out.printf("\t\t %s \n", ffr.getFeature().getPath());
                        }
                    }

                }
            }
        }


        if (runResult.getPassedRules().isEmpty()) {
            System.out.printf("\nNo Rules Passed\n");

        } else {
            System.out.printf("\nPassed rules:\n");
            for (IRule rule : runResult.getPassedRules()) {
                System.out.println("\t" + rule.getRule().name());
            }
        }

    }
}
