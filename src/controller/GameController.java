package controller;

import java.awt.Color;

import entity.*;

public class GameController {
	protected Board board; //An instance of the Board class
	protected Player[] players; //An array of Players

	public GameController(){
		board = new Board();
		GUIController.initializeBoard(board);		
	}

	public void resetGame(int playerAmount, int balance){
		players = new Player[playerAmount];
		Color color = null;
		
		for (int i = 0; i < players.length; i++){
			
			switch (i){
			case 0: color = Color.red; break;
			case 1: color = Color.green; break;
			case 2: color = Color.yellow; break;
			case 3: color = Color.blue; break;
			case 4: color = Color.white; break;
			case 5: color = Color.black; break;
			default: System.exit(1);
			}
			
			players[i] = new Player(i+1,Messages.getGeneralMessages()[10]+(i+1),new Piece(color), new Account(balance));

			GUIController.addPlayer(players[i]);
			
			for (Field j : board.getFields()){
				if (j instanceof Ownable){
					//remove owner of field
					((Ownable) j).setOwner(null);
					GUIController.removeFieldOwner(j.getId());
					
					//remove houses and hotels
					if (j instanceof Street){
						((Street) j).setHousesOwned(0);
						GUIController.setHouses((Street) j);
					}
				}
				
			}
			
		}
	}

	public void playGame(){
		boolean winnerFound = false;
		Player currentPlayer;

		//first player is player 1
		currentPlayer = players[0];

		while (winnerFound == false){
			
			TurnController turn = new TurnController(currentPlayer,board);
			turn.playTurn();
			
			if (currentPlayer.getAccount().getBalance() < 0){
				removePlayer(currentPlayer);
			}

			if (players.length == 1){
				winnerFound = true;
				GUIController.showMessage(currentPlayer + Messages.getGeneralMessages()[15]);
			}
			else{
				currentPlayer = defineNextPlayer(currentPlayer);
			}
		}
	}

	protected void removePlayer(Player player){
		Player[] temp;
		temp = players;

		players = new Player[temp.length-1];

		int playerCount = 0;
		for (int i = 0; i<temp.length;i++){
			if (temp[i] != player){
				players[playerCount] = temp[i];
				playerCount++;
			}
		}

		GUIController.removeAllCars(player);
		
		//puts players owned fields back on sale
		for (Ownable i : player.getAccount().getOwnedFields()){
			if(i != null){
				GUIController.removeFieldOwner(i.getId());
				i.setOwner(null);
				if (i instanceof Street){
					((Street) i).setHousesOwned(0);
					GUIController.setHouses((Street) i);
				}
			}
		}
		
	}

	protected Player defineNextPlayer(Player currentPlayer){
		Player nextPlayer;

		//current player is death
		if(currentPlayer.getAccount().getBalance() < 0){
			nextPlayer = players[currentPlayer.getID()-1];
		}
		//current player threw two equal
		else if(currentPlayer.getEqualCount() > 0){
			nextPlayer = currentPlayer;
		}
		//current player is last player in array
		else if (currentPlayer == players[players.length-1]){
			nextPlayer = players[0];
		}
		//find next player in array
		else{
			int arrayIndex = 0;
			for (int i=0;i<players.length;i++){
				if (currentPlayer == players[i]){
					arrayIndex=i;
				}
			}
			nextPlayer = players[arrayIndex+1];
		}

		return nextPlayer;
	}

}
