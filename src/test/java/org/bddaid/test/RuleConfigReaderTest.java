package org.bddaid.test;

import com.beust.jcommander.ParameterException;
import org.bddaid.cli.PathValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

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
