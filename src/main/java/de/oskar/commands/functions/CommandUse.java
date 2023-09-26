package de.oskar.commands.functions;

import de.oskar.Game;
import de.oskar.commands.Command;

public class CommandUse extends Command {

    public CommandUse() {
        super("use", "Benuzt ein Item. use <item>");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 0) {
            Game.console.printProcedural("Bitte gib ein Item an.");
            return;
        }
        String item = args[0];
        if(Game.player.hasItem(item, 1)) {
            Game.player.useItem(item);
        } else {
            Game.console.printProcedural("Du hast dieses Item nicht.");
        }
    }
}
