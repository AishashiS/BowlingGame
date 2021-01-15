package com.restproject.bowlinggame.repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.restproject.bowlinggame.domain.BowlingLane;

@Component
public class BowlingRepository {
	private Map<String, BowlingLane> bowlingLanes = new ConcurrentHashMap<String, BowlingLane>();

	/**
	 *
	 * @param id
	 * @return
	 */
	public BowlingLane findBowlingLaneById(final String id) {
		return bowlingLanes.get(id);
	}

	/**
	 *
	 * @param bowlingLane
	 * @return
	 */
	public BowlingLane save(final BowlingLane bowlingLane) {
		String id = UUID.randomUUID().toString();
		bowlingLanes.put(id, bowlingLane);
		bowlingLane.setId(id);

		return bowlingLane;
	}
}
