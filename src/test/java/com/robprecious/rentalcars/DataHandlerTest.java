package com.robprecious.rentalcars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

public class DataHandlerTest {

	private DataHandler classUnderTest;
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new DataHandler();
	}

	@Test
	public void testGetJSON() {
		try {
			JsonObject obj = classUnderTest.getJSON(new URL("http://www.rentalcars.com/js/vehicles.json"));
			JsonArray results = obj.getJsonObject("Search").getJsonArray("VehicleList");
			assertEquals(results.size(), 31);
		} catch (MalformedURLException e) {
			fail("Caught MalformedURLException: " + e.getMessage());
		}
	}
	
	@Test
	public void testImportCars() {
		DataHandler dh = new DataHandler();
		try {
			URL dataURL = new URL("http://www.rentalcars.com/js/vehicles.json");
			List<Car> cars = dh.importCars(dataURL);
			assertEquals(cars.size(), 31);
			assertEquals(cars.get(0).getSpecEntry(), "Ford Focus - CDMR - Compact - 5 doors - Manual - Petrol - AC");	
			
		} catch (MalformedURLException e) {
			fail("Caught MalformedURLException: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetPriceList() {
		List<Car> testCars = new ArrayList<Car>();
		Car car1 = new Car("CDMR","Ford Focus",157.85,"Hertz",8.9);
		Car car2 = new Car("FVAR","Ford Galaxy",706.89,"Hertz",8.9);
		testCars.add(car1);
		testCars.add(car2);

		DataHandler dh = new DataHandler();
		
		String expectedString = "1. Ford Focus - £157.85\n2. Ford Galaxy - £706.89\n"; 
				
		assertEquals(dh.getPriceList(testCars), expectedString);
	}
	
	@Test
	public void testGetSpecList() {
		List<Car> testCars = new ArrayList<Car>();
		Car car1 = new Car("CDMR","B Ford Focus",157.85,"Hertz",8.9);
		Car car2 = new Car("FVAR","A Ford Galaxy",706.89,"Hertz",8.9);
		testCars.add(car1);
		testCars.add(car2);

		DataHandler dh = new DataHandler();
		
		String expectedString = "1. A Ford Galaxy - FVAR - Full size - Passenger Van - Automatic - Petrol - AC\n" + 
								"2. B Ford Focus - CDMR - Compact - 5 doors - Manual - Petrol - AC\n";
		assertEquals(dh.getSpecList(testCars), expectedString);
	}
	
	@Test
	public void testGetRatingsList() {
		List<Car> testCars = new ArrayList<Car>();
		Car car1 = new Car("CDMR","Ford Focus",157.85,"Hertz",7.9);
		Car car2 = new Car("FVAR","Ford Galaxy",706.89,"Hertz",8.9);
		testCars.add(car1);
		testCars.add(car2);

		DataHandler dh = new DataHandler();
		
		String expectedString = "1. Ford Galaxy - Full size - Hertz - 8.9\n2. Ford Focus - Compact - Hertz - 7.9\n"; 
		assertEquals(dh.getRatingsList(testCars), expectedString);
	}
	
	@Test
	public void testGetScoresList() {
		List<Car> testCars = new ArrayList<Car>();
		Car car1 = new Car("CDMR","Ford Focus",157.85,"Hertz",7.9);
		Car car2 = new Car("FVAR","Ford Galaxy",706.89,"Hertz",8.9);
		testCars.add(car1);
		testCars.add(car2);

		DataHandler dh = new DataHandler();
		
		String expectedString = "1. Ford Galaxy - 7 - 8.9 - 15.9\n2. Ford Focus - 3 - 7.9 - 10.9\n"; 
		assertEquals(dh.getScoresList(testCars), expectedString);
	}

	@Test
	public void testGetPriceListHTML() {
		List<Car> testCars = new ArrayList<Car>();
		Car car1 = new Car("CDMR","Ford Focus",157.85,"Hertz",8.9);
		Car car2 = new Car("FVAR","Ford Galaxy",706.89,"Hertz",8.9);
		testCars.add(car1);
		testCars.add(car2);

		DataHandler dh = new DataHandler();
		
		String expectedString = "<h2>RentalCars Price List</h2>\n" +
				"<table class=\"table table-sm\" id=\"cars\">\n" + 
				"<thead>\n" + 
				"<tr>\n" + 
				"<th scope=\"col\">#</th>\n" + 
				"<th scope=\"col\">Name</th>\n" + 
				"<th scope=\"col\">Price</th>\n" + 
				"</tr>\n" + 
				"</thead>\n" + 
				"<tbody>\n" + 
				"<tr>\n" + 
				"<th scope=\"row\">1</th>\n" + 
				"<td>Ford Focus</td>\n" + 
				"<td>£157.85</td>\n" + 
				"</tr>\n" + 
				"<tr>\n" + 
				"<th scope=\"row\">2</th>\n" + 
				"<td>Ford Galaxy</td>\n" + 
				"<td>£706.89</td>\n" + 
				"</tr>\n" + 
				"</tbody>\n" + 
				"</table>"; 
				
		assertEquals(dh.getPriceListHTML(testCars), expectedString);
	}

	@Test
	public void testGetSpecListHTML() {
		List<Car> testCars = new ArrayList<Car>();
		Car car1 = new Car("CDMR","Ford Focus",157.85,"Hertz",8.9);
		Car car2 = new Car("FVAR","Ford Galaxy",706.89,"Hertz",8.9);
		testCars.add(car1);
		testCars.add(car2);

		DataHandler dh = new DataHandler();
		
		String expectedString = "<h2>RentalCars Specification List</h2>\n" + 
				"<table class=\"table table-sm\" id=\"cars\">\n" + 
				"<thead>\n" + 
				"<tr>\n" + 
				"<th scope=\"col\">#</th>\n" + 
				"<th scope=\"col\">Name</th>\n" + 
				"<th scope=\"col\">SIPP</th>\n" + 
				"<th scope=\"col\">Type</th>\n" + 
				"<th scope=\"col\">Doors</th>\n" + 
				"<th scope=\"col\">Transmission</th>\n" + 
				"<th scope=\"col\">Fuel</th>\n" + 
				"<th scope=\"col\">AC</th>\n" + 
				"</tr>\n" + 
				"</thead>\n" + 
				"<tbody>\n" + 
				"<tr>\n" + 
				"<th scope=\"row\">1</th>\n" + 
				"<td>Ford Galaxy</td>\n" + 
				"<td>FVAR</td>\n" + 
				"<td>Full size</td>\n" + 
				"<td>Passenger Van</td>\n" + 
				"<td>Automatic</td>\n" + 
				"<td>Petrol</td>\n" + 
				"<td>AC</td>\n" + 
				"</tr>\n" + 
				"<tr>\n" + 
				"<th scope=\"row\">2</th>\n" + 
				"<td>Ford Focus</td>\n" + 
				"<td>CDMR</td>\n" + 
				"<td>Compact</td>\n" + 
				"<td>5 doors</td>\n" + 
				"<td>Manual</td>\n" + 
				"<td>Petrol</td>\n" + 
				"<td>AC</td>\n" + 
				"</tr>\n" + 
				"</tbody>\n" + 
				"</table>"; 
				
		assertEquals(dh.getSpecListHTML(testCars), expectedString);
	}
	
	@Test
	public void testGetRatingsListHTML() {
		List<Car> testCars = new ArrayList<Car>();
		Car car1 = new Car("CDMR","Ford Focus",157.85,"Hertz",8.9);
		Car car2 = new Car("FVAR","Ford Galaxy",706.89,"Hertz",8.9);
		testCars.add(car1);
		testCars.add(car2);

		DataHandler dh = new DataHandler();
		
		String expectedString = "<h2>RentalCars Ratings List</h2>\n" + 
				"<table class=\"table table-sm\" id=\"cars\">\n" + 
				"<thead>\n" + 
				"<tr>\n" + 
				"<th scope=\"col\">#</th>\n" + 
				"<th scope=\"col\">Name</th>\n" + 
				"<th scope=\"col\">Type</th>\n" + 
				"<th scope=\"col\">Supplier</th>\n" + 
				"<th scope=\"col\">Rating</th>\n" + 
				"</tr>\n" + 
				"</thead>\n" + 
				"<tbody>\n" + 
				"<tr>\n" + 
				"<th scope=\"row\">1</th>\n" + 
				"<td>Ford Focus</td>\n" + 
				"<td>Compact</td>\n" + 
				"<td>Hertz</td>\n" + 
				"<td>8.9</td>\n" + 
				"</tr>\n" + 
				"<tr>\n" + 
				"<th scope=\"row\">2</th>\n" + 
				"<td>Ford Galaxy</td>\n" + 
				"<td>Full size</td>\n" + 
				"<td>Hertz</td>\n" + 
				"<td>8.9</td>\n" + 
				"</tr>\n" + 
				"</tbody>\n" + 
				"</table>"; 
				
		assertEquals(dh.getRatingsListHTML(testCars), expectedString);
	}
	
	@Test
	public void testGetScoressListHTML() {
		List<Car> testCars = new ArrayList<Car>();
		Car car1 = new Car("CDMR","Ford Focus",157.85,"Hertz",8.9);
		Car car2 = new Car("FVAR","Ford Galaxy",706.89,"Hertz",8.9);
		testCars.add(car1);
		testCars.add(car2);

		DataHandler dh = new DataHandler();
		
		String expectedString = "<h2>RentalCars Scores List</h2>\n" + 
				"<table class=\"table table-sm\" id=\"cars\">\n" + 
				"<thead>\n" + 
				"<tr>\n" + 
				"<th scope=\"col\">#</th>\n" + 
				"<th scope=\"col\">Name</th>\n" + 
				"<th scope=\"col\">Car Rating</th>\n" + 
				"<th scope=\"col\">Supplier Rating</th>\n" + 
				"<th scope=\"col\">Combined Rating</th>\n" + 
				"</tr>\n" + 
				"</thead>\n" + 
				"<tbody>\n" + 
				"<tr>\n" + 
				"<th scope=\"row\">1</th>\n" + 
				"<td>Ford Galaxy</td>\n" + 
				"<td>7</td>\n" + 
				"<td>8.9</td>\n" + 
				"<td>15.9</td>\n" + 
				"</tr>\n" + 
				"<tr>\n" + 
				"<th scope=\"row\">2</th>\n" + 
				"<td>Ford Focus</td>\n" + 
				"<td>3</td>\n" + 
				"<td>8.9</td>\n" + 
				"<td>11.9</td>\n" + 
				"</tr>\n" + 
				"</tbody>\n" + 
				"</table>"; 
		assertEquals(dh.getScoresListHTML(testCars), expectedString);
	}

}
