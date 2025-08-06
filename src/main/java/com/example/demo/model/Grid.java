package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

// Represents the ocean floor grid where the probe moves
public class Grid {
    private final int width;
    private final int height;
    private final Set<Coordinate> obstacles = new HashSet<>();

    // Creates a grid of given width and height
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Checks whether a coordinate is within the bounds of the grid
    public boolean isWithinBounds(Coordinate coord) {
        return coord.getX() >= 0 && coord.getX() < width &&
               coord.getY() >= 0 && coord.getY() < height;
    }

    // Adds an obstacle at a specific coordinate if it is within the grid
    public void addObstacle(Coordinate coord) {
        if (isWithinBounds(coord)) {
            obstacles.add(coord);
        }
    }

    // Checks if a coordinate is marked as an obstacle
    public boolean isObstacle(Coordinate coord) {
        return obstacles.contains(coord);
    }

    // Returns all obstacle coordinates
    public Set<Coordinate> getObstacles() {
        return Set.copyOf(obstacles);
    }
}
