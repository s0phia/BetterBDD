package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;
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

        boolean success = true;

        List<ScenarioRunResult> scenarioRunResultList = new ArrayList<>();

        GherkinDocument gherkinDocument = feature.getGherkinDocument();
        List<Pickle> pickles = new Compiler().compile(gherkinDocument);

        if (pickles.size() > 0) {

            for (Pickle pickle : pickles) {
                if (pickle.getSteps().size() > maxSteps) {
                    scenarioRunResultList.add(new ScenarioRunResult(false, this, pickle.getName()));
                    success = false;
                } else {
                    scenarioRunResultList.add(new ScenarioRunResult(true, this, pickle.getName()));

                }
            }

        }

        return new FeatureRunResult(success, this, feature, scenarioRunResultList);
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public void setMaxSteps(int maxSteps) {
        this.maxSteps = maxSteps;
    }
}

