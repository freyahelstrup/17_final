package entity;

import java.awt.Color;

public class GoToPrison extends Field{

	public GoToPrison(int id, Color color) {
		super(id, color);
		
	}
	@Override
	public void landOnField(Player player) {
			player.getPiece().setPosition(11);
			player.setPrisonCount(3);
	}

	
}
