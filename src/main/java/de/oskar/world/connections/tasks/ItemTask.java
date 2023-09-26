package de.oskar.world.connections.tasks;

import de.oskar.Game;

public class ItemTask extends Task {

    private String item;
    private Integer amount;
    private String description;

    private String fail;
    private String success;

    /**
     * Creates a new ItemTask.
     * @param item The item that has to be in the inventory
     * @param amount  The amount of the item that has to be in the inventory
     * @param description The description of the task
     * @param fail  The message that is printed if the item is not in the inventory
     * @param success The message that is printed if the item is in the inventory
     */
    public ItemTask(String item, int amount, String description, String fail, String success) {
        super("item", description);
        this.item = item;
        this.amount = amount;
        this.description = description;
        this.fail = fail;
        this.success = success;
    }

    @Override
    public boolean evaluate() {
        Game.console.printProcedural(description);
        boolean hasItem =  Game.player.hasItem(item, amount);
        if(!hasItem) Game.console.printProcedural(this.fail);
        else Game.console.printProcedural(this.success);
        return hasItem;
    }


    
}
