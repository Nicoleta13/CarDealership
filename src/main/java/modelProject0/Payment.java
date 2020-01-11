package modelProject0;

import DAO.UserImpl;

public class Payment {
	int u_id;
	int c_id;
	int months;	
	double monthly_payment;
	double offer;
	String status;
	
	public Payment() {
		super();
	}

	public Payment(int c_id, int u_id,  int months, double offer, double monthly_payment) {
		super();
		this.u_id = u_id;
		this.c_id = c_id;
		this.months = months;
		this.offer = offer;
		this.monthly_payment = monthly_payment;	
	}


	public Payment(int c_id, int months, double offer, double monthly_payment) {
		super();
		this.c_id = c_id;
		this.months = months;
		this.monthly_payment = monthly_payment;
		this.offer = offer;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public double getMonthly_payment() {
		return monthly_payment;
	}

	public void setMonthly_payment(double monthly_payment) {
		this.monthly_payment = monthly_payment;
	}

	public double getOffer() {
		return offer;
	}

	public void setOffer(double offer) {
		this.offer = offer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "\t" + "Car nr: " + c_id + "  User nr: " + u_id + "   Months = " + months + 
				";   Offer = " + offer + 
				";   Monthly_payment =  " + monthly_payment + ";";
	}	

}
