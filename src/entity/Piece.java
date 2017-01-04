package entity;

import java.awt.Color;

public class Piece {
	private int position;
	private Color color;

	// Constructor - In order to create the piece, you will need to give the vehicle a color using java.awt.Color;
	public Piece(Color color) {
		this.color = color;
		position = 1; //player starts on field 1
	}
	
	public Color getColor() {
		return this.color;
	}
	
	// Move the piece to a position using an integer
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return position;
	}

}
