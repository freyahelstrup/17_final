package entity;

import java.awt.Color;

public class Street extends Ownable {

	private int rent;
	
	public Street(int id, Color color, int price, int rent) {
		super(id, color, price);

		this.rent = rent;
	}

	@Override
	public int getRent() {
		return rent;
	}

}
