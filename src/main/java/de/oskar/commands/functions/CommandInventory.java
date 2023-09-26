package de.oskar.commands.functions;

import java.util.HashMap;

import de.oskar.Game;
import de.oskar.commands.Command;
import de.oskar.world.Item;
import de.oskar.world.player.Inventory;

public class CommandInventory extends Command {

    public CommandInventory() {
        super("inventory", "Zeigt dir dein Inventar an.");
    }

    @Override
    public void execute(String[] args) {
        Inventory inventory = Game.player.getInventory();
        
        if(inventory.getItems().isEmpty()) {
            Game.console.print("Dein Inventar ist leer.");
        } else {
            Game.console.print("In deinem Inventar befinden sich:");
            HashMap<String, Item> items = inventory.getItems();
            for (String key : items.keySet()) {
                Item item = items.get(key);
                Game.console.printProcedural(item.getAmount() + "x " + item.getName() + " (" + item.getDescription() + ")");
            }
        }
    }
    
}
