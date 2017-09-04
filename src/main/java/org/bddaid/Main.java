package org.bddaid;

import com.beust.jcommander.JCommander;
import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ParserException;
import gherkin.ast.GherkinDocument;
import org.bddaid.cli.AppArgs;
import org.bddaid.model.Feature;
import org.bddaid.model.result.*;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.model.result.impl.FeaturesRunResult;
import org.bddaid.model.result.impl.ScenarioRunResult;
import org.bddaid.rules.IRule;

import org.bddaid.rules.impl.*;
import org.bddaid.runner.TestRunner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<File> featureFiles = getFeatureFiles(appArgs.getPath());
        List<Feature> parsedFeatures = parseFeatureFiles(featureFiles);

        List<RunResult> runResultList = new TestRunner().runRules(parsedFeatures, getRules());


        for (RunResult runResult : runResultList) {

            if (runResult instanceof FeatureRunResult) {

                FeatureRunResult featureRunResult = (FeatureRunResult) runResult;
                System.out.printf("\nRule: %s \nFeature: %s \nResult: %s", featureRunResult.getRule().getName(),
                        featureRunResult.getFeature().getPath(), featureRunResult.isSuccess() ? "PASS\n" : "FAIL");

                if (!featureRunResult.isSuccess()) {
                    System.out.println(" - " + featureRunResult.getRule().getErrorMessage());

                    if (featureRunResult.getScenarioRunResultList() != null && featureRunResult.getScenarioRunResultList().size() > 0) {

                        for (ScenarioRunResult scenarioRunResult : featureRunResult.getScenarioRunResultList()) {

                            if (!scenarioRunResult.isSuccess())
                                System.out.println("\t" + scenarioRunResult.getScenario());

                        }
                    }
                }
            } else if (runResult instanceof FeaturesRunResult) {

                FeaturesRunResult featuresRunResult = (FeaturesRunResult) runResult;
                System.out.printf("\nRule: %s \nResult: %s", featuresRunResult.getRule().getName(),
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

    private List<Feature> parseFeatureFiles(List<File> featureFiles) {

        List<Feature> parsedFeatures = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        Reader reader;
        GherkinDocument gherkinDocument;
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());

        for (File featureFile : featureFiles) {

            try {
                reader = new FileReader(featureFile);
                gherkinDocument = parser.parse(reader);
                parsedFeatures.add(new Feature(featureFile.getAbsolutePath(), gherkinDocument));

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);

            } catch (ParserException e) {

                String message = String.format("\n\nCould not parse feature file: %s \n %s",
                        featureFile, e.getMessage());
                errors.add(message);
            }

        }
        if (errors.size() > 0)
            throw new RuntimeException(errors.toString());

        return parsedFeatures;
    }


    private List<IRule> getRules() {

        List<IRule> rules = new ArrayList<>();
        rules.add(new EmptyFeature());
        rules.add(new DuplicateScenarioName());
        rules.add(new DuplicateFeatureName());
        rules.add(new MissingVerificationStep());
        rules.add(new TooManyScenarioSteps());
        rules.add(new MissingScenarioSteps());
        return rules;
    }

    private List<File> getFeatureFiles(String path) {
        List<File> features;
        try {
            features = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".feature"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (features.size() < 1)
            throw new RuntimeException(String.format("No feature files found in given path: %s", path));

        return features;
    }


}



