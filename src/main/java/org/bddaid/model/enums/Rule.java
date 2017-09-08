package org.bddaid.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Rule {
    empty_feature_file("Prevents empty feature files"),
    bad_feature_name("Prevents a feature definition with a missing or meaningless name"),
    bad_scenario_name("Prevents a feature definition with a missing or meaningless name"),
    duplicate_feature_name("Prevents more than one feature in a collection of feature files having the same name"),
    duplicate_scenario_name("Prevents more than one scenario definition from having the same name"),
    too_many_scenario_steps("Prevents a scenario definition from having an excessive number of steps"),
    technical_language("Prevents the use of technical language in a scenario definition"),
    missing_action_step("Prevents a scenario definition missing a 'When' action step"),
    missing_scenario_steps("Prevents a scenario definition without any steps"),
    missing_verification_step("Prevents a scenario definition missing a 'Then' verification step");

    private final String description;

    Rule(String description) {
        this.description = description;
    }

    public String description() {
        return this.description;
    }

    public static String printRules() {
    String rules = "";
        for (Rule rule : Rule.values())
            rules = rules + String.format("- %s: %s%n", rule, rule.description);
        return rules;
    }

    public static List<String> getRuleNames() {
        List<String> rules = new ArrayList<>();
        for (Rule rule : Rule.values())
            rules.add(rule.name());
        return rules;
    }
}
