package de.oskar.world.player;

import de.oskar.Game;
import de.oskar.utils.Direction;
import de.oskar.world.Item;
import de.oskar.world.Location;
import de.oskar.world.connections.Connection;

public class Player {
    
    private Inventory inventory;

    private Location position;
    private Location visitedBefore;

    public Player() {
        this.inventory = new Inventory();
    }

    /**
     * Checks if the player has the given item
     * @param item The item to check
     * @param amount The amount of the item to check
     * @return True if the player has the given item, false if not
     */
    public boolean hasItem(String item, Integer amount) {
        Item itemObject = inventory.getItems().get(item);
        if(itemObject == null) return false;
        return itemObject.getAmount() >= amount;
    }

    public void addItem(Item item) {
        inventory.addItem(item);
    }	

    public void removeItem(Item item) {
        inventory.removeItem(item);
    }

    public void useItem(String item) {
        if(!hasItem(item, 1)) return;
        this.position.useItem(item);
    }

    /**
     * Moves the player to the given direction
     * @param direction The direction to move to
     */
    public void move(Direction direction) {
        if(this.position == null) return;
        Connection connection = this.position.getConnection(direction);
        if(connection == null) return;
        Location destination = connection.getMovedLocation();
        if(destination == null || destination == position) return;
        this.visitedBefore = this.position;
        this.position = destination;
        if(connection.getUseText() != null) Game.console.printProcedural(connection.getUseText());
        this.position.visit();
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    public Location getVisitedBefore() {
        return visitedBefore;
    }

    public void setVisitedBefore(Location visitedBefore) {
        this.visitedBefore = visitedBefore;
    }

}
