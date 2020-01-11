package DAO;

import java.util.List;

import modelProject0.Car;

public interface CarDAO {
		
	public boolean addCar();
	
	public Car getCar(int c_id);
	
	public List<Car> getAllCars();
	
	public boolean deleteCar();

}
