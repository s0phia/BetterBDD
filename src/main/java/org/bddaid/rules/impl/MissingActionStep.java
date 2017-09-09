package org.bddaid.rules.impl;

import org.bddaid.model.Feature;
import org.bddaid.model.enums.Rule;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.result.RunResult;
import org.bddaid.rules.IRuleSingle;

import static org.bddaid.model.enums.Rule.missing_action_step;
import static org.bddaid.model.enums.RuleCategory.NON_DECLARATIVE;

public class MissingActionStep extends IRuleSingle {

    private static final Rule RULE = missing_action_step;
    private static final String DESCRIPTION = missing_action_step.description();
    private static final String ERROR_MESSAGE = "Scenarios with no 'When' action step found";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;

    public MissingActionStep() {
        super(RULE, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
    }

    @Override
    public RunResult applyRule(Feature feature) {
        System.out.println("rule: '" + getRule() + "' is not implemented!");
        //TODO: log warning
        return null;
    }

}

