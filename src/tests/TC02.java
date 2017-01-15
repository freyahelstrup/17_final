package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import controller.GUIController;
import entity.Account;
import entity.Board;
import entity.Messages;
import entity.Ownable;
import entity.Piece;
import entity.Player;
import entity.Street;

public class TC02 {

	@Test
	public void playTurn_buyHouse() {
		Player player = new Player(1,"Spiller 1", new Piece(Color.red),new Account(1500));
		Board board = new Board();
		GUIController.initializeBoard(board);
		
		//Player owns field 4
		player.getAccount().setOwnedField((Ownable) board.getFields()[3]);
		((Ownable) board.getFields()[3]).setOwner(player);

		//Player owns field 2
		player.getAccount().setOwnedField((Ownable) board.getFields()[1]);
		((Ownable) board.getFields()[1]).setOwner(player);
		
		TurnController_TestClass turn = new TurnController_TestClass(player, board);
		turn.setTestData(new int[]{1,2}); //Land på Hvidovre
		turn.setUserChoice(new String[]{
				Messages.getGeneralMessages()[21] //Køb hus
				,Messages.getFieldNames()[1] //Field 2
				,Messages.getGeneralMessages()[1] //Ja
				,Messages.getGeneralMessages()[7] //Slå med terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[13] //OK
		});
		turn.playTurn();
		
		assertEquals(1500-50, player.getAccount().getBalance());
		assertEquals(1, ((Street) board.getFields()[1]).getHousesOwned());

	}

}
