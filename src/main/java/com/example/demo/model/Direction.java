package com.example.demo.model;

// Enum representing the four directions the probe can face
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    // Returns the direction after turning left (counter-clockwise)
    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST  -> SOUTH;
            case SOUTH -> EAST;
            case EAST  -> NORTH;
        };
    }

    // Returns the direction after turning right (clockwise)
    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST  -> SOUTH;
            case SOUTH -> WEST;
            case WEST  -> NORTH;
        };
    }

    // Returns the movement on X-axis when moving in this direction
    public int dx() {
        return switch (this) {
            case EAST  -> 1;
            case WEST  -> -1;
            default    -> 0;
        };
    }

    // Returns the movement on Y-axis when moving in this direction
    public int dy() {
        return switch (this) {
            case NORTH -> 1;
            case SOUTH -> -1;
            default    -> 0;
        };
    }
}
