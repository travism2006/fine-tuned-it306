package vmspro; 

/**
 * Primarily used for reducing 'magic numbers and values' across the code base.
 * @author tmitchu2
 * */

public interface VMSPro_Constants
{
	/*Needed for the JTxtFields in the GUIs and form inputs*/
	int pixelColWidth = 17;
	int TxtAreaHeight = 20;
	int TxtAreaWidth = 13;
	String TxtCarFullHeader = "Lot ID\tVIN\t\tMake\tModel\tYear\tColor\tType\tCustomer ID";
	String TxtCustomerHeader = "ID Number\tFirst Name\tLast Name\tNumber of Cars\tAddress";
	
	String HasNoClient = "\n%d\t%s\t%s\t%s\t%d\t%s\t%s\tN\\A";
	String HasClient = "\n%d\t%s\t%s\t%s\t%d\t%s\t%s\t%d";
	String HasCar = "%d\t%s\t%s\t%d\t%s";
	
	/*Required from Phase 1 and beyond*/
	int VIN_LENGTH = 17;
	int MAX_CUSTOMERS = 800;
	
	/*Useful for validating inputs of type double*/
	int MIN_NUMBER_INPUT = 0;
	
	/*These are the current types of cars that VMS Pro supports. In version
	 * 1.0.b there could be more types supported as well as different colors.
	 */
	public enum CarTypes
	{
		SEDAN, TRUCK, VAN
	}
	public enum CarColors
	{
		RED, BLACK, WHITE, BLUE
	}

	/*Options for sorting lists of cars and customers*/
	public enum SortOptions
	{INSERT, MERGE, QUICK}
	
	/*Search options for searching customers or cars*/
	public enum SearchOptions
	{BINARY_SEARCH_TREE,BINARY_SEARCH, HASHTABLE,HASHMAP,LINEAR}
}