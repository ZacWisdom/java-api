//package metova.java.test;

import static metova.java.test.ToJSON.*;
import java.util.Random;
import static spark.Spark.*;
import java.util.*;

public class CarsController {
	//HTTP catches
	public CarsController(final CarsManager CM, final GaragesManager GM) {
		
		//Preprocessing for Authentication token. Generation and TTL not implemented. 
		before("/*", (req, res)->{
			String token = req.headers("token");
			
			if (token.equals("abcdef")){
				return;
			}else{
				halt(401, "Token expired. Please login.");
			}
			
			
		});
	
		//Catch for URL GET : /api/cars
		//Returns all cars
		get("/api/cars", (req, res) -> {
			
			res.type("application/json");
			res.header("Authorization", "Token");
			res.header("id", req.headers("id"));
			res.header("token", "GET TOKEN");
			return CM.getCars();

		}, json());
		
		//Catch for URL GET : /api/cars/:id
		//Returns car with id :id
		get("/api/cars/:id", (req, res) -> {
			String carID = req.params("id");
			res.type("application/json");
			res.header("Authorization", "Token");
			res.header("id", req.headers("id"));
			res.header("token", "GET TOKEN");
			return CM.getCar(carID);

		}, json());
		
		//Catch for URL GET : /api/garages/:garageID/cars/:carID
		//Returns car with id :carID in garage with id :garageID
		get("/api/garages/:garageID/cars/:carID", (req, res) -> {
			String garageID = req.params("garageID");
			String carID = req.params("carID");
			
			res.type("application/json");
			res.header("Authorization", "Token");
			res.header("id", req.headers("id"));
			res.header("token", "GET TOKEN");

			if(CM.getCar(carID).getGarageID().equals(garageID)){
				return toJson(CM.getCar(carID));
			}else{
				res.status(404);
				return "{\n \"error\": \"Record not found\", \n " + "\"message\": \"Could not find Car with id: " + carID + "in Garage with id: " + garageID + "\"}";
			}
		});
		
		//Catch for URL GET : /api/garages/:garageID/cars
		//Returns all cars in garage with id :garageID
		get("/api/garages/:garageID/cars", (req, res) -> {
			
			res.type("application/json");
			res.header("Authorization", "Token");
			res.header("id", req.headers("id"));
			res.header("token", "GET TOKEN");
			
			//Pulls Map for cars from Manager class, iterated over values finding matching garageID with URL parameter.
			String garageID = req.params(":garageID");
			Map<String, Car> cars = CM.getCarsMap();
			ArrayList<Car> carsByGarage = new ArrayList<>();
			for (Map.Entry<String,Car> entry : cars.entrySet()) {
				String key = entry.getKey();
				Car car = entry.getValue();
				if (car.getGarageID().equals(garageID)){
					carsByGarage.add(car);
				}
			}
			
			return carsByGarage;
		
		}, json());
		
		//Catch for URL POST : /api/cars
		//Creates new car from request header and parameters.
		post("/api/cars", (req, res) -> {
			res.status(201);
			String id = req.headers("id");
			String carID = generateID();
			res.type("application/json");
			res.header("Authorization", "Token");
			res.header("id", id);
			res.header("token", "GET TOKEN");
			CM.newCar(carID,
				id,
				req.queryParams("make"),
				req.queryParams("model"),
				req.queryParams("year")
				);
			return CM.getCar(carID);
			
		}, json());	
	
		//Catch for URL POST : /api/garages/:garageID/cars
		//Returns all cars in garage with id :garageID
		post("/api/garages/:garageID/cars", (req, res) -> {
		res.status(201);
			String id = req.headers("id");
			String carID = generateID();
			String garageID = req.params("garageID");
			res.type("application/json");
			res.header("Authorization", "Token");
			res.header("id", id);
			res.header("token", "GET TOKEN");
			CM.newCar(carID,
				id,
				garageID,
				req.queryParams("make"),
				req.queryParams("model"),
				req.queryParams("year")
				);
			return CM.getCar(carID);
			
		}, json());	
		//Catch for URL PUT : /api/cars/:id
		//Updates car with id :id using given request parameters.
		put("/api/cars/:id", (req, res) -> {
			String carID = req.params(":id");
			String userID = req.headers("id");
			String make = req.queryParams("make");
			String model = req.queryParams("model");
			String year = req.queryParams("year");
			
			res.type("application/json");
		
			if(CM.getCar(carID).getUserID().equals(userID)){
				res.status(204);
				res.header("Authorization", "token");
				res.header("token", "PLACEHOLDER TOKEN");
				res.header("id", userID);
				return toJson(CM.updateCar(carID, make, model, year));
			}else{
				res.status(403);
				return "{\n \"error\": \"not authorized to edit\", \n " + "\"message\": not allowed to update this car:" + toJson(CM.getCar(carID));
			}
		});
		//Catch for URL PUT : /api/garages/:garageID/cars/:carID
		//Updates Car with id :carID in garage with id :garageID.
		put("/api/garages/:garageID/cars/:carID", (req, res) -> {
			String garageID = req.params(":garageID");
			String carID = req.params(":carID");
			String userID = req.headers("id");
			String make = req.queryParams("make");
			String model = req.queryParams("model");
			String year = req.queryParams("year");
			
			res.type("application/json");
			res.header("Authorization", "Token");
			res.header("id", req.headers("id"));
			res.header("token", "GET TOKEN");
			
			if(CM.getCar(carID).getUserID().equals(userID)){
				res.status(204);
				res.header("Authorization", "token");
				res.header("token", "PLACEHOLDER TOKEN");
				res.header("id", userID);
				return toJson(CM.updateCar(carID, make, model, year));
			}else{
				res.status(403);
				return "{\n \"error\": \"not authorized to edit\", \n " + "\"message\": not allowed to update this car:" + toJson(CM.getCar(carID));
			}
			
		});
		//Catch for URL DELETE : /api/cars/:id
		//Delets car with id :id
		delete("/api/cars/:id", (req, res) -> {
			String carID = req.params(":id");
			String userID = req.headers("id");
			
			res.header("Authorization", "token");
			res.header("token", "PLACEHOLDER TOKEN");
			res.header("id", userID);
			
			if(CM.getCar(carID).getUserID().equals(userID)){
				res.status(204);
				return CM.deleteCar(carID);
			}else{
				res.status(403);
				res.type("application/json");
				return "{\n \"error\": \"not authorized to edit\", \n " + "\"message\": not allowed to update this car:" + toJson(CM.getCar(carID));
			}
		
		});
		//Catch for URl DELETE : /api/garage/:garageID/cars/:carID
		//Delets car with id :carID in garage with id :garageID
		delete("/api/garages/:garageID/cars/:carID", (req, res) -> {
			String carID = req.params(":carID");
			String garageID =  req.params(":garageID");
			String userID = req.headers("id");
			
			res.header("Authorization", "token");
			res.header("token", "PLACEHOLDER TOKEN");
			res.header("id", userID);
			res.type("application/json");
			
			if(CM.getCar(carID) != null){
				if(CM.getCar(carID).getUserID().equals(userID)){
					res.status(204);
					String result = CM.deleteCar(carID);
				
					return result;			
				}else{
					res.status(403);
					res.type("application/json");
					return "{\n \"error\": \"not authorized to edit\", \n " + "\"message\": not allowed to update this car:" + toJson(CM.getCar(carID));
				}
			}else{
				res.status(404);
				return "{\n \"error\": \"Record not found\", \n " + "\"message\": \"Could not find Car with id: " + carID + "\"\n}";
			}
			
		
	});
	
	}
	//Generates randomID for garage. 
	private String generateID(){
		Random rand = new Random();
		int num = rand.nextInt(9000000) + 1000000;
		return Integer.toString(num);
	}

}
