package de.oskar.commands.functions;

import de.oskar.Game;
import de.oskar.commands.Command;

public class CommandDirections extends Command {
    
    public CommandDirections() {
        super("directions", "Zeigt alle Richtungen an.");

    }

    @Override
    public void execute(String[] args) {
        Game.player.getPosition().printDirections();
    }

    

}
