//package metova.java.test;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Car{

	private String id;
	private String userID;
	private String garageID;
	private String make;
	private String model;
	private String year;
	private String createDate;
	private String updateDate;
	
	


	public Car(String userID, String garageID, String make, String model, String year){
		this.id = UUID.randomUUID().toString();
		this.userID = userID;
		this.garageID = garageID;
		this.make = make;
		this.model = model;
		this.year = year;
		this.createDate = formattedDate();
		this.updateDate = formattedDate();
		
	}
	
	private String formattedDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		return dateFormat.format(date).toString();
	}

	public void Update(){
		this.updateDate = formattedDate();
	}
	
	public String getCreateDate(){
		return createDate;
	}

	public String getUpdateDate(){
		return updateDate;
	}
	
	public String getID(){
		return id;
	}
	
	public String getUserID(){
		return userID;
	}

	public String getGarageID(){
		return garageID;
	}
	
	public String getMake(){
		return make;
	}
	
	public String getModel(){
		return model;
	}
	
	public String getYear(){
		return year;
	}
	
	public void setGarageID(String garageID){
		this.garageID = garageID;
		Update();
	}
	
	public void setUserID(String userID){
		this.userID = userID;
		Update();
	}
	
	public void setMake(String make){
		this.make = make;
		Update();
	}
	
	public void setModel(String model){
		this.model = model;
		Update();
	}
	
	public void setYear(String year){
		this.year = year;
		Update();
	}
}
