package org.bddaid.model.result;

import org.bddaid.rules.IRule;

public interface RunResult {

    boolean isSuccess();

    IRule getRule();
}
