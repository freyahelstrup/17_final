package demo;

import controller.GUIController;
import controller.GameController;
import controller.TurnController;
import tests.TurnController_TestClass;
import entity.Board;
import entity.Player;

public class DemoGameController extends GameController {
	int[][] testData = new int[][]{
		{2, 1}, 
		{3, 1}, 
		{4, 1}
		};
	int testDataCounter = 0;
	
	@Override
	public void playGame(){
		boolean winnerFound = false;
		Player currentPlayer;

		//first player is player 1
		currentPlayer = players[0];

		while (winnerFound == false){
			
			TurnController_TestClass turn = new TurnController_TestClass(currentPlayer,board);
			if(testDataCounter<testData.length){
				turn.setTestData(testData[testDataCounter]);
				testDataCounter++;
			}
			turn.playTurn();
			
			if (currentPlayer.getAccount().getBalance() < 0){
				removePlayer(currentPlayer);
			}

			if (players.length == 1){
				winnerFound = true;
			}
			else{
				currentPlayer = defineNextPlayer(currentPlayer);
			}
		}
	}
}
