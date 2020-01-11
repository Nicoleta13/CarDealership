package DAO;

import java.util.List;

import modelProject0.Car;
import modelProject0.Payment;

public interface PaymentDAO {
	
	public boolean addPayment();

	
	public Payment getPaymentByUserId(int u_id);
	
	
	public Payment getPaymentByCarId(int c_id);
	
	public boolean getAcceptedPaymentForCar(int c_id);
	
	
	public List<Payment> getAllPayments(String status);	
	
	public List<Payment> getPendingPaymentsByCar(int c_id);
	
	public List<Payment> getUserPayments();
	
	
	public List<Car> getOwnedCars();
	
	public boolean updatePayment(int u_id, int c_id);
	
	public boolean deletePayment(int c_id);
	public boolean deletePaymentByUser(int u_id, int c_id);

}
