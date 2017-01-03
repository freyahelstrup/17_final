package entity;

import java.awt.Color;

public abstract class Field {
	protected int id;
	protected Color color;
	
	public Field(int id, Color color){
		this.id = id;
		this.color = color;
	}
	
	public abstract void landOnField(Player player);
	
}
