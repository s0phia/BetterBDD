
package org.bddaid.reports;

import org.bddaid.model.enums.ReportFormat;
import org.bddaid.model.result.BDDRunResult;

import java.io.File;
import java.io.IOException;

public class Reporter {

    public void printReport(BDDRunResult runResult, ReportFormat format) throws IOException {

        String fileName = "better_bdd_report";
        switch (format) {
            case CONSOLE:
                ConsoleReporter.runReport(runResult);
                break;
            case HTML:
                new HTMLReporter().saveReport(runResult, new File(fileName +".html"));
                break;
                break;
        }
    }


}

