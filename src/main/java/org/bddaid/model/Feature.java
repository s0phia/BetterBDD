package org.bddaid.model;

import gherkin.ast.GherkinDocument;

public class Feature {

    String fileName;
    GherkinDocument gherkinDocument;

    public Feature(String fileName, GherkinDocument gherkinDocument) {
        this.fileName = fileName;
        this.gherkinDocument = gherkinDocument;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public GherkinDocument getGherkinDocument() {
        return gherkinDocument;
    }

    public void setGherkinDocument(GherkinDocument gherkinDocument) {
        this.gherkinDocument = gherkinDocument;
    }

}
