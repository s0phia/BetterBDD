package org.bddaid.readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.bddaid.model.enums.Rule;
import org.bddaid.rules.IRule;
import org.bddaid.rules.impl.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RuleConfigReader {

    private static String ENABLED_KEY = "enabled";
    private static String RULE_KEY = "rule";
    private static String MIN_WORDS_KEY = "min_words";
    private static String MAX_STEPS_KEY = "max_steps";

    //here i used a set as opposed to list as there shuould not be any duplicat entryies is each rule should be uniguw
    public Set<IRule> readRules(File file) throws RuntimeException, IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List configList = mapper.readValue(file, ArrayList.class);

        LogManager.getLogger().log(Level.DEBUG,
                String.format("rule configuration read from file:%s %n%s", file.getPath(), configList));

        Set<IRule> rules = new HashSet<>();
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
                        rules.add(bddRule);
                        break;

                    case missing_action_step:
                        rules.add(new MissingActionStep());
                        break;

                    case missing_verification_step:
                        rules.add(new MissingVerificationStep());
                        break;

                    case missing_scenario_steps:
                        rules.add(new MissingScenarioSteps());
                        break;

                     case named_third_person_narrative:
                         rules.add(new NamedThirdPersonNarrative());
                         break;

                }
            }
        }

        return rules;
    }

}





