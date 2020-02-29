package flipkartProblemSolving;

import java.util.List;

public class Customer {

	private String customerName;
	private double averageRating;
	private List<Integer> ratings;

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public Customer(String customerName) {
		super();
		this.customerName = customerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Integer> getRatings() {
		return ratings;
	}

	public void setRatings(List<Integer> ratings) {
		this.ratings = ratings;
	}

	@Override
	public boolean equals(Object obj) {
		Customer customer = (Customer) obj;
		if (this.customerName.equalsIgnoreCase(customer.getCustomerName()))
			return true;
		else
			return false;
	}
}
