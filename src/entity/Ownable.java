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
		int balance = player.getAccount().getBalance();
		
		if (owner == null && balance >= price){
			if (player.getChoice().equals(Messages.getGeneralMessages()[1]));
			{//user chooses yes
				owner = player;
				player.getAccount().setOwnedField(this);
				player.getAccount().setBalance(balance-price);
			}
		}
		else if (owner.getAccount().getBalance() > 0){//pay rent to owner if he is not bankrupt
			int rent = 0;
			if (this instanceof Brewery){
				//when LaborCamp we should multiply dice sum with 100 and number of owned labor camps
				rent = getRent()*player.getLastThrow().getSum();
			}
			else{
				rent = getRent();
			}
			
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
