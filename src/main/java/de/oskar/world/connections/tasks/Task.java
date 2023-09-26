package de.oskar.world.connections.tasks;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class Task {
    
    private String name;
    private String description;

    Task(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public abstract boolean evaluate();

    public void load(JsonNode config) {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
