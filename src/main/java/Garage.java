//package metova.java.test;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Garage {
	private String id;
	private String userID;
	private String name;
	private String createDate;
	private String updateDate;
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	
	public Garage(String userID, String name){
		this.id = UUID.randomUUID().toString();
		this.userID = userID;
		this.name = name;
		this.createDate = dateFormat.format(date).toString();
		this.updateDate = dateFormat.format(date).toString();
		
	}
	
	public void Update(){
		this.updateDate = dateFormat.format(date).toString();
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