package DAO;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import modelProject0.Car;

class CarDAOTest {

	
		CarDAO testCar = new CarImpl();
	
		
		@Test
		public void addCarToLot() {	
			Assert.assertTrue(testCar.addCar());		
		}
			
		
		@Test
		public void getCarFromTheLot() {
			Car checkCar = new Car(5, "Audi", "A5", 2019, 40000.00);

			Assert.assertEquals(checkCar.getMake(), testCar.getCar(5).getMake());	
			Assert.assertEquals(checkCar.getModel(), testCar.getCar(5).getModel());
			Assert.assertEquals(checkCar.getYear(), testCar.getCar(5).getYear());
		}		
		
		
		@Test
		public void deleteCarFromTheLot() {		
			Assert.assertTrue(testCar.deleteCar());		
		}
		

}
