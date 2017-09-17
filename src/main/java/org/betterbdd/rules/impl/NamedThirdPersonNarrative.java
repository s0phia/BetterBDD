package org.betterbdd.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import gherkin.ast.Step;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.betterbdd.model.Feature;
import org.betterbdd.model.enums.Keyword;
import org.betterbdd.model.enums.Rule;
import org.betterbdd.model.enums.RuleCategory;
import org.betterbdd.model.result.RunResult;
import org.betterbdd.model.result.impl.FeatureRunResult;
import org.betterbdd.model.result.impl.ScenarioRunResult;
import org.betterbdd.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.betterbdd.model.enums.Rule.named_third_person_narrative;
import static org.betterbdd.model.enums.RuleCategory.NON_DECLARATIVE;

public class NamedThirdPersonNarrative extends IRuleSingle {

    private static final Rule RULE_NAME = named_third_person_narrative;
    private static final String DESCRIPTION = named_third_person_narrative.description();
    private static final String ERROR_MESSAGE = "Scenarios do not use named third-person narrative";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;
    private List<String> namedSubjects;
    private List<String> pronouns;

    public NamedThirdPersonNarrative() {
        super(RULE_NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
        namedSubjects = Arrays.asList("Mary","Simon");
        pronouns = Arrays.asList("he", "she", "his", "her");
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

                    if (!isThirdPersonNarrative(scenario) ){
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

    public boolean isThirdPersonNarrative(ScenarioDefinition scenario) {
        List<Step> steps = scenario.getSteps();

        for (Step step : steps) {
            String[] stepWords = step.getText().split(" ");
            String firstWord = stepWords[0];
            String secondWord = stepWords[1];

            Keyword keyword = Keyword.valueOf(step.getKeyword().toUpperCase().trim());
            switch (keyword) {

                case GIVEN:
                    if (!namedSubjects.contains(firstWord)
                            && !(firstWord.equals("that") && namedSubjects.contains(secondWord)))
                        return false;
                    break;
                case WHEN:
                    if (!(pronouns.contains(firstWord) && secondWord.endsWith("s")))
                        return false;
                case THEN:
                    if (!pronouns.contains(firstWord))
                        return false;
                    break;

            }

        }

        return true;
    }

    public List<String> getNamedSubjects() {
        return namedSubjects;
    }

    public void setNamedSubjects(List<String> namedSubjects) {
        this.namedSubjects = namedSubjects;
    }


}

