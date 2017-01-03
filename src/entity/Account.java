package entity;

public class Account {
	private int jailFreeCounter;
	private int balance;
	
	public Account(int startBalance){
		jailFreeCounter = 0;
		this.balance = startBalance;
		
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public void setJailFreeCounter(int jailFreeCounter){
		this.jailFreeCounter = jailFreeCounter;
	}

	public int getJailFreeCounter(){
		return jailFreeCounter;
	}
}
