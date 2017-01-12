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
		int rent = 0;
		
		if (housesOwned == 0 && owner != null){
			//find amount of streets in group
			int groupAmount;
			if (color == Color.cyan || color == Color.orange){
				groupAmount = 2;
			}
			else{
				groupAmount = 3;
			}
			
			//find streets in group owned by player
			int ownedInGroup = 0;
			for(Ownable i : owner.getAccount().getOwnedFields()){
				if (i.getColor() == color && i instanceof Street){
					ownedInGroup++;
				}
			}
			
			//double rent
			if (ownedInGroup == groupAmount){
				rent = rents[0]*2;
			}
			else{
				rent = rents[0];
			}
		}
		else{
			rent = rents[housesOwned];
		}
		
		return rent;
		
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
