package com.example.demo.service;

import com.example.demo.model.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProbeService {

    private Probe probe;

    // Public method to initialize the probe with grid and obstacles
    public void initialize(int x, int y, Direction direction, int width, int height, List<Coordinate> obstacles) {
        Grid grid = new Grid(width, height);
        for (Coordinate obstacle : obstacles) {
            grid.addObstacle(obstacle);
        }
        this.probe = new Probe(new Coordinate(x, y), direction, grid);
    }

    // Execute command string like "FFLRB"
    public void executeCommands(String commands) {
        if (probe == null) return;
        for (char cmd : commands.toUpperCase().toCharArray()) {
            switch (cmd) {
                case 'F' -> probe.moveForward();
                case 'B' -> probe.moveBackward();
                case 'L' -> probe.turnLeft();
                case 'R' -> probe.turnRight();
                default -> {
                    // Ignore invalid commands
                }
            }
        }
    }

    // Get current position
    public Coordinate getCurrentPosition() {
        return probe != null ? probe.getCurrentPosition() : null;
    }

    // Get current direction
    public Direction getCurrentDirection() {
        return probe != null ? probe.getCurrentDirection() : null;
    }

    // Get all coordinates visited
    public List<Coordinate> getVisitedPath() {
        return probe != null ? probe.getVisitedPath() : List.of();
    }
}
