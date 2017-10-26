package com.robprecious.rentalcars;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
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
	
	

}
