package Phase4;

/**
 * Primarily used for reducing 'magic numbers and values' across the code base.
 * @author tmitchu2
 * */
public interface VMSPro_Constants
{
	String CarFileName = "vehicleData.txt";
	String CustomerFileName = "customerData.txt";
	int pixelColWidth = 15;
	int VIN_LENGTH = 17;
	
	public enum CarTypes
	{
		SEDAN, TRUCK, VAN
	}
	public enum CarColors
	{
		RED, BLACK, WHITE, BLUE, GREEN, YELLOW, CYAN,ORANGE,GRAY
	}
}
