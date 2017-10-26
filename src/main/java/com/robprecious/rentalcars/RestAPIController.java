package com.robprecious.rentalcars;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPIController {
	private List<Car> cars;
	private DataHandler dh = new DataHandler();
	
	RestAPIController(){
		
		try {
			URL dataURL = new URL("http://www.rentalcars.com/js/vehicles.json");
			cars = dh.importCars(dataURL);
		}
		catch (MalformedURLException e) {
			System.err.println("Caught MalformedURLException: " + e.getMessage());
		}
	}
	
	@RequestMapping("/price-list")
    public String priceList() {
        return dh.getPriceList(cars).replaceAll("\n", "<br>");
    }
	
	@RequestMapping("/spec-list")
    public String specList() {
        return dh.getSpecList(cars).replaceAll("\n", "<br>");
    }
	
	@RequestMapping("/rating-list")
    public String ratingList() {
        return dh.getRatingsList(cars).replaceAll("\n", "<br>");
    }
	
	@RequestMapping("/scores-list")
    public String scoresList() {
        return dh.getScoresList(cars).replaceAll("\n", "<br>");
    }
}
