package de.oskar.world;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import de.oskar.Game;
import de.oskar.utils.Direction;
import de.oskar.world.connections.Connection;
import de.oskar.world.objects.LocationObject;
import de.oskar.world.objects.Locker;
import de.oskar.world.objects.Lootable;
import de.oskar.world.objects.dialogue.Dialogue;

public class Location {

    private String id;
    private String name;
    private String description;
    private boolean visited = false;

    private LinkedList<LocationObject> objects = new LinkedList<LocationObject>();
    private HashMap<Direction, Connection> connections = new HashMap<Direction, Connection>();

    /**
     * Creates a new location
     * @param id The id of the location
     * @param name The name of the location
     * @param description The description of the location
     */
    public Location(String id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    /**
     * Get a maybe existing Llootable LocationObject
     * @return The Lootable LocationObject
     */
    public Lootable getLootable() {
        for (LocationObject object : objects) {
            if (object instanceof Lootable) {
                return (Lootable) object;
            }
        }
        return null;
    }

    /**
     * Get a maybe existing Locker LocationObject
     * @return The Locker LocationObject
     */
    public Dialogue getDialogue() {
        for (LocationObject object : objects) {
            if (object instanceof Dialogue) {
                return (Dialogue) object;
            }
        }
        return null;
    }

    /**
     * Use an Item
     */
    public void useItem(String item) {
        for (LocationObject locationObject : objects) {
            if (locationObject instanceof Locker) {
                if (((Locker) locationObject).getItem() != null)
                    ((Locker) locationObject).execute();
            }
        }
    }

    /**
     * Visiting the location. This will print the description and the objects in the location
     */
    public void visit() {
        this.visited = true;
        if (this.id.equalsIgnoreCase("end_room")) {
            Game.console.printProcedural(description);
            Game.endGame();
            return;
        }
        this.printLocation();
        for (LocationObject locationObject : objects) {
            if (locationObject instanceof Locker) {
                if (((Locker) locationObject).getItem() == null)
                    ((Locker) locationObject).execute();
            }
        }
        for (LocationObject locationObject : objects) {
            if (locationObject instanceof Dialogue) {
                if (((Dialogue) locationObject).isAutoStart()) {
                    ((Dialogue) locationObject).execute();
                    break;
                }
            }
        }
    }

    /**
     * prints the description of the location
     */
    public void printDirections() {
        String directions = "\n";
        if (Game.player.getPosition().getConnections().isEmpty()) {
            Game.console.printProcedural("Du kannst hier nicht weiter gehen.");
            return;
        }
        Set<Direction> directionSet = Game.player.getPosition().getConnections().keySet();
        for (Direction direction : directionSet) {
            if (Game.player.getPosition().getConnections().get(direction).isLocked())
                continue;
            if (Game.player.getPosition().getConnections().get(direction).getTo().isVisited())
                directions += "Besucht: ";
            directions += Game.player.getPosition().getConnections().get(direction).getDescription() + " (" + direction
                    + ")" + ",\n";
        }

        directions = directions.substring(0, directions.length() - 2);
        Game.console.printProcedural("Du kannst in diese Richtungen gehen:$ " + directions);
    }

    /**
     * prints the name of the location
     */
    public void printLocation() {
        Game.console.printProcedural("Du bist hier: " + this.name);
    }

    /**
     * prints the description of the location
     */
    public void printDescription() {
        Game.console.printProcedural(this.description);
    }

    /**
     * adds a connection to the location
     * @param connection The connection
     * @param direction The direction of the connection
     */
    public void addConnection(Connection connection, Direction direction) {
        this.connections.put(direction, connection);
    }

    /**
     * Get a maybe existing connection
     * @param direction The direction of the connection
     * @return The connection
     */
    public Connection getConnection(Direction direction) {
        Connection connection = this.connections.get(direction);
        if (connection == null)
            return null;
        if (connection.isLocked())
            return null;
        return this.connections.get(direction);
    }

    /**
     * Get a maybe existing connection
     * @param direction The direction of the connection
     * @param ignoreLock If the connection should be ignored if it is locked
     * @return
     */
    public Connection getConnection(Direction direction, boolean ignoreLock) {
        if (ignoreLock)
            return this.connections.get(direction);
        return this.getConnection(direction);
    }

    public HashMap<Direction, Connection> getConnections() {
        return connections;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LinkedList<LocationObject> getObjects() {
        return objects;
    }

    public void addObject(LocationObject object) {
        this.objects.add(object);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setObjects(LinkedList<LocationObject> objects) {
        this.objects = objects;
    }
}
