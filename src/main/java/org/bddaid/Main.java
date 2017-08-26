package org.bddaid;

import com.beust.jcommander.JCommander;
import org.bddaid.cli.AppArgs;
import org.bddaid.rules.IRuleSingle;
import org.bddaid.rules.impl.EmptyFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final AppArgs appArgs = new AppArgs();

    private static List<IRuleSingle> rules;
    private static List<File> featureFiles;

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

        getRules();
        featureFiles = getFeatureFiles(appArgs.getPath());
        runRules();

    }

    private void runRules() {
        featureFiles.forEach((f -> rules.forEach((r) -> r.applyRule(f))));
    }

    private void getRules() {
        rules = new ArrayList<>();
        rules.add(new EmptyFeature());
    }

    private List<File> getFeatureFiles(String path) {
        List<File> features = new ArrayList<>();
        try {
            features = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".feature"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (features.size() <1)
            throw new RuntimeException(String.format("No feature files found in given path [%s]", path));

        return features;
    }


}



