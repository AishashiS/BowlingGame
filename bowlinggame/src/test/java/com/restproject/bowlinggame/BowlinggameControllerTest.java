package com.restproject.bowlinggame;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.restproject.bowlinggame.domain.Player;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BowlinggameControllerTest {
	
	 @Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;
     }
    
     @Test
     public void testAddPlayerToBowlingLane() {
         Player p = new Player();
         Player.newWithName("Aish");
         System.out.println(getRootUrl());
         ResponseEntity<Player> postResponse = restTemplate.postForEntity(getRootUrl() + "/bowlinggame/1/players", p, Player.class);
         assertNotNull(postResponse);
         assertNotNull(postResponse.getBody());
         
        
     }

}
