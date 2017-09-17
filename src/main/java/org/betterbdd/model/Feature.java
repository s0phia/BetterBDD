package org.betterbdd.model;

import gherkin.ast.GherkinDocument;

public class Feature {

    private String path;
    private GherkinDocument gherkinDocument;

    public Feature(String path, GherkinDocument gherkinDocument) {
        this.path = path;
        this.gherkinDocument = gherkinDocument;
    }

    public String getPath() {
        return path;
    }

    public GherkinDocument getGherkinDocument() {
        return gherkinDocument;
    }
    public String getFileName() {
        return path.substring(path.lastIndexOf("/")+1);
    }


}
