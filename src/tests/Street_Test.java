package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import entity.Account;
import entity.Piece;
import entity.Player;
import entity.Street;

public class Street_Test {

	@Test
	public void test() {
		//Initialization of fields
		Player player = new Player(1, "Hansi", new Piece(Color.green), new Account(500));
		Street street = new Street(2, Color.cyan, 60, new int[]{2,10,30,90,160,250},50); //Stats identiske med rødovrevej
		int[] rents = new int[]{2,10,30,90,160,250};
		//Make player own the field
		player.getAccount().setOwnedField(street);
		street.setOwner(player);
		
		assertEquals(2, street.getRent()); //Check if rent without houses works
		
		for(int i=1; i<=5; i++){
			street.setHousesOwned(i);
			assertEquals(rents[i], street.getRent()); //Check if rent without houses works
		}
		
	
	}

}
