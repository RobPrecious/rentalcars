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
		    + "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css\" rel=\"stylesheet\"/>"
		    + "<h1>RentalCars Graduate Application - Technical Exercise</h1>"
		    + "<a href=\"/price-list\">Price List</a><br>" 
		    + "<a href=\"/spec-list\">Specification List</a><br>"
		    + "<a href=\"/rating-list\">Ratings List</a><br>"
		    	+ "<a href=\"/scores-list\">Scores List</a>"
		    	+ "<hr>";
	
	private String htmlFooter = "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script><script src=\"https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/jquery.dataTables.min.js\"></script><script src=\"https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.13/js/dataTables.bootstrap4.min.js\"></script>"
			+ "<script>"
			+ "$(document).ready(function() {\n"
			+ "  $('#cars').DataTable();\n"
			+ "})"
			+ "</script>";
			
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
        return formatHtml(dh.getPriceListHTML(cars));
    }
	
	@RequestMapping("/spec-list")
    public String specList() {
        return formatHtml(dh.getSpecListHTML(cars));
    }
	
	@RequestMapping("/rating-list")
    public String ratingList() {
        return formatHtml(dh.getRatingsListHTML(cars));
    }
	
	@RequestMapping("/scores-list")
    public String scoresList() {
        return formatHtml(dh.getScoresListHTML(cars));
    }
	
	private String formatHtml(String contents) {
		return  "<div class=\"container\">\n" + 
				"<div class=\"row justify-content-md-center\">" +
				this.htmlHeader +
				contents + 
				"</div></div>" +
				this.htmlFooter;
	}
	
	
}
