package org.betterbdd;

import com.beust.jcommander.JCommander;
import org.betterbdd.cli.AppArgs;
import org.betterbdd.model.Feature;
import org.betterbdd.model.result.BDDRunResult;
import org.betterbdd.readers.FeatureParser;
import org.betterbdd.readers.RuleConfigReader;
import org.betterbdd.reports.Reporter;
import org.betterbdd.rules.IRule;
import org.betterbdd.runner.BDDRuleRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.betterbdd.model.enums.ReportFormat.CONSOLE;
import static org.betterbdd.model.enums.ReportFormat.HTML;
import static org.betterbdd.model.enums.ReportFormat.JSON;

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

        String path = appArgs.getOutPutPath();
        if (path.endsWith(".html")) {
            reporter.printReport(runResult, HTML, path);
        } else if (path.endsWith(".json")) {
            reporter.printReport(runResult, JSON, path);
        }
        reporter.printReport(runResult, CONSOLE, appArgs.getOutPutPath());
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



