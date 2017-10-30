package com.robprecious.rentalcars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class CarTest {

	private Car classUnderTest;
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new Car("CDMR", "Ford Focus", 157.85, "Hertz", 8.9);

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

	@Test
	public void testGetItems() {
		String[] priceItems = classUnderTest.getItems(1);
		assertEquals(priceItems.length, 2);
		assertEquals(priceItems[0], "Ford Focus");
		assertEquals(priceItems[1], "£157.85");
		
		String[] specItems = classUnderTest.getItems(2);
		assertEquals(specItems.length, 7);
		assertEquals(specItems[0], "Ford Focus");
		assertEquals(specItems[1], "CDMR");
		assertEquals(specItems[2], "Compact");
		assertEquals(specItems[3], "5 doors");
		assertEquals(specItems[4], "Manual");
		assertEquals(specItems[5], "Petrol");
		assertEquals(specItems[6], "AC");
		
		String[] ratingsItems = classUnderTest.getItems(3);
		assertEquals(ratingsItems.length, 4);
		assertEquals(ratingsItems[0], "Ford Focus");
		assertEquals(ratingsItems[1], "Compact");
		assertEquals(ratingsItems[2], "Hertz");
		assertEquals(ratingsItems[3], "8.9");
		
		String[] scoresItems = classUnderTest.getItems(4);
		assertEquals(scoresItems.length, 4);
		assertEquals(scoresItems[0], "Ford Focus");
		assertEquals(scoresItems[1], "3");
		assertEquals(scoresItems[2], "8.9");
		assertEquals(scoresItems[3], "11.9");
		
		String[] errorCase = classUnderTest.getItems(0);
		assertNull(errorCase);

		
		}
	
}
