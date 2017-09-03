package org.bddaid.model.result;


import org.bddaid.rules.IRule;

public class Failure {
    IRule rule;
    String message;

    public Failure(IRule rule, String message) {
        this.rule = rule;
        this.message = message;
    }

    public IRule getRule() {
        return rule;
    }

    public String getMessage() {
        return message;
    }

}
