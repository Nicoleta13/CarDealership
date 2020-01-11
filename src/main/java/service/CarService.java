package service;

import java.util.List;

import DAO.CarDAO;
import DAO.CarImpl;
import modelProject0.Car;

public class CarService {
	
	public static CarDAO carList = new CarImpl();
	
	public static boolean addCar() {
		return carList.addCar();
	}
	
	public static Car getCar(int c_id) {
		return carList.getCar(c_id);
	}
	
	public static List<Car> getAllCars() {
		return carList.getAllCars();
	}
	
	public static boolean deleteCar() {
		return carList.deleteCar();
	}

}
