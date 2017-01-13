package tests;

import java.awt.Color;

import controller.GUIController;
import controller.TurnController;
import entity.Board;
import entity.Chance;
import entity.DiceCup;
import entity.Field;
import entity.Messages;
import entity.Ownable;
import entity.Player;
import entity.Street;
import entity.Tax;

public class TurnController_TestClass extends TurnController {
	private int[] data;
	private String[] userChoice;
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
	}
	
	@Override
	public void playTurn(){
		//This used to be in the constructor, but problems arrived when trying to implement
		//a test class inheriting from this class as it requires the super class constructor to be used,
		//which in turn prevents us from doing any of our own determined inputs
		
		do{

			boolean buyHouseChoice = false;
			
			//we go through owned buildable streets
			for (Street ownedStreet : player.getAccount().getBuildableStreets()){
				
				Color fieldColor = ownedStreet.getColor();
				
				//we find number of streets in group
				int groupAmount = 0;
				for (Field boardField : board.getFields()){
					if (boardField.getColor() == fieldColor && boardField instanceof Street){
						groupAmount++;
					}
				}
				
				//we find streets in group owned by player
				int ownedInGroup = 0;
				for(Ownable ownedField : player.getAccount().getOwnedFields()){
					if (ownedField.getColor() == fieldColor && ownedField instanceof Street){
						ownedInGroup++;
					}
				}
				
				//we find out if player has all streets in group
				if (ownedInGroup == groupAmount){ //player owns all in group
					buyHouseChoice = true;
					break;	
				}
			}

			//Player can buy houses
			if (buyHouseChoice == true){

				//Do you want to throw dice or buy houses/hotels
				player.setChoice(determineUserInput(new String[]{Messages.getGeneralMessages()[11] + player.getName() + Messages.getGeneralMessages()[12],
					userChoice[userChoiceCounter]}));
				userChoiceCounter++;
				
				//Player wants to buy house or hotel
				if (player.getChoice().equals(Messages.getGeneralMessages()[21])){
					buyHouseHotel();
				}
			}
			//Player can only throw dice
			else{
				player.setChoice(determineUserInput(new String[]{Messages.getGeneralMessages()[11] + player.getName() + Messages.getGeneralMessages()[12],
						userChoice[userChoiceCounter]}));
				userChoiceCounter++;
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
			if((currentField instanceof Chance) && (Chance.isMoveCard() == true)){
				GUIController.removeAllCars(player.getName());
				currentField = board.getFields()[player.getPiece().getPosition()-1];
				GUIController.setCar(player.getPiece().getPosition(), player.getName());
				landOnField();
			}
		}
	}
	
	@Override
	protected void landOnField(){
		// You landed on
		if (player.getPiece().getPosition()-1 != 0) { // No need to tell that you landed on start, when the MovePiece says it
		}

		if (player.getPiece().getPosition()-1 == 30) { // goToPrison field.
			GUIController.removeAllCars(player.getName());
			moveToPrison();
		}

		int playerBalance = player.getAccount().getBalance();

	//Ownable
		if (currentField instanceof Ownable) {
			Player owner = ((Ownable) currentField).getOwner();
			int price = ((Ownable) currentField).getPrice();

		// Do you wish to buy it?
			if (owner == null && playerBalance >= price) {
				String playerChoice = determineUserInput(new String[]{
						Messages.getGeneralMessages()[0] + ((Ownable) currentField).getPrice() + "?"
						,userChoice[userChoiceCounter]
								});
				userChoiceCounter++;
				
				player.setChoice(playerChoice);

				if (playerChoice.equals(Messages.getGeneralMessages()[1])) { // User chooses yes
					GUIController.setFieldOwner(player.getName(), player.getPiece().getPosition());
				}
			}
			// You don't have enough money to buy field
			else if(owner == null && playerBalance < price){
			}
		// You own the field
			else if (owner == player){
			}
		// You have to pay rent
			else if (owner.getAccount().getBalance() >= 0){//pay rent to owner if he is not bankrupt
			}
		}

	//Tax
		else if( currentField instanceof Tax) {
			if ( ((Tax) currentField).getTaxRate() > 0) {

			String playerChoice = determineUserInput(new String[]{
					Messages.getGeneralMessages()[3]
					,userChoice[userChoiceCounter]});
			userChoiceCounter++;

			player.setChoice(playerChoice);
			}
		}
	//Chance	
		else if( currentField instanceof Chance){
		}

		currentField.landOnField(player);
		GUIController.setPlayerBalance(player.getName(),player.getAccount().getBalance());
		if (currentField instanceof Ownable){
			if (((Ownable) currentField).getOwner() != null){
				GUIController.setPlayerBalance(((Ownable) currentField).getOwner().getName(),((Ownable) currentField).getOwner().getAccount().getBalance());
			}
		}
	}
	
	protected void buyHouseHotel(){

		Street[] ownedStreets = player.getAccount().getBuildableStreets();
		
		//we go through owned buildable streets
		for (Street ownedStreet : player.getAccount().getBuildableStreets()){
			
			Color fieldColor = ownedStreet.getColor();
			
			//we find number of streets in group and min and max houses
			int groupAmount = 0;
			int maxHouses = 0;
			int minHouses = 5;
			for (Field boardField : board.getFields()){
				if (boardField.getColor() == fieldColor && boardField instanceof Street){
					groupAmount++;

					//we find current min amount of houses in group
					if(((Street) boardField).getHousesOwned() < minHouses){
						minHouses = ((Street) boardField).getHousesOwned();
					}
					
					//we find current max amount of houses in group
					if(((Street) boardField).getHousesOwned() > maxHouses){
						maxHouses = ((Street) boardField).getHousesOwned();
					}
				}
			}
			
			//we find streets in group owned by player
			int ownedInGroup = 0;
			for(Ownable ownedField : player.getAccount().getOwnedFields()){
				if (ownedField.getColor() == fieldColor && ownedField instanceof Street){
					ownedInGroup++;
				}
			}
			
			//we find out if player has all streets in group
			if (ownedInGroup == groupAmount //player owns all in group
				&& (ownedStreet.getHousesOwned() < maxHouses //field has less houses than other field in group
					|| (ownedStreet.getHousesOwned() == maxHouses && minHouses == maxHouses))){ //all fields have same number of houses
				//everything is good
			}
			//we remove street from ownedStreets
			else{
				Street[] oldArray = ownedStreets;
				ownedStreets = new Street[oldArray.length-1];
						
				int counter = 0;
				for (Street i : oldArray){
					if (i != ownedStreet){
						ownedStreets[counter] = i;
						counter++;
					}
				}
			}
		}
		
		//we find names of the streets
		String[] fieldNames = new String[ownedStreets.length];

		for (int i = 0; i < fieldNames.length; i++){
			fieldNames[i] = Messages.getFieldNames()[ownedStreets[i].getId()-1];
		}

		//For which street do you want a house/hotel?
		String userSelection = userChoice[userChoiceCounter];

		Street chosenField = null;
		for (Field i : board.getFields()){
			if (Messages.getFieldNames()[i.getId()-1].equals(userSelection)){
				chosenField = (Street) i;
			}
		}

		//Are you sure?
		String areYouSure = determineUserInput(new String[]{Messages.getGeneralMessages()[23] + chosenField.getHousePrice() + Messages.getGeneralMessages()[24]
				, Messages.getGeneralMessages()[1], Messages.getGeneralMessages()[2]});

		if (areYouSure.equals(Messages.getGeneralMessages()[1])){
			chosenField.setHousesOwned(chosenField.getHousesOwned()+1);
			
			if (chosenField.getHousesOwned() == 5){
				GUIController.setHotel(chosenField.getId());
			}
			else{
				GUIController.setHouses(chosenField.getId(),chosenField.getHousesOwned());
			}
			
			player.getAccount().setBalance(player.getAccount().getBalance()-chosenField.getHousePrice());
			GUIController.setPlayerBalance(player.getName(), player.getAccount().getBalance());
		}
		
	}
	
	public void setTestData(int[] data){
		this.data = data;
	}
	
	public void setUserChoice(String[] data){
		userChoiceCounter = 0;
		this.userChoice = data;
	}
	
}
