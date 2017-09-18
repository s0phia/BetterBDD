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

import static org.betterbdd.model.enums.Keyword.*;
import static org.betterbdd.model.enums.Rule.bad_step_sequence;
import static org.betterbdd.model.enums.RuleCategory.COHERENCE;

public class BadStepSequence extends IRuleSingle {

    private static final Rule RULE_NAME = bad_step_sequence;
    private static final String DESCRIPTION = bad_step_sequence.description();
    private static final String ERROR_MESSAGE = "Scenarios have incorrect sequence of steps";
    private static final RuleCategory CATEGORY = COHERENCE;


    public BadStepSequence() {
        super(RULE_NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
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


                    if (!isValidStepSequence(scenario)) {
                        isScenarioPassed = false;
                    } else {
                        isScenarioPassed = true;
                    }

                    scenarioRunResultList.add(new ScenarioRunResult(isScenarioPassed, this, scenario.getName()));
                } else {
                    LogManager.getLogger().log(Level.WARN,
                            String.format("Rule %s not executed on scenario [%s] as no steps found!",
                                    this.getName(), scenario.getName()));

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

    public boolean isValidStepSequence(ScenarioDefinition scenario) {
        List<Step> steps = scenario.getSteps();

        Keyword firstKeyword = Keyword.valueOf(steps.get(0).getKeyword().trim().toUpperCase());
        if (!firstKeyword.equals(GIVEN))
            return false;

        Keyword currentKeyword;
        Keyword nextKeyword;
        for (int i = 1; i < steps.size(); i++) {

            currentKeyword = Keyword.valueOf(steps.get(i).getKeyword().toUpperCase().trim());

            if (!(steps.size() - 1 == i)) {
                nextKeyword = Keyword.valueOf(steps.get(i + 1).getKeyword().toUpperCase().trim());

                switch (currentKeyword) {

                    case GIVEN:
                        return false;

                    case WHEN:
                        if (!Arrays.asList(AND, BUT, THEN).contains(nextKeyword))
                            return false;
                        break;

                    case THEN:
                        if (!nextKeyword.equals(AND))
                            return false;
                        break;

                    case AND:
                        if (!Arrays.asList(WHEN, AND, BUT, THEN).contains(nextKeyword))
                            return false;
                        break;

                    case BUT:
                        if (!Arrays.asList(WHEN, AND, THEN).contains(nextKeyword))
                            return false;
                        break;
                }
            }
        }

        return true;
    }

}

