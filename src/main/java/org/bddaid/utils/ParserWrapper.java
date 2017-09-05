package org.bddaid.utils;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ParserException;
import gherkin.ast.GherkinDocument;
import org.bddaid.model.Feature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ParserWrapper {

    static List<String> errors = new ArrayList<>();


    private static Feature parse(File featureFile) {
        return parseFeatureFile(featureFile);
    }

    public static List<Feature> parseFeatureFiles(List<File> featureFiles) {

        List<Feature> parsedFeatures = new ArrayList<>();

        for (File featureFile : featureFiles)
            parsedFeatures.add(parse(featureFile));

        return parsedFeatures;
    }

    public static Feature parseFeatureFile(File featureFile) {

        Feature parsedFeature = null;
        GherkinDocument gherkinDocument;
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());

        try {
            Reader reader = new FileReader(featureFile);
            gherkinDocument = parser.parse(reader);
            parsedFeature = new Feature(featureFile.getAbsolutePath(), gherkinDocument);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (ParserException e) {

            String message = String.format("\n\nCould not parse feature file: %s \n %s",
                    featureFile, e.getMessage());
            errors.add(message);
        }

        if (errors.size() > 0)
            throw new RuntimeException(errors.toString());

        return parsedFeature;
    }
}