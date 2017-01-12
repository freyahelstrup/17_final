package entity;

import java.awt.Color;

public class Tax extends Field {
	private int taxRate; //in percent. If 0 then no taxRate
	private int taxAmount;
	
	public Tax(int id, Color color, int taxRate, int taxAmount){
		super(id, color);
		this.taxRate = taxRate;
		this.taxAmount = taxAmount;
	}
	
	@Override
	public void landOnField(Player player){
		int balance = player.getAccount().getBalance();
		
		if (taxRate > 0){			
			String choice = player.getChoice();
			if (choice.equals(Messages.getGeneralMessages()[4] + taxAmount)){//user chooses taxAmount
				player.getAccount().setBalance(balance - taxAmount);
			}
			else{//user chooses taxRate
				player.getAccount().setBalance(balance - (int)((taxRate/100.0) * player.getAccount().calculateAssets()));
			}
		}
		else{
			player.getAccount().setBalance(balance - taxAmount);
		}
	}

	public int getTaxAmount(){
		return taxAmount;
	}

	public int getTaxRate(){
		return taxRate;
	}
}
