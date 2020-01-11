package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.PaymentDAO;
import DAO.PaymentImpl;
import modelProject0.Car;
import modelProject0.Payment;

public class PaymentService {
	
	static Scanner scanner =  new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("0.00");
	
	public static PaymentDAO paymentList = new PaymentImpl();

	public static boolean addPayment() {
		return paymentList.addPayment();
	}
	
	
	public static boolean getAcceptedPaymentForCar(int c_id) {
		return paymentList.getAcceptedPaymentForCar(c_id);
	}
	
	public static Payment getPaymentByUserId(int u_id) {
		return paymentList.getPaymentByUserId(u_id);
	}
	
	public static Payment getPaymentByCarId(int c_id) {
		return paymentList.getPaymentByCarId(c_id);
	}
	
	public static List<Payment> getUserPayments() {
		return paymentList.getUserPayments();
	}
	
	public static List<Payment> getPendingPaymentsByCar(int c_id){
		return paymentList.getPendingPaymentsByCar(c_id);
	}
	
	public static List<Payment> getAllPayments(String status){
		return paymentList.getAllPayments(status);
	}
	
	public static List<Car> getOwnedCars() {
		return paymentList.getOwnedCars();
	}
	
	public static boolean updatePayment(int u_id, int c_id) {
		return paymentList.updatePayment( u_id, c_id);
	}
	
	public static boolean deletePayment(int c_id) {
		return paymentList.deletePayment(c_id);
	}
	
	public static boolean deletePaymentByUser(int u_id, int c_id) {
		return paymentList.deletePaymentByUser(u_id, c_id);
	}
	
	
	public static double calculateRate(int months, double fullPrice, double offerPrice) {
		double rate = (fullPrice - offerPrice) / months;
		System.out.println("Over " + months + " months your monthly payment will be : $"  + df.format(rate) + "\n");
		return rate;
	}
	
	public static  void  updateMenu() {
		
		System.out.println("\nChoose a car number to accept/reject it:");
		int carId = scanner.nextInt();
		
		Payment p = getPaymentByCarId(carId);
		
		List<Payment> offers = getPendingPaymentsByCar(carId);
		
		System.out.println("\nHere are all the offers in pending for the specified car.\n");
		offers.forEach(System.out::println);
		
		
		System.out.println("\nChoose a user nr to accept or reject an offer:\n");
		int userId = scanner.nextInt();
		scanner.nextLine();
		
		Payment userP = getPaymentByUserId(userId);
		
		System.out.println("\nWould you like to accept or reject an offer?\n");
		System.out.println("\t0 - to accept\n" +
							"\t1 - to reject\n");
		
		System.out.println("\nEnter an action: ");
		int action = scanner.nextInt();
		scanner.nextLine();
		
		switch (action) {
	        case 0:
	        	updatePayment(userId, carId);
	        	if(updatePayment(userId, carId)) {
	        		System.out.println("The offer was accepted! All other offers for this car will be deleted.");
	        		deletePayment(carId);
	        	} else {
	        		System.out.println("Something went wrong! Try one more time");
	        		updatePayment(userId, carId);
	        	}
	        	
	      	  	ExecuteApp.employeePage();
	            break;
	        case 1:
	        	deletePaymentByUser(userId, carId);
	        	System.out.println("The car was deleted successfully.");
	        	
	        	ExecuteApp.employeePage();
	            break; 
		}
	}
		
	
	public static boolean getMyPayment() {
		List<Payment> payments = PaymentService.getUserPayments();
		List<Car> ownedCars = PaymentService.getOwnedCars();
		ownedCars.forEach(System.out::println);
		
		 if(payments.isEmpty()) {
   		  	System.out.println("You dont have any owned cars yet.\n");
	   	  } else {
	   		System.out.println("\nChoose a car to see the payment:\n");
			int carNr = scanner.nextInt();
			
			getAcceptedPaymentForCar(carNr);
	   	  }
		return false;
		
	}	
	
}
