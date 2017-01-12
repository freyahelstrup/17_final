package demo;

import controller.GameController;
import tests.TurnController_TestClass;
import entity.Player;

public class DemoGameController extends GameController {
	int[][] testData = new int[][]{
		{5,1}, 	//Roskildevej
		{4,1}, 	//Øresund
		{3,1},	//Indkomstskat
		
		{5,1},	//Tuborg
		{6,1},	//Tuborg (leje) 4*7
		{1,1}, 	//Roskildevej (leje)
		{3,1},	//På besøg i fængsel (ryk dobbelt efter terningekast lige med hinanden)
		
		{4,1},	//Prøv lykken
		{2,1}, 	//DFDS
		{4,1}, 	//DFDS (leje)
		
		{6,2}, 	//ØK
		{6,4},	//ØK (leje)
		{4,1},	//Parkering
		
		{2,1},	//Carlsberg
		{2,1},	//Carlsberg (leje) 3*10
		{1,1},	//Prøv lykken
		{1,1},	//Grønningen
		{1,1}, 	//Fængsel
		
		{1,1}, 	//Gå i fængsel
		{6,4}, 	//Skat uden taxrate
		{1,2}, 	//Slå ikke to ens
		
		{1,1}, 	//Spiller 1 slår ud
		{1,2},	//DFDS
		{1,1},	//Start
		{2,1}, 	//Hvidovrevej
		{1,2}, 	//Spiller 3 slår ikke ud
		
		{1,3}, 	//Ikke vigtigt slag
		{1,3}, 	//Ikke vigtigt slag
		{1,3}	//Spiller 3 slår ikke ud og er tvunget til at betale 50 kr.
		
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
