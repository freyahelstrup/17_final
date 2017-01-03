package entity;

public class DiceCup {
	protected Dice[] dice;
	
//Constructor to set amount of dice-sides and the amount of dices
//furthermore uses the setAllValuesRandom method which simulates a roll
	public DiceCup(int diceSides, int diceAmount){
		dice = new Dice[diceAmount];
		
		for (Dice i : dice){
			i = new Dice(diceSides);
			i.setRandom();
		}
	}
	
	public Dice[] getDice(){
		return dice;
	}
	
	public int getSum(){
		int sum = 0;
		for (Dice i : dice){
			sum += i.getValue();
		}
		return sum;
	}
	
	public void throwDice(){
		for (Dice i : dice){
			i.setRandom();
		}
	}
	
	public boolean isEqual(){
		boolean isEqual = true;
		int value = dice[0].getValue();
		
		//Checks if the first value of dice is equal to the rest
		for (Dice i : dice){
			if (value != i.getValue()){
				isEqual = false;
			}
		}
		
		return isEqual;
	}
	
}
