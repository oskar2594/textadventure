package de.oskar.world;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.fasterxml.jackson.databind.JsonNode;

import de.oskar.utils.Direction;
import de.oskar.world.connections.Connection;
import de.oskar.world.connections.tasks.Task;
import de.oskar.world.connections.tasks.TaskBuilder;
import de.oskar.world.objects.LocationObject;
import de.oskar.world.objects.ObjectBuilder;

public class WorldBuilder {


    /**
     * Builds the items from the given json node for rawItems
     * @param items The json node containing all items
     * @return The items
     */
    public static HashMap<String, Item> buildItems(JsonNode items) {
        HashMap<String, Item> itemMap = new HashMap<String, Item>();

        Iterator<JsonNode> itemsIterator = items.elements();

        while (itemsIterator.hasNext()) {
            JsonNode item = itemsIterator.next();
            String inspect = null;
            if (item.has("inspect")) {
                inspect = item.get("inspect").asText();
            }
            Item i = new Item(item.get("id").asText(), item.get("description").asText(),
                    item.get("weight").asInt(), inspect);
            itemMap.put(i.getName(), i);
        }

        return itemMap;
    }

    /**
     * Builds the world from the given json node
     * @param rooms The json node containing all rooms
     * @return The world
     */
    public static World buildWorld(JsonNode rooms) {
        World world = new World();

        // Iterate over all rooms
        Iterator<JsonNode> roomsIterator = rooms.elements();

        // Creates all locations
        while (roomsIterator.hasNext()) {
            JsonNode room = roomsIterator.next();
            Location location = new Location(room.get("id").asText(), room.get("name").asText(),
                    room.get("description").asText());
            world.addLocation(location);
        }
        roomsIterator = rooms.elements();
        //create further details
        while (roomsIterator.hasNext()) {
            JsonNode room = roomsIterator.next();
            Location location = world.getLocation(room.get("id").asText());
            Iterator<JsonNode> connectionsIterator = room.get("connections").elements();
            // Create connections
            while (connectionsIterator.hasNext()) {
                JsonNode connection = connectionsIterator.next();
                Location to = world.getLocation(connection.get("room_id").asText());
                Direction direction = Direction.getDirection(connection.get("direction").asText());
                Connection conn = new Connection(connection.get("id").asText(), connection.get("description").asText(), connection.get("use").asText(), location, to);
                if(connection.has("locked"))
                    conn.setLocked(connection.get("locked").asBoolean());
                
                location.addConnection(conn, direction);
                // add tasks
                if (connection.has("task") && !connection.get("task").isNull()) {
                    Task task = TaskBuilder.buildTask(connection.get("task"));
                    if (task != null)
                        location.getConnection(direction, true).setTask(task);
                }
            }
            // create location objects
            if (room.has("objects") && !room.get("objects").isNull()) {
                LinkedList<LocationObject> objects = new LinkedList<LocationObject>();
                Iterator<JsonNode> objecIterator = room.get("objects").elements();
                while (objecIterator.hasNext()) {
                    JsonNode object = objecIterator.next();
                    objects.add(ObjectBuilder.buildObject(object));
                }
                location.setObjects(objects);
            }
        }

        return world;

    }

}
