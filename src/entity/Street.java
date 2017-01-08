package entity;

import java.awt.Color;

public class Street extends Ownable {

	private final int[] rents;
	private int housesOwned;
	private final int housePrice;
	
	public Street(int id, Color color, int price, int[] rents, int housePrice) {
		super(id, color, price);

		this.rents = rents;
		housesOwned = 0;
		this.housePrice = housePrice;
	}

	@Override
	public int getRent() {
		
		return rents[housesOwned];
		
	}
	
	public int getHousesOwned(){
		return housesOwned;
	}

	public int getHousePrice(){
		return housePrice;
	}
	
	public void setHousesOwned(int amount){
		housesOwned = amount;
	}

}
