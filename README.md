# BowlingGame

Once you have it running, you can execute the following operations:

- POST /bowlingLane > creates a new game
- POST /bowlingLane/players with body {"name":"player name"} > adds a player named "player name" to the game
- POST /bowlingLane/{bowlingLaneId}/start > starts the game
- POST /bowlingLane/{bowlingLaneId}/droppins/{numberOfPins} > drops {numberOfPins} for the current player
