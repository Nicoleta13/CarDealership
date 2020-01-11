package modelProject0;

public class Car {
	int c_id;
	String make;
	String model;
	int year;
	double price;

	
	public Car() {
		super();
	}
	
	public Car(String make, String model, int year, double price) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
	}

	public Car(int c_id, String make, String model, int year, double price) {
		super();
		this.c_id = c_id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
	}


	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "\t Car nr:" +  c_id + ";   Make = " + make + ";   Model = " + model + 
				";   Year = " + year + ";   Price = " + price + "\n";
	}

	
}
