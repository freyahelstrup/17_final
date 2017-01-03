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
	
	public int getSum(){
		int sum = 0;
		for (Dice i : dice){
			sum += i.getValue();
		}
		return sum;
	}
	
}
