package org.bddaid.cli;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class AppArgs {

    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = {"-h", "--help"}, help = true, description = "Displays help information")
    private boolean help;

    @Parameter(names = {"-p", "--path"},
            validateWith = PathValidator.class,
            description = "Path to directory containing feature files")
    private String path = ".";

    @Parameter(names = {"-r", "--rules"},
            validateWith = PathValidator.class,
            description = "Path to rules config .yml file")
    private String rulesPath = ".";


    public List<String> getParameters() {
        return parameters;
    }

    public boolean isHelp() {
        return help;
    }

    public String getPath() {
        return path;
    }

    public String getRulesPath() {
        return rulesPath;
    }
}






