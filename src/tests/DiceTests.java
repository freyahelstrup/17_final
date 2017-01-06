package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.Dice;

public class DiceTests {

	
	final int throwAmount = 60000;   	//the number of times the dice is thrown
	int[] diceCount = new int[6];		//array to count how many times each value is rolled
	boolean failRandom = false;
	boolean failOnce = false;
	
	Dice testDice = new Dice(6);
	
	// testing that each side on the dice is rolled approximately the same number of times
	@Test  
	
	public void DiceTestRandom() {
		
		
for (int n = 0; n < throwAmount; n++){
	
		testDice.setRandom();
			
		switch(testDice.getValue()){
		
		case 1:
			diceCount[0] = diceCount[0] + 1;  	    //counting 1's
			break;
			
		case 2:
			diceCount[1] = diceCount[1] + 1;		//counting 2's
			break;
			
		case 3:
			diceCount[2] = diceCount[2] + 1;		//counting 3's
			break;
				
		case 4:
			diceCount[3] = diceCount[3] + 1;		//counting 4's
			break;
			
		case 5:
			diceCount[4] = diceCount[4] + 1;		//counting 5's
			break;
			
		case 6:
			diceCount[5] = diceCount[5] + 1;		//counting 6's
			break;
			}
		}
	
		// testing with a deviation of 5 procent
		for(int i = 0; i < diceCount.length; i++){
			if ((diceCount[i] < (throwAmount/6)*(0.95)) || diceCount[i] > ((throwAmount/6)*(1.05))){
			failRandom = true;
			}	
		}
	
		assertEquals(failRandom, false);
	}
	//Testing that the dice only rolls values within the dicesides
	@Test 
	public void DiceTestvalues() {  
		
		for (int i = 0; i < throwAmount; i++){

			testDice.setRandom();
			
			if((testDice.getValue() < 0) || (testDice.getValue() > 6) )
				failOnce = true;
			
			}
			assertEquals(failOnce, false);
		}
}


