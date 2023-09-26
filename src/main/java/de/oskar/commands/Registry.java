package de.oskar.commands;

import java.util.HashMap;

public class Registry {
    
    private HashMap<String, Command> commands = new HashMap<String, Command>();


    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }
    
}
