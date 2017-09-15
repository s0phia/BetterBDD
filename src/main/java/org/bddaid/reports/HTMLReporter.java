package org.bddaid.reports;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.bddaid.model.Feature;
import org.bddaid.model.enums.Rule;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.result.BDDRunResult;
import org.bddaid.rules.IRule;

import java.io.*;
import java.util.*;

import static org.bddaid.model.enums.RuleCategory.*;

public class HTMLReporter implements ReportFormatter {

    public void saveReport(BDDRunResult runResult, File file) throws IOException {

        Configuration cfg = getConfiguration();

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("title", "BetterBDD Report");
        input.put("rulesTotal", runResult.getRules().size());
        input.put("rulesPassed", runResult.getPassedRules().size());
        input.put("rulesFailed", runResult.getFailedRules().size());

        input.put("featuresTotal", runResult.getFeatures().size());
        input.put("featuresPassedCount", runResult.getPassedFeatures().size());
        input.put("featuresFailedCount", runResult.getFailedFeatures().size());

        List<Feature> featuresScanned = new ArrayList<>();
        featuresScanned.addAll(runResult.getPassedFeatures());
        featuresScanned.addAll(runResult.getPassedFeatures());
        input.put("featuresScanned", runResult.getPassedFeatures());
        input.put("featuresPassed", runResult.getPassedFeatures());
        input.put("featuresFailed", runResult.getFailedFeatures());


        input.put("rules", runResult.getRules());
        input.put("allRules", Rule.getRuleNames());
        input.put("ruleCategoryMap", getRuleCategoryMap(runResult));
        input.put("ruleCategoryPassedMap", getRuleCategoryPassMap(runResult));
        input.put("ruleCategoryfailedMap", getRuleCategoryFailedMap(runResult));

        Template template;
        try {

            template = cfg.getTemplate("report.ftl");
            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(input, consoleWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            LogManager.getLogger().log(Level.DEBUG,
                    String.format("Saving result to file:%s", file.getPath()));

            Writer fileWriter = new FileWriter(file);

            try {
                template.process(input, fileWriter);
            } finally {
                fileWriter.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Integer> getRuleCategoryMap(BDDRunResult runResult) {

        Map<String, Integer> ruleCategoryMap = new HashMap();
        Map<Integer, Integer> resultMap = new HashMap();
        resultMap.put(0, 0);
        ruleCategoryMap.put(COHERENCE.name(), 0);
        ruleCategoryMap.put(REDUNDANCY.name(), 0);
        ruleCategoryMap.put(DUPLICATION.name(), 0);
        ruleCategoryMap.put(NON_DECLARATIVE.name(), 0);


        for (IRule rule : runResult.getRules()) {
            switch (rule.getCategory()) {

                case COHERENCE:
                    ruleCategoryMap.put(COHERENCE.name(), ruleCategoryMap.get(COHERENCE.name()) + 1);
                    break;
                case REDUNDANCY:
                    ruleCategoryMap.put(REDUNDANCY.name(), ruleCategoryMap.get(REDUNDANCY.name()) + 1);
                    break;
                case DUPLICATION:
                    ruleCategoryMap.put(DUPLICATION.name(), ruleCategoryMap.get(DUPLICATION.name()) + 1);
                    break;
                case NON_DECLARATIVE:
                    ruleCategoryMap.put(NON_DECLARATIVE.name(), ruleCategoryMap.get(NON_DECLARATIVE.name()) + 1);
                    break;

            }
        }
        return ruleCategoryMap;
    }

    private Map<String, Integer> getRuleCategoryPassMap(BDDRunResult runResult) {

        Map<String, Integer> ruleCategoryMap = new HashMap();
        for (RuleCategory ruleCategory : RuleCategory.values()) {
            ruleCategoryMap.put(ruleCategory.name(), 0);
        }

        List<String> passed = new ArrayList<>();
        for (IRule rule : runResult.getPassedRules()) {
            passed.add(rule.getCategory().name());
        }

        for (RuleCategory ruleCategory : RuleCategory.values()) {
            for (IRule rule : runResult.getPassedRules()) {
                if (rule.getCategory().equals(ruleCategory)) {
                    ruleCategoryMap.put(ruleCategory.name(), ruleCategoryMap.get(ruleCategory.name()) + 1);
                }
            }
        }

        return ruleCategoryMap;
    }

    private Map<String, Integer> getRuleCategoryFailedMap(BDDRunResult runResult) {

        Map<String, Integer> ruleCategoryMap = new HashMap();
        for (RuleCategory ruleCategory : RuleCategory.values()) {
            ruleCategoryMap.put(ruleCategory.name(), 0);
        }

        List<String> passed = new ArrayList<>();
        for (IRule rule : runResult.getFailedRules()) {
            passed.add(rule.getCategory().name());
        }

        for (RuleCategory ruleCategory : RuleCategory.values()) {
            for (IRule rule : runResult.getFailedRules()) {
                if (rule.getCategory().equals(ruleCategory)) {
                    ruleCategoryMap.put(ruleCategory.name(), ruleCategoryMap.get(ruleCategory.name()) + 1);
                }
            }
        }

        return ruleCategoryMap;
    }


    private static Configuration getConfiguration() {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(HTMLReporter.class, "templates");

        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.UK);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }
}
