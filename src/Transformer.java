import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Transformer {

	// this class is going to be used to perform the business logic on the
	// different object, for example customer

	public static Customer findCustomerWithFirstAndLastName(List<Customer> customerList, String firstName,
			String lastName) {

		// return customer;
		return null;
	}

	public static List<Customer> sortCustomersByFistName(List<Customer> customerList) {

		Collections.sort(customerList, Comparator.comparing(Customer::getFirstName));

		return customerList;
	}

	public static List<Customer> sortCustomersById(List<Customer> customerList) {

		Collections.sort(customerList, Comparator.comparing(Customer::getCustomerId));

		return customerList;
	}

	public static List<Customer> sortCustomersByLastName(List<Customer> customerList) {

		Collections.sort(customerList, Comparator.comparing(Customer::getLastName));

		return customerList;
	}

	public static List<Customer> addCustomer(List<Customer> customerList, Customer customer) {

		customerList.add(customer);

		return customerList;
	}

	public static List<Customer> removeCustomer(List<Customer> customerList, Customer customer) {

		customerList.remove(customer);

		return customerList;
	}

	public static List<Vehicle> sortVehicleByVin(List<Vehicle> vehicleList) {

		Collections.sort(vehicleList, Comparator.comparing(Vehicle::getVin));

		return vehicleList;
	}

	public static List<Vehicle> sortVehicleByMake(List<Vehicle> vehicleList) {

		Collections.sort(vehicleList, Comparator.comparing(Vehicle::getMake));

		return vehicleList;
	}

	public static List<Vehicle> sortVehicleByModel(List<Vehicle> vehicleList) {

		Collections.sort(vehicleList, Comparator.comparing(Vehicle::getModel));

		return vehicleList;
	}
}
