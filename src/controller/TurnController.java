package controller;

import desktop_resources.GUI;
import entity.*;

public class TurnController {
	protected DiceCup dice; //An instance of the DiceCup class
	protected Field currentField;
	protected Player player;
	protected Board board;
	protected boolean movingToPrison;

	public TurnController(Player player, Board board){
		this.player = player;
		this.board = board;

		dice = new DiceCup(6,2);

	}

	public void playTurn(){
		//This used to be in the constructor, but problems arrived when trying to implement
		//a test class inheriting from this class as it requires the super class constructor to be used,
		//which in turn prevents us from doing any of our own determined inputs

		boolean ownedStreets = false;
		for (Ownable i : player.getAccount().getOwnedFields()){
			if (i instanceof Street){
				ownedStreets = true;
				break;
			}
		}

		do{
			//Player owns street
			if (ownedStreets == true){

				//Do you want to throw dice or buy houses/hotels
				player.setChoice(determineUserInput(new String[]{Messages.getGeneralMessages()[11] + player.getName() + Messages.getGeneralMessages()[12],
					Messages.getGeneralMessages()[7],
					Messages.getGeneralMessages()[21]}));

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

		//We check if dice values are equal
		if(dice.isEqual() == true && player.getEqualCount() != 2){
			player.setEqualCount(player.getEqualCount()+1);
		}
		
		// Player is not in prison
		if (player.getPrisonCount() == 0) {
			throwDice();
			if(dice.isEqual() == true && player.getEqualCount() != 2){

				player.setEqualCount(player.getEqualCount()+1);
				movingToPrison = false;
			}
			else if(dice.isEqual() == true && player.getEqualCount() == 2) { // if player rolls two equals for the third time, the player goes straight to prison.
				movingToPrison = true;
				player.setEqualCount(0);
			}
			else{
				player.setEqualCount(0);
				movingToPrison = false;
			}
			movePiece();
			landOnField();
		}
		// If player is in Prison
		else if (player.getPrisonCount() > 0) {
			prisonEscape();
		}
	}

	protected void throwDice(){
		dice.throwDice();
		player.setLastThrow(dice);
		GUIController.setDice(dice);
	}

	protected void buyHouseHotel(){

		//we find streets of all owned fields
		Ownable[] ownedFields = player.getAccount().getOwnedFields();

			//find number of streets
			int streetCounter = 0;
			for (Ownable i : ownedFields){
				if (i instanceof Street && ((Street) i).getHousesOwned() < 5){
					streetCounter++;
				}
			}

			//create new Street array
			Street[] ownedStreets = new Street[streetCounter];

			streetCounter = 0;
			for (int i = 0; i < ownedStreets.length; i++){
				if (ownedFields[i] instanceof Street && ((Street) ownedFields[i]).getHousesOwned() < 5){
					ownedStreets[streetCounter] = (Street) ownedFields[i];
					streetCounter++;
				}
			}

		//we find all names of owned streets
		String[] fieldNames = new String[ownedStreets.length];

		for (int i = 0; i < fieldNames.length; i++){
			fieldNames[i] = Messages.getFieldNames()[ownedStreets[i].getId()-1];
		}

		//For which street do you want a house/hotel?
		String userChoice = GUIController.getUserSelection(Messages.getGeneralMessages()[30], fieldNames);

		Street chosenField = null;

		for (Field i : board.getFields()){
			if (Messages.getFieldNames()[i.getId()-1] == userChoice){
				chosenField = (Street) i;
			}
		}

		chosenField.setHousesOwned(chosenField.getHousesOwned()+1);
		GUIController.setHouses(chosenField);

	}

	protected void movePiece(){
		int oldPosition = player.getPiece().getPosition();	// Save the old player position
		int position;

		//if (player.getPiece().getPosition() != 0){
		/*
		 * If there has already been placed a car, we remove it before placing a new one
		 * We avoid bugs in the first turn by having the position set to 0
		 * Every field is then a value of 1-21.
		 */
		GUIController.removeAllCars(player);
		//}

		// Make sure the player did not throw 3 equals in a row.
		if (movingToPrison == false) {
			/*
			 * Position is equal to the current position + the sum of the dice throw
			 * We use modulus to calculate whether the player would end up outside the board
			 */
			position = (player.getPiece().getPosition() + dice.getSum()) % board.getFields().length;
			//Since we use mod 40, we need to have a special case for field 40, or else we get position == 0
			if (position == 0){
				position = board.getFields().length;
			}

			// Money when passing or landing on start
			if (oldPosition > position) {
				if (position == 1) {
					GUIController.showMessage(
							Messages.getGeneralMessages()[26] + Messages.getFieldNames()[0] + 	// You landed on Start
							Messages.getGeneralMessages()[28] + "200");							// and receives 200
				}else {
					GUIController.showMessage(Messages.getGeneralMessages()[27] + Messages.getFieldNames()[0] +	// You passed start
							Messages.getGeneralMessages()[28] + "200");											// and receives 200

				}
				player.getAccount().setBalance(player.getAccount().getBalance() + 200);
				GUIController.setPlayerBalance(player);
			}

			//We set the car and piece position to the new values
			player.getPiece().setPosition(position);
			GUIController.setCar(player);
			currentField = board.getFields()[position-1];
		}
		else{
			moveToPrison();	// Moves the player and its piece straight to prison
		}
	}

	protected void landOnField(){
		// You landed on
		if (player.getPiece().getPosition()-1 != 0) { // No need to tell that you landed on start, when the MovePiece says it
			determineUserInput(new String[]{Messages.getGeneralMessages()[26] + Messages.getFieldNames()[(player.getPiece().getPosition())-1],
					Messages.getGeneralMessages()[13]});
		}

		if (player.getPiece().getPosition()-1 == 30) { // goToPrison field.
			moveToPrison();
			GUIController.showMessage(Messages.getGeneralMessages()[29]);
		}

		int playerBalance = player.getAccount().getBalance();

	//Ownable
		if (currentField instanceof Ownable) {
			Player owner = ((Ownable) currentField).getOwner();
			int price = ((Ownable) currentField).getPrice();

		// Do you wish to buy it?
			if (owner == null && playerBalance >= price) {
				String playerChoice = determineUserInput(new String[]{
						Messages.getGeneralMessages()[0] + ((Ownable) currentField).getPrice() + "?", //Do you want to buy field?
						Messages.getGeneralMessages()[1], 	// Yes
						Messages.getGeneralMessages()[2] 	// No
								});

				player.setChoice(playerChoice);

				if (playerChoice.equals(Messages.getGeneralMessages()[1])) { // User chooses yes
					GUIController.setFieldOwner(player, player.getPiece().getPosition());
				}
			}
			// You don't have enough money to buy field
			else if(owner == null && playerBalance < price){
				determineUserInput(new String[]{player.getName() + ": " + Messages.getGeneralMessages()[25],
						Messages.getGeneralMessages()[13]});
			}
		// You own the field
			else if (owner == player){
				determineUserInput(new String[]{player.getName() + ": " + Messages.getGeneralMessages()[20],
						Messages.getGeneralMessages()[13]});
			}
		// You have to pay rent
			else if (owner.getAccount().getBalance() > 0){//pay rent to owner if he is not bankrupt
				int rent = 0;
				if (currentField instanceof Brewery){
					//when brewery we should multiply dice sum with 4 or 10, depending on the amount of owned brewery from the same owner.
					rent = ((Ownable) currentField).getRent()*player.getLastThrow().getSum();
				}
				else{
					rent = ((Ownable) currentField).getRent();
				}

				determineUserInput(new String[]{player.getName() + ": " + Messages.getGeneralMessages()[9] + rent + Messages.getGeneralMessages()[16],
						Messages.getGeneralMessages()[13]});
			}
		}

	//Tax
		else if( currentField instanceof Tax) {
			if ( ((Tax) currentField).getTaxRate() > 0) {

			String playerChoice = determineUserInput(new String[]{
					Messages.getGeneralMessages()[3],
					Messages.getGeneralMessages()[4] + ((Tax) currentField).getTaxRate() + Messages.getGeneralMessages()[5],	// Percent taxes of all assets
					Messages.getGeneralMessages()[4] + ((Tax) currentField).getTaxAmount()});	// Fixed amount of tax

			player.setChoice(playerChoice);
			}
		}

		currentField.landOnField(player);
		GUIController.setPlayerBalance(player);
		if (currentField instanceof Ownable){
			if (((Ownable) currentField).getOwner() != null){
				GUIController.setPlayerBalance(((Ownable) currentField).getOwner());
			}
		}
	}


	protected String determineUserInput(String[] input){
		String text;
		switch(input.length) {
			case 1:
				text = GUIController.getUserButtonPressed(input[0]);
				break;
			case 2:
				text = GUIController.getUserButtonPressed(input[0], input[1]);
				break;
			case 3:
				text = GUIController.getUserButtonPressed(input[0], input[1], input[2]);
				break;
			case 4:
				text = GUIController.getUserButtonPressed(input[0], input[1], input[2], input[3]);
				break;
			default:
				text = "";
				break;
		}
		return text;
	}

	protected void moveToPrison() {
		int position = 11; // prison
		player.getPiece().setPosition(position);
		GUIController.setCar(player);
		currentField = board.getFields()[position-1];
		player.setPrisonCount(3); // Three attempts to roll two equals to escape prison
		movingToPrison = false;
	}

	protected void prisonEscape() {
		// Player is in prison and has several attempts to get out.
		if (player.getPrisonCount() > 1) {
			throwDice();
			if (dice.isEqual() == true) {
				player.setEqualCount(1);
				player.setPrisonCount(0);
				movePiece();
				landOnField();
			}else {
				player.setEqualCount(0);
				player.setPrisonCount(player.getPrisonCount()-1);
			}
		}
		// Player is using his last attempt to get out
		else if(player.getPrisonCount() == 1) {
			throwDice();
			if (dice.isEqual() == true) {
				player.setEqualCount(1);
				player.setPrisonCount(0);
			}
			else {
				player.setEqualCount(0);
				player.setPrisonCount(0);
				player.getAccount().setBalance(player.getAccount().getBalance()-50); // pay the fine for getting out of prison
				GUIController.setPlayerBalance(player);
			}
		movePiece();
		landOnField();
		}
	}
}
