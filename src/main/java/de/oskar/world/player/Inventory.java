package de.oskar.world.player;

import java.util.HashMap;
import de.oskar.world.Item;

public class Inventory {
    
    public static HashMap<String, Item> allItems = new HashMap<String, Item>();

    /**
     * Returns a copy of the item with the given name
     * @param name The name of the item
     * @return A copy of the item with the given name
     */
    public static Item getRawItem(String name) {
        Item rawItem = allItems.get(name);
        return new Item(rawItem.getName(), rawItem.getDescription(), rawItem.getWeight(), rawItem.getInspect());
    }

    private HashMap<String, Item> items = new HashMap<String, Item>();
    
    /**
     * Adds an item to the inventory
     * @param item  The item to add
     */
    public void addItem(Item item) {
        if(this.items.containsKey(item.getName())) {
            Item i = this.items.get(item.getName());
            i.setAmount(i.getAmount() + item.getAmount());
        } else {
            this.items.put(item.getName(), item);
        }
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    /**
     * Removes an item from the inventory
     * @param item The item to remove
     * @param amount The amount of the item to remove
     */
    public void removeItem(Item item, Integer amount) {
        if(this.items.containsKey(item.getName())) {
            Item i = this.items.get(item.getName());
            if(i.getAmount() - amount <= 0) {
                this.items.remove(item.getName());
            } else {
                i.setAmount(i.getAmount() - amount);
            }
        }
    }

    public void removeItem(Item item) {
        this.removeItem(item, item.getAmount());
    }

    public boolean containsItem(Item item) {
        return this.items.containsKey(item.getName());
    }



}
