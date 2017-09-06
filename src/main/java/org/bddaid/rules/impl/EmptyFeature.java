package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;
import org.bddaid.model.Feature;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.enums.RunLevel;
import org.bddaid.model.result.impl.FeatureRunResult;
import org.bddaid.rules.IRuleSingle;

import java.util.List;

import static org.bddaid.model.enums.RuleCategory.REDUNDANCY;

public class EmptyFeature extends IRuleSingle {

    private static final String NAME = "empty-feature-file";
    private static final String DESCRIPTION = "This rule prevents empty feature files";
    private static final String ERROR_MESSAGE = "Feature file is empty";
    private static final RuleCategory CATEGORY = REDUNDANCY;


    public EmptyFeature(boolean enabled) {
        super(NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY, enabled);
    }


    @Override
    public FeatureRunResult applyRule(Feature feature) {

        boolean success = true;
        GherkinDocument gherkinDocument = feature.getGherkinDocument();
        List<Pickle> pickles = new Compiler().compile(gherkinDocument);

        if (pickles.size() < 1) {
            success = false;
        }

        return new FeatureRunResult(success, this, feature);
    }

}



