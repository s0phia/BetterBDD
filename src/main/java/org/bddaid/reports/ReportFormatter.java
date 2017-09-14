package org.bddaid.reports;

import org.bddaid.model.result.BDDRunResult;

import java.io.File;
import java.io.IOException;

public interface ReportFormatter {

    void saveReport(BDDRunResult runResult, File file) throws IOException;
}
