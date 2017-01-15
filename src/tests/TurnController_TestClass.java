package tests;

import java.awt.Color;

import controller.GUIController;
import controller.TurnController;
import entity.Board;
import entity.DiceCup;
import entity.Field;
import entity.Messages;
import entity.Ownable;
import entity.Player;
import entity.Street;

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
		return userChoice[userChoiceCounter++]; //We return the current userChoice and then increment the counter
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
		userChoiceCounter++;
		
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
