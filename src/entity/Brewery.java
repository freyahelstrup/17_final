package entity;

import java.awt.Color;

public class Brewery extends Ownable {

	private final int FACTOR_1 = 4;
	private final int FACTOR_2 = 10;

	public Brewery(int id, Color color, int price) {
		super(id, color, price);
	}

	@Override
	public int getRent() {
		int ownedBreweries = 0;
		int rent = 0;

		if (this.getOwner() != null){
			for (Ownable i : this.getOwner().getAccount().getOwnedFields()){

				if (i instanceof Brewery){
					ownedBreweries++;
				}

			}
		}		
		
		switch (ownedBreweries){
		case 1: rent = FACTOR_1; break;
		case 2: rent = FACTOR_2; break;
		default: rent = 0;
		}

		return rent;

	}

}
