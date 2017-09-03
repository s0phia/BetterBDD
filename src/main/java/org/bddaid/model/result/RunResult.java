package org.bddaid.model.result;

import org.bddaid.model.result.BDDRunResult;
import org.bddaid.model.result.FeatureRunResult;
import org.bddaid.model.result.FeaturesRunResult;

import java.util.ArrayList;
import java.util.List;

public class RunResult {

    private boolean isSuccess;
    private List<BDDRunResult> bddRunResultList = new ArrayList<>();

    public RunResult() {
    }

    public RunResult(boolean isSuccess, List<BDDRunResult> featureRunResultList) {
        this.isSuccess = isSuccess;
        this.bddRunResultList = featureRunResultList;
    }


    public boolean isSuccess() {

        boolean isSuccess = false;

        for (BDDRunResult bddRunResult : bddRunResultList) {
            if (bddRunResult instanceof FeaturesRunResult &&
                    ((FeaturesRunResult) bddRunResult).getFailures().size() > 0) {
                return false;

            }
            if (bddRunResult instanceof FeatureRunResult &&
                    ((FeatureRunResult) bddRunResult).getFailures().size() > 0) {
                return false;
            } else {
                isSuccess = true;
            }
        }
        return isSuccess;
    }


    public List<BDDRunResult> getFeatureRunResults() {
        return bddRunResultList;
    }

    public void addFeatureRunResults(List<BDDRunResult> featureRunResultList) {
        this.bddRunResultList.addAll(featureRunResultList);
    }

    public void addBDDRunResult(BDDRunResult featureRunResult) {
        this.bddRunResultList.add(featureRunResult);
    }


}
