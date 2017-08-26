package org.bddaid.rules.impl;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ParserException;
import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;
import org.bddaid.rules.IRuleSingle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class EmptyFeature implements IRuleSingle {

    @Override
    public boolean applyRule(File featureFile) {

        Reader reader = null;
        GherkinDocument gherkinDocument = null;
        boolean result = true;

        try {
            reader = new FileReader(featureFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result = false;
        }

        try {
            Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
            gherkinDocument = parser.parse(reader);

        } catch (ParserException e) {

            System.out.println("Could not parse feature file: " + featureFile + "\n" + e.getMessage());

        }

        List<Pickle> pickles = new Compiler().compile(gherkinDocument);

        if (pickles.size() < 1) {
            System.out.println(getErrorMessage(featureFile.toString()));
        }

        return result;

    }

    @Override
    public String getName() {
        return "Empty feature file";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getErrorMessage(String fileName) {
        return String.format("\nFeature file %s is empty", fileName);
    }
}
