package service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import logging.MyLogger;
import modelProject0.Car;
import modelProject0.Payment;



public class ExecuteApp {

	static Scanner scanner = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("0.00");
	
	public static void main(String[] args) {
		MyLogger.logger.info("Program Started");
		mainMenu();  
	}
	
	public static void mainMenu() {
		
		System.out.println("Please login or register:\n");
	    System.out.println("\t0 - Login\n" +
	    					"\t1 - Register"); 
			
			System.out.println("\nEnter an action: ");
			int action = scanner.nextInt();
			scanner.nextLine();
	          
	      switch (action) {
	          case 0:
	        	  MyLogger.logger.info("User just logged in.");
	        	  UserService.login();
	        	  
	              break;
	          case 1:
	        	  MyLogger.logger.info("New user was registered.");
	        	  UserService.register();
	        	  
	              break; 
	          default:
	        	  //write a code not to allow letters
	        	  System.out.println("Try one more time");
	        	  mainMenu();
	      }         
	}
	
	public static void employeePage() {
		
		System.out.println("\nEmployee Menu:\n");
	    System.out.println("\t0 - to add a car to the lot\n" +
	    					"\t1 - to remove a car from the lot\n" +
	    					"\t2 - to print all the payments\n" +
	    					"\t3 - to print all the offers in pending\n" +
	    					"\t4 - to go to login page"); 
		
			System.out.println("\nEnter an action: ");
			int action = scanner.nextInt();
			scanner.nextLine();
			
			List<Payment> acceptedOffers = PaymentService.getAllPayments("accepted");
			List<Payment> pendingOffers = PaymentService.getAllPayments("pending");
	          
	      switch (action) {
	          case 0:
	        	  CarService.addCar();
	        	  employeePage();
	              break;
	          case 1:
	        	  CarService.deleteCar();
	        	  employeePage();
	              break; 
	          case 2:
	        	  if(!(acceptedOffers.isEmpty())) {
	        		  System.out.println("Here are all the payments:\n");
		        	  acceptedOffers.forEach(System.out::println);
		        	  
	        	  } else {
	        		  System.out.println("There are no accepted offers.");
	        	  }
	        	  employeePage();
	              break; 
	          case 3:
	        	  
	        	  if(!(pendingOffers.isEmpty())) {
	        		  System.out.println("Here are all the pending offers:\n");
	        		  pendingOffers.forEach(System.out::println);
	        		  PaymentService.updateMenu();
	        	  } else {
	        		  System.out.println("There are no pending offers.");   		 
	        	  }

	        	  employeePage();
	              break; 
	          case 4:
	        	  MyLogger.logger.info("Program ended");
	        	  mainMenu();
	        	 
	              break;
	      }         
   }
	
	
	public static void customerMenu() {
		System.out.println("Customer menu:\n");
	    System.out.println("\t0 - to make an offer\n" +
	    					"\t1 - to print all the cars from the lot\n" +
	    					"\t2 - to print the cars that i own\n" +
	    					"\t3 - to go back to main menu"); 
		
			System.out.println("\nEnter an action: ");
			int action = scanner.nextInt();
			scanner.nextLine();
			
			List<Car> carList = CarService.getAllCars();
	          
	      switch (action) {
	      	  case 0:
	      		  PaymentService.addPayment();   
	      		  customerMenu(); 
	      		  break;
	          case 1:
	        	  System.out.println("Here are all the cars from the lot:\n\n");
	      		  carList.forEach(System.out::println);
	      		  customerMenu();  	  
	              break;
	          case 2:
	        	  PaymentService.getMyPayment();
	        	  customerMenu();
	        	  break;
	          case 3:
	        	  mainMenu();
	              break;        
      }
	}
	//scanner.close();
}
