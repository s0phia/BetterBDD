package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import org.bddaid.model.Feature;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.enums.RunLevel;
import org.bddaid.model.result.RunResult;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.model.result.impl.ScenarioRunResult;
import org.bddaid.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.List;

import static org.bddaid.model.enums.RuleCategory.NON_DECLARATIVE;

public class MissingScenarioSteps extends IRuleSingle {

    private static final String NAME = "missing_scenario_steps";
    private static final String DESCRIPTION = "Prevents scenarios without steps";
    private static final String ERROR_MESSAGE = "Scenario with missing steps found";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;

    public MissingScenarioSteps(boolean enabled) {
        super(NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY, enabled);
    }

    @Override
    public RunResult applyRule(Feature feature) {

        boolean success = true;

        List<ScenarioRunResult> scenarioRunResultList = new ArrayList<>();

        GherkinDocument gherkinDocument = feature.getGherkinDocument();
        if (gherkinDocument.getFeature() != null) {
            for (ScenarioDefinition scenario : gherkinDocument.getFeature().getChildren()) {

                if (scenario.getSteps().size() < 1) {

                    scenarioRunResultList.add(new ScenarioRunResult(false, this, scenario.getName()));
                    success = false;

                } else {
                    scenarioRunResultList.add(new ScenarioRunResult(true, this, scenario.getName()));

                }
            }
        }

        return new FeatureRunResult(success, this, feature, scenarioRunResultList);


    }

    @Override
    public String getName() {
        return "missing_scenario_steps";
    }

    @Override
    public String getDescription() {
        return "This rule prevents scenarios with an excessive number of steps";
    }

    @Override
    public String getErrorMessage() {
        return "Scenario with missing steps found";
    }

    @Override
    public RuleCategory getCategory() {
        return NON_DECLARATIVE;
    }

    @Override
    public RunLevel getRunLevel() {
        return RunLevel.FEATURE;
    }
}

