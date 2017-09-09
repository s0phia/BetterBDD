package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import gherkin.ast.Step;
import org.bddaid.model.Feature;
import org.bddaid.model.enums.Rule;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.result.RunResult;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.model.result.impl.ScenarioRunResult;
import org.bddaid.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.List;

import static org.bddaid.model.enums.Rule.missing_verification_step;
import static org.bddaid.model.enums.RuleCategory.NON_DECLARATIVE;

public class MissingVerificationStep extends IRuleSingle {

    private static final Rule RULE_NAME = missing_verification_step;
    private static final String DESCRIPTION = missing_verification_step.description();
    private static final String ERROR_MESSAGE = "Scenarios with no 'Then' verification step found";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;


    public MissingVerificationStep() {
        super(RULE_NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
    }

    @Override
    public RunResult applyRule(Feature feature) {

        int verificationStepCount = 0;

        List<ScenarioRunResult> scenarioRunResultList = new ArrayList<>();
        GherkinDocument gherkinDocument = feature.getGherkinDocument();

        if (gherkinDocument.getFeature() != null
                && !gherkinDocument.getFeature().getChildren().isEmpty()) {

            for (ScenarioDefinition scenario : gherkinDocument.getFeature().getChildren()) {

                if (!scenario.getSteps().isEmpty()) {
                    for (Step step : scenario.getSteps()) {
                        if (step.getKeyword().trim().equals("Then")) {
                            verificationStepCount++;
                        }
                    }
                }
            }

            return new FeatureRunResult(verificationStepCount > 0, this, feature, scenarioRunResultList);

        }
        return null;

    }



}

