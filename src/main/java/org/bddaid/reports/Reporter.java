
package org.bddaid.reports;

import org.bddaid.model.enums.ReportFormat;
import org.bddaid.model.result.BDDRunResult;

public class Reporter {

    private ReportFormat format;

    public Reporter(ReportFormat format) {
        this.format = format;
    }

    public void printReport(BDDRunResult runResult) {

        switch (format) {
            case CONSOLE:
                ConsoleReporter.runReport(runResult);
                break;
            case HTML:
                HTMLReporter.runReport(runResult);
                break;
        }
    }


}

