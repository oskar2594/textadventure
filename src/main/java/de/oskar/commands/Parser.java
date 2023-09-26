package de.oskar.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import de.oskar.Game;
import de.oskar.commands.functions.CommandDirections;
import de.oskar.commands.functions.CommandExit;
import de.oskar.commands.functions.CommandHelp;
import de.oskar.commands.functions.CommandInspect;
import de.oskar.commands.functions.CommandInventory;
import de.oskar.commands.functions.CommandLook;
import de.oskar.commands.functions.CommandLoot;
import de.oskar.commands.functions.CommandMove;
import de.oskar.commands.functions.CommandUse;

public class Parser {
    
    private Registry registry;

    public Parser() {
        registry = new Registry();

        registry.register(new CommandHelp());
        registry.register(new CommandMove());
        registry.register(new CommandLook());
        registry.register(new CommandExit());
        registry.register(new CommandDirections());
        registry.register(new CommandLoot());
        registry.register(new CommandInventory());
        registry.register(new CommandUse());
        registry.register(new CommandInspect());
    }

    public void register(Command command) {
        registry.register(command);
    }

    public Command getCommand(String name) {
        return registry.getCommand(name);
    }

    public HashMap<String, Command> getCommands() {
        return registry.getCommands();
    }

    /**
     * Handles the input and executes the command if it exists.
     * @param input The input
     */
    public void handleInput(String input) {
        if(input.length() == 0) {
            Game.console.print("Befehl nicht gefunden!");
            return;
        }
        String[] args = input.split(" ");
        String commandName = args[0];
        Command command = getCommand(commandName);
        if(command == null) {
            String fullfilledCommand = handleComplete(input);
            if(fullfilledCommand != null) {
                command = getCommand(fullfilledCommand);
            }
        }
        if (command != null) {
            command.execute(Arrays.copyOfRange(args, 1, args.length));
        } else {
            Game.console.print("Befehl nicht gefunden!");
        }
    }

    /**
     * Handles the completion of commands.
     * @param input The input
     * @return The fullfilled command
     */
    public String handleComplete(String input) {
        String[] args = input.split(" ");
        if(args.length != 1) {
            return "";
        }
        String commandName = args[0];
        Set<String> commands = getCommands().keySet();
        for (String command : commands) {
            if(command.startsWith(commandName)) {
                return command;
            }
        }
        return "";
    }

}
