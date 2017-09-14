package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import org.bddaid.model.Feature;
import org.bddaid.model.enums.Rule;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.result.RunResult;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.model.result.impl.ScenarioRunResult;
import org.bddaid.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.List;

import static org.bddaid.model.enums.Rule.too_many_scenario_steps;
import static org.bddaid.model.enums.RuleCategory.NON_DECLARATIVE;

public class TooManyScenarioSteps extends IRuleSingle {

    private static final Rule RULE_NAME = too_many_scenario_steps;
    private static final String DESCRIPTION = too_many_scenario_steps.description();
    private static final String ERROR_MESSAGE = "Scenarios with too many steps found";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;
    private int maxSteps = 8;

    public TooManyScenarioSteps() {
        super(RULE_NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
    }

    @Override
    public RunResult applyRule(Feature feature) {

      List<ScenarioRunResult> scenarioRunResultList = new ArrayList<>();

        GherkinDocument gherkinDocument = feature.getGherkinDocument();

        if (gherkinDocument.getFeature() != null
                && !gherkinDocument.getFeature().getChildren().isEmpty()) {

            for (ScenarioDefinition scenario : gherkinDocument.getFeature().getChildren()) {

                boolean isScenarioPassed;

                if (scenario.getSteps().size() > maxSteps) {
                    isScenarioPassed = false;
                } else {
                    isScenarioPassed = true;
                }

                scenarioRunResultList.add(new ScenarioRunResult(isScenarioPassed, this, scenario.getName()));
            }

        }

        boolean isFeaturePassed = true;

        for (ScenarioRunResult scenarioResult : scenarioRunResultList) {
            if (!scenarioResult.isSuccess())
                isFeaturePassed = false;
            break;
        }

        return new FeatureRunResult(isFeaturePassed, this, feature, scenarioRunResultList);
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public void setMaxSteps(int maxSteps) {
        this.maxSteps = maxSteps;
    }
}

