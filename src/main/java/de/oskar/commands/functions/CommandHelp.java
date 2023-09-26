package de.oskar.commands.functions;

import de.oskar.Game;
import de.oskar.commands.Command;

public class CommandHelp extends Command {

    public CommandHelp() {
        super("help", "Zeigt alle Commands an.");
    }

    @Override
    public void execute(String[] args) {
        Game.console.print("Commands:");
        for (Command command : Game.parser.getCommands().values()) {
            Game.console.print(command.getName() + ": " + command.getDescription());
        }
        
    }
    
}
