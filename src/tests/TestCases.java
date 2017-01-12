package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import controller.GUIController;
import entity.Player;
import entity.Account;
import entity.Board;
import entity.Piece;

public class TestCases {

	@Test
	public void TC01() {
		Player player = new Player(1,"Spiller 1", new Piece(Color.red),new Account(1500));
		Board board = new Board();
		GUIController.initializeBoard(board);
		
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		turn.setTestData(new int[]{1,2});
		turn.playTurn();
		
		assertEquals(player.getAccount().getBalance(), 1500-60);
		assertEquals(player.getAccount().getOwnedFields()[0], board.getFields()[3]);
	}

}
