package entity;

public class Account {
	private int jailFreeCounter;
	private int balance;
	private Ownable[] ownedFields;
	
	public Account(int startBalance){
		jailFreeCounter = 0;
		this.balance = startBalance;

		ownedFields = new Ownable[0];
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
		//add new Ownable to Ownable array

		Ownable[] temp = ownedFields;		
		ownedFields = new Ownable[temp.length+1];
		
		//insert previous owned fields into new array
		for (int i = 0; i<temp.length;i++){
			ownedFields[i] = temp[i];
		}

		//insert new owned field into new array
		ownedFields[temp.length] = field;

	}

	public Street[] getBuildableStreets(){
		
		//find number of streets
		int streetCounter = 0;
		for (Ownable i : ownedFields){
			if (i instanceof Street 
					&& ((Street) i).getHousesOwned() < 5
					&& balance >= ((Street) i).getHousePrice()){
				streetCounter++;
			}
		}

		//create new Street array
		Street[] ownedStreets = new Street[streetCounter];

		streetCounter = 0;
		for (int i = 0; i < ownedFields.length; i++){
			if (ownedFields[i] instanceof Street 
					&& ((Street) ownedFields[i]).getHousesOwned() < 5
					&& balance >= ((Street) ownedFields[i]).getHousePrice()){
				ownedStreets[streetCounter] = (Street) ownedFields[i];
				streetCounter++;
			}
		}
		
		return ownedStreets;
	}
}
