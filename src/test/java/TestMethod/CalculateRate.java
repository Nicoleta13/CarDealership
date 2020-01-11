package TestMethod;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import service.PaymentService;

public class CalculateRate {
	
	@Test
	public void calculatePrice() {
		int monthlyPayment = (int) PaymentService.calculateRate(12, 30000, 4000);
		Assert.assertEquals((int) 2166.67, monthlyPayment);
	}

}
