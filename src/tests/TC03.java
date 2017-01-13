package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import demo.DemoGameController;

public class TC03 {

	@Test
	public void winGame() {
		DemoGameController game = new DemoGameController();
		game.resetGame(2, 100);
		game.setTestData(new int[][]{{3,1}}); //Player 1 lander på betal indkomstskat
		game.playGame();
		assertEquals(game.getPlayerArray().length, 1);
		
	}

}
