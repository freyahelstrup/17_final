package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import entity.Account;
import entity.Messages;
import entity.Piece;
import entity.Player;
import entity.Tax;

public class Tax_Test {

	@Test
	public void test() {
		Player[] players = new Player[2];
		players[0] = new Player(1, "Hansi", new Piece(Color.green), new Account(500));
		Tax tax = new Tax(1,Color.black,10, 200);
		
		players[0].setChoice(""); //Player chooses taxRate
		
		tax.landOnField(players[0]); //Do land on field
		
		assertEquals(450, players[0].getAccount().getBalance()); //Assert if balance is correct
		
		players[0].setChoice(Messages.getGeneralMessages()[4] + tax.getTaxAmount()); //Choose taxAmount
		tax.landOnField(players[0]); //Do land on field
		
		assertEquals(250, players[0].getAccount().getBalance()); //Assert if balance is correct
		
		
		
	}

}
