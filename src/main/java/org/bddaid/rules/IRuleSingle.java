package org.bddaid.rules;

import org.bddaid.model.Feature;
import org.bddaid.model.result.FeatureRunResult;

public interface IRuleSingle extends  IRule{

    FeatureRunResult applyRule(Feature feature);

}

