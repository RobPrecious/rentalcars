package com.robprecious.rentalcars;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DataHandler {

	public JsonObject getJSON(URL url) {
		InputStream is;
		try {
			is = url.openStream();
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
			return null;
		}
		JsonReader rdr = Json.createReader(is);
		JsonObject obj = rdr.readObject();
		return obj;
		
	}
	
	public List<Car> importCars(URL url){
		List<Car> carsOutput = new ArrayList<Car>();
		JsonObject obj = this.getJSON(url);
		JsonArray results = obj.getJsonObject("Search").getJsonArray("VehicleList");
		
		for (JsonObject result : results.getValuesAs(JsonObject.class)) {
			Car car = new Car(result.getString("sipp"),
							  result.getString("name"), 
							  result.getJsonNumber("price").bigDecimalValue().doubleValue(),
							  result.getString("supplier"),
							  result.getJsonNumber("rating").bigDecimalValue().doubleValue()
							  );
			
			carsOutput.add(car);
		}
		return carsOutput;
	}
	
	public String getPriceList(List<Car> cars) {
		Collections.sort(cars, new Comparator<Car>(){
		    public int compare(Car c1, Car c2) {
		        return Double.compare(c1.getPrice(),c2.getPrice());
		    }
		});
		
		String outputStr = "";
		Integer index = 1;
		for (Car car : cars) {
			outputStr += index + ". " + car.getPriceEntry() + "\n";
			index ++;
		}	
		return outputStr;
	}
	
	public String getSpecList(List<Car> cars) {
		Collections.sort(cars, new Comparator<Car>(){
		    public int compare(Car c1, Car c2) {
		    		return c1.getName().compareTo(c2.getName());
		    }
		});
		
		String outputStr = "";
		Integer index = 1;
		for (Car car : cars) {
			outputStr += index + ". " + car.getSpecEntry() + "\n";
			index ++;
		}	
		return outputStr;
	}
	
	public String getRatingsList(List<Car> cars) {
		Collections.sort(cars, new Comparator<Car>(){
		    public int compare(Car c1, Car c2) {
		    		return -Double.compare(c1.getRating(),c2.getRating());
		    }
		});
		
		String outputStr = "";
		Integer index = 1;
		for (Car car : cars) {
			outputStr += index + ". " + car.getRatingsEntry() + "\n";
			index ++;
		}	
		return outputStr;
	}
	
	public String getScoresList(List<Car> cars) {
		Collections.sort(cars, new Comparator<Car>(){
		    public int compare(Car c1, Car c2) {
		    		return -Double.compare(c1.getCombinedScore(),c2.getCombinedScore());
		    }
		});
		
		String outputStr = "";
		Integer index = 1;
		for (Car car : cars) {
			outputStr += index + ". " + car.getScoresEntry() + "\n";
			index ++;
		}	
		return outputStr;
	}
	

}
