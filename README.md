Ocean Probe Controller-
Hi there!

This project is part of a coding challenge I completed, where I was asked to build a REST API that controls
a remotely operated underwater probe. The probe explores a grid-based ocean floor, and can move, turn, 
and avoid obstacles based on user commands.

What the problem was about-
The idea was to simulate a real-world scenario: you have a probe under the sea, 
0and you want to control it remotely. 
The challenge required:

A 2D grid that represents the ocean floor

The probe should have a starting (x, y) position and face a direction (NORTH, EAST, etc.)

You should be able to send a series of movement commands like:

F → Forward

B → Backward

L → Turn left

R → Turn right

The probe must not:

Move outside the grid

Enter a location marked as an obstacle

Finally, the probe should remember all the coordinates it has visited

What I’ve Built--
To solve this problem, I have developed a Spring Boot REST API that simulates an 
underwater probe exploring a grid-based ocean floor. Here's what it supports:

I have implemented functionality to initialize the probe with a starting position, direction, and grid size.

I can send movement commands (like FFRBL) to control the probe.

The probe avoids obstacles and does not move outside the defined grid.

I can check the probe’s current position and direction at any time.

The API also tracks and returns all the coordinates the probe has visited.

Tech Stack I Used-

Java 17
Spring Boot 3.x
Maven
RESTful API (accepts JSON and plain text)

Postman (for manual testing and validation)
API Endpoints

1. Initialize the Probe
Method: POST
URL: http://localhost:8080/api/probe/init?x=1&y=2&direction=NORTH&width=5&height=5
Request Body (JSON):

[
  { "x": 2, "y": 2 },
  { "x": 4, "y": 4 }
]

2. Move the Probe
Method: POST
URL: /api/probe/move
Request Body (text): FFRBL

3. Get Current Position
Method: GET
URL: /api/probe/position

4. Get Current Direction
Method: GET
URL: /api/probe/direction

5. Get Visited Coordinates
Method: GET
URL: /api/probe/visited

How I Tested the Application
I tested this API using Postman with different scenarios, including:

Initializing the probe with a valid grid and obstacle coordinates

Sending various movement command sequences

Verifying the current position and direction returned by the API

Checking the full path of visited coordinates

Making sure invalid commands are skipped, and the probe stays within bounds

Confirming the probe avoids obstacles without throwing errors