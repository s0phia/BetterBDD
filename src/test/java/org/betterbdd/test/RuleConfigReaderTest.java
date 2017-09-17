package org.betterbdd.test;

import com.beust.jcommander.ParameterException;
import org.betterbdd.cli.PathValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RuleConfigReaderTest {

    private ClassLoader classLoader;

    @BeforeClass
    public void getClassLoader() {
        classLoader = getClass().getClassLoader();
    }

    @Test
    public void should_ThrowError_When_ConfigFileNotFound() {
        String path = "x";
        try {
            PathValidator filePathValidator = new PathValidator();
            filePathValidator.validate("-r", path);
        } catch (ParameterException e) {
            Assert.assertEquals(e.getMessage(), String.format("File or Directory does not exist: [%s]", path));
        }

    }

}
