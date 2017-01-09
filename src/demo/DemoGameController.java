package demo;

import controller.GUIController;
import controller.GameController;
import controller.TurnController;
import tests.TurnController_TestClass;
import entity.Board;
import entity.Player;

public class DemoGameController extends GameController {
	int[] testData = new int[]{1, 2, 3, 1, 2, 3};
	int testDataCounter = 0;
	
	@Override
	public void playGame(){
		boolean winnerFound = false;
		Player currentPlayer;

		//first player is player 1
		currentPlayer = players[0];

		while (winnerFound == false){
			
			TurnController_TestClass turn = new TurnController_TestClass(currentPlayer,board);
			turn.setTestData(testData[testDataCounter]); //Set turn result equal to current data point
			turn.playTurn();
			testDataCounter++; //Increment dataset counter
			
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
