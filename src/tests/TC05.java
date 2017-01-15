package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import controller.GUIController;
import demo.DemoGameController;
import entity.Account;
import entity.Board;
import entity.Chance;
import entity.Messages;
import entity.Piece;
import entity.Player;

public class TC05 {

	@Test
	public void positionTest() {
		Player player = new Player(1,"Hansi", new Piece(Color.red),new Account(1500));
		Board board = new Board();
		GUIController.initializeBoard(board);
		
		/*
		Chance chance = new Chance(500, Color.WHITE);
		chance.setDeck(new int[]{6,6,6,6});
		*/
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		turn.setTestData(new int[]{6,1}); //Land på chance
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7] //Slå terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[4] + 200 //Betal: 200
				,Messages.getGeneralMessages()[13] //OK
		});
		turn.playTurn();
		
		assertEquals(4, player.getPiece().getPosition());
		assertEquals(1300, player.getAccount().getBalance());
	}
	
	/*
	@Test
	public void balanceTest(){
		
	}
	
	@Test
	public void legatTest(){
		
	}*/

}
