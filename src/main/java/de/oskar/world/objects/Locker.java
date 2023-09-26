package de.oskar.world.objects;

import java.util.LinkedList;

import de.oskar.Game;
import de.oskar.world.Item;
import de.oskar.world.Location;
import de.oskar.world.World;
import de.oskar.world.connections.Connection;

public class Locker extends LocationObject {

    private boolean lock;
    private Item item;
    private String connection;

    /**
     * Creates a new Locker
     * @param description The description of the locker
     * @param lock The state of the locker
     * @param item The item that is needed to lock/unlock the locker
     * @param connection The connection that is locked/unlocked
     */
    public Locker(String description, boolean lock, Item item, String connection) {
        super(description);
        this.lock = lock;
        this.item = item;
        this.connection = connection;
    }

    @Override
    public void execute() {
        World world = Game.world;
        if(this.item != null) {
            if(!Game.player.hasItem(item.getName(), item.getAmount())) return;
        }
        LinkedList<Location> locations =  new LinkedList<Location>(world.getLocations().values());
        for (Location location : locations) {
            LinkedList<Connection> connections = new LinkedList<Connection>(location.getConnections().values());
            for (Connection connection : connections) {
                if (connection.getId().equals(this.connection)) {
                    if(item != null) Game.console.printProcedural(this.getDescription());
                    connection.setLocked(this.lock);
                }
            }
        }
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }
}
