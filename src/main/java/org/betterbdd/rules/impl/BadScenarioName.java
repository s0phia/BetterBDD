package org.betterbdd.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import org.betterbdd.model.Feature;
import org.betterbdd.model.enums.Rule;
import org.betterbdd.model.enums.RuleCategory;
import org.betterbdd.model.enums.RunLevel;
import org.betterbdd.model.result.RunResult;
import org.betterbdd.model.result.impl.FeatureRunResult;
import org.betterbdd.model.result.impl.ScenarioRunResult;
import org.betterbdd.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.List;

import static org.betterbdd.model.enums.Rule.bad_scenario_name;
import static org.betterbdd.model.enums.RuleCategory.COHERENCE;

public class BadScenarioName extends IRuleSingle {

    private static final Rule RULE_NAME = bad_scenario_name;
    private static final String DESCRIPTION = bad_scenario_name.description();
    private static final RunLevel RUN_LEVEL = RunLevel.SCENARIO;
    private static final String ERROR_MESSAGE = "Bad scenario names found";
    private static final RuleCategory CATEGORY = COHERENCE;

    private int minWords;

    public BadScenarioName() {
        super(RULE_NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
        setMinWords(2);
    }

    @Override
    public RunResult applyRule(Feature feature) {

        List<ScenarioRunResult> scenarioRunResultList = new ArrayList<>();
        GherkinDocument gherkinDocument = feature.getGherkinDocument();

        if (gherkinDocument.getFeature() != null) {

            boolean isScenarioPassed;

            for (ScenarioDefinition scenario : gherkinDocument.getFeature().getChildren()) {
                if (scenario.getName().split(" ").length < minWords) {
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

    public int getMinWords() {
        return minWords;
    }

    public void setMinWords(int minWords) {
        this.minWords = minWords;
    }

    public RunLevel getRunLevel() {
        return this.RUN_LEVEL;
    }
}

