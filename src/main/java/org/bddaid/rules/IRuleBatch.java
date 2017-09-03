package org.bddaid.rules;

import org.bddaid.model.result.BDDRunResult;
import org.bddaid.model.Feature;

import java.util.List;

public interface IRuleBatch extends IRule{

    BDDRunResult applyRule(List<Feature> featureFiles);

}


