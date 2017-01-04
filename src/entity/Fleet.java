package entity;

import java.awt.Color;

public class Fleet extends Ownable {

	private final int[] RENTS = new int[]{25,50,100,200};

	public Fleet(int id, Color color, int price) {
		super(id, color, price);
	}

	@Override
	public int getRent() {
		int ownedFleets = 0;
		int rent = 0;

		if (this.getOwner() != null){
			for (Ownable i : this.getOwner().getAccount().getOwnedFields()){

				if (i instanceof Fleet){
					ownedFleets++;
				}

			}
			rent = RENTS[ownedFleets-1];
		}

		return rent;
	}

}
