package com.robprecious.rentalcars;

import java.text.DecimalFormat;

public class Car {
     String sipp;
	 String name;
	 Double price;
	 String supplier;
	 Double rating;
	 String type;
	 String form;
	 String transmission;
	 String aircon;
	 String fuel = "Petrol";
	 Integer vehicleScore;
	 Double combinedScore;
	 
	 public Car(String sipp, String name, Double price, String supplier, Double rating) {
		 this.sipp = sipp;
		 this.name = name;
		 this.price = price;
		 this.supplier = supplier;
		 this.rating = rating;
		 
		 expandSIPP(this.sipp);
		 
		 this.vehicleScore = getvehicleScore();
		 this.combinedScore = this.vehicleScore + this.rating;
	 }
	 
	 private void expandSIPP(String sipp) {
		/* 
		 M  Mini
		 E	Economy
		 C	Compact
		 I	Intermediate
		 S	Standard
		 F	Full size
		 P	Premium
		 L	Luxury
		 X	Special
		 */

		 switch(sipp.charAt(0)) {
			 case 'M' : this.type = "Mini";
				break;
			 case 'E' : this.type = "Economy";
	 			break;
			 case 'C' : this.type = "Compact";
	 			break;
			 case 'I' : this.type = "Intermediate";
	 			break;
			 case 'S' : this.type = "Standard";
	 			break;
			 case 'F' : this.type = "Full size";
	 			break;
			 case 'P' : this.type = "Premium";
	 			break;
			 case 'L' : this.type = "Luxury";
	 			break;
			 case 'X' : this.type = "Special";
	 			break;
			 default: break;
		 }
		 
		 /*
		    B	2 doors
			C	4 doors
			D	5 doors
			W	Estate
			T	Convertible
			F	SUV
			P	Pick up
			V	Passenger Van
		  */
		 switch(sipp.charAt(1)) {
			 case 'B' : this.form = "2 doors";
				break;
			 case 'C' : this.form = "4 doors";
	 			break;
			 case 'D' : this.form = "5 doors";
	 			break;
			 case 'W' : this.form = "Estate";
	 			break;
			 case 'T' : this.form = "Convertible";
	 			break;
			 case 'F' : this.form = "Suv";
	 			break;
			 case 'P' : this.form = "Pick up";
	 			break;
			 case 'V' : this.form = "Passenger Van";
	 			break;
			 default: break;
		 }
		 
		 /*
		    M	Manual
			A	Automatic
		  */
		 switch(sipp.charAt(2)) {
			 case 'M' : this.transmission = "Manual";
				break;
			 case 'A' : this.transmission = "Automatic";
	 			break;
			 default: break;
		 }
		 
		 /*
		    M	Manual
			A	Automatic
		  */
		 switch(sipp.charAt(3)) {
			 case 'N' : this.aircon = "no AC";
				break;
			 case 'R' : this.aircon = "AC";
	 			break;
			 default: break;
		 }
		 
	 }
	 
	 private Integer getvehicleScore() {
		 Integer score = 0;
		 if(this.transmission == "Manual") {
			 score += 1;
		 }
		 if(this.transmission == "Automatic") {
			 score += 5;
		 }
		 if(this.aircon == "AC") {
			 score += 2;
		 }
		 return score;
	 }
	 
	 public Double getDoublePrice() {
		 return this.price;
	 }
	 
	 public Double getRating() {
		 return this.rating;
	 }
	 
	 public Double getCombinedScore() {
		 return this.combinedScore;
	 }
	 
	 public String getPrice() {
		 DecimalFormat df = new DecimalFormat();
		 df.setMaximumFractionDigits(2);
		 return "£"+df.format(this.price);
	 }
	 
	 public String getPriceEntry() {
		 //	{Vehicle name} – {Price}
		 return this.name + " - " + this.getPrice();
	 }
	 
	 public String getSpecEntry() {
		 //	{Vehicle name} – {SIPP} – {Car type} – {Car type/doors} – {Transmission} – {Fuel} – {Air con}
		 return this.name + " - " 
				 + this.sipp + " - " 
				 + this.type + " - "
				 + this.form + " - "
				 + this.transmission + " - "
				 + this.fuel + " - "
				 + this.aircon;
	 }
	 
	 public String getRatingsEntry() {
		 //	{Vehicle name} – {Car type} – {Supplier} – {Rating}
		 return this.name + " - " 
				 + this.type + " - " 
				 + this.supplier + " - "
				 + this.rating;
	 }

	 public String getScoresEntry() {
		 //	{Vehicle name} – {Vehicle score} – {Supplier rating} – {Sum of scores}
		 return this.name + " - " 
				 + this.vehicleScore + " - " 
				 + this.rating + " - "
				 + this.combinedScore;
	 }
}
