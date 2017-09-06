package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;
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
import static org.bddaid.model.enums.RuleCategory.REDUNDANCY;

public class TooManyScenarioSteps extends IRuleSingle {

    private static final String NAME = "too_many_scenario_steps";
    private static final String DESCRIPTION = "Prevents scenarios with an excessive number of steps";
    private static final String ERROR_MESSAGE = "Scenario with too many steps found";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;

    public TooManyScenarioSteps(boolean enabled) {
        super(NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY, enabled);
    }

    @Override
    public RunResult applyRule(Feature feature) {

        boolean success = true;

        List<ScenarioRunResult> scenarioRunResultList = new ArrayList<>();

        GherkinDocument gherkinDocument = feature.getGherkinDocument();
        List<Pickle> pickles = new Compiler().compile(gherkinDocument);

        if (pickles.size() > 0) {

            for (Pickle pickle : pickles) {
                if (pickle.getSteps().size() > 10) {
                    scenarioRunResultList.add(new ScenarioRunResult(false, this, pickle.getName()));
                    success = false;
                } else {
                    scenarioRunResultList.add(new ScenarioRunResult(true, this, pickle.getName()));

                }
            }


        }

        return new FeatureRunResult(success, this, feature, scenarioRunResultList);


    }

    @Override
    public String getName() {
        return "too_many_scenario_steps";
    }

    @Override
    public String getDescription() {
        return "This rule prevents scenarios with an excessive number of steps";
    }

    @Override
    public String getErrorMessage() {
        return "Scenario with too many steps found";
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

