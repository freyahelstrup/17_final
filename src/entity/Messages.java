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
			"Start" 					//Field 1
			,"Rødovrevej"				//Field 2
			,"Chance"					//Field 3
			,"Hvidovre"					//Field 4
			,"Betal Indkomstskat"		//Field 5
			,"Øresund A/S"				//Field 6
			,"Roskildevej"			    //Field 7
			,"Chance"					//Field 8
			,"Valby Langgade"			//Field 9
			,"Allégade"					//Field 10
			,"Fængsel"					//Field 11
			,"Frederiksberg Allé"		//Field 12
			,"Tuborg"					//Field 13
			,"Bülowsvej"				//Field 14
			,"Gl. Kongevej"				//Field 15
			,"D.F.D.S"					//Field 16
			,"Bernstorffsvej"			//Field 17
			,"Chance"					//Field 18
			,"Hellerupvej"				//Field 19
			,"Strandvej"				//Field 20
			,"Helle"					//Field 21
			,"Trianglen"				//Field 22
			,"Chance"					//Field 23
			,"Østerbrogade"				//Field 24
			,"Grønningen"				//Field 25
			,"Ø.K."						//Field 26
			,"Bredgade"					//Field 27
			,"Kgs. Nytorv"				//Field 28
			,"Carlsberg"				//Field 29
			,"Østergade"				//Field 30
			,"Gå til fængsel"			//Field 31
			,"Amagertorv"				//Field 32
			,"Vimmelskaftet"			//Field 33
			,"Chance"					//Field 34
			,"Nygade"					//Field 35
			,"D/S Bornholm"				//Field 36
			,"Chance"					//Field 37
			,"Frederiksberggade"		//Field 38
			,"Ekstraordinær Statsskat"	//Field 39
			,"Rådhuspladsen"			//Field 40
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
