package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;
import org.bddaid.model.Feature;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.result.RunResult;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.model.result.impl.ScenarioRunResult;
import org.bddaid.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.List;

import static org.bddaid.model.enums.RuleCategory.COHERENCE;

public class BadScenarioName extends IRuleSingle {

    private static final String NAME = "bad_scenario_name";
    private static final String DESCRIPTION = "This rule prevents feature files having bad scenario names";
    private static final String ERROR_MESSAGE = "Bad scenario names found";
    private static final RuleCategory CATEGORY = COHERENCE;

    public BadScenarioName(boolean enabled) {
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
                if (pickle.getName().length() > 0 && pickle.getName().length() < 5) {
                    scenarioRunResultList.add(new ScenarioRunResult(false, this, pickle.getName()));
                    success = false;
                } else {
                    scenarioRunResultList.add(new ScenarioRunResult(true, this, pickle.getName()));

                }
            }
        }

        return new FeatureRunResult(success, this, feature, scenarioRunResultList);

    }

   }

