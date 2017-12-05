package vmspro;

import java.util.HashMap;

/**
 * DDC required per previous phase submissions by client group as well as given
 * documentation for creating the system application.
 * @author hammar
 * @author tmitchu2
 * */
public class Customer implements VMSPro_Standard_Behavior
{
	private static int counter = 0;
	private int custID;
	private String firstName;
	private String lastName;

	private Address address;
	private HashMap<String,Vehicle> carListMap;
	
	/**
	 * Default constructor for id generation and initialization.
	 */
	private Customer()
	{
		counter++;
		this.custID = counter;
		this.carListMap= new HashMap<>();
	}

	/**
	 * Specific constructor for name fields.
	 * @param someFName the first name to be assgined for this field
	 * @param someLName the last name for this customer
	 */
	public Customer(String someFName, String someLName)
	{
		this();
		this.firstName = someFName;
		this.lastName = someLName;
	}
	
	/**
	 * Specific constructor for name fields and the address field.
	 * @param someFName the first name to be assgined for this field
	 * @param someLName the last name for this customer
	 * @param someAddress the associated address for this customer
	 */
	public Customer(String someFName, String someLName, Address someAddress)
	{
		this(someFName, someFName);
		this.address = someAddress;
	}
	
	/**
	 * Validates and assigns the input to the correct field.
	 * @param someFName the first name to be given to this customer.
	 * @return whether or not assignment is successful
	 * */
	public boolean setFirstName(String someFName)
	{
		if(VMSPro.checkString(someFName))
		{
			this.firstName = someFName;
			return true;
		}
		return false;
	}

	/**
	 * Validates and assigns the input to the correct field.
	 * @param someLName the last name to be given to this customer
	 * @return whether or not assignment is successful
	 * */
	public boolean setLastName(String someLName)
	{
		if(VMSPro.checkString(someLName))
		{
			this.lastName = someLName;
			return true;
		}
		return false;
	}

	/**
	 * Validates and assigns the input to the correct field.
	 * @param someAddress the primary address to be given to this customer
	 * @return whether or not assignment is successful
	 * */
	public boolean setAddress(Address someAddress)
	{
		if(someAddress == null)return false;
		this.address = someAddress;
		return true;
	}

	/**
	 * Returns the customer's id number.
	 * @return the id of this customer
	 */
	public int getCustomerId()
	{return this.custID;}

	/**
	 * Returns the first name of this customer.
	 * @return the first name of this customer
	 */
	public String getFirstName()
	{return this.firstName;}
	
	/**
	 * Returns the last name of this customer.
	 * @return the last name of this customer
	 * */
	public String getLastName()
	{return this.lastName;}
	
	/**
	 * Returns the primary address linked to this customer. 
	 * @return the primary address linked to this customer
	 * */
	public Address getPrimaryAddress()
	{return this.address;}
	
	/**
	 * Returns the vehicleList associated with this customer.
	 * @return the vehicleList associated with this customer
	 */
	public HashMap<String,Vehicle> getVehicleList()
	{return this.carListMap;}
	
	/**
	 * Returns the value of the total number of customers.
	 * @return the value of the total number of customers
	 * */
	public static int getCustomerTotal()
	{return counter;}
	
	/**
	 * Returns the first car object if not null.
	 * @return the first car object if not null
	 * */
	public Vehicle getFirstCar()
	{
		Vehicle[] carArr = (Vehicle[]) this.carListMap.values().toArray();
		if(carArr.length == 0)return null;
		return carArr[0];
	}
	
	/**
	 * Returns the last car object, null if empty.
	 * @return the last car object, null if empty
	 * */
	public Vehicle getLastCar()
	{
		Vehicle[] carArr = (Vehicle[]) this.carListMap.values().toArray();
		if(carArr.length == 0)return null;
		else if(carArr.length == 1)return getFirstCar();
		return carArr[carArr.length-1];
	}
	
