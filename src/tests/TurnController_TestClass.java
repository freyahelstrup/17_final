package tests;

import controller.GUIController;
import controller.TurnController;
import entity.Board;
import entity.DiceCup;
import entity.Messages;
import entity.Player;

public class TurnController_TestClass extends TurnController {
	private int[] result;
	private DiceCup dice;
	
	//This class is used to determine specific dice rolls and player inputs to be used in white-box and black-box testing
	public TurnController_TestClass(Player player, Board board) {
		super(player, board); //The super class constructor does all the work of instantiating the class
		//The playTurn() method of the super class can be used to run the turn
		dice = new DiceCup(6,2);
		player.setChoice(Messages.getGeneralMessages()[7]);
	}
	/*
	@Override
	protected String determineUserInput(String[] input){
		return input[0]; //We give a 1 length array as the input, this makes us able to go around the GUI
	}
	*/
	@Override
	protected void throwDice(){
		
		if(result != null){
			dice.getDice()[0].setValue(result[0]);
			dice.getDice()[1].setValue(result[1]);
		} //Result if result is available
		else{dice.throwDice();} //Random values if no result is available
		setDice(dice);
		player.setLastThrow(dice);
		GUIController.setDice(dice);
	}
	
	public void setTestData(int[] value){
		this.result = value;
	}

}
