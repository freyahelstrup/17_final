package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import controller.GUIController;
import desktop_resources.GUI;
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
		
		Chance chance = new Chance(500, Color.WHITE);
		chance.setDeck(new int[]{6,6,6,6});
		
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		turn.setTestData(new int[]{6,1}); //Land på chance
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7] //Slå terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[4] + 200 //Betal: 200
		});
		turn.playTurn();
		
		assertEquals(5, player.getPiece().getPosition());
		assertEquals(1300, player.getAccount().getBalance());

		GUI.close();
	}
	
	
	@Test
	public void balanceTest(){
		Player player = new Player(1,"Hansi", new Piece(Color.red),new Account(1500));
		Board board = new Board();
		GUIController.initializeBoard(board);
		
		Chance chance = new Chance(500, Color.WHITE);
		chance.setDeck(new int[]{10});
		
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		turn.setTestData(new int[]{6,1}); //Land på chance
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7] //Slå terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[13] //OK
		});
		turn.playTurn();
		
		assertEquals(1400, player.getAccount().getBalance());

		GUI.close();
	}
	
	@Test
	public void legatTest_750(){
		Player player = new Player(1,"Hansi", new Piece(Color.red),new Account(750));
		Board board = new Board();
		GUIController.initializeBoard(board);
		
		Chance chance = new Chance(500, Color.WHITE);
		chance.setDeck(new int[]{17});
		
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		turn.setTestData(new int[]{6,1}); //Land på chance
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7] //Slå terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[13] //OK
		});
		turn.playTurn();
		
		assertEquals(2750, player.getAccount().getBalance());

		GUI.close();
	}

	@Test
	public void legatTest_749(){
		Player player = new Player(1,"Hansi", new Piece(Color.red),new Account(749));
		Board board = new Board();
		GUIController.initializeBoard(board);
		
		Chance chance = new Chance(500, Color.WHITE);
		chance.setDeck(new int[]{17});
		
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		turn.setTestData(new int[]{6,1}); //Land på chance
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7] //Slå terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[13] //OK
		});
		turn.playTurn();
		
		assertEquals(2749, player.getAccount().getBalance());

		GUI.close();
	}

	@Test
	public void legatTest_751(){
		Player player = new Player(1,"Hansi", new Piece(Color.red),new Account(751));
		Board board = new Board();
		GUIController.initializeBoard(board);
		
		Chance chance = new Chance(500, Color.WHITE);
		chance.setDeck(new int[]{17});
		
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		turn.setTestData(new int[]{6,1}); //Land på chance
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7] //Slå terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[13] //OK
		});
		turn.playTurn();
		
		assertEquals(751, player.getAccount().getBalance());

		GUI.close();
	}

}
