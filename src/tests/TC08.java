package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import tests.TurnController_TestClass;

import entity.Board;
import entity.Account;
import entity.GoToPrison;
import entity.Messages;
import entity.Piece;
import entity.Player;

public class TC08 {

	@Test
	public void goToPrisonField(){
		
		Player[] players = new Player[2];
		players[0] = new Player(1, "Thief", new Piece(Color.black), new Account(500));
		players[1] = new Player(2, "Sonic", new Piece(Color.blue), new Account(500));
		
		GoToPrison prison = new GoToPrison(31, Color.white);

		prison.landOnField(players[0]);
		
		assertEquals(3, players[0].getPrisonCount());
		assertEquals(11, players[0].getPiece().getPosition());
		
		Board board = new Board();
		TurnController_TestClass controller = new TurnController_TestClass(players[1],board);

		players[1].setEqualCount(2);
		
		int[] data = {1,1};
		controller.setTestData(data);
		controller.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7] //Slå terninger
				,Messages.getGeneralMessages()[13] //OK
		});
				
		controller.playTurn();
		
		assertEquals(3, players[1].getPrisonCount());
		assertEquals(11, players[1].getPiece().getPosition());
		
	}
	
}
