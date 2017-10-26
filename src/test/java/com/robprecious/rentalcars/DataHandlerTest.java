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
			//System.err.println("Caught MalformedURLException: " + e.getMessage());
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

}
