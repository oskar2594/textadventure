package de.oskar.world;

import java.util.HashMap;

public class World {
 
    private HashMap<String, Location> locations;

    /**
     * Creates a new world
     */
    public World() {
        this.locations = new HashMap<String, Location>();
    }

    public void addLocation(Location location) {
        this.locations.put(location.getId(), location);
    }

    public HashMap<String, Location> getLocations() {
        return locations;
    }

    public Location getLocation(String name) {
        return this.locations.get(name);
    }

    

}
