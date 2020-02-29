package flipkartProblemSolving;

import java.util.List;

public class Driver {

	private String driverName;
	private List<Integer> ratings;
	private double averageRating;
	private boolean isOffline;

	public boolean isOffline() {
		return isOffline;
	}

	public void setOffline(boolean isOffline) {
		this.isOffline = isOffline;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setCustomerName(String driverName) {
		this.driverName = driverName;
	}

	public Driver(String driverName) {
		super();
		this.driverName = driverName;
	}

	public List<Integer> getRatings() {
		return ratings;
	}

	public void setRatings(List<Integer> ratings) {
		this.ratings = ratings;
	}

	@Override
	public boolean equals(Object obj) {
		Driver driver = (Driver) obj;
		if (this.driverName.equalsIgnoreCase(driver.getDriverName()))
			return true;
		else
			return false;
	}
	
	
	@Override
	public String toString() {
		return "Driver Name is" + getDriverName();
	}
}
