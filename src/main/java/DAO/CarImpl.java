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
import service.CarService;
import util.JDBCConnection;

public class CarImpl implements CarDAO {
	
	public static Connection conn = JDBCConnection.getConnection();
	
	public static Scanner scanner = new Scanner(System.in);
	
	//*
	public boolean addCar() {
		System.out.println("Enter the make: ");
	    String make = scanner.next(); 
	    scanner.nextLine();
		
		System.out.println("Enter the model: ");
	    String model = scanner.nextLine(); 
		
	    System.out.println("Enter the year: ");
	    int year = scanner.nextInt();
	    
	    System.out.println("Enter the price: ");
	    double price = scanner.nextDouble();
	    
	    Car newCar = new Car(make, model, year, price);
	    
	    String sql = "CALL add_car(?,?,?,?)";
	    
	    List<Car> cars = getAllCars();
		
		try {
			CallableStatement cs = conn.prepareCall(sql);
					
			for(Car eachCar : cars) {
				
				 if((year <= 1970) || (year > 2020)) {
						System.out.println("Wrong year. Try one more time.\n");
						addCar();
						break;
					} else if((price <= 999) || (price > 1500000)) {
						System.out.println("Wrong price. Try one more time.\n");
						addCar();
						break;
					} else if (((make.equalsIgnoreCase(eachCar.getMake())) && 
					(model.equalsIgnoreCase(eachCar.getModel())) && 
					(year == eachCar.getYear()) && 
					(price == eachCar.getPrice()))) {
					System.out.println(newCar + " car already exists. Try one more time.\n");
					addCar();
					break;
				} else {
					
					System.out.println("New car added: " + newCar);
					cs.setString(1, newCar.getMake());
					cs.setString(2, newCar.getModel());
					cs.setInt(3, newCar.getYear());
					cs.setDouble(4, newCar.getPrice());
					
					cs.execute();
					
			   		break;
			   	}
			}
			return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	//*
	public Car getCar(int c_id) {
		try {
			String sql = "SELECT * FROM cars WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(c_id));
			
			ResultSet rs = ps.executeQuery();		
			
			if(rs.next()) {
				return (new Car(
						rs.getInt("C_ID"),
						rs.getString("MAKE"),
						rs.getString("MODEL"),
						rs.getInt("YEAR"),
						rs.getDouble("PRICE")));
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//*
	public List<Car> getAllCars() {
		String sql = "SELECT * FROM cars";
		
		List<Car> cars = new ArrayList<Car>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				cars.add(new Car(
						rs.getInt("C_ID"),
						rs.getString("MAKE"),
						rs.getString("MODEL"),
						rs.getInt("YEAR"),
						rs.getDouble("PRICE")));
			}		
			return cars;	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	
	public boolean deleteCar() {
		System.out.println("Choose a car to remove:\n");

		getAllCars().forEach(System.out::println);
		
		System.out.println("\nEnter the number of the car you want to remove:");
		int carId = scanner.nextInt();
		scanner.nextLine();
		
		try {
			String sql = "DELETE cars WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(carId));
			
			System.out.println("Car: " + getCar(carId).getMake() + " was removed.");
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
	
