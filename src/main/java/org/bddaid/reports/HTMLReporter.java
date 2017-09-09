package org.bddaid.reports;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import org.bddaid.model.enums.Rule;
import org.bddaid.model.result.BDDRunResult;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HTMLReporter {

    public static void runReport(BDDRunResult runResult) {

        Configuration cfg = getConfiguration();

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", "BetterBDD Report");
        input.put("rules", runResult.getRules());
        input.put("rulesPassed", runResult.getPassedRules().size());
        input.put("rulesFailed", runResult.getFailedRules().size());

        input.put("featuresTested", runResult.getFeatures().size());
        input.put("featuresPassed", runResult.getPassedFeatures().size());
        input.put("featuresFailed", runResult.getFailedFeatures().size());
        input.put("featuresAnalysed", runResult.getFeatures());


        input.put("noRules", runResult.getRules().size());
        input.put("allRules", Rule.getRuleNames());

        Template template;
        try {
            template = cfg.getTemplate("report.ftl");

            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(input, consoleWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {

            Writer fileWriter = new FileWriter(new File("output.html"));

            try {
                template.process(input, fileWriter);
            } finally {
                fileWriter.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Configuration getConfiguration() {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(HTMLReporter.class, "templates");

        // Recommended settings
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }
}
