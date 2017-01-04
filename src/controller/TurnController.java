package controller;

import entity.*;

public class TurnController {
	private DiceCup dice; //An instance of the DiceCup class
	private Field currentField;
	private Player player;
	private Board board;
	
	public TurnController(Player player, Board board){
		this.player = player;
		this.board = board;
		
		dice = new DiceCup(6,2);
		
		throwDice();
		movePiece();
		landOnField();
	}
	
	private void throwDice(){
		dice.throwDice();
		player.setLastThrow(dice);
		GUIController.setDice(dice);
	}

	private void movePiece(){
		//if (player.getPiece().getPosition() != 0){
			/*
			 * If there has already been placed a car, we remove it before placing a new one
			 * We avoid bugs in the first turn by having the position set to 0
			 * Every field is then a value of 1-21.
			 */
			GUIController.removeAllCars(player);
		//}
		/*
		 * Position is equal to the current position + the sum of the dice throw
		 * We use modulus to calculate whether the player would end up outside the board
		 */
		int position = (player.getPiece().getPosition() + dice.getSum()) % board.getFields().length;
		//Since we use mod 40, we need to have a special case for field 40, or else we get position == 0
		if (position == 0){
			position = board.getFields().length;
		}

		//We set the car and piece position to the new values
		player.getPiece().setPosition(position);
		GUIController.setCar(player);
		currentField = board.getFields()[position-1];
	}

	private void landOnField(){
		GUIController.showMessage(Messages.getGeneralMessages()[7]);
	}
	
}
