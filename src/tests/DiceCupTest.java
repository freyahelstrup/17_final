package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.DiceCup;

public class DiceCupTest {

	@Test
	public void test() {
		
		
		int throwAmount = 60000;   				//the number of dice thrown
		int[] runs = new int[throwAmount];		//array to save each result of each throw
		int[] diceThrow = new int[6];			//array to save how many times each value is rolled

		Boolean failOnce = false;  				
		Boolean failRandom = false;
		
		int diceSides = 6;

		DiceCup Cup = new DiceCup(diceSides,1);	//initializing a new dicecup 

		//testing that the dice only rolls values within the dicesides
		for (int i = 0; i < throwAmount; i++){

			Cup.throwDice();
			runs[i] = Cup.getSum();
			switch(Cup.getSum()){
			
			case 1:
				diceThrow[0] = diceThrow[0] + 1;
				break;
				
			case 2:
				diceThrow[1] = diceThrow[1] + 1;
				break;
				
			case 3:
				diceThrow[2] = diceThrow[2] + 1;
				break;
					
			case 4:
				diceThrow[3] = diceThrow[3] + 1;
				break;
				
			case 5:
				diceThrow[4] = diceThrow[4] + 1;
				break;
				
			case 6:
				diceThrow[5] = diceThrow[5] + 1;
				break;
			}
			
			if (runs[i] <= 0 || runs[i] > diceSides ){
				
				failOnce = true;
			
			}
		}
		
		assertEquals(failOnce, false);
		
		//testing that each value is rolled approximately the same number of times 
		for (int n = 0; n < diceSides; n++){
			
			// testing with a deviation of 5 procent
			if ((diceThrow[n] > (throwAmount)*0.95) && (diceThrow[n] < (throwAmount)*1.05)){
				
				failRandom = true;
				
			}
			
		}
		assertEquals(failRandom, false);	
		}
		
	}
  
