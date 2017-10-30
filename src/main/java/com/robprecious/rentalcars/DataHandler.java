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
	
	public String getPriceListHTML(List<Car> cars) {
		String[] headings = new String[]{"#", "Name", "Price"};

		Collections.sort(cars, new Comparator<Car>(){
		    public int compare(Car c1, Car c2) {
		        return Double.compare(c1.getPrice(),c2.getPrice());
		    }
		});
		
		return  "<h2>RentalCars Price List</h2>\n" + formatToTable(headings, htmlTableContents(cars,1));
	}
	
	public String getSpecListHTML(List<Car> cars) {
		String[] headings = new String[]{"#", "Name", "SIPP", "Type", "Doors", "Transmission", "Fuel", "AC"};

		Collections.sort(cars, new Comparator<Car>(){
		    public int compare(Car c1, Car c2) {
		    		return -Double.compare(c1.getCombinedScore(),c2.getCombinedScore());
		    }
		});
		
		return  "<h2>RentalCars Specification List</h2>\n" + formatToTable(headings,  htmlTableContents(cars,2));
	}
	

	public String getRatingsListHTML(List<Car> cars) {
		String[] headings = new String[]{"#", "Name", "Type", "Supplier", "Rating"};

		Collections.sort(cars, new Comparator<Car>(){
		    public int compare(Car c1, Car c2) {
		    		return -Double.compare(c1.getRating(),c2.getRating());
		    }
		});
		
		return  "<h2>RentalCars Ratings List</h2>\n" + formatToTable(headings,  htmlTableContents(cars,3));
	}
	
	public String getScoresListHTML(List<Car> cars) {
		String[] headings = new String[]{"#", "Name", "Car Rating", "Supplier Rating", "Combined Rating"};

		Collections.sort(cars, new Comparator<Car>(){
		    public int compare(Car c1, Car c2) {
		    		return -Double.compare(c1.getCombinedScore(),c2.getCombinedScore());
		    }
		});
		
		return  "<h2>RentalCars Scores List</h2>\n" + formatToTable(headings,  htmlTableContents(cars,4));

	}
	
	// HTML Formatting functions
	private String htmlTableContents(List<Car> cars, int itemsIndex) {
		String outputRow = "";
		String outputTable = "";

		Integer index = 1;
		for (Car car : cars) {
			outputRow = htmlRowHeader(String.valueOf(index));
			
			for(String item : car.getItems(itemsIndex)){
				outputRow += htmlCell(item);
			}
			
			outputTable += htmlRow(outputRow);

			index ++;
		}
		
		return outputTable;
	}
	
	private String htmlRowHeader(String item) {
		return "<th scope=\"row\">"+ item + "</th>\n";
	}
	
	private String htmlCell(String item) {
		return "<td>"+ item + "</td>\n";
	}
	
	private String htmlRow(String content) {
		return "<tr>\n"+ content + "</tr>\n";
	}
	
	private String formatToTable(String[] headings, String contents) {
		
		String output = "<table class=\"table table-sm\" id=\"cars\">\n" + 
						"<thead>\n" + 
						"<tr>\n";
		for(String heading: headings) {
			output += "<th scope=\"col\">"+heading+"</th>\n";
		}
		output += "</tr>\n</thead>\n<tbody>\n";
		
		output += contents;
		
		output += "</tbody>\n</table>";
		return output;
	}
}
