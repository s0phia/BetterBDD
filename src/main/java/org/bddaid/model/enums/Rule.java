package org.bddaid.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Rule {
    empty_feature_file("Prevents empty feature files", RunLevel.FEATURE),
    bad_feature_name("Prevents a feature definition with a missing or meaningless name", RunLevel.FEATURE),
    bad_scenario_name("Prevents a feature definition with a missing or meaningless name", RunLevel.SCENARIO),
    duplicate_feature_name("Prevents more than one feature in a collection of feature files having the same name", RunLevel.FEATURE_GROUP),
    duplicate_scenario_name("Prevents more than one scenario definition from having the same name", RunLevel.SCENARIO_GROUP),
    too_many_scenario_steps("Prevents a scenario definition from having an excessive number of steps", RunLevel.SCENARIO),
    technical_language("Prevents the use of technical language in a scenario definition", RunLevel.SCENARIO),
    missing_action_step("Prevents a scenario definition missing a 'When' action step", RunLevel.SCENARIO),
    missing_scenario_steps("Prevents a scenario definition without any steps", RunLevel.SCENARIO),
    missing_verification_step("Prevents a scenario definition missing a 'Then' verification step", RunLevel.SCENARIO),
    third_person_subject_predicate("Verifies that scenarios are written using a named third-person narrative", RunLevel.SCENARIO);

    private final String description;
    private final RunLevel runLevel;


    Rule(String description, RunLevel runLevel) {
        this.description = description;
        this.runLevel = runLevel;

    }

    public String description() {
        return this.description;
    }
    public RunLevel runLevel() {
        return this.runLevel;
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
