package vmspro;

import vmspro.VMSPro_Constants.CarColors;

/**
 * DDC used for project requirements as instance/example of inheritance.
 * Specifies the fields related to vans in the real world, namely cargo space.
 * @author tmitchu2
 * */

public final class Van extends Vehicle
{
	private double cargoSpace;
	
	/**
	 * Specific constructor for the new field.
	 * @param someMake the manufacturer of this car
	 * @param someModel the model of the car obj
	 * @param someColor the color chosen for this car
	 * @param someYear the year of this car
	 * @param someVIN the vin for the car, once set here never changed later
	 * @param someCargo the amount of cargo space in the van
	 * */
	public Van(String makeN, String modelN, CarColors colorN, int yearN, String someVIN, double someCargo)
	{
		super(makeN, modelN, colorN, yearN, someVIN);
		this.cargoSpace = someCargo;
	}
	
	/**
	 * Specific constructor for the new field.
	 * @param someMake the manufacturer of this car
	 * @param someModel the model of the car obj
	 * @param someColor the color chosen for this car
	 * @param someCust the customer to be linked to
	 * @param someYear the year of this car
	 * @param someVIN the vin for the car, once set here never changed later
	 * @param someCargo the amount of cargo space in the van
	 * */
	public Van(Customer someCust, String makeN, String modelN, CarColors colorN, int yearN, String someVIN, double someCargo)
	{
		super(makeN, modelN, colorN, yearN, someVIN, someCust);
		this.cargoSpace = someCargo;
	}
	
	/**
	 * Assigns if valid input is given for the respective field.
	 * @param someCargoAmt the amt of cargo space
	 * @return success if valid, failure otherwise
	 * */
	public boolean setCargoSpace(double someCargoAmt)
	{
		if(someCargoAmt >= VMSPro_Constants.MIN_NUMBER_INPUT)
		{
			this.cargoSpace = someCargoAmt;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the amount of cargo space.
	 * @return the amount of cargo space
	 * */
	public double getCargoSpace()
	{return this.cargoSpace;}
	
	/**
	 * Returns the str that includes super and new field information.
	 * @return the str that includes super and new field information
	 * */
	public String toString()
	{
		return "Van [cargoSpace=" + cargoSpace + "]";
	}
}