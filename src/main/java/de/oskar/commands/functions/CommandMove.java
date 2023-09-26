package de.oskar.commands.functions;
import de.oskar.Game;
import de.oskar.commands.Command;
import de.oskar.utils.Direction;

public class CommandMove extends Command {

    public CommandMove() {
        super("move", "Bewege dich in eine Richtung. move <Richtung>");
        
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 0) {
            Game.console.print("Bitte gib eine Richtung an.");
            return;
        }
        Direction direction = Direction.getDirection(args[0]);
        if(direction == null) {
            Game.console.print("Diese Richtung gibt es nicht!");
            return;
        }

        if(Game.player.getPosition().getConnection(direction) == null) {
            Game.console.print("Du kannst dich nicht in diese Richtung bewegen!");
        }
        
        Game.player.move(direction);

        
    }
    
}
