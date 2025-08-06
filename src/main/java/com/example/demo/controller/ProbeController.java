package com.example.demo.controller;

import com.example.demo.model.Coordinate;
import com.example.demo.model.Direction;
import com.example.demo.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/probe")
public class ProbeController {

    @Autowired
    private ProbeService probeService;

    // Endpoint to initialize the probe with position, direction, grid size, and obstacles
    @PostMapping("/init")
    public String initialize(
            @RequestParam int x,
            @RequestParam int y,
            @RequestParam String direction,
            @RequestParam int width,
            @RequestParam int height,
            @RequestBody List<Coordinate> obstacles
    ) {
        // Convert string to Direction enum safely
        Direction dir = Direction.valueOf(direction.toUpperCase());
        probeService.initialize(x, y, dir, width, height, obstacles);
        return "Probe initialized.";
    }

    // Endpoint to send a string of movement commands (F, B, L, R)
    @PostMapping("/move")
    public String move(@RequestBody String commands) {
        probeService.executeCommands(commands);
        return "Commands executed.";
    }

    // Get the current position of the probe
    @GetMapping("/position")
    public Coordinate getCurrentPosition() {
        return probeService.getCurrentPosition();
    }

    // Get the current facing direction of the probe
    @GetMapping("/direction")
    public Direction getCurrentDirection() {
        return probeService.getCurrentDirection();
    }

    // Get the list of all coordinates visited by the probe
    @GetMapping("/visited")
    public List<Coordinate> getVisitedPath() {
        return probeService.getVisitedPath();
    }
}
