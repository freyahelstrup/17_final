package entity;

import java.awt.Color;

public class Street extends Ownable {

	private final int RENT_0;
	private final int RENT_1;
	private final int RENT_2;
	private final int RENT_3;
	private final int RENT_4;
	private final int RENT_HOTEL;
	
	
	public Street(int id, Color color, int price, int[] rents) {
		super(id, color, price);

		this.RENT_0 = rents[0];
		this.RENT_1 = rents[1];
		this.RENT_2 = rents[2];
		this.RENT_3 = rents[3];
		this.RENT_4 = rents[4];
		this.RENT_HOTEL = rents[5];
	}

	@Override
	public int getRent() {
		return RENT_0;
	}

}
