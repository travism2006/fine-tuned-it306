import java.util.LinkedList;
import java.util.List;

public class Customer {

	private static int counter = 150;
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;

	private Address address;
	private List<Vehicle> vehicleList = new LinkedList<>();

	/**
	 * default constructor
	 */
	public Customer() {
		this.id = counter + 1;
	}

	/**
	 * @param id
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param address
	 * @param vehicleList
	 */
	public Customer(String firstName, String middleName, String lastName, Address address, List<Vehicle> vehicleList) {
		this.id = counter + 1;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.address = address;
		this.vehicleList = vehicleList;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the vehicleList
	 */
	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	/**
	 * @param vehicleList
	 *            the vehicleList to set
	 */
	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	/**
	 * @return the counter
	 */
	public static int getCounter() {
		return counter;
	}

	/**
	 * @param Vehicle
	 *            that needs to be added to the list
	 */
	public boolean addVehicle(Vehicle vehicle) {
		vehicleList.add(vehicle);
		return true;
	}

	/**
	 * convert the object to a string output
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", address=" + address + ", vehicleList=" + vehicleList + "]";
	}

}
