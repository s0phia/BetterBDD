package org.betterbdd.reports;

import org.betterbdd.model.result.BDDRunResult;

import java.io.File;
import java.io.IOException;

public interface ReportFormatter {

    void saveReport(BDDRunResult runResult, File file) throws IOException;
}
