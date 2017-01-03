package entity;

import java.awt.Color;

public class Board {
	private Field[] fields;

	public Board(){

		fields = new Field[40];
		
/**/		fields[0] = new Street(1, Color.blue, 60, 2);	//Start
		fields[1] = new Street(2, Color.blue, 60, 2);		//Rødovrevej
/**/		fields[2] = new Street(3, Color.blue, 60, 2);	//Chance
		fields[3] = new Street(4, Color.blue, 60, 4);		//Hvidovre
		fields[4] = new Tax(5, Color.lightGray, 10, 200); 	//Betal Indkomstskat
		fields[5] = new Fleet(6, Color.lightGray, 200); 	//Øresund A/S
		fields[6] = new Street(7, Color.pink, 100, 6);		//Roskildevej
/**/		fields[7] = new Street(8, Color.blue, 60, 2);	//Chance
		fields[8] = new Street(9, Color.pink, 100, 6);		//Valby Langgade
		fields[9] = new Street(10, Color.pink, 120, 8);		//Allégade
/**/		fields[10] = new Street(11, Color.blue, 60, 2);	//Fængsel
		fields[11] = new Street(12, Color.green, 140, 10);	//Frederiksberg Allé
		fields[12] = new Brewery(13, Color.lightGray, 150);	//Tuborg
		fields[13] = new Street(14, Color.green, 140, 10);	//Bülowsvej
		fields[14] = new Street(15, Color.green, 160, 12);	//Gl. Kongevej
		fields[15] = new Fleet(16, Color.lightGray, 200);	//D.F.D.S
		fields[16] = new Street(17, Color.gray, 180, 14);	//Bernstorffsvej
/**/		fields[17] = new Street(18, Color.blue, 60, 2);	//Chance
		fields[18] = new Street(19, Color.gray, 180, 14);	//Hellerupvej
		fields[19] = new Street(20, Color.gray, 200, 16);	//Strandvej
/**/		fields[20] = new Street(21, Color.blue, 60, 2);	//Helle
		fields[21] = new Street(22, Color.red, 220, 18);		//Trianglen
/**/		fields[22] = new Street(23, Color.blue, 60, 2);	//Chance
		fields[23] = new Street(24, Color.red, 220, 18);		//Østerbrogade
		fields[24] = new Street(25, Color.red, 240, 20);		//Grønningen
		fields[25] = new Fleet(26, Color.lightGray, 200); 	//Ø.K.
		fields[26] = new Street(27, Color.white, 260, 22);	//Bredgade
		fields[27] = new Street(28, Color.white, 260, 22);	//Kongens Nytorv
		fields[28] = new Brewery(29, Color.lightGray, 150);	//Carlsberg
		fields[29] = new Street(30, Color.white, 280, 22);	//Østergade
/**/		fields[30] = new Street(31, Color.blue, 60, 2);	//Politi (GoToPrison)
		fields[31] = new Street(32, Color.yellow, 300, 26);	//Amagertorv
		fields[32] = new Street(33, Color.yellow, 300, 26);	//Vimmelskaftet
/**/		fields[33] = new Street(34, Color.blue, 60, 2);	//Chance
		fields[34] = new Street(35, Color.yellow, 320, 28);	//Nygade
		fields[35] = new Fleet(36, Color.lightGray, 200); 	//D/S Bornholm
/**/		fields[36] = new Street(37, Color.blue, 60, 2);	//Chance
		fields[37] = new Street(38, Color.cyan, 350, 35);	//Frederiksberggade
		fields[38] = new Tax(39, Color.lightGray, 0, 100); 	//Ekstraordinær statsskat
		fields[39] = new Street(40, Color.cyan, 400, 50);	//Rådhuspladsen
		
	}
	
	public Field[] getFields(){
		return fields;
	}
}
