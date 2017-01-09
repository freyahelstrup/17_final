package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import entity.Account;
import entity.Fleet;
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
		
		player.getAccount().setOwnedField(dataFields[0]);
		assertEquals(25, dataFields[0].getRent());
		//Test id
		//assertEquals(6, testField.getId());
		
	}

}
