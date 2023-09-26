package de.oskar.world.connections;

import de.oskar.Game;
import de.oskar.world.Location;
import de.oskar.world.connections.tasks.Task;

public class Connection {

    private String id;
    private Task task;
    private Location from;
    private Location to;
    private String description;
    private String useText;
    private boolean locked = false;

    /**
     * Creates a new connection 
     * @param id The id of the connection
     * @param description The description of the connection
     * @param useText The text that is printed when the player uses the connection
     * @param from The location where the connection starts
     * @param to The location where the connection ends
     */
    public Connection(String id, String description, String useText, Location from, Location to) {
        this.description = description;
        this.useText = useText;
        this.from = from;
        this.to = to;
        this.id = id;
    }

    public void printDescription() {
        Game.console.printProcedural(this.description);
    }

    public boolean canMove() {
        if (this.task == null) return true;
        return this.task.evaluate();
    }

    public Location getMovedLocation() {
        if (this.task == null || this.task.evaluate()) {
            return this.to;
        }
        return this.from;
    }

    public String getDescription() {
        return description;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public String getId() {
        return id;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public String getUseText() {
        return useText;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }


}
