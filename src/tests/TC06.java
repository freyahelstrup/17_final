package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import controller.GUIController;
import desktop_resources.GUI;
import entity.Account;
import entity.Board;
import entity.Messages;
import entity.Ownable;
import entity.Piece;
import entity.Player;
import entity.Street;

public class TC06 {

	@Test
	public void economy_noHouse() {
		Player player1 = new Player(1,"Spiller 1", new Piece(Color.red),new Account(1500));
		Player player2 = new Player(2,"Spiller 2", new Piece(Color.blue),new Account(1500));
		Board board = new Board();
		GUIController.initializeBoard(board);

		//Player 2 owns field 4
		player2.getAccount().setOwnedField((Ownable) board.getFields()[3]);
		((Ownable) board.getFields()[3]).setOwner(player2);

		TurnController_TestClass turn = new TurnController_TestClass(player1, board);
		turn.setTestData(new int[]{1,2}); //Land på Hvidovre
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7] //Slå terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[13] //OK
		});
		turn.playTurn();
		
		assertEquals(player1.getAccount().getBalance(), 1500-4);
		assertEquals(player2.getAccount().getBalance(), 1500+4);

		GUI.close();
	}

	@Test
	public void economy_4Houses() {
		Player player1 = new Player(1,"Spiller 1", new Piece(Color.red),new Account(1500));
		Player player2 = new Player(1,"Spiller 2", new Piece(Color.blue),new Account(1500));
		Board board = new Board();
		GUIController.initializeBoard(board);

		//Player 2 owns field 4
		player2.getAccount().setOwnedField((Ownable) board.getFields()[3]);
		((Ownable) board.getFields()[3]).setOwner(player2);
		
		//Field 4 has 4 houses
		((Street) board.getFields()[3]).setHousesOwned(4);

		TurnController_TestClass turn = new TurnController_TestClass(player1, board);
		turn.setTestData(new int[]{1,2}); //Land på Hvidovre
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7] //Slå terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[13] //OK
		});
		turn.playTurn();
		
		assertEquals(player1.getAccount().getBalance(), 1500-320);
		assertEquals(player2.getAccount().getBalance(), 1500+320);

		GUI.close();
	}

	@Test
	public void economy_AllFields() {
		Player player1 = new Player(1,"Spiller 1", new Piece(Color.red),new Account(1500));
		Player player2 = new Player(1,"Spiller 2", new Piece(Color.blue),new Account(1500));
		Board board = new Board();
		GUIController.initializeBoard(board);

		//Player 2 owns field 4
		player2.getAccount().setOwnedField((Ownable) board.getFields()[3]);
		((Ownable) board.getFields()[3]).setOwner(player2);

		//Player 2 owns field 2
		player2.getAccount().setOwnedField((Ownable) board.getFields()[1]);
		((Ownable) board.getFields()[1]).setOwner(player2);
		
		TurnController_TestClass turn = new TurnController_TestClass(player1, board);
		turn.setTestData(new int[]{1,2}); //Land på Hvidovre
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7] //Slå terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[13] //OK
		});
		turn.playTurn();
		
		assertEquals(player1.getAccount().getBalance(), 1500-8);
		assertEquals(player2.getAccount().getBalance(), 1500+8);

		GUI.close();
		
	}

}
