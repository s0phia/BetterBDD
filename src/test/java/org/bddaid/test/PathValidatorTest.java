package org.bddaid.test;

import com.beust.jcommander.ParameterException;
import org.bddaid.cli.PathValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class PathValidatorTest {

    private final String FEATURES_DIR = "features";
    private final String VALID_FEATURE_FILE = "basic_feature.feature";
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
            Assert.assertEquals(e.getMessage(), String.format("Directory does no exist: [%s]", path));
        }
    }

    @Test
    public void should_NotThrowError_When_DirectoryDoesExist() {
        File file = new File(classLoader.getResource(FEATURES_DIR).getFile());
        Assert.assertEquals(file.exists(), true);
    }

    @Test
    public void should_NotThrowError_When_FileDoesExist() {
        ;
        File file = new File(classLoader.getResource(FEATURES_DIR + "/valid/" + VALID_FEATURE_FILE).getFile());
        Assert.assertEquals(file.getName(), VALID_FEATURE_FILE);
    }

}
