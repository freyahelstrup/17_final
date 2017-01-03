package entity;

public class Dice {
	protected int value;
	protected int sides;
	
//Constructor to set amount of dice-sides and the amount of dices
//furthermore uses the setAllValuesRandom method which simulates a roll
	public Dice(int diceSides){
		this.sides = diceSides;	
		this.setRandom();
	}
	
	public int getValue(){
		return value;
	}
	
	//Simulates a roll of the chosen dice(s)
	public void setRandom(){
		value = ( (int) (Math.random()*sides)+1);
	}
}
