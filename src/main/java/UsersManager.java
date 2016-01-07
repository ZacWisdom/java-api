// package metova.java.test;

import java.util.*;
public class UsersManager{
	private Map<String, User> users = new HashMap<>();
	
	public List<User> getUsers(){
		return new ArrayList<>(users.values());
	}
	
	public User getUser(String email){
		return users.get(email);
	}
	
	public User newUser(String email, String password){
		checkNull(email, password);
		User user = new User(email, password);
		users.put(user.getEmail(), user);
		return user;
	
	}
	
	public User newUser(String userID, String email, String password){
		checkNull(email, password);
		User user = new User(userID, email, password);
		users.put(user.getEmail(), user);
		return user;
	
	}
	
	private void checkNull(String email, String password) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Email cannot be blank");
		}
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("Password cannot be blank");
		}
	}
}