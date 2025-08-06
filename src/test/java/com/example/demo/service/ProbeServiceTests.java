package com.example.demo.service;

import com.example.demo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProbeServiceTests {

    private ProbeService probeService;

    @BeforeEach
    void setUp() {
        // Initialize the grid with size 5x5 and obstacles at (2,2) and (4,4)
        probeService = new ProbeService();
        probeService.initialize(
                1, 2,
                Direction.NORTH,
                5, 5,
                List.of(new Coordinate(2, 2), new Coordinate(4, 4))
        );
    }

    @Test
    void testInitialPositionAndDirection() {
        assertEquals(new Coordinate(1, 2), probeService.getCurrentPosition());
        assertEquals(Direction.NORTH, probeService.getCurrentDirection());
    }

    @Test
    void testMoveForward() {
        probeService.executeCommands("F");
        assertEquals(new Coordinate(1, 3), probeService.getCurrentPosition());
    }

    @Test
    void testTurnRightAndMoveToObstacle() {
        probeService.executeCommands("RF");
        // Facing EAST, attempt to move to (2,2) which is an obstacle
        assertEquals(new Coordinate(1, 2), probeService.getCurrentPosition());
    }

    @Test
    void testTurnLeft() {
        probeService.executeCommands("L");
        assertEquals(Direction.WEST, probeService.getCurrentDirection());
    }

    @Test
    void testMoveBackward() {
        probeService.executeCommands("B");
        assertEquals(new Coordinate(1, 1), probeService.getCurrentPosition());
    }

    @Test
    void testVisitedPath() {
        probeService.executeCommands("FFRFF");
        List<Coordinate> visited = probeService.getVisitedPath();
        assertTrue(visited.contains(new Coordinate(1, 3)));
        assertTrue(visited.contains(new Coordinate(1, 4)));
    }

    @Test
    void testIgnoreInvalidCommands() {
        probeService.executeCommands("FZXY"); // Z, X, Y are invalid
        assertEquals(new Coordinate(1, 3), probeService.getCurrentPosition());
    }

    @Test
    void testAvoidObstacle() {
        probeService.executeCommands("RFF"); // Should try to go EAST then (2,2), but it's an obstacle
        assertEquals(new Coordinate(1, 2), probeService.getCurrentPosition());
    }
}
