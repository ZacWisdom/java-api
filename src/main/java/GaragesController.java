//package metova.java.test;

import static metova.java.test.ToJSON.*;
import static spark.Spark.*;
import java.util.Random;
//import java.util.UUID;

public class GaragesController {
	//HTTP catches
	public GaragesController(final GaragesManager GM) {
	//Preprocessing for token authentication. TTL Not implemented.
	before("/*", (req, res)->{
			String token = req.headers("token");
			
			if (token.equals("abcdef")){
				return;
			}else{
				halt(401, "Token expired. Please login.");
			}
			
			
		});
	//Catch for URL GET : /api/garages.
	//Returns all garages.
	get("/api/garages", (req, res) -> {
			
			res.header("Authorization", "Token");
			res.header("id", "GET ID");
			res.header("token", "GET TOKEN");
			res.type("application/json");
			return GM.getGarages();
		}, json());
	
	//Catch for URL GET : /api/garages/:id
	//Returns Garage with id :id.
	get("/api/garages/:id", (req, res) -> {
			String garageId = req.params(":id");
			Garage garage = GM.getGarage(garageId);
			if (garage != null) {
				res.header("Authorization", "Token");
				res.header("id", "GET ID");
				res.header("token", "GET TOKEN");
				res.type("application/json");
				return garage;
			}
			res.status(400);
			return String.format("No Garage with id '%s' found", garageId);
			
			
		}, json());
	
	//Catch for URL POST : /api/garages
	//Creates new garage with from parameters, header ID value, and generated garage id.
	post("/api/garages", (req, res) -> {
			res.status(201);
			String id = req.headers("id");
			String garageID = generateID();
			res.type("application/json");
			GM.newGarage(garageID,
				id,
				req.queryParams("name")
				);
			return GM.getGarage(garageID);
		}, json());	
	
	//Catch for URL PUT : /api/garages/:id
	//Updates Garage with id :id.
	put("/api/garages/:id", (req, res) -> {
		String garageID = req.params(":id");
		String userID = req.headers("id");
		String name = req.queryParams("name");
		
		res.type("application/json");
		
		if(GM.getGarage(garageID).getUserID().equals(userID)){
			res.status(204);
			res.header("Authorization", "token");
			res.header("token", "PLACEHOLDER TOKEN");
			res.header("id", userID);
			return toJson(GM.updateGarage(garageID, userID, name));
		}else{
			res.status(403);
			return "{\n \"error\": \"not authorized to edit\", \n " + "\"message\": not allowed to update this garage:" + toJson(GM.getGarage(garageID));
		}
		});
	//Catch for URL DELETE : /api/garages/:id
	//Deletes garage with id :id.
	delete("/api/garages/:id", (req, res) -> {
		String garageID = req.params(":id");
		String userID = req.headers("id");
		
		res.header("Authorization", "token");
		res.header("token", "PLACEHOLDER TOKEN");
		res.header("id", userID);
		
		if(GM.getGarage(garageID).getUserID().equals(userID)){
			res.status(204);
			return GM.deleteGarage(garageID);
		}else{
			res.status(403);
			res.type("application/json");
			return "{\n \"error\": \"not authorized to edit\", \n " + "\"message\": not allowed to update this garage:" + toJson(GM.getGarage(garageID));
		}
		
	});
	//Catch for thrown Illegal Arguements.
	exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(422);
			res.body(toJson(e.getMessage()));
		});
	
	}

	
	private String generateID(){
		//return UUID.randomUUID().toString();
		Random rand = new Random();
		int num = rand.nextInt(9000000) + 1000000;
		return Integer.toString(num);
	}

}