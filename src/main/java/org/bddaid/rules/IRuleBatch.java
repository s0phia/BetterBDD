package org.bddaid.rules;

import org.bddaid.model.Feature;
import org.bddaid.model.result.RunResult;

import java.util.List;

public interface IRuleBatch extends IRule{

    RunResult applyRule(List<Feature> featureFiles);

}


