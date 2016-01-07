//package metova.java.test;

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static metova.java.test.initializer.*;
import static metova.java.test.ToJSON.*;
import static spark.Spark.*;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {

  public static void main(String[] args) {
	
    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");
	
    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());
		
	
	//For scope of this project, I did not use externally saved data. 
	//Because everything is done in JSON format, expanding this functionality would be feasable.
	
	//UsersManager class to manage create, update, and delete of users.
	UsersManager UM = new UsersManager();
	UM.newUser("123456", "a@b.com", "pass1");
	UM.newUser("7891011", "c@d.com", "pass2");
	//CarsManager class to manage create, update and delete of cars.
	CarsManager CM = new CarsManager();
	CM.newCar("1111111", "123456", "1111111", "Mazda", "Six", "2014");
	CM.newCar("2222222", "7891011", "2222222", "Toyota", "Camry", "2008");
	//GaragesManager class to manage create, update, and delete of garages.
	GaragesManager GM = new GaragesManager();
	GM.newGarage("1111111", "123456", "Zac's Garage");
	GM.newGarage("2222222", "7891011", "Jack's Garage");
	//Controllers for all three Managers. These controllers handle all URL redirections. 
	//Controllers Take the managers as parameter.
	new GaragesController(GM);
	new CarsController(CM, GM);
	new UsersController(UM);
	
		
  }
  
  
}
