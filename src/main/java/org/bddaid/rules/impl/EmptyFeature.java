package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import org.bddaid.model.Feature;
import org.bddaid.model.enums.Rule;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.rules.IRuleSingle;

import static org.bddaid.model.enums.Rule.empty_feature_file;
import static org.bddaid.model.enums.RuleCategory.REDUNDANCY;

public class EmptyFeature extends IRuleSingle {

    private static final Rule NAME = empty_feature_file;
    private static final String DESCRIPTION = empty_feature_file.description();
    private static final String ERROR_MESSAGE = "Empty Feature files found";
    private static final RuleCategory CATEGORY = REDUNDANCY;

    public EmptyFeature() {
        super(NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
    }

    @Override
    public FeatureRunResult applyRule(Feature feature) {

        boolean success;
        GherkinDocument gherkinDocument = feature.getGherkinDocument();

        if (gherkinDocument.getFeature() == null)
            success = false;
        else if (gherkinDocument.getFeature().getChildren().size() < 1)
            success = false;
        else
            success = true;

        return new FeatureRunResult(success, this, feature);
    }

}



