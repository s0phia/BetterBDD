
package org.betterbdd.reports;

import org.betterbdd.model.enums.ReportFormat;
import org.betterbdd.model.result.BDDRunResult;

import java.io.File;
import java.io.IOException;

public class Reporter {

    public void printReport(BDDRunResult runResult, ReportFormat format, String filePath) throws IOException {

        String fileName = "better_bdd_report";
        switch (format) {
            case CONSOLE:
                ConsoleReporter.runReport(runResult);
                break;
            case HTML:
                new HTMLReporter().saveReport(runResult, new File(filePath));
                break;
            case JSON:
                new JSONReporter().saveReport(runResult, new File(filePath));
                break;
        }
    }


}

