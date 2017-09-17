package org.betterbdd.rules.impl;

import org.betterbdd.model.Feature;
import org.betterbdd.model.enums.Rule;
import org.betterbdd.model.enums.RuleCategory;
import org.betterbdd.model.result.RunResult;
import org.betterbdd.model.result.impl.FeatureRunResult;
import org.betterbdd.model.result.impl.FeaturesRunResult;
import org.betterbdd.rules.IRuleBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.betterbdd.model.enums.Rule.duplicate_feature_name;
import static org.betterbdd.model.enums.RuleCategory.DUPLICATION;

public class DuplicateFeatureName extends IRuleBatch {

    private static final Rule RULE = duplicate_feature_name;
    private static final String DESCRIPTION = duplicate_feature_name.description();
    private static final String ERROR_MESSAGE = "Duplicate feature names found";
    private static final RuleCategory CATEGORY = DUPLICATION;

    public DuplicateFeatureName() {
        super(RULE, DESCRIPTION, ERROR_MESSAGE, CATEGORY);
    }

    @Override
    public RunResult applyRule(List<Feature> features) {

        List<Feature> featuresWithDuplicates = new ArrayList<>();
        List<FeatureRunResult> featureRunResultList = new ArrayList<>();

        boolean success = false;

        Map<String, Integer> frequency = new HashMap<>();

        for (Feature feature : features) {

            if (feature.getGherkinDocument().getFeature() != null) {
                String featureName = feature.getGherkinDocument().getFeature().getName();
                if (frequency.containsKey(featureName)) {
                    frequency.put(featureName, frequency.get(featureName) + 1);
                } else {
                    frequency.put(featureName, 1);
                }

            }
        }
        for (Map.Entry<String, Integer> fr : frequency.entrySet()) {
            if (fr.getValue() > 1) {
                String name = fr.getKey();
                for (Feature feature : features) {
                    if (feature.getGherkinDocument().getFeature() != null &&
                            feature.getGherkinDocument().getFeature().getName().equals(name)) {
                        featureRunResultList.add(new FeatureRunResult(false, this, feature));
                        featuresWithDuplicates.add(feature);
                    } else {
                        featureRunResultList.add(new FeatureRunResult(true, this, feature));
                    }
                }

            }
        }

        if (featuresWithDuplicates.size() == 0)
            success = true;

        return new FeaturesRunResult(success, this, featureRunResultList);
    }

}


