package org.betterbdd.readers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FeatureFileReader {

    public List<File> readFiles(File path) {
        List<File> features;
        try {
            features = Files.walk(Paths.get(path.toURI()))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".feature"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (features.size() < 1)
            throw new RuntimeException(String.format("No feature files found in given path: %s", path));

        return features;
    }
}



