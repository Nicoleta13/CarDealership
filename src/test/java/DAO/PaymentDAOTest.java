package DAO;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import modelProject0.Payment;

class PaymentDAOTest {

	PaymentDAO testOffers  = new PaymentImpl();
	
	@Test
	public void addOffer() {
		Assert.assertTrue(testOffers.addPayment());
	}
	
	@Test
	public void getPaymentUser() {
		Payment p = new Payment(2, 5, 24, 3500, 604.17);
		Assert.assertEquals(p.getMonths(), testOffers.getPaymentByUserId(2).getMonths());
	
	}
	
	public void getPayment() {
		Assert.assertTrue(testOffers.getAcceptedPaymentForCar(5));
	}
	@Test
	public void updateOffer() {
		Assert.assertTrue(testOffers.updatePayment(2, 5));
	}
	
	@Test
	public void deleteOffer() {
		Assert.assertTrue(testOffers.deletePaymentByUser(2, 5));
	}
	
}
