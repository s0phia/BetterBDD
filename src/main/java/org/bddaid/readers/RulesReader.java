package org.bddaid.readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bddaid.rules.IRule;
import org.bddaid.rules.impl.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RulesReader {

    public static List<IRule> readRules(String filePath) {

        List configList = null;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            configList = mapper.readValue(new File(filePath), ArrayList.class);

            //TODO:logger
            System.out.println(ReflectionToStringBuilder.toString(configList, ToStringStyle.MULTI_LINE_STYLE));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<IRule> rules = new ArrayList<>();

        for (Object ruleConfig : ((ArrayList) configList)) {

            //TODO:logger
            System.out.println("***: " + ruleConfig);

            Map<String, Object> ruleConfigList = (Map) ruleConfig;
            Boolean enabled = Boolean.valueOf(ruleConfigList.get("enabled").toString());
            String ruleName = String.valueOf(ruleConfigList.get("name"));

            switch (ruleName) {
                case "empty-feature-file":
                    rules.add(new EmptyFeature(enabled));
                    break;
                case "duplicate_scenario_name":
                    rules.add(new DuplicateScenarioName(enabled));
                    break;
                case "duplicate_feature_name":
                    rules.add(new DuplicateFeatureName(enabled));
                    break;
                case "bad_scenario_name":
                    rules.add(new BadScenarioName(enabled));
                    break;
                case "too_many_scenario_steps":
                    rules.add(new MissingScenarioSteps(enabled));
                    break;
                case "missin_action_step":
                    rules.add(new TooManyScenarioSteps(enabled));
                    break;
                case "missing_verification_step":
                    rules.add(new BadScenarioName(enabled));
                    break;
                case "missing_scenario_steps":
                    rules.add(new BadScenarioName(enabled));
                    break;

            }
        }
        return rules;
    }

}





