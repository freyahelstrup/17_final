package tests;

import controller.TurnController;
import entity.Board;
import entity.DiceCup;
import entity.Messages;
import entity.Player;

public class TurnController_TestClass extends TurnController {
	private int result;
	private DiceCup dice;
	
	//This class is used to determine specific dice rolls and player inputs to be used in white-box and black-box testing
	public TurnController_TestClass(Player player, Board board) {
		super(player, board); //The super class constructor does all the work of instantiating the class
		//The playTurn() method of the super class can be used to run the turn
		dice = new DiceCup(6,2);
	}
	
	@Override
	protected String determineUserInput(String[] input){
		return input[0]; //We give a 1 length array as the input, this makes us able to go around the GUI
	}
	
	@Override
	protected void throwDice(){
		
		if(result >= 0){dice.getDice()[0].setValue(result);} //Result if result is available
		else{dice.throwDice();} //Random values if no result is available
		
	}
	
	public void setTestData(int value){
		this.result = value;
	}

}
