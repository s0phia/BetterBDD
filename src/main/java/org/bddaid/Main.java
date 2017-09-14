package org.bddaid;

import com.beust.jcommander.JCommander;
import org.bddaid.cli.AppArgs;
import org.bddaid.model.Feature;
import org.bddaid.model.result.BDDRunResult;
import org.bddaid.readers.FeatureParser;
import org.bddaid.readers.RuleConfigReader;
import org.bddaid.reports.Reporter;
import org.bddaid.rules.IRule;
import org.bddaid.runner.BDDRuleRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.bddaid.model.enums.ReportFormat.*;

public class Main {

    private static final AppArgs appArgs = new AppArgs();

    public static void main(String... args) throws IOException {

        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws IOException {

        parseArguments(args);

        FeatureParser featureParser = new FeatureParser();
        List<Feature> parsedFeatures = featureParser.parseFiles(new File(appArgs.getPath()));
        
        RuleConfigReader ruleConfigReader = new RuleConfigReader();
        Set<IRule> bddRules = ruleConfigReader.readRules(new File(appArgs.getRulesPath()));

        BDDRunResult runResult = new BDDRuleRunner().runRules(parsedFeatures, bddRules);
        Reporter reporter = new Reporter();
        reporter.printReport(runResult, HTML);
        reporter.printReport(runResult, CONSOLE);
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
}



