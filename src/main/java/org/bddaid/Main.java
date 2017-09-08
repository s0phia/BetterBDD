package org.bddaid;

import com.beust.jcommander.JCommander;
import org.bddaid.cli.AppArgs;
import org.bddaid.model.Feature;
import org.bddaid.model.result.RunResult;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.model.result.impl.FeaturesRunResult;
import org.bddaid.model.result.impl.ScenarioRunResult;
import org.bddaid.readers.FeatureFileReader;
import org.bddaid.readers.RuleConfigReader;
import org.bddaid.rules.IRule;
import org.bddaid.runner.TestRunner;
import org.bddaid.utils.ParserWrapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final AppArgs appArgs = new AppArgs();

    public static void main(String... args) {

        Main main = new Main();
        main.parseArguments(args);
        main.run();
    }

    private void parseArguments(String... args) {

        JCommander jCommander = new JCommander(appArgs);
        jCommander.parse(args);

        if (appArgs.isHelp()) {
            showUsage(jCommander);
        }
    }

    private void showUsage(JCommander jCommander) {
        jCommander.usage();
        System.exit(0);
    }

    private void run() {

        List<File> featureFiles = FeatureFileReader.readFiles(appArgs.getPath());
        List<Feature> parsedFeatures = ParserWrapper.parseFeatureFiles(featureFiles);
        List<IRule> bddRules = new ArrayList<>();


        try {
            bddRules = RuleConfigReader.readRules(appArgs.getRulesPath());
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

       // BDDRunResult runResultList = new TestRunner().runRules(parsedFeatures, bddRules);
        List<RunResult>  runResultList = new TestRunner().runRules(parsedFeatures, bddRules);
        int passedRules = 0;
        int failedRules = 0;

//        System.out.printf("\nFeature analysed: %s \nFeatures passed: %s \nResult: %s", runResultList.getFeatureRunResultList(),
//                runResultList.getPassedFeatureResults(),  runResultList.getFailedFeatureResults());
//        new Reporter().runReport(bddRules, featureFiles, runResultList);


        for (RunResult runResult : runResultList) {

            if (runResult instanceof FeatureRunResult) {

                FeatureRunResult featureRunResult = (FeatureRunResult) runResult;
                System.out.printf("\nrule: %s \nFeature: %s \nResult: %s", featureRunResult.getRule().getRule(),
                        featureRunResult.getFeature().getPath(), featureRunResult.isSuccess() ? "PASS\n" : "FAIL");
                 passedRules++;
                if (!featureRunResult.isSuccess()) {
                    System.out.println(" - " + featureRunResult.getRule().getErrorMessage());
                    passedRules++;
                    if (featureRunResult.getScenarioRunResultList() != null && featureRunResult.getScenarioRunResultList().size() > 0) {

                        for (ScenarioRunResult scenarioRunResult : featureRunResult.getScenarioRunResultList()) {

                            if (!scenarioRunResult.isSuccess())
                                System.out.println("\t" + scenarioRunResult.getScenario());

                        }
                        failedRules++;
                    }

                }

              //  new Reporter().runReport(bddRules, featureFiles, featureRunResult, passedRules, failedRules);

            } else if (runResult instanceof FeaturesRunResult) {

                FeaturesRunResult featuresRunResult = (FeaturesRunResult) runResult;
                System.out.printf("\nrule: %s \nResult: %s", featuresRunResult.getRule().getRule(),
                        featuresRunResult.isSuccess() ? "PASS\n" : "FAIL");

                if (!featuresRunResult.isSuccess()) {
                    System.out.println(" - " + featuresRunResult.getRule().getErrorMessage());


                    if (featuresRunResult.getFeatureRunResults() != null && featuresRunResult.getFeatureRunResults().size() > 0) {

                        for (FeatureRunResult featureRunResult : featuresRunResult.getFeatureRunResults()) {

                            if (featureRunResult != null && !featureRunResult.isSuccess()) {
                                System.out.printf("\tFeature: %s (%s)\n", featureRunResult.getFeature().getGherkinDocument().getFeature().getName(), featureRunResult.getFeature().getPath());

                                if (featureRunResult.getScenarioRunResultList() != null) {
                                    for (ScenarioRunResult scenarioRunResult : featureRunResult.getScenarioRunResultList()) {

                                        if (!scenarioRunResult.isSuccess())
                                            System.out.printf("\t\t%s\n", scenarioRunResult.getScenario());
                                    }
                                }
                            }
                        }
                    }


                }

            }
        }


    }
}



