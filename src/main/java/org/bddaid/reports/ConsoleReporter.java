package org.bddaid.reports;

import gherkin.ast.Feature;
import gherkin.ast.ScenarioDefinition;
import org.bddaid.model.enums.RunLevel;
import org.bddaid.model.result.BDDRunResult;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.model.result.impl.FeaturesRunResult;
import org.bddaid.model.result.impl.ScenarioRunResult;
import org.bddaid.rules.IRule;
import org.bddaid.rules.IRuleBatch;
import org.bddaid.rules.IRuleSingle;

import java.util.List;

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
                System.out.printf("\n## %s ## - %s:\n", rule.getRule().name(), rule.getErrorMessage());


                if (rule instanceof IRuleSingle) {
                    for (FeatureRunResult r : runResult.getFailedFeatureResults()) {
                        if (r.getRule().getRule().equals(rule.getRule())) {
                            System.out.println("\t\t" + r.getFeature().getPath());
                            Feature feature = r.getFeature().getGherkinDocument().getFeature();
                            if (r.getRule().getRule().runLevel().equals(RunLevel.FEATURE) && feature != null) {
                                System.out.printf("\t\t\t[%s]\n", feature.getName());

                            } else if (r.getRule().getRule().runLevel().equals(RunLevel.SCENARIO)) {
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
                            if (ffr.getRule().getRule().runLevel().equals(RunLevel.FEATURE_GROUP)) {
                                System.out.printf("\t\t\t[%s]\n", ffr.getFeature().getGherkinDocument().getFeature().getName());

                            } else if (ffr.getRule().getRule().runLevel().equals(RunLevel.SCENARIO_GROUP)) {
                                List<ScenarioDefinition> scenarios = ffr.getFeature().getGherkinDocument().getFeature().getChildren();
                                for (ScenarioDefinition scenario : scenarios) {
                                    System.out.printf("\t\t\n[%s]\n", scenario.getName());
                                }

                            }
                        }
                    }

                }
            }
        }


        if (runResult.getPassedRules().isEmpty()) {
            System.out.printf("\nNo Rules Passed\n");

        } else {
            System.out.printf("\nPassed rules:\n\n");
            for (IRule rule : runResult.getPassedRules()) {
                System.out.printf("## %s ##\n", rule.getRule().name());
            }
        }

    }
}
