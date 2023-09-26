package de.oskar.world.objects;

import com.fasterxml.jackson.databind.JsonNode;

import de.oskar.world.Item;
import de.oskar.world.objects.dialogue.Dialogue;
import de.oskar.world.objects.dialogue.DialogueBuilder;
import de.oskar.world.objects.dialogue.Sequence;
import de.oskar.world.player.Inventory;

public class ObjectBuilder {

    /**
     * Builds a location object from a json node
     * @param jsonNode The json node
     * @return The location object
     */

    public static LocationObject buildObject(JsonNode jsonNode) {
        Item item = null;
        
        switch (jsonNode.get("type").asText()) {
            case "unlocker":
                if (jsonNode.has("use_item") && !jsonNode.get("use_item").isNull())
                    item = Inventory.getRawItem(jsonNode.get("use_item").asText());
                return new Locker(jsonNode.get("description").asText(), false, item,
                        jsonNode.get("connection").asText());
            case "locker":
                if (jsonNode.has("use_item") && !jsonNode.get("use_item").isNull())
                    item = Inventory.getRawItem(jsonNode.get("use_item").asText());
                return new Locker(jsonNode.get("description").asText(), true, item,
                        jsonNode.get("connection").asText());
            case "dialogue":
                boolean autoStart = jsonNode.get("autoStart").asBoolean();
                Sequence startSequence = DialogueBuilder.buildSequence(jsonNode.get("startSequence"));
                return new Dialogue(startSequence, autoStart);
            case "lootable":
                String description = jsonNode.get("loot").asText();
                item = Inventory.getRawItem(jsonNode.get("item").asText());
                item.setAmount(jsonNode.get("amount").asInt());

                Item unlocker = null;
                if (jsonNode.has("unlocker") && !jsonNode.get("unlocker").isNull()) {
                    JsonNode unlockerNode = jsonNode.get("unlocker");
                    unlocker = Inventory.getRawItem(unlockerNode.get("item").asText());
                    unlocker.setAmount(unlockerNode.get("amount").asInt());
                }

                Lootable lootable;
                if (unlocker != null) {
                    lootable = new Lootable(item, unlocker, description);
                } else {
                    lootable = new Lootable(item, description);
                }
                return lootable;
            default:
                return null;
        }
    }
}
