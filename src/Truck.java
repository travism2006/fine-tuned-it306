package vmspro;

import vmspro.VMSPro_Constants.CarColors;

/**
 * DDC used for project requirements as instance/example of inheritance.
 * Specifies the fields related to trucks in the real world, specifically
 * towing and carrying capacity.
 * @author tmitchu2
 * */
public final class Truck extends Vehicle
{
	private double towCapacity;
	private double carryCapacity;

	/**
	 * Specific constructor for each of the new two fields.
	 * @param someMake the manufacturer of this car
	 * @param someModel the model of the car obj
	 * @param someColor the color chosen for this car
	 * @param someYear the year of this car
	 * @param someVIN the vin for the car, once set here never changed later
	 * */
	public Truck(String makeN, String modelN, CarColors colorN, int yearN, String someVIN, double someTow, double someCarry)
	{
		super(makeN, modelN, colorN, yearN, someVIN);
		this.towCapacity = someTow;
		this.carryCapacity = someCarry;
	}
	
	/**
	 * Specific constructor for each of the new two fields.
	 * @param someMake the manufacturer of this car
	 * @param someModel the model of the car obj
	 * @param someColor the color chosen for this car
	 * @param someCust the customer to be linked to
	 * @param someYear the year of this car
	 * @param someVIN the vin for the car, once set here never changed later
	 * */
	public Truck(String makeN, String modelN, CarColors colorN, int yearN, String someVIN, Customer someCust, double someTow, double someCarry)
	{
		super(makeN, modelN, colorN, yearN, someVIN, someCust);
		this.towCapacity = someTow;
		this.carryCapacity = someCarry;
	}

	/**
	 * Returns the towing capacity field value.
	 * @return the towing capacity field value
	 * */
	public double getTowCapacity()
	{return this.towCapacity;}

	/**
	 * Assigns the input to the correct field as long as the input is valid.
	 * @param someTowing the towing capacity of this truck
	 * @return success or the failure on the assigning of data
	 * */
	public boolean setTowCapacity(double someTowing)
	{
		if(someTowing >= VMSPro_Constants.MIN_NUMBER_INPUT)
		{
			this.towCapacity = someTowing;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of the carry capacity field.
	 * @return the value of the carry capacity field
	 * */
	public double getCarryCapacity()
	{return this.carryCapacity;}

	/**
	 * If validated, the given input is assigned to the carry capacity field.
	 * @param someCarrying the amount of carrying capacity
	 * @return the success of failure of this assignment
	 * */
	public boolean setCarryCapacity(double someCarrying)
	{
		if(someCarrying >= VMSPro_Constants.MIN_NUMBER_INPUT)
		{
			this.carryCapacity = someCarrying;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the str that includes super and new field information.
	 * @return the str that includes super and new field information
	 * */
	public String toString()
	{
		return "Truck [towing capacity=" + towCapacity + ", carrying capacity=" +carryCapacity+ "]";
	}
}