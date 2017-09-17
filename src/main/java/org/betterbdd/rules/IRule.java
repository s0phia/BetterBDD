package org.betterbdd.rules;

import org.betterbdd.model.enums.Rule;
import org.betterbdd.model.enums.RuleCategory;
import org.betterbdd.model.enums.RunLevel;

public abstract class IRule {

    protected Rule rule;
    protected String description;
    private String errorMessage;
    private RuleCategory category;;
    private RunLevel runLevel;

    public IRule(Rule Rule, String description, String errorMessage, RuleCategory category) {
        this.rule = Rule;
        this.category = category;
        this.description = description;
        this.errorMessage = errorMessage;
    }

    public Rule getRule() {
        return rule;
    }

    public String getName() {
        return rule.name();
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public RuleCategory getCategory() {
        return category;
    }

    public void setCategory(RuleCategory category) {
        this.category = category;
    }

    public RunLevel getRunLevel() {
        return runLevel;
    }

    public void setRunLevel(RunLevel runLevel) {
        this.runLevel = runLevel;
    }


}

