package com.robprecious.rentalcars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

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

}
