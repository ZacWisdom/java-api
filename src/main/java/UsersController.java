//package metova.java.test;

import static metova.java.test.ToJSON.*;
import static spark.Spark.*;

public class UsersController {
	private UsersManager UM;
	
	public UsersController(final UsersManager UM) {
		//Initializes Usermanager for other functions.
		this.UM = UM;
		
		//Checks authorization. Proceeds if valid token, rejects and asks to login if expired token.
		//Currently the TTL for the token is not implemented. But the framework for the API to accept a Time-To-Life backend is in place. 
		before("/*", (req, res)->{
			String token = req.headers("token");
			
			if (token.equals("abcdef")){
				return;
			}else{
				halt(401, "Token expired. Please login.");
			}
			
			
		});
		get("/api/users", (req, res) -> UM.getUsers(), json());
		//Catches URL : POST /api/users.
		//Creates new User. Stores as JSON object.
		post("/api/users", (req, res) -> {
			res.status(201);
			UM.newUser(
				req.queryParams("email"),
				req.queryParams("password")
				);
			return usersResponse(req.queryParams("email"));
		});
		
		//Catches URL : POST /api/users/login.
		//Logs user in with email and password. 
		post("/api/users/login", (req, res) -> {
			res.status(201);
			return usersResponse(req.queryParams("email"));
		} );
		
		//Catch for thrown Illegal arguement exceptions in failed URL redirects.
		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(422);
			res.body(toJson(e.getMessage()));
		});
	}
	//JSON builder for USER login response.
	private String usersResponse(String email){
		User user = UM.getUser(email);
		
		return "{\n \"email\": \"" + user.getEmail() +"\", \n " + "\"id\":" + user.getID() + ",\n" + "\"authentication_token\":" + "\"PLACEHOLDERTOKEN\"\n}";
		
	}
}