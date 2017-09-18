package org.betterbdd.test.acceptance.stepdefs;

import org.betterbdd.model.enums.Rule;
import org.junit.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestBase {

    protected File featureFile;
    protected String baseFeaturePath = "./src/test/resources/test_features";
    protected String baseRulePath = "./src/test/resources/rules_config";
    private String jarLocation = "./target/betterbdd-1.0-SNAPSHOT-jar-with-dependencies.jar";
    private ProcessBuilder pb;
    protected File systemLog;
    protected File errorLog ;
    protected void runRule(Rule rule, File featureFile) throws IOException {

        File rulesConfigFile = new File(String.format("%s/%s.yml", baseRulePath, rule.name()));

        systemLog = new File("systemOut_"+rule.name()+".log");
        errorLog = new File("errorOut_"+rule.name()+".log");
        String args[] = {"java", "-jar", jarLocation,
                "--path", featureFile.getPath(),
                "--rules ", rulesConfigFile.getPath()};

        pb = new ProcessBuilder(args);
        pb.redirectError(errorLog);
        pb.redirectOutput(systemLog);
        pb.start();
    }

    protected void verifyMessage(String expectedMessage) throws FileNotFoundException, InterruptedException {

        boolean isMessageFound = false;
        final Scanner scanner = new Scanner(systemLog);

        //TODO:remove
        Thread.sleep(2000);

        while (scanner.hasNextLine()) {

            final String lineFromFile = scanner.nextLine();
            if (lineFromFile.contains(expectedMessage)) {
                isMessageFound = true;
                break;
            }
        }

        Assert.assertEquals(String.format("\nExpected message: '%s' was not found in file %s",
                expectedMessage, systemLog.getAbsolutePath()), isMessageFound, true);
    }


}
