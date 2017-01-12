package entity;

import java.awt.Color;

public class Chance extends Field {

	public Chance(int id, Color color) {
		super(id, color);
		shuffleDeck();
	} 
	//All cardtypes are identified by an integer
	private static int[] chanceCards = {
			
//not implemented//	0: "Ryk brikken frem til det nærmeste dampskibsselskab og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken.", // Two of these
			1, 	//	"Tag med Øresundsbåden - Flyt brikken frem, og hvis De passerer >>Start<<, indkassér kr. 200,00.",
			2, 	//	"Ryk frem til Frederiksberg Allé. Hvis De passerer >>Start<<, indkassér kr. 200,00.",
			3, 	//	"Ryk frem til Grønningen. Hvis De passerer >>Start<<, indkassér da kr. 200,00.",
			4, 	//	"Tag ind på Rådhuspladsen.",
			5, 	//	"Ryk frem til >>Start<<.",
			6, 6, 	//	"Ryk tre felter tilbage.", // Two of these
			// Property charges
//not implemented//	7: "Ejendomsskatterne er steget, ekstraudgifterne er: kr. 50,00 pr. hus, kr. 125,00 pr. hotel.",
//not implemented//	8: "Kul- og kokspriserne er steget, og De skal betale: kr. 25,00 pr. hus, kr. 125,00 pr. hotel.",
			// Expenses
			9,  //	"De har kørt frem for >>Fuld Stop<<. Betal kr. 100,00 i bøde.",
			10, //	"De har anskaffet et nyt dæk til Deres vogn. Indbetal kr. 100,00.",
			11, //	"Betal kr. 75,00 for modtagne 2 kasser øl.",
			12, //	"De har måttet vedtage en parkeringsbøde. Betal kr. 20,00 til banken.",
			13, //	"Betal for vognvask og smøring kr. 10,00.",
			14, //	"De har været en tur i udlandet og haft for mange cigaretter med hjem. - Betal told kr. 20,00.",
			// Prison
			15, 15,//	"Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer >>Start<<, indkasserer De ikke kr. 200,00.", // Two of these
			// Prison mercy
//not implemented//	16: "I anledning af Kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det.", // Two of these
			// For the needy
			17, //	"De modtager >>Matador-legatet for værdig trængende<<, stort kr. 2000,00. Ved værdig trængende forstås, at Deres formue, d.v.s. Deres kontante penge + skøder + bygninger, ikke overstiger kr. 750,00.",
			// Bonuses
			18, //	"Deres præmieobigation er kommet ud. De modtager kr. 100,00 af banken.",
			19, //	"Værdien af egen avl fra nyttehaven udgør kr. 200,00, som De modtager af banken.",
			20, //	"Efter auktionen på Assistenhuset, hvor De havde pantsat Deres tøj, modtager De ekstra kr. 108,00.",
			21, //	"De har rettidigt afleveret Deres abonnementskort. Depositum kr. 1,00 udbetales Dem af banken.",
			22, //	"Modtag udbytte af Deres aktier: kr. 50,00.",
			23, //	"Manufakturvarerne er blevet billigere og bedre, herved sparer De kr. 50,00, som De modtager af banken.",
			24, //	"Kommunen har eftergivet et kvartals skat, hæv i banken til en glad aften kr. 150,00.",
			25, //	"De har solgt Deres gamle klude. Modtag kr. 20,00.",
			26, //	"Grundet på dyrtiden har De fået gageforhøjelse. Modtag kr 25,00.",
			// Money collector
//not implemented//	27: "De har lagt penge ud til sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver medspiller kr. 25,00."
	};

	@Override
	public void landOnField(Player player) {
		switch(chanceCards[0]){
//not implemented		case 0:	
//				break;
		case 1:	passedStart(player, 6);
				player.getPiece().setPosition(6);
				break;
		case 2: passedStart(player, 12);
				player.getPiece().setPosition(12);
				break;
		case 3: passedStart(player, 25);
				player.getPiece().setPosition(25);
				break;
		case 4: player.getPiece().setPosition(40);
				break;
		case 5:	passedStart(player, 1);
				player.getPiece().setPosition(1);
				break;
		case 6:	player.getPiece().setPosition(player.getPiece().getPosition()-3); //ryk 3 felter tilbage
				break;
//not implemented		case 7: 
//				break;
//not implemented		case 8: 
//				break;
		case 9: player.getAccount().setBalance(player.getAccount().getBalance()-100);
				break;
		case 10:player.getAccount().setBalance(player.getAccount().getBalance()-100);
				break;
		case 11:player.getAccount().setBalance(player.getAccount().getBalance()-75);
				break;
		case 12:player.getAccount().setBalance(player.getAccount().getBalance()-20);
				break;
		case 13:player.getAccount().setBalance(player.getAccount().getBalance()-10);
				break;
		case 14:player.getAccount().setBalance(player.getAccount().getBalance()-20);
				break;
		case 15:player.getPiece().setPosition(11); //move player to prison
				player.setPrisonCount(3);		
				break;
//not implemented		case 16:
//				break;
		case 17:if(player.getAccount().calculateAssets() <= 750){		
					player.getAccount().setBalance(player.getAccount().getBalance()+2000);
					}
				break;
		case 18:player.getAccount().setBalance(player.getAccount().getBalance()+100);
				break;
		case 19:player.getAccount().setBalance(player.getAccount().getBalance()+200);
				break;
		case 20:player.getAccount().setBalance(player.getAccount().getBalance()+108);
				break;
		case 21:player.getAccount().setBalance(player.getAccount().getBalance()+1);
				break;
		case 22:player.getAccount().setBalance(player.getAccount().getBalance()+50);
				break;
		case 23:player.getAccount().setBalance(player.getAccount().getBalance()+50);
				break;
		case 24:player.getAccount().setBalance(player.getAccount().getBalance()+150);
				break;
		case 25:player.getAccount().setBalance(player.getAccount().getBalance()+20);
				break;
		case 26:player.getAccount().setBalance(player.getAccount().getBalance()+25);
				break;
//not implemented		case 27:
//				break;
		}
//		if(chanceCards[0] == 16){  //When a jailFree card is drawn, it should be removed from the deck
//			removeCard();
//		}
//		else{
			moveCardToBottom();
//		}
	
	}

	public void addCard(int cardID){ //adds a card to the bottom of the deck
		int[] tempDeck = new int[chanceCards.length+1];
		for(int i = 0; i < tempDeck.length; i++){
			if(i < tempDeck.length-1){
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
	
	private void moveCardToBottom(){  //moves the top card of the deck to the bottom
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
	private void passedStart(Player player, int newPosition){
		int oldPosition = player.getPiece().getPosition();
		if(oldPosition > newPosition){												//did the player pass start?	
			player.getAccount().setBalance(player.getAccount().getBalance()+200); 	//The player receives 200
		}
	}
	public static int getCardID(){
		return chanceCards[0];
	
	}
	public static boolean isMoveCard(){
		boolean isMoveCard = false;
		switch(chanceCards[chanceCards.length-1]){
		case 1: isMoveCard = true;
				break;
		case 2: isMoveCard = true;
				break;
		case 3: isMoveCard = true;
				break;
		case 4: isMoveCard = true;
				break;
		case 5: isMoveCard = true;
				break;
		case 6: isMoveCard = true;
				break;
		case 15: isMoveCard = true;
				break;
		default: isMoveCard = false;
		}
		return isMoveCard;
	}
	
}
