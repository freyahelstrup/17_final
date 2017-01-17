package demo;

import controller.GameController;
import tests.TurnController_TestClass;
import entity.Board;
import entity.Player;
import entity.Chance;

public class DemoGameController extends GameController {
	int[][] testData = new int[][]{
		{4,1}, 	//Øresund
		{3,1},	//Indkomstskat
		{5,3}, 	//Valby Langgade
		
		{5,2},	//Tuborg
		{1,3},	//Valby Langgade (leje)
		{1,1}, 	//Fængsel - på besøg
		{1,1}, 	//Tuborg (leje 4*4)
		{1,1}, 	//Tre ens i træk - fængsel
		
		{2,1}, 	//DFDS
		{4,5},	//Prøv lykken - rykker 3 felter tilbage til Gl. Kongevej
		{1,1}, 	//Slå to ens, ud af fængsel
		{2,1}, 	//DFDS (leje)
		
		{6,4}, 	//ØK
		{6,5},	//ØK (leje)
		{4,1},	//Parkering
		
		{2,1},	//Carlsberg
		{2,1},	//Carlsberg (leje 10*3)
		{1,1},	//Prøv lykken - Betal 75 kr for øl
		{5,5},	//Vimmelskaftet
		{5,4}, 	//Rødovrevej
		
		{1,1}, 	//Gå i fængsel
		{6,4}, 	//Ekstraordinær Statsskat
		{1,1}, 	//Hvidovre
		{1,6},  //Køb hus, Fængsel
		
		{3,4}, 	//Spiller 1 slår ikke ud
		{1,2},	//Rødovrevej (leje)
		{3,1}, 	//Gl. Kongevej (leje)

		{3,4}, 	//Spiller 1 slår ikke ud
		{1,1}, 	//Hvidovre (leje)
		{1,3}, 	//Prøv lykken - Matadorlegatet
		{1,5},	//Helle

		{3,4}, 	//Spiller 1 slår ikke ud og er tvunget til at betale 50 kr.
		
		};
	int testDataCounter = 0;
	String[][] userChoice = new String[][]{
		};
	int userChoiceCounter = 0;
	
	@Override
	public void playGame(){
		boolean winnerFound = false;
		Player currentPlayer;
		
		((Chance) board.getFields()[2]).setDeck(new int[]{6, 11, 17});

		//first player is player 1
		currentPlayer = players[0];

		while (winnerFound == false){
			
			TurnController_TestClass turn = new TurnController_TestClass(currentPlayer,board);
			if(testDataCounter<testData.length){
				turn.setTestData(testData[testDataCounter]);
				testDataCounter++;
			}
			if(userChoiceCounter<userChoice.length){
				turn.setUserChoice(userChoice[userChoiceCounter]);
				userChoiceCounter++;
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
	
	public void setTestData(int[][] data){
		this.testData = data;
	}

	public void setUserChoice(String[][] userChoice){
		this.userChoice = userChoice;
	}
	
	public Player[] getPlayerArray(){
		return players;
	}
	
	public Board getBoard(){
		return board;
	}
}
