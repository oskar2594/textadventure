package de.oskar.world.objects;

public abstract class LocationObject {
    
    private String description;

    public LocationObject(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    abstract public void execute();

}
