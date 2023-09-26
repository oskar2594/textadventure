package de.oskar.world.connections.tasks;

import java.util.Scanner;

import de.oskar.Game;

public class CodeTask extends Task {

    private String description;
    private String fail;
    private String success;
    private String code;

    /**
     * Creates a new CodeTask.
     * @param description The description of the task
     * @param fail The message that is printed if the code is wrong
     * @param success The message that is printed if the code is correct
     * @param code The code that has to be entered
     */
    CodeTask(String description, String fail, String success, String code) {
        super("code", description);
        this.description = description;
        this.fail = fail;
        this.success = success;
        this.code = code;
    }

    @Override
    public boolean evaluate() {
        Scanner scanner = Game.scanner;
        Game.console.printProcedural(description);
        System.out.print("Bitte gib die Lösung ein: ");
        String input = scanner.nextLine();
        if(!input.equalsIgnoreCase(code)) Game.console.printProcedural(fail);
        else Game.console.printProcedural(success);
        return input.equalsIgnoreCase(code);
    }

}
