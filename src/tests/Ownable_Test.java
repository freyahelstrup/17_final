package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import entity.Account;
import entity.Brewery;
import entity.DiceCup;
import entity.Messages;
import entity.Piece;
import entity.Player;
import entity.Street;

public class Ownable_Test {

	@Test
	public void brewery() {
		DiceCup dice = new DiceCup(6,2);
		Player[] players = new Player[2];
		players[0] = new Player(1, "Hansi", new Piece(Color.green), new Account(500));
		players[1] = new Player(2, "Mansi", new Piece(Color.blue), new Account(500));
		Brewery brewery = new Brewery(13, Color.white, 150);
		
		players[0].setChoice(Messages.getGeneralMessages()[1]); //Hansi's choice is yes. They should buy the field.
		
		brewery.landOnField(players[0]); //Hansi should now own the field
		
		assertEquals(500-150, players[0].getAccount().getBalance()); //Has Hansi paid for the field?
		
		dice.getDice()[0].setValue(1);
		dice.getDice()[1].setValue(5);
		
		players[1].setLastThrow(dice);
		System.out.println(players[1].getLastThrow().getSum());
		
		brewery.landOnField(players[1]); //Mansi should now pay rent to Hansi.
		System.out.println(players[1].getAccount().getBalance());
		System.out.println(500-(4*6));
		
		assertEquals(500-(4*6), players[1].getAccount().getBalance());
		//When the balance of Mansi has been reduced appropriately, 
		//We know that Hansi owns the field and Mansi has paid rent to him
		
	}
	
	@Test
	public void otherFieldTypes(){
		//This test should function identically for Fleet and Street, so we only test for 1 of them
		Player[] players = new Player[2];
		players[0] = new Player(1, "Hansi", new Piece(Color.green), new Account(500));
		players[1] = new Player(2, "Mansi", new Piece(Color.blue), new Account(500));
		Street street = new Street(2, Color.cyan, 60, new int[]{2,10,30,90,160,250},50); //Stats identiske med rødovrevej
		
		//First test
		players[0].setChoice(Messages.getGeneralMessages()[1]); //Hansi's choice is yes. They should buy the field.
		street.landOnField(players[0]); //Hansi should now own the field
		assertEquals(500-60, players[0].getAccount().getBalance()); //Has Hansi paid for the field?
		
		//Second test
		street.landOnField(players[1]);
		assertEquals(500-2, players[1].getAccount().getBalance()); //Test Street rent without a house
		
		//Third test
		players[1].getAccount().setBalance(500);
		street.setHousesOwned(5);
		street.landOnField(players[1]);
		assertEquals(500-250, players[1].getAccount().getBalance()); //Test whether Mansi pays differently with 5 houses
		
	}

}
