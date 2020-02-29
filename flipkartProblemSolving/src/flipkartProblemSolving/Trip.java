package flipkartProblemSolving;

public class Trip {

	private Customer customer;
	private Driver driver;
	int customerRating;
	int driverRating;

	public Trip(Customer customer, Driver driver, int customerRating, int driverRating) {
		super();
		this.customer = customer;
		this.driver = driver;
		this.customerRating = customerRating;
		this.driverRating = driverRating;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public int getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(int customerRating) {
		this.customerRating = customerRating;
	}

	public int getDriverRating() {
		return driverRating;
	}

	public void setDriverRating(int driverRating) {
		this.driverRating = driverRating;
	}

}
