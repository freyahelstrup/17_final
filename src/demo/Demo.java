package demo;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.GUIController;
import desktop_resources.GUI;
import entity.Messages;

public class Demo {

	@Test
	public void test() {
		DemoGameController game = new DemoGameController(); //Initialize game
		
		while (GUIController.getUserButtonPressed(Messages.getGeneralMessages()[6] //do you want to create new game?
				, Messages.getGeneralMessages()[1] //yes
						, Messages.getGeneralMessages()[2] //no
				).equals(Messages.getGeneralMessages()[1]) //user chooses yes
			){
			int playerAmount = Integer.parseInt(GUIController.getUserChoice(Messages.getGeneralMessages()[8],"3","4","5","6"));
			game.resetGame(playerAmount, 1500); //Reset the game with new amount of players and set start balance
			game.playGame();
			GUI.close();
		}
		GUI.close();
		System.exit(0);
	}

}
