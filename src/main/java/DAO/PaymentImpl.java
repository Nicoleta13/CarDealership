package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelProject0.Car;
import modelProject0.Payment;
import service.CarService;
import service.PaymentService;
import service.UserService;
import util.JDBCConnection;

public class PaymentImpl implements PaymentDAO {
	
	public static Connection conn = JDBCConnection.getConnection();

	static Scanner scanner = new Scanner(System.in);
	
	
	public boolean addPayment() {
		
		System.out.println("Here are all the cars from the lot:\n");
		
		List<Car> cars = CarService.getAllCars();
		cars.forEach(System.out::println);
		
		System.out.println("\nChoose a car to make an offer:");
		int carId = scanner.nextInt();
		scanner.nextLine();

			
		Car findCar = CarService.getCar(carId);
		
		if (findCar == null) {
			System.out.println("Invalid car number. Try one  more time\n");
			addPayment();				
		} else {
		System.out.println("\nThis is the matched car:\t " + findCar);
		}
		
		System.out.println("\nHow much you want to offer?");
		double offerPrice = scanner.nextDouble();
		scanner.nextLine();
		
		double fullPrice = 0;
		double monthlyPayment = 0;

		fullPrice = findCar.getPrice();
		
		System.out.println("\nHow long would you like to pay off the car in months:\n");

		int months = scanner.nextInt();
		scanner.nextLine();
		

		monthlyPayment = PaymentService.calculateRate(months, fullPrice, offerPrice);

		Payment p = new Payment(carId, UserService.userId, months ,offerPrice, monthlyPayment);
		
		String sql = "CALL add_payment(?,?,?,?,?)";
		
		try {
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, p.getU_id());
			cs.setInt(2, p.getC_id());
			cs.setInt(3, p.getMonths());
			cs.setDouble(4, p.getOffer());
			cs.setDouble(5, p.getMonthly_payment());
			
			cs.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	//**
	public Payment getPaymentByUserId(int u_id) {
		
		try {
			String sql = "SELECT * FROM payment WHERE u_id = ? AND status = 'pending'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(u_id));
			
			ResultSet rs = ps.executeQuery();		
			
			if(rs.next()) {
				return (new Payment(
						rs.getInt("C_ID"),
						rs.getInt("U_ID"),
						rs.getInt("MONTHS"),
						rs.getDouble("OFFER"),
						rs.getDouble("MONTHLY_PAYMENT")));		
			}					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//**
	public Payment getPaymentByCarId(int c_id) {
		
		try {
			String sql = "SELECT * FROM payment WHERE c_id = ? AND status = 'pending'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(c_id));
			
			ResultSet rs = ps.executeQuery();		
			
			if(rs.next()) {
				return (new Payment(
						rs.getInt("C_ID"),
						rs.getInt("U_ID"),
						rs.getInt("MONTHS"),
						rs.getDouble("OFFER"),
						rs.getDouble("MONTHLY_PAYMENT")));
			}					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//**
	public boolean getAcceptedPaymentForCar(int c_id) {
		
		try {
			String sql = "SELECT * FROM (SELECT * FROM payment WHERE status = 'accepted') WHERE u_id = ? AND c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(UserService.userId));
			ps.setString(2, Integer.toString(c_id));
			
			ResultSet rs = ps.executeQuery();		
			
			if(rs.next()) {
				System.out.println("\tOver " + rs.getInt("MONTHS") + " months your monthly payment will be $" + rs.getDouble("MONTHLY_PAYMENT") + "\n");
				return true;
			}					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//**
	public List<Payment> getPendingPaymentsByCar(int c_id) {	
		
		String sql = "SELECT * FROM payment WHERE c_id = ? AND status = 'pending'";
		List<Payment> paymentsByCar = new ArrayList<Payment>();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(c_id));
			
			ResultSet rs = ps.executeQuery();		
			
			while(rs.next()) {
				paymentsByCar.add(new Payment(
						rs.getInt("C_ID"),
						rs.getInt("U_ID"),
						rs.getInt("MONTHS"),
						rs.getDouble("OFFER"),
						rs.getDouble("MONTHLY_PAYMENT")));
			}		
			
			return paymentsByCar;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//**
	public List<Payment> getUserPayments() {
		
		String sql = "SELECT * FROM payment WHERE u_id = ? AND status = 'accepted'";
		List<Payment> payments = new ArrayList<Payment>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(UserService.userId));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				payments.add(new Payment(
						rs.getInt("C_ID"),
						rs.getInt("U_ID"),
						rs.getInt("MONTHS"),
						rs.getDouble("OFFER"),
						rs.getDouble("MONTHLY_PAYMENT")));
			}
			return payments;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//**
	public List<Payment> getAllPayments(String status) {
		
		String sql = "SELECT * FROM payment WHERE status = ? ORDER BY c_id";
		List<Payment> payments = new ArrayList<Payment>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, (status));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				payments.add(new Payment(
						rs.getInt("C_ID"),
						rs.getInt("U_ID"),
						rs.getInt("MONTHS"),
						rs.getDouble("OFFER"),
						rs.getDouble("MONTHLY_PAYMENT")));
			}
			return payments;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//**
	public List<Car> getOwnedCars(){
		List<Payment> payments = getUserPayments();
		List<Car> ownedCars = new ArrayList<Car>();
		
		for(Payment p : payments) {
			int carId = p.getC_id();
			Car carFound = CarService.getCar(carId);
			
			ownedCars.add(carFound);			
		}
		return ownedCars;
	}

	public boolean updatePayment(int u_id, int c_id) {
		
		String sql = "UPDATE payment SET status = 'accepted' WHERE u_id = ? AND c_id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(u_id));
			ps.setString(2, Integer.toString(c_id));
					
			ps.executeQuery();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean deletePayment(int c_id) {
		
		try {
			String sql = "DELETE payment WHERE c_id = ? AND status = 'pending'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(c_id));
			
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deletePaymentByUser(int u_id, int c_id) {	

		try {
			String sql = "DELETE FROM (SELECT * FROM payment WHERE status = 'pending') WHERE u_id = ? AND c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(u_id));
			ps.setString(2, Integer.toString(c_id));
			
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
