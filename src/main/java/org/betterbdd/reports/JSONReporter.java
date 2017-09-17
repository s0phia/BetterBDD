package org.betterbdd.reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.betterbdd.model.result.BDDRunResult;

import java.io.File;
import java.io.IOException;

public class JSONReporter implements ReportFormatter {

    public void saveReport(BDDRunResult runResult, File file) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        LogManager.getLogger().log(Level.DEBUG,
                String.format("Saving result to file:%s", file.getPath()));
        mapper.writeValue(file, runResult);

    }
}
