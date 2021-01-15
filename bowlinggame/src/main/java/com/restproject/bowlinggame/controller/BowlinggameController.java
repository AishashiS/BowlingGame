package com.restproject.bowlinggame.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restproject.bowlinggame.domain.BowlingLane;
import com.restproject.bowlinggame.domain.Player;
import com.restproject.bowlinggame.repository.BowlingRepository;

/**
 * play a Bowling Game through the REST API.
 *
 * 
 * POST /bowlinggame/bowlingLane > creates a new game 
 * POST /bowlinggame/bowlingLane/players with body {"name":"player name"} > adds a player named "player name" to the game 
 * POST /bowlinggame/bowlingLane/{bowlingLaneId}/start > starts the game 
 * POST /bowlinggame/bowlingLane/{bowlingLaneId}/droppins/{numberOfPins} > drops {numberOfPins}
 * for the current player
 *
 * @author aishws
 */

@RestController
@RequestMapping("/bowlinggame")
public class BowlinggameController {
	@Autowired
	private BowlingRepository bowlingRepository;

	@PostMapping("/bowlingLane")
	public BowlingLane createBowlingLane() {
		BowlingLane bowlingLane = new BowlingLane();
		bowlingRepository.save(bowlingLane);
		
		return bowlingLane;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{bowlingLaneId}")
	public BowlingLane getBowlingLane(@PathVariable("bowlingLaneId") final String id) {
		return bowlingRepository.findBowlingLaneById(id);
	}

	/**
	 *
	 * @param bowlinglaneId
	 * @param player
	 * @return
	 */
	@PostMapping("/{bowlingLaneId}/players")
	public ResponseEntity<Player> addPlayerToBowlingLane(@PathVariable("bowlingLaneId") final String bowlinglaneId,
			final Player player) {
		BowlingLane bowlingLane = bowlingRepository.findBowlingLaneById(bowlinglaneId);
		bowlingLane.addPlayer(player);
		bowlingRepository.save(bowlingLane);
		
		return ResponseEntity.created(URI.create(String.format("/players/%s", player.getName()))).body(player);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("/{bowlingLaneId}/start")
	public BowlingLane startBowlingLane(@PathVariable("bowlingLaneId") final String id) {
		BowlingLane bowlingLane = bowlingRepository.findBowlingLaneById(id);
		bowlingLane.start();
		bowlingRepository.save(bowlingLane);

		return bowlingLane;
	}

	@PostMapping("/{bowlingLaneId}/droppins/{pinsDropped}")
	public BowlingLane throwBall(@PathVariable("bowlingLaneId") final String id,
			@PathVariable("pinsDropped") final int pinsDropped) {
		BowlingLane bowlingLane = bowlingRepository.findBowlingLaneById(id);
		bowlingLane.dropPins(pinsDropped);
		bowlingRepository.save(bowlingLane);

		return bowlingLane;
	}

}
