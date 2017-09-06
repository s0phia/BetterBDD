package org.bddaid.rules.impl;

import org.bddaid.model.Feature;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.result.RunResult;
import org.bddaid.rules.IRuleSingle;

import static org.bddaid.model.enums.RuleCategory.NON_DECLARATIVE;

public class MissingVerificationStep extends IRuleSingle {

    private static final String NAME = "missing_verification_step";
    private static final String DESCRIPTION = "Prevents scenarios without a 'Then' action step";
    private static final String ERROR_MESSAGE = "Scenarios with no verification step found";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;


    public MissingVerificationStep(boolean enabled) {
        super(NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY, enabled);
    }

    @Override
    public RunResult applyRule(Feature feature) {
        System.out.print("Rule: '" + getName() + "' is not implemented!");
        return null;
    }


}

