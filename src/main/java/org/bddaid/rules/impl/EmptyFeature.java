package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;
import org.bddaid.model.*;
import org.bddaid.model.result.Failure;
import org.bddaid.model.result.FeatureRunResult;
import org.bddaid.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.List;

import static org.bddaid.model.RuleCategory.REDUNDANCY;

public class EmptyFeature implements IRuleSingle {

    @Override
    public FeatureRunResult applyRule(Feature feature) {

        List<Failure> failures = new ArrayList<>();

        GherkinDocument gherkinDocument = feature.getGherkinDocument();
        List<Pickle> pickles = new Compiler().compile(gherkinDocument);

        if (pickles.size() < 1) {
            failures.add(new Failure(this, getErrorMessage()));
        }

        return new FeatureRunResult(feature, failures);
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


}



