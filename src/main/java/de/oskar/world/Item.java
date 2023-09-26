package de.oskar.world;

public class Item {
    
    private String name;
    private String description;
    private String inspect;

    private Integer weight;

    private Integer amount = 1;

    /**
     * Creates a new Item.
     * @param name The name of the item
     * @param description The description of the item
     * @param weight The weight of the item
     * @param inspect The inspect text of the item
     */
    public Item(String name, String description, Integer weight, String inspect) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.inspect = inspect;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getInspect() {
        return inspect;
    }

    public void addAmount(Integer amount) {
        this.amount += amount;
    }
}
