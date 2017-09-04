package org.bddaid.rules;

import org.bddaid.model.enums.RuleCategory;
import org.bddaid.model.enums.RunLevel;

public interface IRule {

    String getName();

    String getDescription();

    String getErrorMessage();

    RuleCategory getCategory();

    RunLevel getRunLevel();
}

