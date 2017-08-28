package org.bddaid.rules;

import org.bddaid.model.Feature;
import org.bddaid.model.RunResult;

import java.util.List;

public interface IRuleBatch extends IRule{

    RunResult applyRule(List<Feature> featureFiles);

}


