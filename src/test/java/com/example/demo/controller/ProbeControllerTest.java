package com.example.demo.controller;

import com.example.demo.model.Coordinate;
import com.example.demo.model.Direction;
import com.example.demo.service.ProbeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProbeController.class)
public class ProbeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProbeService probeService;

    @Test
    void testInitializeProbe() throws Exception {
        String json = "[{\"x\":1,\"y\":2},{\"x\":3,\"y\":4}]";

        mockMvc.perform(post("/api/probe/init")
                        .param("x", "0")
                        .param("y", "0")
                        .param("direction", "NORTH")
                        .param("width", "5")
                        .param("height", "5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Probe initialized."));
    }

    @Test
    void testMove() throws Exception {
        mockMvc.perform(post("/api/probe/move")
                        .content("FLFR")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Commands executed."));
    }

    @Test
    void testGetCurrentPosition() throws Exception {
        when(probeService.getCurrentPosition()).thenReturn(new Coordinate(2, 3));

        mockMvc.perform(get("/api/probe/position"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"x\":2,\"y\":3}"));
    }

    @Test
    void testGetCurrentDirection() throws Exception {
        when(probeService.getCurrentDirection()).thenReturn(Direction.EAST);

        mockMvc.perform(get("/api/probe/direction"))
                .andExpect(status().isOk())
                .andExpect(content().string("\"EAST\""));
    }

    @Test
    void testGetVisitedPath() throws Exception {
        List<Coordinate> visited = List.of(
                new Coordinate(0, 0),
                new Coordinate(0, 1)
        );
        when(probeService.getVisitedPath()).thenReturn(visited);

        mockMvc.perform(get("/api/probe/visited"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"x\":0,\"y\":0},{\"x\":0,\"y\":1}]"));
    }
}
