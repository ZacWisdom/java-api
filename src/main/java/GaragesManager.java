//package metova.java.test;

import java.util.*;

public class GaragesManager{
	private Map<String,Garage> garages = new HashMap<>();
	
	public List<Garage> getGarages(){
		return new ArrayList<>(garages.values());
	}
	public Garage getGarage(String id){
		return garages.get(id);
	}
	
	public Garage newGarage(String garageID, String userID, String name){
		checkNull(name);
		Garage garage = new Garage(garageID, userID, name);
		garages.put(garage.getID(), garage);
		return garage;
	}
	
	public Garage updateGarage(String id, String userID, String name){
		Garage garage = garages.get(id);
		checkNull(name);
		garage.setID(id);
		garage.setUserID(userID);
		garage.setName(name);
		return garage;
	}
	
	public String deleteGarage(String id){
		garages.remove(id);
		return "Deleted Garage " + id;
	}
	
	private void checkNull(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be blank");
		}
	}
}