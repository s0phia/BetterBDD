package org.bddaid.rules;

import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.enums.RunLevel;

public abstract class IRule {

    protected String name;
    protected String description;
    private String errorMessage;
    private RuleCategory category;

    private boolean enabled;
    private RunLevel runLevel;

    public IRule(String name, String description, String errorMessage, RuleCategory category, boolean enabled) {
        this.name = name;
        this.category = category;
        this.enabled = enabled;
        this.description = description;
        this.errorMessage = errorMessage;
    }

    public IRule( boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public RunLevel getRunLevel() {
        return runLevel;
    }

    public void setRunLevel(RunLevel runLevel) {
        this.runLevel = runLevel;
    }

}

