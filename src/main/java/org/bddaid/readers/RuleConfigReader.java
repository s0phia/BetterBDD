package org.bddaid.readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.bddaid.model.enums.Rule;
import org.bddaid.rules.IRule;
import org.bddaid.rules.impl.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleConfigReader {

    private static String ENABLED_KEY = "enabled";
    private static String RULE_KEY = "rule";
    private static String MIN_WORDS_KEY = "min_words";
    private static String MAX_STEPS_KEY = "max_steps";

    public static List<IRule> readRules(String filePath) throws RuntimeException, IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List configList = mapper.readValue(new File(filePath), ArrayList.class);
        //LogManager.getLogger().
         ///       log(Level.DEBUG, String.format("rule configuration read from file:%s %n%s", filePath, configList));

        List<IRule> rules = new ArrayList<>();
        for (Object ruleConfigList : ((ArrayList) configList)) {

            Map<String, Object> ruleConfigMap = (HashMap<String, Object>) ruleConfigList;

            String ruleName = ruleConfigMap.get(RULE_KEY).toString();

            Boolean isRuleEnabled = ruleConfigMap.get(ENABLED_KEY) != null ?
                    Boolean.valueOf(ruleConfigMap.get(ENABLED_KEY).toString()) : false;


            Rule rule = null;
            try {
                rule = rule.valueOf(ruleName);

            } catch (IllegalArgumentException e) {
                String errorMessage = String.format("\nrule '%s' does not exist - available rules are: \n%s",
                        ruleName, (Rule.printRules()));
                throw new IllegalArgumentException(errorMessage);
            }

            if (isRuleEnabled) {

                switch (rule) {

                    case empty_feature_file:
                        rules.add(new EmptyFeature());
                        break;

                    case duplicate_scenario_name:
                        rules.add(new DuplicateScenarioName());
                        break;

                    case duplicate_feature_name:

                        rules.add(new DuplicateFeatureName());
                        break;

                    case bad_feature_name:
                        BadFeatureName badFeatureNameRule = new BadFeatureName();
                        if (ruleConfigMap.containsKey(MIN_WORDS_KEY))
                            badFeatureNameRule.setMinWords(Integer.valueOf(ruleConfigMap
                                    .get(MIN_WORDS_KEY).toString()));
                        rules.add(badFeatureNameRule);
                        break;
                    case bad_scenario_name:
                        BadScenarioName badScenarioNameRule = new BadScenarioName();
                        if (ruleConfigMap.containsKey(MIN_WORDS_KEY))
                            badScenarioNameRule.setMinWords(Integer.valueOf(ruleConfigMap
                                    .get(MIN_WORDS_KEY).toString()));
                        rules.add(badScenarioNameRule);
                        break;
                    case too_many_scenario_steps:
                        TooManyScenarioSteps bddRule = new TooManyScenarioSteps();
                        if (ruleConfigMap.containsKey(MAX_STEPS_KEY))
                            bddRule.setMaxSteps(Integer.valueOf(ruleConfigMap.get(MAX_STEPS_KEY).toString()));
                        rules.add(new MissingScenarioSteps());
                        break;

                    case missing_action_step:
                        //TODO:   rules.add(new TooManyScenarioSteps(isEnabled));
                        break;

                    case missing_verification_step:
                        //TODO:  rules.add(new BadScenarioName(isEnabled));
                        break;

                    case missing_scenario_steps:
                        //TODO: rules.add(new BadScenarioName(isEnabled));
                        break;

                }
            }
        }
        return rules;
    }

}





