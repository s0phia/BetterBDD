package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import org.bddaid.model.Feature;
import org.bddaid.model.enums.Rule;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.result.RunResult;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.rules.IRuleSingle;

import static org.bddaid.model.enums.Rule.bad_feature_name;
import static org.bddaid.model.enums.RuleCategory.COHERENCE;

public class BadFeatureName extends IRuleSingle {

    private static final Rule RULE_NAME = bad_feature_name;
    private static final String DESCRIPTION = bad_feature_name.description();
    private static final String ERROR_MESSAGE = "Bad feature names found";
    private static final RuleCategory CATEGORY = COHERENCE;
    private int minWords = 13;


    public BadFeatureName() {
        super(RULE_NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
        setMinWords(3);
    }

    @Override
    public RunResult applyRule(Feature feature) {
        boolean isSuccess = false;
        GherkinDocument gherkinDocument = feature.getGherkinDocument();
        if (gherkinDocument.getFeature() != null) {
            if (gherkinDocument.getFeature().getName().split(" ").length < minWords) {
                isSuccess = false;
            } else {
                isSuccess = true;
            }
        }
        return new FeatureRunResult(isSuccess, this, feature);
    }

    public int getMinWords() {
        return minWords;
    }

    public void setMinWords(int minWords) {
        this.minWords = minWords;
    }
}

