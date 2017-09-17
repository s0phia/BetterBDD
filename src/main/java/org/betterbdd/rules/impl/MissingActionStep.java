package org.betterbdd.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import gherkin.ast.Step;
import org.betterbdd.model.Feature;
import org.betterbdd.model.enums.Rule;
import org.betterbdd.model.enums.RuleCategory;
import org.betterbdd.model.result.RunResult;
import org.betterbdd.model.result.impl.FeatureRunResult;
import org.betterbdd.model.result.impl.ScenarioRunResult;
import org.betterbdd.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.List;

import static org.betterbdd.model.enums.Rule.missing_action_step;
import static org.betterbdd.model.enums.RuleCategory.NON_DECLARATIVE;

public class MissingActionStep extends IRuleSingle {

    private static final Rule RULE = missing_action_step;
    private static final String DESCRIPTION = missing_action_step.description();
    private static final String ERROR_MESSAGE = "Scenarios with no 'When' action step found";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;

    public MissingActionStep() {
        super(RULE, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
    }

    @Override
    public RunResult applyRule(Feature feature) {

        int actionStepCount = 0;

        List<ScenarioRunResult> scenarioRunResultList = new ArrayList<>();
        GherkinDocument gherkinDocument = feature.getGherkinDocument();

        if (gherkinDocument.getFeature() != null
                && !gherkinDocument.getFeature().getChildren().isEmpty()) {

            for (ScenarioDefinition scenario : gherkinDocument.getFeature().getChildren()) {

                if (!scenario.getSteps().isEmpty()) {
                    for (Step step : scenario.getSteps()) {
                        if (step.getKeyword().trim().equals("When")) {
                            actionStepCount++;
                        }
                    }
                }
            }

            return new FeatureRunResult(actionStepCount > 0, this, feature, scenarioRunResultList);

        }
        return null;

    }

}

