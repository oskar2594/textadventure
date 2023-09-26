package de.oskar;

import java.util.Scanner;

import de.oskar.commands.Parser;

public class Input {

    private Scanner scanner;
    private Parser parser;

    private static boolean noInput = false;

    private boolean running = true;

    /**
     * Creates a new Input
     * @param parser The parser that handles the input
     */
    public Input(Parser parser) {
        scanner = Game.scanner;
        this.parser = parser;
    }

    /**
     * Starts the input
     */
    public void start() {
        read();
        scanner.close();
    }

    /**
     * Reads the input from the console
     */
    public void read() {
        if(!running) return;
        if(!noInput) System.out.print("> ");
        noInput = false;
        String input = scanner.nextLine();
        if(input.length() == 0) {
            noInput = true;
            read();
            return;
        }
        parser.handleInput(input);
        read();
    }

    /**
     * Stops the input
     */
    public void stop() {
        running = false;
    }
}
