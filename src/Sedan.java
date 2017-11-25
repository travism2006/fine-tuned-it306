package vmspro;

import vmspro.VMSPro_Constants.CarColors;

/**
 * DDC used for project requirements as instance/example of inheritance.
 * Specifies the fields related to sedans in the real world- cargo space.
 * @author tmitchu2
 * */
public final class Sedan extends Vehicle
{
	private boolean isConvertible;
	
	/**
	 * Specific constructor for the new field as well as fields for super call.
	 * @param someMake the manufacturer of this car
	 * @param someModel the model of the car obj
	 * @param someColor the color chosen for this car
	 * @param someYear the year of this car
	 * @param someVIN the vin for the car, once set here never changed later
	 * @param mayConvert the boolean saying whether or not this converts
	 * */
	public Sedan(String makeN, String modelN, CarColors colorN, int yearN, String someVIN, boolean mayConvert)
	{
		super(makeN, modelN, colorN, yearN, someVIN);
		this.isConvertible = mayConvert;
	}
	
	/**
	 * Specific constructor for the new field as well as fields for super call.
	 * @param someMake the manufacturer of this car
	 * @param someModel the model of the car obj
	 * @param someColor the color chosen for this car
	 * @param someCust the customer to be linked to
	 * @param someYear the year of this car
	 * @param someVIN the vin for the car, once set here never changed later
	 * @param mayConvert the boolean saying whether or not this converts
	 * */
	public Sedan(Customer someCust, String makeN, String modelN, CarColors colorN, int yearN, String someVIN, boolean mayConvert)
	{
		super(makeN, modelN, colorN, yearN, someVIN, someCust);
		this.isConvertible = mayConvert;
	}
	
	/**
	 * Assigns without validation the given input for the respective field.
	 * @param someStatus value that is binary - either is or is not true
	 * */
	public void setConvertibleStatus(boolean someStatus)
	{this.isConvertible = someStatus;}
	
	/**
	 * Returns the value of the status of convertible.
	 * @return the value of the status of convertible
	 * */
	public boolean getConvertibleStatus()
	{return this.isConvertible;}
	
	/**
	 * Returns the str that includes super and new field information.
	 * @return the str that includes super and new field information
	 * */
	public String toString()
	{
		String out = super.toString();
		out += String.format(", Convertible: %b", this.isConvertible);
		return out;
	}
}