package org.bddaid.rules;

import java.io.File;
import java.util.List;

public interface IRuleBatch extends IRule{

    boolean applyRule(List<File> featureFiles);

}


