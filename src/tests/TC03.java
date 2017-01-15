package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import demo.DemoGameController;
import entity.Messages;

public class TC03 {

	@Test
	public void winGame() {
		DemoGameController game = new DemoGameController();
		game.resetGame(2, 100);
		game.setTestData(new int[][]{{3,1}}); //Player 1 lander på betal indkomstskat
		game.setUserChoice(new String[][]{ {Messages.getGeneralMessages()[7]//Slå terninger
				,Messages.getGeneralMessages()[13] //OK
				,Messages.getGeneralMessages()[4] + "200"}});//Betal 200
		game.playGame();
		assertEquals(game.getPlayerArray().length, 1);
		
	}

}
