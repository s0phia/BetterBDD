package org.bddaid.rules;

import org.bddaid.model.Feature;
import org.bddaid.model.enums.Rule;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.result.RunResult;

public abstract class IRuleSingle extends  IRule{

    public IRuleSingle(Rule rule, String description, String errorMessage, RuleCategory category) {
        super(rule, description, errorMessage, category);
    }

    public abstract RunResult applyRule(Feature feature);
    
}

