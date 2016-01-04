//package metova.java.test;

import static metova.java.test.ToJSON.*;
import static spark.Spark.*;

public class UsersController {
	//HTTP catches
	public UsersController(final UsersManager UM) {
		
		get("/api/users", (req,res) -> UM.getUsers(), json());
	
	}
}