package de.oskar.commands.functions;

import de.oskar.Game;
import de.oskar.commands.Command;
import de.oskar.world.player.Inventory;

public class CommandInspect extends Command {

    public CommandInspect() {
        super("inspect", "Schau dir ein Item genauer an inspect <item>");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 0) {
            Game.console.printProcedural("Bitte gib ein Item an.");
            return;
        }
        String item = args[0];
        if(Game.player.hasItem(item, 1)) {
            Game.console.printProcedural(Inventory.getRawItem(item).getInspect());
        } else {
            Game.console.printProcedural("Du hast dieses Item nicht.");
        }
    }
    
}
