package com.robprecious.rentalcars;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Main {
 public static void main(String[] args) {
		DataHandler dh = new DataHandler();
		try {
			URL dataURL = new URL("http://www.rentalcars.com/js/vehicles.json");
			List<Car> cars = dh.importCars(dataURL);
			Boolean exit_not_called = true;
			while(exit_not_called) {
				System.out.println("===== RentalCars Console =====");
				System.out.println("1 - Price List");
				System.out.println("2 - Specification List");
				System.out.println("3 - Ratings List");
				System.out.println("4 - Score List");
				System.out.println("5 - Quit");
	
			    Scanner scanner = new Scanner(System.in);
			    System.out.print(">");
			    int choice;
			    
			    do {
			        while (!scanner.hasNextInt()) {
			            System.out.println("That was not a valid input.");
			            scanner.next(); 
			        }
			        choice = scanner.nextInt();
			    } while (choice <= 0);
	
			    switch (choice) {
			        case 1:
			        		System.out.println(dh.getPriceList(cars));
			            break;
			        case 2:
			        		System.out.println(dh.getSpecList(cars));
			            break;
			        case 3:
			        		System.out.println(dh.getRatingsList(cars));
			            break;
			        case 4:
			        		System.out.println(dh.getScoresList(cars));
			        		break;
			        case 5:
			        		exit_not_called = false;
						System.out.print("Closed\n");
			        		scanner.close();
			            break;
			        default:
			            // The user input an unexpected choice.
			    }
			}
		} catch (MalformedURLException e) {
			System.err.println("Caught MalformedURLException: " + e.getMessage());
		}
	}
}
