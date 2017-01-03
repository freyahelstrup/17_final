package entity;

import java.awt.Color;

public abstract class Ownable extends Field{

	protected int price;
	protected Player owner;
	
	public Ownable(int id, Color color, int price){
		super(id, color);
		this.price = price;
	}
	
	public void landOnField(Player player){
		
	}
	
	public int getPrice(){
		return price;
	}
	
	public abstract int getRent();
	
	public Player getOwner(){
		return owner;
	}
	
	public void setOwner(Player owner){
		this.owner = owner;
	}
	
}
