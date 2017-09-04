package org.bddaid.rules.impl;

import org.bddaid.model.Feature;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.enums.RunLevel;
import org.bddaid.model.result.RunResult;
import org.bddaid.rules.IRuleSingle;

import static org.bddaid.model.enums.RuleCategory.NON_DECLARATIVE;

public class MissingVerificationStep implements IRuleSingle {

    public MissingVerificationStep() {
        System.out.print("Rule: '" + getName() + "' is not implemented!");
    }

    @Override
    public RunResult applyRule(Feature feature) {
        return null;
    }

    @Override
    public String getName() {
        return "missing_verification_step";
    }

    @Override
    public String getDescription() {
        return "Prevents scenarios without a 'Then' action step";
    }

    @Override
    public String getErrorMessage() {
        return "Scenarios with no verification step found";
    }

    @Override
    public RuleCategory getCategory() {
        return NON_DECLARATIVE;
    }

    @Override
    public RunLevel getRunLevel() {
        return RunLevel.FEATURE;
    }
}

