package controller;

import entity.*;

public class TurnController {
	protected DiceCup dice; //An instance of the DiceCup class
	protected Field currentField;
	protected Player player;
	protected Board board;

	protected final int prisonEscapeFine = 50;
	protected final int payday = 200;

	protected boolean movingToPrison;
	protected boolean movingPiece;

	public TurnController(Player player, Board board){
		this.player = player;
		this.board = board;

		dice = new DiceCup(6,2);

	}

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

	protected void throwDice(){
		dice.throwDice();
		player.setLastThrow(dice);
		GUIController.setDice(dice);
		
		//Are dice equal?
		if(dice.isEqual() == true){
			//Player has thrown equals less than 3 times in a row
			if (player.getEqualCount() != 2){
				player.setEqualCount(player.getEqualCount()+1);
				movingToPrison = false;
			}
			//Player has thrown equals 3 times in a row
			else{
				GUIController.showMessage(Messages.getGeneralMessages()[29]);
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

		//we find streets of all owned fields
		Ownable[] ownedFields = player.getAccount().getOwnedFields();

			//find number of streets
			int streetCounter = 0;
			for (Ownable i : ownedFields){
				if (i instanceof Street 
						&& ((Street) i).getHousesOwned() < 5
						&& player.getAccount().getBalance() >= ((Street) i).getHousePrice()){
					streetCounter++;
				}
			}

			//create new Street array
			Street[] ownedStreets = new Street[streetCounter];

			streetCounter = 0;
			for (int i = 0; i < ownedFields.length; i++){
				if (ownedFields[i] instanceof Street 
						&& ((Street) ownedFields[i]).getHousesOwned() < 5
						&& player.getAccount().getBalance() >= ((Street) ownedFields[i]).getHousePrice()){
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
			if (Messages.getFieldNames()[i.getId()-1].equals(userChoice)){
				chosenField = (Street) i;
			}
		}

		//Are you sure?
		String areYouSure = GUIController.getUserButtonPressed(Messages.getGeneralMessages()[23] + chosenField.getHousePrice() + Messages.getGeneralMessages()[24]
				, Messages.getGeneralMessages()[1], Messages.getGeneralMessages()[2]);

		if (areYouSure.equals(Messages.getGeneralMessages()[1])){
			chosenField.setHousesOwned(chosenField.getHousesOwned()+1);
			
			if (chosenField.getHousesOwned() == 5){
				GUIController.setHotel(chosenField);
			}
			else{
				GUIController.setHouses(chosenField);
			}
			
			player.getAccount().setBalance(player.getAccount().getBalance()-chosenField.getHousePrice());
			GUIController.setPlayerBalance(player);
		}
		
	}

	protected void movePiece(){
		int oldPosition = player.getPiece().getPosition();	// Save the old player position
		int position;

		/*
		 * If there has already been placed a car, we remove it before placing a new one
		 */
		GUIController.removeAllCars(player);

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

			//We set the car and piece position to the new values
			player.getPiece().setPosition(position);
			GUIController.setCar(player);
			currentField = board.getFields()[position-1];

			// Money when passing or landing on start
			if (oldPosition > position) {
				if (position == 1) {
					GUIController.showMessage(
							Messages.getGeneralMessages()[26] + Messages.getFieldNames()[0] + 		// You landed on Start
							Messages.getGeneralMessages()[28] + payday);							// and receives the payday amount (200)
				}else {
					GUIController.showMessage(Messages.getGeneralMessages()[27] + Messages.getFieldNames()[0] +		// You passed start
							Messages.getGeneralMessages()[28] + payday);											// and receives the payday amount (200)

				}
				player.getAccount().setBalance(player.getAccount().getBalance() + payday);

				GUIController.setPlayerBalance(player);
			}
		}
		else{
			currentField = board.getFields()[10];
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
			GUIController.removeAllCars(player);
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
			else if (owner.getAccount().getBalance() >= 0){//pay rent to owner if he is not bankrupt
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
		else if( currentField instanceof Chance){
			
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
		GUIController.removeAllCars(player);
		player.getPiece().setPosition(11);
		player.setPrisonCount(3);
		GUIController.setCar(player);
		player.setEqualCount(0);
		movingToPrison = false;
	}

	protected void prisonEscape() {
		// Player is in prison and has several attempts to get out.
		if (player.getPrisonCount() > 1) {
			String playerChoice = determineUserInput(new String[]{
					Messages.getGeneralMessages()[32],		//"De er i fængsel. For at komme ud kan de betale 50 kr. eller slå to ens"
					Messages.getGeneralMessages()[31],		//"Betal 50 kr."
					Messages.getGeneralMessages()[7]});		//"Slå med terningerne"
			
			player.setChoice(playerChoice);
			
			if(playerChoice.equals(Messages.getGeneralMessages()[7])){
				throwDice();
				
				if (dice.isEqual() == true) {
					player.setEqualCount(1);
					player.setPrisonCount(0);
					movingPiece = true;
				}else {
					player.setEqualCount(0);
					player.setPrisonCount(player.getPrisonCount()-1);
			}
			
			}
			else if(playerChoice.equals(Messages.getGeneralMessages()[31])){
				player.setEqualCount(0);
				player.setPrisonCount(0);
				player.getAccount().setBalance(player.getAccount().getBalance()-prisonEscapeFine); // pay the fine for getting out of prison
				GUIController.setPlayerBalance(player);
				throwDice();
				movingPiece = true;
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
				player.getAccount().setBalance(player.getAccount().getBalance()-prisonEscapeFine); // pay the fine for getting out of prison
				GUIController.setPlayerBalance(player);
			}
			movingPiece = true;
		}
	}
	
	protected void setDice(DiceCup dice){
		this.dice = dice;
	}
}
