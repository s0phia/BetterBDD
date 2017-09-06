package org.bddaid.rules.impl;

import org.bddaid.model.Feature;
import org.bddaid.model.enums.RuleCategory;

import org.bddaid.model.enums.RunLevel;
import org.bddaid.model.result.*;
import org.bddaid.rules.IRuleSingle;

import static org.bddaid.model.enums.RuleCategory.DUPLICATION;
import static org.bddaid.model.enums.RuleCategory.NON_DECLARATIVE;
import static org.bddaid.model.enums.RuleCategory.REDUNDANCY;

public class MissingActionStep extends IRuleSingle {


    private static final String NAME = "missing_action_step";
    private static final String DESCRIPTION = "Prevents scenarios without a 'When' action step";
    private static final  String ERROR_MESSAGE = "Scenarios with no action step found";
    private static final RuleCategory CATEGORY = NON_DECLARATIVE;

    public MissingActionStep(boolean enabled) {
        super(NAME, DESCRIPTION, ERROR_MESSAGE, CATEGORY, enabled);
    }
    @Override
    public RunResult applyRule(Feature feature) {
        System.out.println("Rule: '" + getName() + "' is not implemented!");
        //TODO: log warning
        return null;
    }


}

