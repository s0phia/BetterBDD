package org.betterbdd.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import org.betterbdd.model.Feature;
import org.betterbdd.model.enums.Rule;
import org.betterbdd.model.enums.RuleCategory;
import org.betterbdd.model.result.RunResult;
import org.betterbdd.model.result.impl.FeatureRunResult;
import org.betterbdd.model.result.impl.ScenarioRunResult;
import org.betterbdd.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.List;

import static org.betterbdd.model.enums.Rule.missing_scenario_steps;
import static org.betterbdd.model.enums.RuleCategory.NON_DECLARATIVE;

public class MissingScenarioSteps extends IRuleSingle {

    private static final Rule RULE = missing_scenario_steps;
    private static final String DESCRIPTION = missing_scenario_steps.description();
    private static final String ERROR_MESSAGE = "Scenarios with missing steps found";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;

    public MissingScenarioSteps() {
        super(RULE, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
    }

    @Override
    public RunResult applyRule(Feature feature) {

     List<ScenarioRunResult> scenarioRunResultList = new ArrayList<>();

        GherkinDocument gherkinDocument = feature.getGherkinDocument();
        if (gherkinDocument.getFeature() != null) {
            for (ScenarioDefinition scenario : gherkinDocument.getFeature().getChildren()) {
                boolean isScenarioPassed;

                if (scenario.getSteps().size() < 1) {
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

}

