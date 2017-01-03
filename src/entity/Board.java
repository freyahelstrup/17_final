package entity;

import java.awt.Color;

public class Board {
	private Field[] fields;

	public Board(){

		fields = new Field[40];
		
		fields[0] = new Refuge(1,Color.white);		//startField

	}
	
	public Field[] getFields(){
		return fields;
	}
}
