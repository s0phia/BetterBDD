package org.betterbdd.readers;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ParserException;
import gherkin.ast.GherkinDocument;
import org.apache.logging.log4j.Level;
import org.betterbdd.model.Feature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

public class FeatureParser {

    static List<String> errors = new ArrayList<>();

    public List<Feature> parseFiles(File filepath) {

        List<File> featureFiles = new FeatureFileReader().readFiles(filepath);
        List<Feature> parsedFeatures = new ArrayList<>();

        for (File featureFile : featureFiles)
            parsedFeatures.add(parseFeatureFile(featureFile));

        return parsedFeatures;
    }

    public Feature parseFeatureFile(File featureFile) {

        Feature parsedFeature = null;
        GherkinDocument gherkinDocument;
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());

        try {
            Reader reader = new FileReader(featureFile);
            org.apache.logging.log4j.LogManager.getLogger().log(Level.DEBUG,
                    String.format("Parsing feature: %s",featureFile.getAbsolutePath()));

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