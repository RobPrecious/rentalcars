package com.robprecious.rentalcars;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CarTest {

	private Car classUnderTest;
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new Car("CDMR", "Ford Focus", 157.85, "Hertz", 8.9);

	}

	@Test
	public void testGetPrice() {
		String strPrice = classUnderTest.getStringPrice();
		assertEquals(strPrice, "£157.85");
		
	}

	@Test
	public void testGetPriceEntry() {
		String strPriceEntry = classUnderTest.getPriceEntry();
		assertEquals(strPriceEntry, "Ford Focus - £157.85");	
	}
	
	@Test
	public void testGetSpecEntry() {
		String strSpecEntry = classUnderTest.getSpecEntry();
		assertEquals(strSpecEntry, "Ford Focus - CDMR - Compact - 5 doors - Manual - Petrol - AC");	
	}
	
	@Test 
	public void testGetRatingsEntry() {
		String strRatingsEntry = classUnderTest.getRatingsEntry();
		assertEquals(strRatingsEntry, "Ford Focus - Compact - Hertz - 8.9");	
	}
	
	@Test 
	public void testGetScoresEntry() {
		String strScoressEntry = classUnderTest.getScoresEntry();
		assertEquals(strScoressEntry, "Ford Focus - 3 - 8.9 - 11.9");	
	}
}
