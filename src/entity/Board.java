package entity;

import java.awt.Color;

public class Board {
	private Field[] fields;

	public Board(){

		fields = new Field[40];
		
		fields[0] = new Tax(1, Color.red, 10, 400);

	}
	
	public Field[] getFields(){
		return fields;
	}
}
