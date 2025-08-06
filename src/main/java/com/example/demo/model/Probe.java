package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Probe {
    private Coordinate position;
    private Direction direction;
    private final Grid grid;
    private final List<Coordinate> visited = new ArrayList<>();

    public Probe(Coordinate start, Direction direction, Grid grid) {
        this.position = start;
        this.direction = direction;
        this.grid = grid;
        visited.add(start);
    }

    public void moveForward() {
        move(direction.dx(), direction.dy());
    }

    public void moveBackward() {
        move(-direction.dx(), -direction.dy());
    }

    private void move(int dx, int dy) {
        Coordinate next = position.move(dx, dy);
        if (grid.isWithinBounds(next) && !grid.isObstacle(next)) {
            position = next;
            visited.add(position);
        }
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public Coordinate getCurrentPosition() {
        return position;
    }

    public Direction getCurrentDirection() {
        return direction;
    }

    public List<Coordinate> getVisitedPath() {
        return List.copyOf(visited);
    }
}
