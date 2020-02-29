package flipkartProblemSolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Runner {
	private static List<Customer> customers = new ArrayList<Customer>();
	private static List<Driver> drivers = new ArrayList<Driver>();
	private static List<Trip> trips = new ArrayList<Trip>();

	public static void main(String[] args) {

		// Register Customers
		registerCustomers();

		// Register Drivers
		registerDrivers();

		// Store Each trip
		storeEachtripdata();

		// calculate Average Rating
		computeAverageRatingOfCustomers();
		computeAverageRatingOfDriver();

		// Find Eligible drivers
		for (Customer customer : customers) {
			List<Driver> eligibleDrivers = getEligibleDrivers(customer);
			if (eligibleDrivers.isEmpty()) {
				System.out.println("Sorry No Cabs for You!!!");
			} else {
				System.out.println("Customer Name " + customer.getCustomerName() + "    " + "Eligible DriversList="
						+ eligibleDrivers.toString());
			}
		}
	}

	private static void registerCustomers() {
		// Register Customers
		Customer cust[] = { new Customer("c1"), new Customer("c2"), new Customer("c3") };
		customers = Arrays.asList(cust);
	}

	private static void registerDrivers() {
		// Register Drivers
		Driver drivrs[] = { new Driver("d1"), new Driver("d2"), new Driver("d3") };
		drivers = Arrays.asList(drivrs);
	}

	private static void storeEachtripdata() {
		trips.add(new Trip(
				customers.stream().filter(customer -> "c1".equalsIgnoreCase(customer.getCustomerName()))
						.collect(Collectors.toList()).get(0),
				drivers.stream().filter(driver -> "d1".equalsIgnoreCase(driver.getDriverName()))
						.collect(Collectors.toList()).get(0),
				5, 4));
		trips.add(new Trip(
				customers.stream().filter(customer -> "c2".equalsIgnoreCase(customer.getCustomerName()))
						.collect(Collectors.toList()).get(0),
				drivers.stream().filter(driver -> "d1".equalsIgnoreCase(driver.getDriverName()))
						.collect(Collectors.toList()).get(0),
				4, 5));
		trips.add(new Trip(
				customers.stream().filter(customer -> "c3".equalsIgnoreCase(customer.getCustomerName()))
						.collect(Collectors.toList()).get(0),
				drivers.stream().filter(driver -> "d1".equalsIgnoreCase(driver.getDriverName()))
						.collect(Collectors.toList()).get(0),
				2, 1));

		trips.add(new Trip(
				customers.stream().filter(customer -> "c1".equalsIgnoreCase(customer.getCustomerName()))
						.collect(Collectors.toList()).get(0),
				drivers.stream().filter(driver -> "d2".equalsIgnoreCase(driver.getDriverName()))
						.collect(Collectors.toList()).get(0),
				1, 5));
		trips.add(new Trip(
				customers.stream().filter(customer -> "c2".equalsIgnoreCase(customer.getCustomerName()))
						.collect(Collectors.toList()).get(0),
				drivers.stream().filter(driver -> "d2".equalsIgnoreCase(driver.getDriverName()))
						.collect(Collectors.toList()).get(0),
				5, 5));
		trips.add(new Trip(
				customers.stream().filter(customer -> "c3".equalsIgnoreCase(customer.getCustomerName()))
						.collect(Collectors.toList()).get(0),
				drivers.stream().filter(driver -> "d2".equalsIgnoreCase(driver.getDriverName()))
						.collect(Collectors.toList()).get(0),
				5, 4));

		trips.add(new Trip(
				customers.stream().filter(customer -> "c1".equalsIgnoreCase(customer.getCustomerName()))
						.collect(Collectors.toList()).get(0),
				drivers.stream().filter(driver -> "d3".equalsIgnoreCase(driver.getDriverName()))
						.collect(Collectors.toList()).get(0),
				2, 3));
		trips.add(new Trip(
				customers.stream().filter(customer -> "c2".equalsIgnoreCase(customer.getCustomerName()))
						.collect(Collectors.toList()).get(0),
				drivers.stream().filter(driver -> "d3".equalsIgnoreCase(driver.getDriverName()))
						.collect(Collectors.toList()).get(0),
				5, 4));
		trips.add(new Trip(
				customers.stream().filter(customer -> "c3".equalsIgnoreCase(customer.getCustomerName()))
						.collect(Collectors.toList()).get(0),
				drivers.stream().filter(driver -> "d3".equalsIgnoreCase(driver.getDriverName()))
						.collect(Collectors.toList()).get(0),
				3, 3));
	}

	private static void computeAverageRatingOfDriver() {

		Map<Driver, List<Integer>> driverRatingsMap = new HashMap<Driver, List<Integer>>();
		for (Trip trip : trips) {

			driverRatingsMap.computeIfAbsent(trip.getDriver(), k -> new ArrayList<Integer>())
					.add(trip.getCustomerRating());

		}

		driverRatingsMap.entrySet().stream()
				.forEach(e -> drivers.stream().filter(driver -> e.getKey().equals(driver)).collect(Collectors.toList())
						.get(0).setAverageRating(e.getValue().stream().mapToInt(i -> i).average().getAsDouble()));

		drivers.stream().map(driver -> driver)
				.forEach(driver -> System.out.println(driver.getDriverName() + " " + driver.getAverageRating()));
	}

	private static void computeAverageRatingOfCustomers() {
		Map<Customer, List<Integer>> customerRatingsMap = new HashMap<Customer, List<Integer>>();

		for (Trip trip : trips) {
			customerRatingsMap.computeIfAbsent(trip.getCustomer(), k -> new ArrayList<Integer>())
					.add(trip.getDriverRating());

		}

		customerRatingsMap.entrySet().stream()
				.forEach(e -> customers.stream().filter(customer -> e.getKey().equals(customer))
						.collect(Collectors.toList()).get(0)
						.setAverageRating(e.getValue().stream().mapToInt(i -> i).average().getAsDouble()));

		customers.stream().map(customer -> customer).forEach(
				customer -> System.out.println(customer.getCustomerName() + " " + customer.getAverageRating()));

	}

	private static List<Driver> getEligibleDrivers(Customer customer) {
		List<Driver> driverList = new ArrayList<Driver>();
		driverList
				.addAll(drivers.stream()
						.filter(driver -> driver.getAverageRating() > customer.getAverageRating()
								&& !isDriverRestricted(customer, driver) && !driver.isOffline())
						.collect(Collectors.toList()));
		if (driverList.isEmpty()) {
			List<Trip> tempTripList = trips.stream().filter(trip -> trip.getCustomer().equals(customer))
					.collect(Collectors.toList());
			Collections.reverse(tempTripList);
			driverList.add(tempTripList.stream().map(trip -> trip.getDriver()).filter(driver -> !driver.isOffline())
					.collect(Collectors.toList()).get(0));
		}
		return driverList;
	}

	private static boolean isDriverRestricted(Customer customer, Driver driver) {
		return trips.stream().anyMatch(trip -> (trip.getCustomer().equals(customer) && trip.getDriver().equals(driver))
				&& (trip.getCustomerRating() == 1 || trip.getDriverRating() == 1));
	}
}
