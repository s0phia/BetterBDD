package org.bddaid;

import com.beust.jcommander.JCommander;
import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ParserException;
import gherkin.ast.GherkinDocument;
import org.bddaid.cli.AppArgs;
import org.bddaid.model.Feature;
import org.bddaid.model.result.*;
import org.bddaid.rules.IRule;
import org.bddaid.rules.impl.DuplicateScenarioName;
import org.bddaid.rules.impl.EmptyFeature;
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

        RunResult runResult = new TestRunner().runRules(parsedFeatures, getRules());

        if (!runResult.isSuccess()) {

            for (BDDRunResult bddRunResult : runResult.getFeatureRunResults()) {
                if (bddRunResult instanceof FeatureRunResult) {

                    FeatureRunResult featureRunResult = ((FeatureRunResult) bddRunResult);

                    System.out.println("Feature: " + featureRunResult.getFeature().getFileName() +
                            "Result: " + bddRunResult.isSuccess());

                    if (!bddRunResult.isSuccess()) {
                        System.out.println("\tFailures: ");

                        for (Failure failure : featureRunResult.getFailures()) {
                            System.out.println("\t Rule violation: " + failure.getRule().getName() + "\n\t\t " + failure.getMessage());
                        }

                    }
                } else if (bddRunResult instanceof FeaturesRunResult) {

                    FeaturesRunResult featuresRunResult = ((FeaturesRunResult) bddRunResult);
                    if (!featuresRunResult.isSuccess()) {

                        for (Feature feature : featuresRunResult.getFeatures()) {
                            if (!featuresRunResult.isSuccess()) {
                                System.out.println("\t" + feature.getFileName());
                            }
                        }
                        System.out.println("\t\t Rule violation: " + featuresRunResult.getFailures().get(0).getRule().getName());

                    }
                    System.out.println("\t Features: ");
                    for (Failure failure : featuresRunResult.getFailures()) {

                    }

                }
            }
            System.exit(1);
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



