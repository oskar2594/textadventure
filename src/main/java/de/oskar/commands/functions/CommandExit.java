package de.oskar.commands.functions;

import java.util.Scanner;

import de.oskar.Game;
import de.oskar.commands.Command;

public class CommandExit extends Command {

    public CommandExit() {
        super("exit", "Beendet das Spiel.");
    }

    @Override
    public void execute(String[] args) {
        Scanner scanner = Game.scanner;
        Game.console.print("Bist du dir sicher? (y/n)");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("y")) {
            Game.console.print("Beende...");
            System.exit(0);
        } else if(input.equalsIgnoreCase("n")) {
            Game.console.print("Fortfahren...");
        }
    }
    
}
