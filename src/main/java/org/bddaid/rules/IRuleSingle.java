package org.bddaid.rules;

import org.bddaid.model.Feature;
import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.enums.RunLevel;
import org.bddaid.model.result.RunResult;

public abstract class IRuleSingle extends  IRule{

    public IRuleSingle(String name, String description, String errorMessage, RuleCategory category, boolean enabled) {
        super(name, description, errorMessage, category, enabled);
    }

    public abstract RunResult applyRule(Feature feature);

    public RunLevel getRunLevel() {
        return RunLevel.FEATURE;
    }

}

