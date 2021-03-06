package com.restproject.bowlinggame.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class BowlingLane {
	
	private String id;

    @JsonUnwrapped
    private Players players = new Players();
    private boolean gameStarted = false;

    /**
     * @return The id of the BowlingLane. If it hasn't been persisted, it will
     *      return {@code null}.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id to {@code id}.
     *
     * @param id The id to be set.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Starts the bowling game. In order for this to start, at least one
     * player had to be registered in the game.
     *
     * It will set the current player to the first player registered in the
     * game.
     *
     * @throws IllegalStateException If the game has already started, or if the
     * game does not have any players registered.
     */
    public void start() {
        if (gameStarted == true) {
            throw new IllegalStateException("The game has already started");
        }

        if (!players.hasAny()) {
            throw new IllegalStateException("The game can't start without players");
        }

        gameStarted = true;
        players.setCurrentPlayer(players.getFirstPlayer());
    }

    /**
     * Evaluates whether or not this game has started.
     *
     * @return true if it has started, false otherwise.
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Evaluates whether or not the game is over.
     *
     * For a game to be over, all players have to have had completed all 10
     * frames
     *
     * @return true if it is over, false otherwise.
     */
    public boolean isGameOver() {
        boolean isGameOver = false;

        Player player = players.getCurrentPlayer();
        if (player != null) {
            isGameOver = players.isLastPlayer(player) && player.isDonePlaying();
        }

        return isGameOver;
    }

    /**
     *
     * @return
     */
    public Player getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    /**
     * @return The players that are playing in this BowlingLane.
     */
    public Players getPlayers() {
        return players;
    }

    /**
     * Adds the player {@code player} to this bowling game.
     *
     * Note that players can't be added to a game that is already in
     * progress.
     *
     * @param player The player that will be added.
     * @return The bowling lane object for easy method chaining.
     * @throws IllegalStateExeption If the game has started already.
     */
    public BowlingLane addPlayer(final Player player) {
        if (gameStarted == true) {
            throw new IllegalStateException(
                    "Players can't be added while the game is in progress");
        }

        players.addPlayer(player);

        return this;
    }

    /**
     * Drops a total of {@code pinsDropped} on the current player.
     *
     * If the frame is complete for the current player, it will advance to
     * the next frame.
     *
     * @param pinsDropped The number of pins that were dropped
     * @throws IllegalStateException If you try to drop pins and the game is over.
     */
    public void dropPins(final int pinsDropped) {
        if (isGameOver()) {
            throw new IllegalStateException("The game is over");
        }

        Player player = players.getCurrentPlayer();
        Frames frames = player.getFrames();
        Frame currentFrame = player.getCurrentFrame();

        currentFrame.dropPins(pinsDropped);

        if (currentFrame.isComplete()) {
            if (!(player.isLastPlayer(players) && player.isDonePlaying())) {
                players.nextPlayer();
            }
        }

        if (currentFrame.isComplete() && !currentFrame.isLastFrame()) {
            frames.nextFrame();
        }
    }

}
