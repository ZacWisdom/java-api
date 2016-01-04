//package metova.java.test;

import java.util.*;
public class UsersManager{
	private Map<String, User> users = new HashMap<>();
	
	public List<User> getUsers(){
		return new ArrayList<>(users.values());
	}
	
	public User getUser(String id){
		return users.get(id);
	}
	
	public User newUser(String email, String password){
		User user = new User(email, password);
		users.put(user.getID(), user);
		return user;
	
	}
	/*
	public User updateUser(String id, String email, String password){
		//TODO::
	}*/
	
	private void failIfInvalid(String email, String password) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Parameter 'email' cannot be empty");
		}
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("Parameter 'password' cannot be empty");
		}
	}
}