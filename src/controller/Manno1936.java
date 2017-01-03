package controller;

import desktop_resources.GUI;
import entity.*;

public class Manno1936 {

	public static void main(String[] args) {
		GameController game = new GameController(); //Initialize game
		while (GUI.getUserButtonPressed(Messages.getGeneralMessages()[6] //do you want to create new game?
				, Messages.getGeneralMessages()[1] //yes
						, Messages.getGeneralMessages()[2] //no
				) == Messages.getGeneralMessages()[1] //user chooses yes
				){
			int playerAmount = Integer.parseInt(GUIController.getUserChoice("Vælg","3","4","5","6"));
			//Maybe possibility to name players
			game.resetGame(playerAmount, 1500); //Reset the game with new amount of players and set start balance
			game.playGame();
		}
		GUI.close();
	}

}
