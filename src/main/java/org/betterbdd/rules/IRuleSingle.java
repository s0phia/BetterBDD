package org.betterbdd.rules;

import org.betterbdd.model.Feature;
import org.betterbdd.model.enums.Rule;
import org.betterbdd.model.enums.RuleCategory;
import org.betterbdd.model.result.RunResult;

public abstract class IRuleSingle extends  IRule{

    public IRuleSingle(Rule rule, String description, String errorMessage, RuleCategory category) {
        super(rule, description, errorMessage, category);
    }

    public abstract RunResult applyRule(Feature feature);
    
}

