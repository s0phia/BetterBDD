package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;
import org.bddaid.model.Feature;
import org.bddaid.model.RunResult;
import org.bddaid.rules.IRuleSingle;

import java.util.ArrayList;
import java.util.List;

public class EmptyFeature implements IRuleSingle {

    private Feature feature;

    @Override
    public RunResult applyRule(Feature feature) {

        this.feature = feature;
        boolean isSuccess = true;
        List<String> errors = new ArrayList<>();

        GherkinDocument gherkinDocument = feature.getGherkinDocument();
        List<Pickle> pickles = new Compiler().compile(gherkinDocument);

        if (pickles.size() < 1) {
            errors.add(getErrorMessage());
            isSuccess = false;
        }

        return new RunResult(isSuccess, errors);
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
        return String.format("\nFeature file %s is empty", feature.getFileName());
    }
}
