package entity;

import java.awt.Color;

import controller.GUIController;

public abstract class Ownable extends Field{

	protected int price;
	protected Player owner;
	
	public Ownable(int id, Color color, int price){
		super(id, color);
		this.price = price;
	}
	
	public void landOnField(Player player){
		int balance = player.getAccount().getBalance();
		
		if (owner == null && balance >= price){
			if (player.getChoice().equals(Messages.getGeneralMessages()[1]))
			{//user chooses yes
				owner = player;
				player.getAccount().setOwnedField(this); //Field is added to Player's owned fields
				player.getAccount().setBalance(balance-price); //Balance of Player is changed
			}
		}
		else if (owner == null){
			GUIController.showMessage(Messages.getGeneralMessages()[25]); //Message sent, you do not have enough money to buy the field
		}
		else if (owner.getAccount().getBalance() > 0){//pay rent to owner if he is not bankrupt
			int rent = getRent();
			/*
			if (this instanceof Brewery){
				//Brewery 
				//rent = getRent()*player.getLastThrow().getSum();
			}
			else{
				rent = getRent();
			}*/
			
			owner.getAccount().setBalance(owner.getAccount().getBalance() + rent);
			player.getAccount().setBalance(balance - rent);		
		}
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
