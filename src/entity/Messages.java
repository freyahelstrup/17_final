package entity;

public class Messages {
	private static String[] chanceMessages = {
			"",
			"",
			"",
			"",
			"",
			"",
			"",
	};
	private static String[] fieldNames = {
			"Fæstning" 					//Field 1
			,"Guldmine"					//Field 2
			,"Stamme Lejr"				//Field 3
			,"Sejl nr. 2"				//Field 4
			,"Krater"					//Field 5
			,"Bjerg"					//Field 6
			,"Bjerghytter"			    //Field 7
			,"Kold Ørken"				//Field 8
			,"Hav Grover"				//Field 9
			,"Grotte"					//Field 10
			,"Varulvemuren"				//Field 11
			,"Kloster"					//Field 12
			,"Campingvogn"				//Field 13
			,"Sørøverskibet"			//Field 14
			,"Hullet"					//Field 15
			,"Bjergby"					//Field 16
			,"Den sydlige hovedstad"	//Field 17
			,"Palads"					//Field 18
			,"Lejesoldater"				//Field 19
			,"Tårn"						//Field 20
			,"Slot"						//Field 21
	};
	private static String[] boardMessages = {
			"Pris:",				//0	
			"Leje:", 				//1
			"Modtag:",				//2
			"Betal:",				//3
			"eller",				//4 
			"af alle ejendele",		//5
	};
	
	private static String[] generalMessages = {
	/*0*/	"Denne ejendom er ikke ejet af nogen spiller. Vil du købe den for ", 	
	/*1*/	"Ja",					
	/*2*/	"Nej",					
	/*3*/	"Du har nu to muligheder",	
	/*4*/	"Betal ",				
	/*5*/	"% af alle ejendele ",	
	/*6*/	"Vil du starte et nyt spil?",
	/*7*/	"Slå med terningerne",
	/*8*/	"Hvor mange spillere skal deltage i spillet?",
	/*9*/	"Du er landet på en anden spillers ejendom. Du skal betale ",
	/*10*/	"Spiller ",
	/*11*/	"Det er ",
	/*12*/	"'s tur.",
	/*13*/	"Du er landet på en arbejdslejr og skal slå med terningerne, for at bestemme hvor meget du skal betale i leje.",
	/*14*/	"Tillykke ",
	/*15*/	", du har vundet spillet!",
	/*16*/	" i leje.",
	/*17*/	"Du skal betale ",
	/*18*/	" til skattefar.",
	/*19*/	"Du modtager ",
	/*20*/	"Du er landet på din egen ejendom og nyder de dejlige omgivelser.",
	/*21*/	"Ejeren af denne ejendom er gået bankerot, og du slipper derfor for at betale leje.",
	/*22*/	"Leje: ",
	/*23*/	"Leje: 500, 1000, 2000, 5000",
	/*24*/	"Leje: 100*øjne*labor camps ejet",
	/*25*/  "Du har ikke nok penge til at købe dette felt.",
	};

	public static String[] getChanceMessages(){
		return chanceMessages;
	}

	public static String[] getFieldNames(){
		return fieldNames;
	}
	
	public static String[] getBoardMessages(){
		return boardMessages;
	}

	public static String[] getGeneralMessages(){
		return generalMessages;
	}
}
