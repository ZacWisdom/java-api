//package metova.java.test;

//import java.util.UUID;
import java.util.Random;

public class User {
	//Class to hold User data. Getters/Setters for USER ID, email, and password.
	private String id;
	private String email;
	private String password;

	public User(String email, String password){
		this.id = generateID();
		this.email = email;
		this.password = password;
	}
	
	public User(String userID, String email, String password){
		this.id = userID;
		this.email = email;
		this.password = password;
	}
	
	private String generateID(){
		//return UUID.randomUUID().toString();
		Random rand = new Random();
		int num = rand.nextInt(9000000) + 1000000;
		return Integer.toString(num);
	}
	
	public String getID(){
		return id;
	}
	
	public String getIDString(){
		return id.toString();
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setPassword(String password){
		this.password = password;
	}

}