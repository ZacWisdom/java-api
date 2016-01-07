//package metova.java.test;

import java.util.*;

public class CarsManager{
	private Map<String,Car> cars = new HashMap<>();
		
	public List<Car> getCars(){
		return new ArrayList<>(cars.values());
	}
	
	public Map<String,Car> getCarsMap(){
		return cars;
	}
	
	public Car getCar(String id){
		return cars.get(id);
	}
	
	public Car newCar(String carID, String userID, String garageID, String make, String model, String year){
		checkNull(make);
		Car car = new Car(carID, userID, garageID, make, model, year);
		cars.put(car.getID(), car);
		return car;
	}
	
	public Car newCar(String carID, String userID, String make, String model, String year){
		checkNull(make);
		Car car = new Car(carID, userID, make, model, year);
		cars.put(car.getID(), car);
		return car;
	}
	
	public Car updateCar(String id, String make, String model, String year){
		checkNull(make);
		Car car = cars.get(id);
		car.setMake(make);
		car.setModel(model);
		car.setYear(year);
		return car;
	}
	
	public Car updateCar(String id, String garageID, String make, String model, String year){
		checkNull(make);
		Car car = cars.get(id);
		car.setMake(make);
		car.setModel(model);
		car.setYear(year);
		car.setGarageID(garageID);
		return car;
	}
	
	public String deleteCar(String id){
			cars.remove(id);
			return "Deleted Car " + id;
	
	}
	
	private void checkNull(String make) {
		if (make == null || make.isEmpty()) {
			throw new IllegalArgumentException("Make cannot be blank");
		}
		
	}
	
}