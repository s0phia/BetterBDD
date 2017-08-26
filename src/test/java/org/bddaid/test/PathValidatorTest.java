package org.bddaid.test;

import com.beust.jcommander.ParameterException;
import org.bddaid.cli.PathValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class PathValidatorTest {

    public final String FEATURES_DIR = "features/valid";
    public final String VALID_FEATURE_FILE = "basic_feature.feature";

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
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FEATURES_DIR).getFile());
        Assert.assertEquals(file.exists(), true);
    }

    @Test
    public void should_NotThrowError_When_FileDoesExist() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FEATURES_DIR + "/" + VALID_FEATURE_FILE).getFile());
        Assert.assertEquals(file.getName(), VALID_FEATURE_FILE);
    }
}
