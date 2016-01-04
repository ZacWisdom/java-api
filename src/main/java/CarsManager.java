//package metova.java.test;

import java.util.*;

public class CarsManager{
	private Map<String,Car> cars = new HashMap<>();
	
	public List<Car> getCars(){
		return new ArrayList<>(cars.values());
	}
	public Car getCar(String id){
		return cars.get(id);
	}
	
	public Car newCar(String userID, String garageID, String make, String model, String year){
		Car car = new Car(userID, garageID, make, model, year);
		cars.put(car.getID(), car);
		return car;
	}
	
	public Car updateCar(String id, String make, String model, String year){
		Car car = cars.get(id);
		if (car == null){
			//TODO::ERROR HANDLE
		}
		car.setMake(make);
		car.setModel(model);
		car.setYear(year);
		return car;
	}
	
	private void failIfInvalid(String make, String model, String year) {
		if (make == null || make.isEmpty()) {
			throw new IllegalArgumentException("Parameter 'make' cannot be empty");
		}
		if (model == null || model.isEmpty()) {
			throw new IllegalArgumentException("Parameter 'model' cannot be empty");
		}
		if (year == null || year.isEmpty()) {
			throw new IllegalArgumentException("Parameter 'year' cannot be empty");
		}
	}
	
}