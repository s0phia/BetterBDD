package org.bddaid.rules;

import org.bddaid.model.RuleCategory;

public interface IRule {

    String getName();

    String getDescription();

    String getErrorMessage();

    RuleCategory getCategory();
}

