package vmspro;

/**
 * DDC for aggregate relationship to form between addresses and customers.
 * @author hammar
 * @author tmitchu2
 * */
public final class Address
{
	private String streetAddress;
	private String city;
	private String state;
	private int zipCode;
	
	/**
	 * Specific constructor for each field.
	 * @param someStreetAddress
	 * @param someCity
	 * @param someState the state where this address is located in
	 * @param someZip the zipcode for this address
	 */
	public Address(String someStreetAddress, String someCity, String someState, int someZip)
	{
		this.streetAddress = someStreetAddress;
		this.city = someCity;
		this.state = someState;
		this.zipCode = someZip;
	}

	/**
	 * Returns the value of the street address.
	 * @return the value of the street address
	 * */
	public String getStreetAddress()
	{return this.streetAddress;}

	/**
	 * Specifies the house number or apartment number on a street.
	 * Validates for null attributes and pointers throug static methods in VMS Pro.
	 * @param someStreet the street address with apt. # or house number
	 * @return whether or not the assigning of data worked
	 * */
	public boolean setStreetAddress(String someStreet)
	{
		if(VMSPro.checkString(someStreet))
		{
			this.streetAddress = someStreet;
			return true;
		}
		return false;
	}

	/**
	 * Returns the string value for the city field.
	 * @return the string value for the city field
	 * */
	public String getCity()
	{return this.city;}

	/**
	 * Validates for null attributes and pointers throug static methods in VMS Pro.
	 * Will attempt to assign to the city field if not null.
	 * @param someCity the city to be assigned for the respective field
	 * @return whether or not the assigning of data worked
	 * */
	public boolean setCity(String someCity)
	{
		if(VMSPro.checkString(someCity))
		{
			this.city = someCity;
			return true;
		}
		return false;
	}

	/**
	 * Returns the string value for the state field.
	 * @return the string value for the state field
	 * */
	public String getState()
	{return this.state;}

	/**
	 * Validates for null attributes and pointers throug static methods in VMS Pro.
	 * Will attempt to assign to the state field if not null.
	 * @param someState the state to be assigned for the respective field
	 * @return whether or not the assigning of data worked
	 * */
	public boolean setState(String someState)
	{
		if(VMSPro.checkString(someState))
		{
			this.city = someState;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the human readable string representation of this address obj.
	 * @return the human readable string representation of this address obj
	 * */
	public String toString()
	{
		String out = String.format("Street Address: %s, City: %s, State: %s, Zipcode: %d",
				  this.streetAddress, this.city, this.state,this.zipCode);
		return out;
	}
}