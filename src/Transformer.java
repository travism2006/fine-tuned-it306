import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * this class is designed for the purpose of sorting the object for displaying
 * and reports purpose this class will have methods for different
 * implementation.
 * 
 * @author hassan ammar
 *
 */
public class Transformer {
	/**
	 * this method will arrange the customer list in alphabetically by the first
	 * name
	 * 
	 * @param customerList
	 * @return
	 */
	public static List<Customer> sortCustomersByFistName(List<Customer> customerList) {

		Collections.sort(customerList, Comparator.comparing(Customer::getFirstName));

		return customerList;
	}

	/**
	 * this method will arrange the customer list in alphabetically by the first
	 * name using insertion sort
	 * 
	 * @param customerList
	 * @return
	 */
	public static void insertionSortCustomerByFirstName(List<Customer> custList) {
		int in, out;

		for (out = 1; out < custList.size(); out++) {
			Customer temp = custList.get(out);
			in = out;

			while (in > 0 && custList.get(in - 1).getFirstName().compareTo(temp.getFirstName()) > 0) {
				custList.set(in, custList.get(in - 1));
				--in;
			}
			custList.set(in, temp);
		}
	}

	/**
	 * this method will arrange the customer list in alphabetically by the Last
	 * name using insertion sort
	 * 
	 * @param customerList
	 * @return
	 */
	public static void insertionSortCustomerByLastName(List<Customer> custList) {
		int in, out;

		for (out = 1; out < custList.size(); out++) {
			Customer temp = custList.get(out);
			in = out;

			while (in > 0 && custList.get(in - 1).getLastName().compareTo(temp.getLastName()) > 0) {
				custList.set(in, custList.get(in - 1));
				--in;
			}
			custList.set(in, temp);
		}
	}

	/**
	 * Selection Sort this method will arrange the customer list in
	 * alphabetically by the first name using insertion sort
	 * 
	 * @param customerList
	 * @return
	 */
	public static List<Customer> selectionSortCustomer(List<Customer> data) {
		Customer min;
		int k = 0;
		for (int i = 0; i < data.size() - 1; i++) {
			min = data.get(i);
			for (int j = i + 1; j < data.size(); j++) {

				if (Integer.parseInt(min.getFirstName()) > Integer.parseInt(data.get(j).getFirstName())) {
					min = data.get(j);
					k = j;
				}
			}

			if (Integer.parseInt(min.getFirstName()) < Integer.parseInt(data.get(i).getFirstName())) { // swap
				Customer temp = data.get(i);
				data.set(i, data.get(k));
				data.set(k, temp);
			}
		}

		return data;

	}

	/**
	 * this method will arrange the customer list in assending order by the
	 * customer id
	 * 
	 * @param customerList
	 * @return
	 */
	public static List<Customer> sortCustomersById(List<Customer> customerList) {

		Collections.sort(customerList, Comparator.comparing(Customer::getCustomerId));

		return customerList;
	}

	/**
	 * this method will arrange the customer list in alphabetically by the Last
	 * name
	 * 
	 * @param customerList
	 * @return
	 */
	public static List<Customer> sortCustomersByLastName(List<Customer> customerList) {

		Collections.sort(customerList, Comparator.comparing(Customer::getLastName));

		return customerList;
	}

	/**
	 * this method will arrange the vehicle list in by the vin number
	 * 
	 * @param vehicleList
	 * @return
	 */
	public static List<Vehicle> sortVehicleByVin(List<Vehicle> vehicleList) {

		Collections.sort(vehicleList, Comparator.comparing(Vehicle::getVin));

		return vehicleList;
	}

	/**
	 * this method will arrange the vehicle list in alphabetically by the makers
	 * name
	 * 
	 * @param vehicleList
	 * @return
	 */
	public static List<Vehicle> sortVehicleByMake(List<Vehicle> vehicleList) {

		Collections.sort(vehicleList, Comparator.comparing(Vehicle::getMake));

		return vehicleList;
	}

	/**
	 * this method will arrange the vehicle list in alphabetically by the model
	 * 
	 * @param vehicleList
	 * @return
	 */
	public static List<Vehicle> sortVehicleByModel(List<Vehicle> vehicleList) {

		Collections.sort(vehicleList, Comparator.comparing(Vehicle::getModel));

		return vehicleList;
	}
}
