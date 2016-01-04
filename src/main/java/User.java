//package metova.java.test;

import java.util.UUID;

public class User {

	private String id;
	private String email;
	private String password;

	public User(String email, String password){
		this.id = UUID.randomUUID().toString();
		this.email = email;
		this.password = password;
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