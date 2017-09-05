package org.bddaid.readers;

import org.bddaid.rules.IRule;
import org.bddaid.rules.impl.*;

import java.util.ArrayList;
import java.util.List;

public class RulesReader {

    public static List<IRule> readRules() {

        List<IRule> rules = new ArrayList<>();
        rules.add(new EmptyFeature());
        rules.add(new DuplicateScenarioName());
        rules.add(new DuplicateFeatureName());
        rules.add(new MissingVerificationStep());
        rules.add(new TooManyScenarioSteps());
        rules.add(new MissingScenarioSteps());
        return rules;
    }


}



