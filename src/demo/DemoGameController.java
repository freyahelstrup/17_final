package demo;

import controller.GUIController;
import controller.GameController;
import tests.TurnController_TestClass;
import entity.Board;
import entity.Player;
import entity.Chance;
import entity.Messages;

public class DemoGameController extends GameController {
	int[][] testData = new int[][]{
		
		{4,1}, 	//Øresund
		{1,1},	//Prøv lykken - betal 100
		{1,1},	//Indkomstskat
		{1,2},	//Prøv lykken - betal 100
		{3,3}, 	//Roskildevej
		{1,1}, 	//Valby Langgade
		{5,4}, 	//Prøv lykken - modtag 200
		
		{3,4}, //Tuborg
		{1,4}, //Tuborg (leje)
		{1,4}, //Prøv lykken - modtag 200
		
		{1,2}, //D.F.D.S
		{1,2}, //D.F.D.S (leje)
		{5,6}, //Prøv lykken - modtag 200

		{5,5}, //Ø.K.
		{1,2}, //Carlsberg
		{5,5}, //Ø.K. (leje)
		{1,2}, //Carlsberg (leje)
		{2,2}, //Frederiksberggade
		{1,1}, //Rådhuspladsen
		{1,2}, //Prøv lykken - modtag 200

		{1,6}, //D.S. Bornholm
		{1,6}, //D.S. Bornholm (leje)
		{4,1}, //Prøv lykken - modtag 200
		
		/*We begin pressing buttons*/
		
		{3,6},	//Indkomstskat
		{4,6}, 	//Øresund
		{1,1}, 	//Allégade
		{1,2}, 	//Tuborg (leje 10*3)
		
		{5,3},	//Tuborg
		{1,3},	//Allégade (leje)
		{5,5}, 	//Prøv lykken - modtag 200
		{2,2}, 	//Carlsberg (leje 10*6)
		{3,3}, 	//Tre ens i træk - fængsel
		
		{2,3}, 	//Prøv lykken - gå i fængsel
		{3,5},	//Prøv lykken - betal 100
		{1,1}, 	//Slå to ens, ud af fængsel
		{1,3}, 	//Bernstorffsvej
		
		{6,4}, 	//Spiller 1 slår ikke ud
		{1,4},	//Prøv lykken - Matadorlegatet
		{3,1},	//Parkering
		
		{2,1},	//Spiller 1 slår ikke ud
		{2,2},	//Bredgade
		{2,1},	//Østergade
		{1,1},	//Prøv lykken - Betal 75 kr for øl
		{5,5},	//Vimmelskaftet
		{5,4}, 	//Rødovrevej
		
		{4,3}, 	//Spiller 1 slår ud af fængsel, Prøv lykken - ryk til Rådhuspladsen, spiller 1 dør
		{6,3}, 	//Ekstraordinær Statsskat
		{1,1}, 	//Køb huse, Hvidovre
		{1,1},	//Øresund A/S
		{1,4},	//Fængsel, på besøg
		
		{6,2},	//Roskildevej (leje)
		{1,2},	//Bülowsvej
		
		{1,1},	//Valby Langgade (leje)
		{3,6},	//Prøv lykken - ryk til Rådhuspladsen
		
	};
	int testDataCounter = 0;
	String[][] userChoice = new String[][]{
		{//Spiller 1, Køb Øresund
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[1],//Ja, køb felt
		},
		{//Spiller 2, Prøv lykken
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 2, Indkomstskat
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[4] + 10 + Messages.getGeneralMessages()[5], //Betal 10%
		},
		{//Spiller 2, Prøv lykken
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 3, Køb Roskildevej
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[1],//Ja, køb felt
		},
		{//Spiller 3, Køb Valby Langgade
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[1],//Ja, køb felt
		},
		{//Spiller 3, Prøv lykken
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 1, Køb Tuborg
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[1],//Ja, køb felt
		},
		{//Spiller 2, Tuborg
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 3, Prøv lykken
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 1, Køb D.F.D.S
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[1],//Ja, køb felt
		},
		{//Spiller 2, D.F.D.S
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 3, Prøv lykken
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 1, Køb Ø.K.
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[1],//Ja, køb felt
		},
		{//Spiller 1, Køb Carlsberg
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[1],//Ja, køb felt
		},
		{//Spiller 2, Ø.K.
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 2, Carlsberg
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 3, Køb Frederiksberggade
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[1],//Ja, køb felt
		},
		{//Spiller 3, Køb Rådhuspladsen
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[1],//Ja, køb felt
		},
		{//Spiller 3, Køb huse, land på Prøv lykken
/*1*/		Messages.getGeneralMessages()[21],//Køb hus eller hotel
			Messages.getFieldNames()[37],//Frederiksberggade
			Messages.getGeneralMessages()[1],//Ja, køb hus
			Messages.getGeneralMessages()[21],//Køb hus eller hotel
			Messages.getFieldNames()[39],//Rådhuspladsen
			Messages.getGeneralMessages()[1],//Ja, køb hus
/*2*/		Messages.getGeneralMessages()[21],//Køb hus eller hotel
			Messages.getFieldNames()[37],//Frederiksberggade
			Messages.getGeneralMessages()[1],//Ja, køb hus
			Messages.getGeneralMessages()[21],//Køb hus eller hotel
			Messages.getFieldNames()[39],//Rådhuspladsen
			Messages.getGeneralMessages()[1],//Ja, køb hus
/*3*/		Messages.getGeneralMessages()[21],//Køb hus eller hotel
			Messages.getFieldNames()[37],//Frederiksberggade
			Messages.getGeneralMessages()[1],//Ja, køb hus
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 1, Køb D.S. Bornholm
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[1],//Ja, køb felt
		},
		{//Spiller 2, D.S. Bornholm
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
		{//Spiller 3, Køb huse, land på Prøv lykken
/*3*/		Messages.getGeneralMessages()[21],//Køb hus eller hotel
			Messages.getFieldNames()[39],//Rådhuspladsen
			Messages.getGeneralMessages()[1],//Ja, køb hus
/*4*/		Messages.getGeneralMessages()[21],//Køb hus eller hotel
			Messages.getFieldNames()[37],//Frederiksberggade
			Messages.getGeneralMessages()[1],//Ja, køb hus
			Messages.getGeneralMessages()[7],//Slå med terningerne
			Messages.getGeneralMessages()[13],//OK
			Messages.getGeneralMessages()[13],//OK
		},
	};
	int userChoiceCounter = 0;
	
	@Override
	public void playGame(){
		boolean winnerFound = false;
		Player currentPlayer;
		
		((Chance) board.getFields()[2]).setDeck(new int[]{9, 9, 19, 19, 19, 19, 19, 19, 15, 9, 17, 11, 4, 4});

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
				GUIController.showMessage(players[0].getName() + Messages.getGeneralMessages()[15]);
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
