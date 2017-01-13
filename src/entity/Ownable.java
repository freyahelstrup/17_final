package entity;

import java.awt.Color;

public abstract class Ownable extends Field{

	protected int price;
	protected Player owner;
	
	public Ownable(int id, Color color, int price){
		super(id, color);
		this.price = price;
	}
	
	@Override
	public void landOnField(Player player){
		int balance = player.getAccount().getBalance();
		
		//no owner of field
		if (owner == null){
			//player can afford field and wants to buy field
			if (balance >= price && player.getChoice().equals(Messages.getGeneralMessages()[1])){
				owner = player;
				player.getAccount().setOwnedField(this); //Field is added to Player's owned fields
				player.getAccount().setBalance(balance-price); //Balance of Player is changed
			}
		}
		//player owns field
		else if (owner == player){
			
		}
		//pay rent to owner if he is not bankrupt
		else if (owner.getAccount().getBalance() >= 0){
			int rent = 0;
			if (this instanceof Brewery){
				//Brewery is a special case as we need the player's last dice throw to calculate rent
				//Since we do not pass a player to getRent(), we need to do this multiplication on this level
				rent = getRent()*player.getLastThrow().getSum();
			}
			else{
				rent = getRent();
			}
			//if the player does not have enough money to pay all the rent, the owner should only be given what's left
			if(player.getAccount().getBalance() < rent){
				owner.getAccount().setBalance(owner.getAccount().getBalance() + player.getAccount().getBalance());	
			}
			else{
				owner.getAccount().setBalance(owner.getAccount().getBalance() + rent);	
			}
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
