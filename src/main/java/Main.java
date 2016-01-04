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
            //attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());
		
	
	//Encountered bug. Second level (/api/cars) could not be accessed until first level(/api) had been called, this forces an early call to init to allow URLs to 
	// be processed. 
	
	//String initial = getinit();
	UsersManager UM = new UsersManager();
	UM.newUser("a@b.com", "pass1");
	UM.newUser("c@d.com", "pass2");
	
	CarsManager CM = new CarsManager();
	CM.newCar("1", "2", "Mazda", "Six", "2014");
	CM.newCar("3", "4", "Toyota", "Camry", "2008");
	new CarsController(CM);
	new UsersController(UM);
	
	get("/test", (req, res) -> test());
	
	//get("/api", (req, res) -> getinit());
	post("/api", (req, res) -> postinit());
	put("/api", (req, res) -> putinit());
	delete("/api", (req, res) -> deleteinit());
	/*
	after((req, res) -> {
			res.type("application/json");
		});*/
		
  }
  
  
}
