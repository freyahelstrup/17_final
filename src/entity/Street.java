package entity;

import java.awt.Color;

public class Street extends Ownable {

	private final int[] rents;
	private int housesOwned;
	
	
	public Street(int id, Color color, int price, int[] rents) {
		super(id, color, price);

		this.rents = rents;
		housesOwned = 0;
	}

	@Override
	public int getRent() {
		
		return rents[housesOwned];
		
	}
	
	public int getHousesOwned(){
		return housesOwned;
	}
	
	public void setHousesOwned(int amount){
		housesOwned = amount;
	}

}
