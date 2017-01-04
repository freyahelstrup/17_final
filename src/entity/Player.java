package entity;

public class Player {
	
	private int id;
	private String name;
	private Piece piece;
	private Account account;
	private String choice;
	private int prisonCount; //counts number of turns in prison. If 0 then player is not in prison.
	private int equalCount; //counts number of equal diceValues in DiceCup in a row.
	private DiceCup lastThrow;

	public Player(int id, String name, Piece piece, Account account){
		this.id = id;
		this.name = name;
		this.piece = piece;
		this.account = account;
		prisonCount = 0;
		equalCount = 0;
	}

	public String getName(){
		return name;
	}

	public Account getAccount(){
		return account;
	}

	public Piece getPiece(){
		return piece;
	}

	public int getID(){
		return id;
	}
	
	public String getChoice(){
		return choice;
	}
	
	public void setChoice(String choice){
		this.choice = choice;
	}
	
	public int getPrisonCount(){
		return prisonCount;
	}

	public void setPrisonCount(int prisonCount){
		this.prisonCount = prisonCount;
	}
	
	public int getEqualCount(){
		return equalCount;
	}

	public void setEqualCount(int equalCount){
		this.equalCount = equalCount;
	}
	
	public DiceCup getLastThrow(){
		return lastThrow;
	}
	
	public void setLastThrow(DiceCup lastThrow){
		this.lastThrow = lastThrow;
	}
	
	
}
