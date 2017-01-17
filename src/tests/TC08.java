package tests;

import entity.*;
import java.awt.Color;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.GUIController;
import desktop_resources.GUI;

public class TC08 {

	@Test
	public void prison_payEscape() { //the player is in prison and chooses to pay
		Player player = new Player(1,"Spiller 1", new Piece(Color.red),new Account(1500));
		Board board = new Board();
		GUIController.initializeBoard(board);
		
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		player.getPiece().setPosition(11);
		player.setPrisonCount(3);

		turn.setTestData(new int[]{1,1}); //roll two equal dice
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7], //slå med terningerne
				Messages.getGeneralMessages()[31], //"Betal 50 kr."	
				Messages.getGeneralMessages()[13], //OK
				Messages.getGeneralMessages()[2] //Nej
		});
		turn.playTurn();
		
		assertEquals(player.getAccount().getBalance(), 1500-50);
		assertEquals(player.getPrisonCount(), 0);
	} 
	
	@Test
	public void prison_diceEscape_success(){ 	//the player is in prison, chooses to throw dice and
												//rolls two equal dice
		Player player = new Player(1,"Spiller 1", new Piece(Color.red),new Account(1500));
		Board board = new Board();
		GUIController.initializeBoard(board);
		
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		player.getPiece().setPosition(11);
		player.setPrisonCount(3);
		
		turn.setTestData(new int[]{4,4}); //roll two equal dice
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7], //slå med terningerne
				Messages.getGeneralMessages()[7], //slå med terningerne
				Messages.getGeneralMessages()[13], //OK
				Messages.getGeneralMessages()[2], //Nej
		});
		turn.playTurn();
		
		assertEquals(1500, player.getAccount().getBalance());
		assertEquals(0, player.getPrisonCount());
	}
	
	@Test
	public void prison_diceEscape_fail(){	//the player is in prison, chooses to throw dice and
											//rolls two not equal dice
		Player player = new Player(1,"Spiller 1", new Piece(Color.red),new Account(1500));
		Board board = new Board();
		GUIController.initializeBoard(board);
		
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		player.getPiece().setPosition(11);
		player.setPrisonCount(3);
		
		turn.setTestData(new int[]{2,4}); //roll two non-equal dice
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[7], //slå med terningerne
				Messages.getGeneralMessages()[7] //slå med terningerne
		});
		turn.playTurn();
		
		assertEquals(1500, player.getAccount().getBalance());
		assertEquals(2, player.getPrisonCount());

		GUI.close();
	}
}
