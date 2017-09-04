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

public class EmptyFeature implements IRuleSingle {

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

    @Override
    public String getName() {
        return "empty_feature_file";
    }

    @Override
    public String getDescription() {
        return "This rule prevents empty feature files";
    }

    @Override
    public String getErrorMessage() {
        return ("Feature file is empty");
    }

    @Override
    public RuleCategory getCategory() {
        return REDUNDANCY;
    }

    @Override
    public RunLevel getRunLevel() {
        return RunLevel.FEATURE;
    }


}



