package org.betterbdd.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import gherkin.ast.Step;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.betterbdd.model.Feature;
import org.betterbdd.model.enums.Rule;
import org.betterbdd.model.enums.RuleCategory;
import org.betterbdd.model.result.RunResult;
import org.betterbdd.model.result.impl.FeatureRunResult;
import org.betterbdd.model.result.impl.ScenarioRunResult;
import org.betterbdd.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.betterbdd.model.enums.Rule.technical_language;
import static org.betterbdd.model.enums.RuleCategory.NON_DECLARATIVE;

public class TechnicalLanguage extends IRuleSingle {

    private static final Rule RULE_NAME = technical_language;
    private static final String DESCRIPTION = technical_language.description();
    private static final String ERROR_MESSAGE = "Scenarios do not use named third-person narrative";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;
    private List<String> technicalTerms;
    private List<String> technicalSubtrings;

    public TechnicalLanguage() {
        super(RULE_NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
        technicalTerms = Arrays.asList("clicks", "swipes", "types");
        technicalSubtrings = Arrays.asList("http","//", "POST", "GET", "PUT", "DELETE", "UPDATE");

     }

    @Override
    public RunResult applyRule(Feature feature) {

        List<ScenarioRunResult> scenarioRunResultList = new ArrayList<>();
        GherkinDocument gherkinDocument = feature.getGherkinDocument();

        if (gherkinDocument.getFeature() != null && !gherkinDocument.getFeature().getChildren().isEmpty()) {

            List<ScenarioDefinition> scenarios = gherkinDocument.getFeature().getChildren();

            for (ScenarioDefinition scenario : scenarios) {

                if (!scenario.getSteps().isEmpty()) {
                    boolean isScenarioPassed;

                    if (containsTechnicalLanguage(scenario) ){
                        isScenarioPassed = false;
                    } else {
                        isScenarioPassed = true;
                    }

                    scenarioRunResultList.add(new ScenarioRunResult(isScenarioPassed, this, scenario.getName()));
                }else {
                    LogManager.getLogger().log(Level.WARN,
                            String.format("Rule %s not executed on scenario [%s] as no steps found!",
                                    this.getName(), scenario.getName() ));

                }
            }

            boolean isFeaturePassed = true;

            for (ScenarioRunResult scenarioResult : scenarioRunResultList) {
                if (!scenarioResult.isSuccess())
                    isFeaturePassed = false;

            }

            return new FeatureRunResult(isFeaturePassed, this, feature, scenarioRunResultList);

        }
        return null;

    }

    public boolean containsTechnicalLanguage(ScenarioDefinition scenario) {

        List<Step> steps = scenario.getSteps();

        for (Step step : steps) {

            for (String substr: technicalSubtrings) {
                if (step.getText().toLowerCase().contains(substr.toLowerCase()))
                    return true;
            }
            List<String> words = Arrays.asList(step.getText().split(" "));

            for (String word : words) {
                if (technicalTerms.contains(word.toLowerCase()))
                    return true;
            }
        }

        return false;
    }


}

