package de.oskar.commands.functions;

import de.oskar.Game;
import de.oskar.commands.Command;
import de.oskar.world.Location;
import de.oskar.world.objects.Lootable;

public class CommandLoot extends Command {
    

    public CommandLoot() {
        super("loot", "Lootet ein Item.");
    }

    @Override
    public void execute(String[] args) {
        Location location = Game.player.getPosition();
        if(location == null) return;
        Lootable lootable = location.getLootable();
        if(lootable == null) {
            Game.console.print("Hier gibt es nichts zu looten!");
            return;
        }
        lootable.execute();
    }

}
