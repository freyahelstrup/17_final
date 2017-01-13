package tests;

import controller.GUIController;
import controller.TurnController;
import entity.Board;
import entity.DiceCup;
import entity.Messages;
import entity.Ownable;
import entity.Player;
import entity.Street;

public class TurnController_TestClass extends TurnController {
	private int[] data;
	private DiceCup dice;
	private String[] userChoiceArray;
	private int userChoiceCounter;
	
	//This class is used to determine specific dice rolls and player inputs to be used in white-box and black-box testing
	public TurnController_TestClass(Player player, Board board) {
		super(player, board); //The super class constructor does all the work of instantiating the class
		//The playTurn() method of the super class can be used to run the turn
		dice = new DiceCup(6,2);
	}
	
	@Override
	protected String determineUserInput(String[] input){
		return input[1]; //We give a 2 length array as the input, this makes us able to go around the GUI
		//The first string of the user input is usually just the message for the GUI, so we leave that be.
	}
	
	@Override
	protected void throwDice(){
		if(data != null){
			dice.getDice()[0].setValue(data[0]);
			dice.getDice()[1].setValue(data[1]); 
		}
		else{
			//We set it random if we've reached the end of our result array so the program won't crash 
			//if the teachers want us to keep playing after the demo
			dice.throwDice();
		}
		
		if(dice.isEqual() == true){
			//Player has thrown equals less than 3 times in a row
			if (player.getEqualCount() != 2){
				player.setEqualCount(player.getEqualCount()+1);
				movingToPrison = false;
			}
			//Player has thrown equals 3 times in a row
			else{
				movingToPrison = true;
				player.setEqualCount(0);
			}
		}
		else{
			player.setEqualCount(0);
			movingToPrison = false;
		}
		
	}
		
	
	@Override
	public void playTurn(){
		//This used to be in the constructor, but problems arrived when trying to implement
		//a test class inheriting from this class as it requires the super class constructor to be used,
		//which in turn prevents us from doing any of our own determined inputs

		do{

			boolean buyHouseChoice = false;
			for (Ownable i : player.getAccount().getOwnedFields()){
				if (i instanceof Street 
						&& ((Street) i).getHousesOwned() < 5 //we should only allow houses bought when field has less than 5 houses (hotel) already
						&& player.getAccount().getBalance() >= ((Street) i).getHousePrice()){
					buyHouseChoice = true;
					break;	
				}
			}
			

			//Player can buy houses
			if (buyHouseChoice == true){

				//Do you want to throw dice or buy houses/hotels
				player.setChoice(determineUserInput(new String[]{Messages.getGeneralMessages()[11] + player.getName() + Messages.getGeneralMessages()[12],
					userChoiceArray[userChoiceCounter]}));

				//Player wants to buy house or hotel
				if (player.getChoice().equals(Messages.getGeneralMessages()[21])){

					buyHouseHotel();

				}
			}
			else{
				player.setChoice(determineUserInput(new String[]{Messages.getGeneralMessages()[11] + player.getName() + Messages.getGeneralMessages()[12],
						Messages.getGeneralMessages()[7]}));
			}
		}
		while(!player.getChoice().equals(Messages.getGeneralMessages()[7])); //player does not want to throw dice

		movingPiece = false; // A boolean to define whether or not a piece shall move. I saw it necessary to simplify the code.

		// Player is not in prison
		if (player.getPrisonCount() == 0) {
			throwDice();
			movingPiece = true;
		}
		// If player is in Prison
		else if (player.getPrisonCount() > 0) {
			prisonEscape();
		}
		
		if (movingPiece) {
			movePiece();
			landOnField();
		}
	}
	
	public void setTestData(int[] data){
		this.data = data;
	}
	
	public void setUserChoiceArray(String[] data){
		userChoiceCounter = 0;
		this.userChoiceArray = data;
	}
	
	

}