	/**
	 * Returns the fact that the car is linked to this customer.
	 * @param someCar the car to be searched for
	 * @return the fact that the car is linked to this customer
	 * */
	public boolean hasCarX(Vehicle someCar)
	{
		if(someCar == null)return false;
		else if(this.carListMap.isEmpty())return false;
		return this.carListMap.containsValue(someCar);
	}
	
	/**
	 * Adds the car instance as long as it's not null.
	 * @param car the car title to be added to this customer's list
	 * @return the success or failure to add the car to the list
	 * */
	public boolean addCarTitle(Vehicle car)
	{
		if(car == null)return false;
		this.carListMap.put(car.getVin(), car);
		car.linkCustomer(this);
		return true;
	}
	
	/**
	 * Removes the first car from the list/map.
	 * @return the success or failure of the operation
	 * */
	public boolean removeFirstCar()
	{
		if(this.carListMap.isEmpty())return false;
		Vehicle[] cars = (Vehicle[])this.carListMap.values().toArray();
		Vehicle firstCar = this.carListMap.remove(cars[0].getVin());
		firstCar.linkCustomer(null);
		return true;
	}
	
	/**
	 * Removes the last car from the list/map.
	 * @return the success or failure of the operation
	 * */
	public boolean removeLastCar()
	{
		if(this.carListMap.isEmpty())return false;
		
		//O(1) time complexity call
		if(this.carListMap.size() == 1)
		{return this.removeFirstCar();}
		
		//worst case is O(n) time complexity for large number of related cars
		Vehicle[] cars = (Vehicle[])this.carListMap.values().toArray();
		Vehicle lastCar = this.carListMap.remove(cars[cars.length-1].getVin());
		lastCar.linkCustomer(null);
		return true;
	}
	
	/**
	 * Uses the HashMap's clear() property to render the map empty.
	 * Requires no validation.
	 * */
	public void removeAllCars()
	{this.carListMap.clear();}
	
	/**
	 * Removes the specified car from the list/map.
	 * @return the success or failure of the operation
	 * */
	public boolean removeCar(Vehicle someCar)
	{
		if(this.carListMap.isEmpty())return false;
		
		//O(1) time complexity call
		if(this.carListMap.size() == 1)
		{return this.removeFirstCar();}
		
		/*if the specified car is not contained, no operation can be performed*/
		if(!this.carListMap.values().contains(someCar))
		{return false;}
		
		//otherwise it's inside the list, so destroy the relationship
		Vehicle carX = carListMap.remove(someCar.getVin());
		carX.linkCustomer(null);
		return true;
	}
	
	/**
	 * Returns the human friendly representation of this customer.
	 * @return the human friendly representation of this customer
	 * */
	public String toString()
	{
		String out = String.format("Customer ID: %d, Full Name: %s %s, Primary Address: %s",
				this.custID, this.firstName, this.lastName, this.address.toString());
		out+= "\n";
		for(Vehicle car: carListMap.values())
		{
			if(car instanceof Sedan)
			{
				Sedan sidsSedan = (Sedan)car;
				out+= String.format("Lot#: %d, Car VIN: %s, Car Type: Sedan,",
						sidsSedan.getLotNumber(), sidsSedan.getVin());
			}else if(car instanceof Truck)
			{
				Truck tammysTruck = (Truck)car;
				out+= String.format("Lot#: %d, Car VIN: %s, Car Type: Truck,",
						tammysTruck.getLotNumber(), tammysTruck.getVin());
			}else if(car instanceof Van)
			{
				Van vincentsVan = (Van)car;
				out+= String.format("Lot#: %d, Car VIN: %s, Car Type: Van,",
						vincentsVan.getLotNumber(), vincentsVan.getVin());
			}
		}
		return out;
	}
	
	/**
	 * Compares on the customer ID field on this object and the given object.
	 * If the IDs are identical, then it is said that the given object is 
	 * essentially the same customer as the current one.
	 * @param someCust the customer object to compare against
	 * @return whether or not the given and current customer's IDs are the same
	 * */
	public boolean equals(Customer someCust)
	{
		if(this.custID == someCust.getCustomerId())return true;
		return false;
	}
}