package de.oskar.utils;

public enum Direction {
    UP("up"), DOWN("down"), LEFT("left"), RIGHT("right");

    private final String name;
    private Direction(String name) {
        this.name = name;
    }

    /**
     * Returns the opposite direction of this direction.
     * @return The opposite direction
     */
    public Direction getOpposite() {
        switch(this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return null;
        }
    }

    /**
     * Returns the direction with the given name. If no direction with the given name exists, null is returned.
     * @param name The name of the direction
     * @return The direction with the given name
     */
    public static Direction getDirection(String name) {
        for(Direction direction : Direction.values()) {
            if(direction.getName().equalsIgnoreCase(name)) {
                return direction;
            }
        }
        return null;
    }

    /**
     * Returns the name of this direction.
     * @return The name of this direction
     */
    public String getName() {
        return this.name;
    }
}
