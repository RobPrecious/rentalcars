package com.robprecious.rentalcars;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.lang.String;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPIController {
	private List<Car> cars;
	private DataHandler dh = new DataHandler();
	
	private String htmlHeader = "<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\""
		    + "crossorigin=\"anonymous\">"
		    + "<h1>RentalCars Graduate Application - Technical Exercise</h1>"
		    + "<a href=\"/price-list\">Price List</a><br>" 
		    + "<a href=\"/spec-list\">Specification List</a><br>"
		    + "<a href=\"/rating-list\">Ratings List</a><br>"
		    	+ "<a href=\"/spec-list\">Specification List</a>"
		    	+ "<hr>";
	
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
        return formatHtml(htmlHeader + formatToTable(dh.getPriceList(cars)));
    }
	
	@RequestMapping("/spec-list")
    public String specList() {
        return formatHtml(htmlHeader + formatToTable(dh.getSpecList(cars)));
    }
	
	@RequestMapping("/rating-list")
    public String ratingList() {
        return formatHtml(htmlHeader + formatToTable(dh.getRatingsList(cars)));
    }
	
	@RequestMapping("/scores-list")
    public String scoresList() {
        return formatHtml(htmlHeader + formatToTable(dh.getScoresList(cars)));
    }
	
	private String formatToTable(String str) {
		String[] items = str.split("\n");
		String output = "<ul class=\"list-group\">\n";
		for(String item: items) {
			output += "<li class=\"list-group-item\">" + item + "</li>";
		}
		output += "</ul></div></div></div>";
		return output;
	}
	
	private String formatHtml(String str) {
		return 	"<div class=\"container\">\n" + 
				"<div class=\"row justify-content-md-center\">" +
				str + 
				"</div></div>";
	}
}
