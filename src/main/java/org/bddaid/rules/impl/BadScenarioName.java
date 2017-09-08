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

import static org.bddaid.model.enums.Rule.bad_scenario_name;
import static org.bddaid.model.enums.RuleCategory.COHERENCE;

public class BadScenarioName extends IRuleSingle {

    private static final Rule RULE_NAME = bad_scenario_name;
    private static final String DESCRIPTION = bad_scenario_name.description();
    private static final String ERROR_MESSAGE = "Bad scenario names found";
    private static final RuleCategory CATEGORY = COHERENCE;

    private int minWords;

    public BadScenarioName() {
        super(RULE_NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
        setMinWords(3);
    }

    @Override
    public RunResult applyRule(Feature feature) {

        boolean success = true;

        List<ScenarioRunResult> scenarioRunResultList = new ArrayList<>();
        GherkinDocument gherkinDocument = feature.getGherkinDocument();

        if (gherkinDocument.getFeature() != null) {

            for (ScenarioDefinition scenario : gherkinDocument.getFeature().getChildren()) {
                if (scenario.getName().split(" ").length < minWords) {
                    scenarioRunResultList.add(new ScenarioRunResult(false, this, scenario.getName()));
                    success = false;
                } else {
                    scenarioRunResultList.add(new ScenarioRunResult(true, this, scenario.getName()));

                }
            }
        }

        return new FeatureRunResult(success, this, feature, scenarioRunResultList);

    }

    public int getMinWords() {
        return minWords;
    }

    public void setMinWords(int minWords) {
        this.minWords = minWords;
    }
}

