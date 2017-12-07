package Phase4;
 
/**
 * VMS Pro desktop application is started through this file.
 * The 'manager' file works with the gui file(s) to create
 * the 'full stack' from front end to back end.
 * For VMS Pro, Java's Swing and AWT features are used.
 * @author tmitchu2
 * */

public class VMSProApp
{
	/**
	 * Validates a provided VIN string against the required
	 * length (17) as well as standard value and pointer checking.
	 * @param text the VIN to be validated
	 * @return whether or not the provided VIN is valid
	 * */
	public static boolean checkVIN(String text)
	{
		if(checkString(text))
		{
			if(text.length() != VMSPro_Constants.VIN_LENGTH)return false;
			/*now work the valid string data*/
			char[] textChars = text.toCharArray();
			for(char txtChar: textChars)
			{
				/*
				 * Check the VIN characters for invalid characters that are not
				 * digits or letters.
				 * */
				if(!Character.isLetterOrDigit(txtChar))
					return false;
			}
			return true;
		}
		
		return false;
	}

	/**
	 * Checks if the string can be cast as int, but will throw
	 * NumberFormatException if this cannot be done.
	 * @param text the str to be validated and parsed
	 * @return whether or not the input str is strictly a number
	 * @throws NumberFormatException if the input text cannot be parsed as int
	 * */
	public static boolean checkCarYear(String text)
	{
		try
		{
			int yearVal = Integer.parseInt(text);
			
			if(yearVal > 0)
			{return true;}
			return false;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * */
	public static boolean checkCustomerID(String text)
	{
		
		
		
		
		return false;
	}
	
	/**
	 * Determines if the string provided points to null value in memory,
	 * is containing null value, or is equivalent to an empty string.
	 * Passing these 3 tests, the string is said to be 'valid' and 'unique'.
	 * @param someStr the string to be tested for validity and uniqueness
	 * @return whether or not the str is valid and uniquely set
	 * */
	public static boolean checkString(String someStr)
	{
		if(someStr == null)
		{return false;}
		else if(someStr.equals(""))
		{return false;}
		else if(someStr.equals(null))
		{return false;}
		return true;
		
	}
}
