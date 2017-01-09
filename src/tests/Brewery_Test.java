package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import entity.Account;
import entity.Brewery;
import entity.Piece;
import entity.Player;

public class Brewery_Test {

	@Test
	public void test() {
		Player player = new Player(1, "Hansi", new Piece(Color.green), new Account(500));
		Brewery brewery = new Brewery(13, Color.white, 150);
		Brewery brewery2 = new Brewery(13, Color.white, 150);
		
		//Player owns brewery
		player.getAccount().setOwnedField(brewery);
		brewery.setOwner(player);
		
		assertEquals(4, brewery.getRent()); //Test 1 owned brewery
		
		player.getAccount().setOwnedField(brewery2);
		brewery2.setOwner(player);
		
		assertEquals(10, brewery.getRent());
		
		
	}

}
