package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import entity.Account;
import entity.Piece;
import entity.Player;
import entity.Street;

public class Street_Test {

	//Initialization of fields
	Player player = new Player(1, "Hansi", new Piece(Color.green), new Account(500));
	int[] rents = new int[]{2,10,30,90,160,250};
	Street street1 = new Street(1, Color.cyan, 60, rents,50); //Stats identiske med rødovrevej
	Street street2 = new Street(2, Color.cyan, 60, rents,50); //Stats identiske med rødovrevej
	
	
	@Test
	public void testRent() {
		//Make player own one field
		player.getAccount().setOwnedField(street1);
		street1.setOwner(player);

		assertEquals(2, street1.getRent()); //Check if rent without houses works
		
		for(int i=1; i<=5; i++){
			street1.setHousesOwned(i);
			assertEquals(rents[i], street1.getRent()); //Check if rent with houses works
		}

	}

	@Test
	public void testColorGroups() {
		//Make player own both fields
		player.getAccount().setOwnedField(street1);
		street1.setOwner(player);
		player.getAccount().setOwnedField(street2);
		street2.setOwner(player);
		
		assertEquals(4, street1.getRent());
	}

}
