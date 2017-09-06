package org.bddaid.rules;

import org.bddaid.model.Feature;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.enums.RunLevel;
import org.bddaid.model.result.RunResult;

import java.util.List;

public abstract class IRuleBatch extends IRule {

    public IRuleBatch(String name, String description, String errorMessage, RuleCategory category, boolean enabled) {
        super(name, description, errorMessage, category, enabled);
    }

    public IRuleBatch(boolean enabled) {
        super(enabled);
    }

    abstract public RunResult applyRule(List<Feature> featureFiles);

    public RunLevel getRunLevel() {
        return RunLevel.FEATURES;
    }

}


