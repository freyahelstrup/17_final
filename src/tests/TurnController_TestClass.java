package tests;

import controller.TurnController;
import entity.Board;
import entity.DiceCup;
import entity.Messages;
import entity.Player;

class TurnController_TestClass extends TurnController {
	//This class is used to determine specific dice rolls and player inputs to be used in white-box and black-box testing
	public TurnController_TestClass(Player player, Board board) {
		super(player, board); //The super class constructor does all the work of instantiating the class
		//The playTurn() method of the super class can be used to run the turn
	}
	
	@Override
	protected String determineUserInput(String[] input){
		return input[0]; //We give a 1 length array as the input, this makes us able to go around the GUI
	}

}
