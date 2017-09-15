package org.bddaid.rules;

import org.bddaid.model.Feature;
import org.bddaid.model.enums.Rule;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.enums.RunLevel;
import org.bddaid.model.result.RunResult;

import java.util.List;

public abstract class IRuleBatch extends IRule {

    public IRuleBatch(Rule rule, String description, String errorMessage, RuleCategory category) {
        super(rule, description, errorMessage, category);
    }

    abstract public RunResult applyRule(List<Feature> featureFiles);

    public RunLevel getRunLevel() {
        return RunLevel.FEATURE_GROUP;
    }

}


