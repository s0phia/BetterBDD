/*
package org.bddaid.rules.impl;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ParserException;
import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;
import org.bddaid.rules.IRuleBatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateFeatureName implements IRuleBatch {

    private Map<String, Map<String, Integer>> data = new HashMap<>();

    @Override
    public boolean applyRule(List<File> featureFiles) {

        Reader reader = null;
        GherkinDocument gherkinDocument = null;
        boolean result = true;

        for (File featureFile : featureFiles) {

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
            //System.out.println("Feature name: "+gherkinDocument.getFeature().getName());
            Map<String, Integer> frequency = new HashMap<>();

            if (pickles.size() < 1) {
                //  System.out.println(getErrorMessage("" +featureFile.toString()));
            } else {

                for (Pickle pickle : pickles) {
                    if (frequency.containsKey(pickle.getName())) {
                        frequency.put(pickle.getName(), frequency.get(pickle.getName()) + 1);
                    } else {
                        frequency.put(pickle.getName(), 1);
                    }
                }
            }

            data.put(featureFile.getName(), frequency);

        }
        for (Map.Entry<String, Map<String, Integer>> entry : data.entrySet()) {

            System.out.println("*** " + entry.getKey() + ":");

            for (Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()) {
                System.out.println(entry2.getKey() + " = " + entry2.getValue() + " ");
            }
            //   System.out.println(entry.getValue().entrySet() + " = " + entry.getValue() + " ");
        }


        System.out.println();

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
*/
