package org.bddaid.rules.impl;

import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;
import org.bddaid.model.*;
import org.bddaid.model.result.BDDRunResult;
import org.bddaid.model.result.Failure;
import org.bddaid.model.result.FeaturesRunResult;
import org.bddaid.rules.IRuleBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bddaid.model.RuleCategory.DUPLICATION;

public class DuplicateScenarioName implements IRuleBatch {

    private Map<String, Map<String, Integer>> featuresWithDuplicates = new HashMap<>();

    @Override
    public BDDRunResult applyRule(List<Feature> features) {

        List<Failure> failures = new ArrayList<>();

        GherkinDocument gherkinDocument = null;
        for (Feature feature : features) {

            gherkinDocument = feature.getGherkinDocument();
            List<Pickle> pickles = new Compiler().compile(gherkinDocument);

            Map<String, Integer> frequency = new HashMap<>();
            if (pickles.size() > 1) {
                for (Pickle pickle : pickles) {
                    if (frequency.containsKey(pickle.getName())) {
                        frequency.put(pickle.getName(), frequency.get(pickle.getName()) + 1);
                    } else {
                        frequency.put(pickle.getName(), 1);
                    }
                }

                for (Map.Entry<String, Integer> fr : frequency.entrySet()) {
                    if (fr.getValue() > 1)
                        this.featuresWithDuplicates.put(feature.getFileName(), frequency);


                }
            } else {
                //TODO: log warning
            }

        }
        if (featuresWithDuplicates.size() > 0) {
            failures.add(new Failure(this, getErrorMessage()));
        }
        return new FeaturesRunResult(features, failures);

    }

    @Override
    public String getName() {
        return "duplicate_scenario_name";
    }

    @Override
    public String getDescription() {
        return "This rule prevents feature files having duplicate scenario names";
    }

    @Override
    public String getErrorMessage() {

        String msg = "Duplicate scenario names detected in feature files:";

        for (Map.Entry<String, Map<String, Integer>> feature : featuresWithDuplicates.entrySet())
            msg = msg + String.format("\n Feature file: %s \n\tScenarios: %s", feature.getKey(), feature.getValue().entrySet());

        return msg;
    }

    @Override
    public RuleCategory getCategory() {
        return DUPLICATION;
    }
}

