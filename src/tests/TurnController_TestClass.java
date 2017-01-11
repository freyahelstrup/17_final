package tests;

import controller.TurnController;
import entity.Board;
import entity.DiceCup;
import entity.Messages;
import entity.Player;

class TurnController_TestClass extends TurnController {
	private int resultCounter;
	private int[] data;
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
		if(resultCounter<data.length){
			dice.getDice()[0].setValue(data[resultCounter]); //The first dice keeps stepping through the data array
			dice.getDice()[1].setValue(0); //There might be issues with the brewery class if we don't have a second dice...
			resultCounter++;
		}
		else{
			//We set it random if we've reached the end of our result array so the program won't crash 
			//if the teachers want us to keep playing after the demo
			dice.throwDice();
		}
	}
	
	public void setTestData(int[] data){
		resultCounter = 0; //resultCounter is the place we are in the array. When we get a new dataset, we reset it.
		this.data = data;
	}

}
