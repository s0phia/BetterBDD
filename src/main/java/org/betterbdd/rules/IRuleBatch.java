package org.betterbdd.rules;

import org.betterbdd.model.Feature;
import org.betterbdd.model.enums.Rule;
import org.betterbdd.model.enums.RuleCategory;
import org.betterbdd.model.enums.RunLevel;
import org.betterbdd.model.result.RunResult;

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


