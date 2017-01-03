package entity;

import java.awt.Color;

public class Fleet extends Ownable {
	
	private final int RENT_1 = 25;
	private final int RENT_2 = 50;
	private final int RENT_3 = 100;
	private final int RENT_4 = 200;

	public Fleet(int id, Color color, int price) {
		super(id, color, price);
	}

	@Override
	public int getRent() {
		int ownedFleets = 0;
		int rent = 0;
		
		for (Ownable i : this.getOwner().getAccount().getOwnedFields()){
			
			if (i instanceof Fleet){
				ownedFleets++;
			}
			
		}
		
		switch (ownedFleets){
		case 1: rent = RENT_1; break;
		case 2: rent = RENT_2; break;
		case 3: rent = RENT_3; break;
		case 4: rent = RENT_4; break;
		default: rent = 0;
		}
		
		return rent;
	}

}
