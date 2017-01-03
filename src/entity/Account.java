package entity;

public class Account {
	private int jailFreeCounter;
	private int balance;
	private Ownable[] ownedFields;
	
	public Account(int startBalance){
		jailFreeCounter = 0;
		this.balance = startBalance;
		
		ownedFields = new Ownable[28];
	}

	public int calculateAssets(){
		int assets = balance;
		
		for (Ownable i : ownedFields){
			if (i != null){
				assets += i.getPrice();
			}
		}
		return assets;
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

	public Ownable[] getOwnedFields(){

		return ownedFields;		

	}
	
	public void setOwnedField(Ownable field){
		//set next empty position in Ownable array to the given field
		for (int i = 0; i<ownedFields.length;i++){
			if (ownedFields[i] == null){
				ownedFields[i] = field;
				break; //exit the loop
			}
		}

	}
	
}
