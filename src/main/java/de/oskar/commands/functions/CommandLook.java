package de.oskar.commands.functions;

import de.oskar.Game;
import de.oskar.commands.Command;

public class CommandLook extends Command {

    public CommandLook() {
        super("look", "Schau dich um.");
    }

    @Override
    public void execute(String[] args) {
        Game.player.getPosition().printDescription();
    }
    
}
