package entity;

import java.awt.Color;

public class Chance extends Field {

	public Chance(int id, Color color) {
		super(id, color);
		shuffleDeck();
	} 
	//All cardtypes are identified by an integer
	private static int[] chanceCards = {
				5,		//"Tag ind på Rådhuspladsen."
				8,		//"Ejendomsskatterne er steget, ekstraudgifterne er: kr. 50,00 pr. hus, kr. 125,00 pr. hotel."
				10,		//"De har kørt frem for >>Fuld Stop<<. Betal kr. 100,00 i bøde."
				13,		//"Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer >>Start<<, indkasserer De ikke kr. 200,00."
				17		//"I anledning af Kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det."
	};

	@Override
	public void landOnField(Player player) {
		switch(chanceCards[0]){
		case 5:	player.getPiece().setPosition(40);
				break;
		case 8:	player.getPiece().setPosition(40);
				break;
		case 10: player.getPiece().setPosition(40);
				break;
		case 17: player.getPiece().setPosition(40);
				break;
		}
	}

	public void addCard(int cardID){ //adds a card to the bottom of the deck
		int[] tempDeck = new int[chanceCards.length+1];
		for(int i = 0; i < tempDeck.length; i++){
			if(i < tempDeck.length){
				tempDeck[i] = chanceCards[i];
			}
			else{
				tempDeck[i] = cardID;
			}
		}
		chanceCards = tempDeck;
	}
	
	private void removeCard(){  //removes the top card of the deck
		int[] tempDeck = new int[chanceCards.length-1];
		for(int i = 1; i < chanceCards.length; i++){
			tempDeck[i-1] = chanceCards[i];
			}
		chanceCards = tempDeck;
		}
	
	private void moveCardtoBottom(){  //moves the top card of the deck to the bottom
		addCard(chanceCards[0]);
		removeCard();
		}
	
	private void shuffleDeck(){
		for(int i = 0; i < chanceCards.length; i++){
			int ranNum = ( (int) (Math.random()*(chanceCards.length)));
			int currentCard = chanceCards[i];
			chanceCards[i] = chanceCards[ranNum];
			chanceCards[ranNum] = currentCard;
		}
	}
	
}
