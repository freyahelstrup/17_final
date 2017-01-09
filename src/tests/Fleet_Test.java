package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import entity.Account;
import entity.Fleet;
import entity.Ownable;
import entity.Field;
import entity.Messages;
import entity.Piece;
import entity.Player;
import junit.framework.Assert;

public class Fleet_Test {

	@Test
	public void test() {
		
		Player player = new Player(1, "Hansi", new Piece(Color.green), new Account(500));
		Fleet[] dataFields = new Fleet[4];
		for(int i=0; i<dataFields.length; i++){
			dataFields[i]=new Fleet(i+1, Color.white, 200);
		}
		
		//Both of the following commands are necessary for making a player an owner of a field
		player.getAccount().setOwnedField(dataFields[0]);
		dataFields[0].setOwner(player);
		
		//Test 1 owned fleet
		//System.out.println(dataFields[0].getRentsArray()[0]);
		assertEquals(25, dataFields[0].getRent());
		
		//Test 2 owned fleets
		player.getAccount().setOwnedField(dataFields[1]);
		dataFields[1].setOwner(player);
		
		//System.out.println(dataFields[1].getRentsArray()[1]);
		assertEquals(50, dataFields[0].getRent());
		
		//Test 3 owned fleets
		player.getAccount().setOwnedField(dataFields[2]);
		dataFields[2].setOwner(player);
		
		//System.out.println(dataFields[2].getRentsArray()[2]);
		assertEquals(100, dataFields[0].getRent());
		
		//Test 4 owned fleets
		player.getAccount().setOwnedField(dataFields[3]);
		dataFields[3].setOwner(player);
		
		//System.out.println(dataFields[1].getRentsArray()[3]);
		assertEquals(200, dataFields[0].getRent());
		
	}

}
