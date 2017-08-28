package org.bddaid.rules;

import org.bddaid.model.Feature;
import org.bddaid.model.RunResult;

public interface IRuleSingle extends  IRule{

    RunResult applyRule(Feature feature);

}

