package org.bddaid;

import com.beust.jcommander.JCommander;
import org.bddaid.cli.AppArgs;
import org.bddaid.model.Feature;
import org.bddaid.model.enums.ReportFormat;
import org.bddaid.model.result.BDDRunResult;
import org.bddaid.readers.RuleConfigReader;
import org.bddaid.reports.Reporter;
import org.bddaid.rules.IRule;
import org.bddaid.runner.BDDRuleRunner;
import org.bddaid.readers.FeatureParser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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
        Reporter reporter = new Reporter(ReportFormat.HTML);
        Reporter reporter = new Reporter(ReportFormat.CONSOLE);
        reporter.printReport(runResult);
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



