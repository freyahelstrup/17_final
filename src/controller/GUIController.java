package controller;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_resources.GUI;
import entity.*;

public class GUIController {

	public static void initializeBoard(Board board){
		Field[] fields = board.getFields();
		desktop_fields.Field[] graphicfields = new desktop_fields.Field[fields.length];
		
		for(int i = 0; i < fields.length; i++){
			if (fields[i] instanceof Brewery){
				graphicfields[i] = new desktop_fields.Brewery.Builder()
						.setTitle(Messages.getFieldNames()[i])
						.setDescription(Messages.getFieldNames()[i])
						.setSubText(determineSubText(board, i))
						.setRent(determineRent(board, i))
						.build();	
			}
			else if (fields[i] instanceof Fleet){
				graphicfields[i] = new desktop_fields.Shipping.Builder()
						.setTitle(Messages.getFieldNames()[i])
						.setDescription(Messages.getFieldNames()[i])
						.setSubText(determineSubText(board, i))
						.setRent(determineRent(board, i))
						.build();	
			}
			else if (fields[i] instanceof Tax){
				graphicfields[i] = new desktop_fields.Tax.Builder()
						.setTitle(determineSubText(board, i))
						.setDescription(Messages.getFieldNames()[i])
						.setSubText(determineSubText(board, i))
						.build();
			}
			else if (fields[i] instanceof GoToPrison || i == 10){
					graphicfields[i] = new desktop_fields.Jail.Builder()
							.setTitle(determineSubText(board, i))
							.setDescription(Messages.getFieldNames()[i])
							.setSubText(determineSubText(board, i))
							.build();	
			}
			else if (fields[i] instanceof Chance){
					graphicfields[i] = new desktop_fields.Chance.Builder()
//							.setTitle(determineSubText(board, i))
//							.setDescription(Messages.getFieldNames()[i])
//							.setSubText(determineSubText(board, i))
							.build();
			}	
			else if (i == 20){
				graphicfields[i] = new desktop_fields.Refuge.Builder()
						.setTitle(Messages.getFieldNames()[i])
						.setDescription(Messages.getFieldNames()[i])
						.setSubText(determineSubText(board, i))
						.build();
			}	
			else if (i == 0){
				graphicfields[i] = new desktop_fields.Start.Builder()
						.setTitle(Messages.getFieldNames()[i])
						.setDescription(Messages.getFieldNames()[i])
						.setSubText(determineSubText(board, i))
						.build();	
			}
			else{
				graphicfields[i] = new desktop_fields.Street.Builder()
						.setBgColor(fields[i].getColor())
						.setTitle(Messages.getFieldNames()[i])
						.setDescription(Messages.getFieldNames()[i])
						.setSubText(determineSubText(board, i))
						.setRent(determineRent(board, i))
						.build();	
			}
			
			
		}

		GUI.create(graphicfields);
		GUI.displayChanceCard();
	}
	
	public static void addPlayer(String name, int balance, Color pieceColor){
		Car car = new Car.Builder()
				.primaryColor(pieceColor)
				.build();
		GUI.addPlayer(name, balance, car);
		GUI.setBalance(name, balance);
		GUI.setCar(1,name);
	}

	public static void removeAllCars(String playerName){
		GUI.removeAllCars(playerName);
	}
	
	private static String determineSubText(Board board, int fieldNumber){
		Field[] fields = board.getFields();
		
		String text = "";
		if(fields[fieldNumber] instanceof Ownable){
			text += Messages.getBoardMessages()[0]; //Price:
			text += " " + String.valueOf(((Ownable) fields[fieldNumber]).getPrice());
		}
		else if(fields[fieldNumber] instanceof Tax){
			text += Messages.getBoardMessages()[3]; //Pay:
			text += " " + String.valueOf(((Tax) fields[fieldNumber]).getTaxAmount());
			if(((Tax) fields[fieldNumber]).getTaxRate() > 0){
				text += " " + Messages.getBoardMessages()[4];
				text += " " + String.valueOf(((Tax) fields[fieldNumber]).getTaxRate());
				text += "% " + Messages.getBoardMessages()[5];
			}
		}
		return text;
	}
	
	private static String determineRent(Board board, int fieldNumber){ 
		Field[] fields = board.getFields();
		
		String rent = "";
		if(fields[fieldNumber] instanceof Ownable){
			rent += Messages.getGeneralMessages()[22] + String.valueOf((((Ownable) fields[fieldNumber]).getRent()));
		}

		return rent;
	}
	
	public static String getUserChoice(String message, String ... options){
		return GUI.getUserSelection(message, options);
	}
	
	public static String getUserButtonPressed(String message, String ... buttons){
		return GUI.getUserButtonPressed(message, buttons);
	}

	public static void showMessage(String message){
		GUI.showMessage(message);
	}
	
	public static void setDice(int value1, int value2){
		GUI.setDice(value1, value2);
	}
	
	public static void setCar(int position, String playerName){
		GUI.setCar(position, playerName);
	}
	
	public static void setFieldOwner(String playerName, int fieldNumber){
			GUI.setOwner(fieldNumber, playerName);
	}

	public static void removeFieldOwner(int fieldNumber){
			GUI.removeOwner(fieldNumber);
	}
	
	public static void setPlayerBalance(String playerName, int balance){
		GUI.setBalance(playerName, balance);
	}
	
	public static String getUserSelection(String message, String ... options){
		return GUI.getUserSelection(message, options);	
	}
	
	public static void setHouses(int fieldNumber, int houses){
		GUI.setHouses(fieldNumber, houses);
	}

	public static void setHotel(int fieldNumber){
		GUI.setHotel(fieldNumber, true);
	}
	
}

