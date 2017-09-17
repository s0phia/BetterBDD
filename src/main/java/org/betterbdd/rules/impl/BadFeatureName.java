package org.betterbdd.rules.impl;

import gherkin.ast.GherkinDocument;
import org.betterbdd.model.Feature;
import org.betterbdd.model.enums.Rule;
import org.betterbdd.model.enums.RuleCategory;
import org.betterbdd.model.result.RunResult;
import org.betterbdd.model.result.impl.FeatureRunResult;
import org.betterbdd.rules.IRuleSingle;

import static org.betterbdd.model.enums.Rule.bad_feature_name;
import static org.betterbdd.model.enums.RuleCategory.COHERENCE;

public class BadFeatureName extends IRuleSingle {

    private static final Rule RULE_NAME = bad_feature_name;
    private static final String DESCRIPTION = bad_feature_name.description();
    private static final String ERROR_MESSAGE = "Bad feature names found";
    private static final RuleCategory CATEGORY = COHERENCE;
    private int minWords = 2;

    public BadFeatureName() {
        super(RULE_NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
        setMinWords(minWords);
    }

    @Override
    public RunResult applyRule(Feature feature) {

        boolean isPassed = false;
        GherkinDocument gherkinDocument = feature.getGherkinDocument();

        if (gherkinDocument.getFeature() != null) {

            if (gherkinDocument.getFeature().getName().split(" ").length > minWords) {
                isPassed = true;
            } else {
                isPassed = false;
            }

            return new FeatureRunResult(isPassed, this, feature);
        } else {
            return null;
        }
    }

    public int getMinWords() {
        return minWords;
    }

    public void setMinWords(int minWords) {
        this.minWords = minWords;
    }

}

