package entity;

import java.awt.Color;

public class Tax extends Field {
	private int taxRate; //in percent
	private int taxAmount;
	
	public Tax(int id, Color color, int taxRate, int taxAmount){
		super(id, color);
		this.taxRate = taxRate;
		this.taxAmount = taxAmount;
	}
	
	public void landOnField(Player player){
		
	}
}
