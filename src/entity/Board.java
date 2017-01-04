package entity;

import java.awt.Color;

public class Board {
	private Field[] fields;

	public Board(){

		fields = new Field[40];
		
/**/		fields[0] = new Street(1, Color.white, 60, new int[]{0,0,0,0,0,0});	//Start
		fields[1] = new Street(2, Color.cyan, 60, new int[]{2,10,30,90,160,250});		//Rødovrevej
/**/		fields[2] = new Chance(3, Color.white);	//Chance
		fields[3] = new Street(4, Color.cyan, 60, new int[]{4,20,60,180,320,540});		//Hvidovre
		fields[4] = new Tax(5, Color.white, 10, 200); 	//Betal Indkomstskat
		fields[5] = new Fleet(6, Color.white, 200); 	//Øresund A/S
		fields[6] = new Street(7, Color.pink, 100, new int[]{6,30,90,270,400,550});		//Roskildevej
/**/		fields[7] = new Chance(8, Color.white);	//Chance
		fields[8] = new Street(9, Color.pink, 100, new int[]{6,30,90,270,400,550});		//Valby Langgade
		fields[9] = new Street(10, Color.pink, 120, new int[]{8,40,100,300,450,600});		//Allégade
/*MISSING*/		fields[10] = new Chance(11, Color.white);	//Fængsel
		fields[11] = new Street(12, Color.green, 140, new int[]{10,50,150,450,625,750});	//Frederiksberg Allé
		fields[12] = new Brewery(13, Color.white, 150);	//Tuborg
		fields[13] = new Street(14, Color.green, 140, new int[]{10,50,150,450,625,750});	//Bülowsvej
		fields[14] = new Street(15, Color.green, 160, new int[]{12,60,180,500,700,900});	//Gl. Kongevej
		fields[15] = new Fleet(16, Color.white, 200);	//D.F.D.S
		fields[16] = new Street(17, Color.gray, 180, new int[]{14,70,200,550,750,950});	//Bernstorffsvej
/**/		fields[17] = new Chance(18, Color.white);	//Chance
		fields[18] = new Street(19, Color.gray, 180, new int[]{14,70,200,550,750,950});	//Hellerupvej
		fields[19] = new Street(20, Color.gray, 200, new int[]{16,80,220,600,800,1000});	//Strandvej
/*MISSING*/		fields[20] = new Chance(21, Color.white);	//Helle
		fields[21] = new Street(22, Color.red, 220, new int[]{18,90,250,700,875,1050});		//Trianglen
/**/		fields[22] = new Chance(23, Color.white);	//Chance
		fields[23] = new Street(24, Color.red, 220, new int[]{18,90,250,700,875,1050});		//Østerbrogade
		fields[24] = new Street(25, Color.red, 240, new int[]{20,100,300,750,925,1100});		//Grønningen
		fields[25] = new Fleet(26, Color.white, 200); 	//Ø.K.
		fields[26] = new Street(27, Color.magenta, 260, new int[]{22,110,330,800,975,1150});	//Bredgade
		fields[27] = new Street(28, Color.magenta, 260, new int[]{22,110,330,800,975,1150});	//Kongens Nytorv
		fields[28] = new Brewery(29, Color.white, 150);	//Carlsberg
		fields[29] = new Street(30, Color.magenta, 280, new int[]{22,120,360,850,1025,1200});	//Østergade
/*MISSING*/		fields[30] = new Chance(31, Color.white);	//Politi (GoToPrison)
		fields[31] = new Street(32, Color.yellow, 300, new int[]{26,130,390,900,1100,1275});	//Amagertorv
		fields[32] = new Street(33, Color.yellow, 300, new int[]{26,130,390,900,1100,1275});	//Vimmelskaftet
/**/		fields[33] = new Chance(34, Color.white);	//Chance
		fields[34] = new Street(35, Color.yellow, 320, new int[]{28,150,450,1000,1200,1400});	//Nygade
		fields[35] = new Fleet(36, Color.white, 200); 	//D/S Bornholm
/**/		fields[36] = new Chance(37, Color.white);	//Chance
		fields[37] = new Street(38, Color.orange, 350, new int[]{35,175,500,1100,1300,1500});	//Frederiksberggade
		fields[38] = new Tax(39, Color.white, 0, 100); 	//Ekstraordinær statsskat
		fields[39] = new Street(40, Color.orange, 400, new int[]{50,200,600,1400,1700,2000});	//Rådhuspladsen
		
	}
	
	public Field[] getFields(){
		return fields;
	}
}
