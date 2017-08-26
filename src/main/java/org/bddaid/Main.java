package org.bddaid;

import com.beust.jcommander.JCommander;
import org.bddaid.cli.AppArgs;

public class Main {

    private static final AppArgs appArgs = new AppArgs();

    public static void main(String... args) {

        Main main = new Main();
        main.parseArguments(args);
        main.run();
    }

    private void parseArguments(String... args) {
        JCommander jCommander = new JCommander(appArgs);
        jCommander.parse(args);

        if (appArgs.isHelp()) {
            showUsage(jCommander);
        }

    }

    private void showUsage(JCommander jCommander) {
        jCommander.usage();
        System.exit(0);
    }


    public void run() {
        System.out.println("*** Running...");
    }


}



