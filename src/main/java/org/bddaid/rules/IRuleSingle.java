package org.bddaid.rules;

import java.io.File;

public interface IRuleSingle extends  IRule{

    boolean applyRule(File featureFile);

}

