package de.oskar.world.connections.tasks;

import com.fasterxml.jackson.databind.JsonNode;

public class TaskBuilder {

    /**
     * Builds a task from a json node
     * @param jsonNode The json node
     * @return The task
     */
    public static Task buildTask(JsonNode jsonNode) {
        switch (jsonNode.get("type").asText()) {
            case "item":
                return new ItemTask(jsonNode.get("item_id").asText(), jsonNode.get("amount").asInt(), jsonNode.get("description").asText(), jsonNode.get("fail").asText(), jsonNode.get("success").asText()); 
            case "code": 
                return new CodeTask(jsonNode.get("description").asText(), jsonNode.get("fail").asText(), jsonNode.get("success").asText(), jsonNode.get("code").asText());
            default:
                return null;
        }
    }
    

}
