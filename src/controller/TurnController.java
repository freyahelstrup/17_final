package controller;

import desktop_resources.GUI;
import entity.*;

public class TurnController {
	private DiceCup dice; //An instance of the DiceCup class
	private Field currentField;
	private Player player;
	
	public TurnController(Player player, Board board){
		
	}
	
	private void throwDice(){
		dice.throwDice();
		player.setLastThrow(dice);
		GUI.setDice(dice.getDice()[0].getValue(), dice.getDice()[1].getValue());
	}

	private void movePiece(){
		
	}

	private void landOnField(){
		
	}
	
}
