package org.betterbdd.test;

import com.beust.jcommander.ParameterException;
import org.betterbdd.cli.PathValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static org.betterbdd.test.TestConstants.FEATURES_BASE_PATH;
import static org.betterbdd.test.TestConstants.VALID_FEATURE_FILE;

public class PathValidatorTest {

    private ClassLoader classLoader;

    @BeforeClass
    public void getClassLoader() {
        classLoader = getClass().getClassLoader();
    }

    @Test
    public void should_ThrowError_When_FilePathDoesNotExist() {
        String path = "x";
        try {
            PathValidator filePathValidator = new PathValidator();
            filePathValidator.validate("-p", path);
        } catch (ParameterException e) {
            Assert.assertEquals(e.getMessage(), String.format("File or Directory does not exist: [%s]", path));
        }
    }

    @Test
    public void should_NotThrowError_When_DirectoryDoesExist() {
        File file = new File(classLoader.getResource(FEATURES_BASE_PATH).getFile());
        Assert.assertEquals(file.exists(), true);
    }

    @Test
    public void should_NotThrowError_When_FileDoesExist() {
        File file = new File(classLoader.getResource(FEATURES_BASE_PATH + "/valid/" + VALID_FEATURE_FILE).getFile());
        Assert.assertEquals(file.getName(), VALID_FEATURE_FILE);
    }

}
