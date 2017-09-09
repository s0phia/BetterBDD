package org.bddaid.cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        Path filePath = Paths.get(value);
        if (!(Files.exists(filePath))) {
            String message = String.format("Directory does not exist: [%s]", filePath);
            throw new ParameterException(message);
        }
    }
}
