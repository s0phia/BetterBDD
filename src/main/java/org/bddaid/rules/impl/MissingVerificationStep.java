package org.bddaid.rules.impl;

import org.bddaid.model.Feature;
import org.bddaid.model.enums.Rule;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.result.RunResult;
import org.bddaid.rules.IRuleSingle;

import static org.bddaid.model.enums.Rule.missing_verification_step;
import static org.bddaid.model.enums.RuleCategory.NON_DECLARATIVE;

public class MissingVerificationStep extends IRuleSingle {

    private static final Rule RULE_NAME = missing_verification_step;
    private static final String DESCRIPTION = missing_verification_step.description();
    private static final String ERROR_MESSAGE = "Scenarios with no 'Then' verification step found";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;


    public MissingVerificationStep() {
        super(RULE_NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
    }

    @Override
    public RunResult applyRule(Feature feature) {
        System.out.print("rule: '" + getRule() + "' is not implemented!");
        return null;
    }


}

