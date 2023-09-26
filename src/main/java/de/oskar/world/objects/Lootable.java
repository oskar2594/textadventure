package de.oskar.world.objects;

import de.oskar.Game;
import de.oskar.world.Item;

public class Lootable extends LocationObject {

    private Item item;
    private Item unlocker;
    private String description;
    private boolean isLooted = false;

    /**
     * Creates a new Lootable.
     * @param item The item that is looted
     * @param description The description of the lootable
     */
    public Lootable(Item item, String description) {
        super(description);
        this.item = item;
        this.description = description;
    }

    /**
     * Creates a new Lootable.
     * @param item  The item that is looted
     * @param unlocker The item that is needed to loot the item
     * @param description   The description of the lootable
     */
    public Lootable(Item item, Item unlocker, String description) {
        super(description);
        this.item = item;
        this.unlocker = unlocker;
        this.description = description;
    }

    public void execute() {
        if(isLooted) {
            Game.console.printProcedural("Du hast das Item bereits aufgenommen.");
            return;
        }
        if(unlocker == null) {
            isLooted = true;
            Game.console.printProcedural(this.description);
            Game.player.addItem(item);
        } else {
            if(Game.player.hasItem(unlocker.getName(), unlocker.getAmount())) {
                Game.console.printProcedural(this.description);
                Game.player.addItem(item);
                isLooted = true;
            } else {
                Game.console.printProcedural("Du brauchst " + unlocker.getName() + " um das Item zu erhalten.");
            }
        }
    }

    public Item getItem() {
        return item;
    }

    public Item getUnlocker() {
        return unlocker;
    }

    public String getDescription() {
        return description;
    }


}
