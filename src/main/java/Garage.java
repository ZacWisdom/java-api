//package metova.java.test;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.UUID;

public class Garage {
	
	//Class to hold Garage data. Getters/setters for GARAGE id, userID, name, and timestamp information.
	private String id;
	private String userID;
	private String name;
	private String createDate;
	private String updateDate;
	
	
	
	public Garage(String garageID, String userID, String name){
		this.id = garageID;
		this.userID = userID;
		this.name = name;
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
	
	public String getID(){
		return id;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public String getName(){
		return name;
	}
	
	public String getCreateDate(){
		return createDate;
	}

	public String getUpdateDate(){
		return updateDate;
	}
	
	public void setID(String id){
		this.id = id;
		Update();
	}
	
	public void setUserID(String userID){
		this.userID = userID;
		Update();
	}
	
	public void setName(String name){
		this.name = name;
		Update();
	}
	
	
	
}