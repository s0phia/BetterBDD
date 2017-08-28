package org.bddaid.model;

import java.util.ArrayList;
import java.util.List;

public class RunResult {

    private boolean isSuccess;
    private List<String> errors = new ArrayList<>();

    public RunResult(boolean isSuccess, List<String> errors) {
        this.isSuccess = isSuccess;
        this.errors = errors;
    }

    public RunResult() {

    }

    public boolean isSuccess() {
        return errors.size() > 0 ? false : true;
    }


    public List<String> getErrors() {
        return errors;
    }

    public void addErrors(List<String> errors) {
        this.errors.addAll(errors);
    }



}
