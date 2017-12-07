package vmspro;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * this class is designed for the purpose of sorting the object for displaying and reports purpose
 * this class will have methods for different implementation.
 * @author hassan ammar
 */

public final class Transformer
{
	/**
	 * this method will arrange the customer list in alphabetically by the first name
	 * @param customerList
	 * @return
	 */
	public static List<Customer> sortCustomersByFistName(List<Customer> customerList) {
		 
		 	Collections.sort(customerList, Comparator.comparing(Customer::getFirstName));

		return customerList;
	}

	/**
	 * this method will arrange the customer list in assending order by the customer id
	 * @param customerList
	 * @return
	 */
	public static List<Customer> sortCustomersById(List<Customer> customerList) {

		Collections.sort(customerList, Comparator.comparing(Customer::getCustomerId));

		return customerList;
	}

	/**
	 * this method will arrange the customer list in alphabetically by the Last name
	 * @param customerList
	 * @return
	 */
	public static List<Customer> sortCustomersByLastName(List<Customer> customerList) {

		Collections.sort(customerList, Comparator.comparing(Customer::getLastName));

		return customerList;
	}

	/**
	 * this method will arrange the vehicle list in  by the vin number
	 * @param vehicleList
	 * @return
	 */
	public static List<Vehicle> sortVehicleByVin(List<Vehicle> vehicleList) {

		Collections.sort(vehicleList, Comparator.comparing(Vehicle::getVin));

		return vehicleList;
	}

	/**
	 * this method will arrange the vehicle list in alphabetically by the makers name
	 * @param vehicleList
	 * @return
	 */
	public static List<Vehicle> sortVehicleByMake(List<Vehicle> vehicleList) {

		Collections.sort(vehicleList, Comparator.comparing(Vehicle::getMake));

		return vehicleList;
	}

	/**
	 * this method will arrange the vehicle list in alphabetically by the model
	 * @param vehicleList
	 * @return
	 */
	public static List<Vehicle> sortVehicleByModel(List<Vehicle> vehicleList) {

		Collections.sort(vehicleList, Comparator.comparing(Vehicle::getModel));

		return vehicleList;
}
}