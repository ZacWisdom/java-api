//package metova.java.test;

import static metova.java.test.ToJSON.*;
import static spark.Spark.*;

public class CarsController {
	//HTTP catches
	public CarsController(final CarsManager CM) {
		get("/api/cars", (req, res) -> CM.getCars(), json());
		
		
		
		
	}

}
