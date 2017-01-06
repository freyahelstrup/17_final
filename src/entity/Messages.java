package entity;

public class Messages {
	private static String[] chanceMessages = {
				// Cards resulting in new position of piece
			/*0*/	"Ryk brikken frem til det nærmeste dampskibsselskab og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken.", // Two of these
			/*1*/	"Tag med Øresundsbåden - Flyt brikken frem, og hvis De passerer >>Start<<, indkassér kr. 200,00.",
			/*2*/	"Ryk frem til Frederiksberg Allé. Hvis De passerer >>Start<<, indkassér kr. 200,00.",
			/*3*/	"Ryk frem til Grønningen. Hvis De passerer >>Start<<, indkassér da kr. 200,00.",
			/*4*/	"Tag ind på Rådhuspladsen.",
			/*5*/	"Ryk frem til >>Start<<.",
			/*6*/	"Ryk tre felter tilbage.", // Two of these
				// Property charges
			/*7*/	"Ejendomsskatterne er steget, ekstraudgifterne er: kr. 50,00 pr. hus, kr. 125,00 pr. hotel.",
			/*8*/	"Kul- og kokspriserne er steget, og De skal betale: kr. 25,00 pr. hus, kr. 125,00 pr. hotel.",
				// Expenses
			/*9*/	"De har kørt frem for >>Fuld Stop<<. Betal kr. 100,00 i bøde.",
			/*10*/	"De har anskaffet et nyt dæk til Deres vogn. Indbetal kr. 100,00.",
			/*11*/	"Betal kr. 75,00 for modtagne 2 kasser øl.",
			/*12*/	"De har måttet vedtage en parkeringsbøde. Betal kr. 20,00 til banken.",
			/*13*/	"Betal for vognvask og smøring kr. 10,00.",
			/*14*/	"De har været en tur i udlandet og haft for mange cigaretter med hjem. - Betal told kr. 20,00.",
				// Prison
			/*15*/	"Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer >>Start<<, indkasserer De ikke kr. 200,00.", // Two of these
				// Prison mercy
			/*16*/	"I anledning af Kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det.", // Two of these
				// For the needy
			/*17*/	"De modtager >>Matador-legatet for værdig trængende<<, stort kr. 2000,00. Ved værdig trængende forstås, at Deres formue, d.v.s. Deres kontante penge + skøder + bygninger, ikke overstiger kr. 750,00.",
				// Bonuses
			/*18*/	"Deres præmieobigation er kommet ud. De modtager kr. 100,00 af banken.",
			/*19*/	"Værdien af egen avl fra nyttehaven udgør kr. 200,00, som De modtager af banken.",
			/*20*/	"Efter auktionen på Assistenhuset, hvor De havde pantsat Deres tøj, modtager De ekstra kr. 108,00.",
			/*21*/	"De har rettidigt afleveret Deres abonnementskort. Depositum kr. 1,00 udbetales Dem af banken.",
			/*22*/	"Modtag udbytte af Deres aktier: kr. 50,00.",
			/*23*/	"Manufakturvarerne er blevet billigere og bedre, herved sparer De kr. 50,00, som De modtager af banken.",
			/*24*/	"Kommunen har eftergivet et kvartals skat, hæv i banken til en glad aften kr. 150,00.",
			/*25*/	"De har solgt Deres gamle klude. Modtag kr. 20,00.",
			/*26*/	"Grundet på dyrtiden har De fået gageforhøjelse. Modtag kr 25,00.",
				// Money collector
			/*27*/	"De har lagt penge ud til sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver medspiller kr. 25,00."
	};
	private static String[] fieldNames = {
			"Start" 					//Field 1
			,"Rødovrevej"				//Field 2
			,"Prøv lykken"				//Field 3
			,"Hvidovre"					//Field 4
			,"Betal Indkomstskat"		//Field 5
			,"Øresund A/S"				//Field 6
			,"Roskildevej"			    //Field 7
			,"Prøv lykken"				//Field 8
			,"Valby Langgade"			//Field 9
			,"Allégade"					//Field 10
			,"Fængsel"					//Field 11
			,"Frederiksberg Allé"		//Field 12
			,"Tuborg"					//Field 13
			,"Bülowsvej"				//Field 14
			,"Gl. Kongevej"				//Field 15
			,"D.F.D.S"					//Field 16
			,"Bernstorffsvej"			//Field 17
			,"Prøv lykken"				//Field 18
			,"Hellerupvej"				//Field 19
			,"Strandvej"				//Field 20
			,"Helle"					//Field 21
			,"Trianglen"				//Field 22
			,"Prøv lykken"				//Field 23
			,"Østerbrogade"				//Field 24
			,"Grønningen"				//Field 25
			,"Ø.K."						//Field 26
			,"Bredgade"					//Field 27
			,"Kgs. Nytorv"				//Field 28
			,"Carlsberg"				//Field 29
			,"Østergade"				//Field 30
			,"De sættes i fængsel"		//Field 31
			,"Amagertorv"				//Field 32
			,"Vimmelskaftet"			//Field 33
			,"Prøv lykken"				//Field 34
			,"Nygade"					//Field 35
			,"D/S Bornholm"				//Field 36
			,"Prøv lykken"				//Field 37
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
			/*13*/	"OK",
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
			/*26*/ 	"De er landet på ",
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
