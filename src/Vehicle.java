
public class Vehicle {

	private int idCounter = 500;
	private int id;
	private String make;
	private String model;
	private String color;
	private int year;
	private String vin;
	private String type;

	/**
	 * Default Constructor
	 */
	public Vehicle() {
		this.id = idCounter + id;
	}

	/**
	 * @param idCounter
	 * @param id
	 * @param make
	 * @param model
	 * @param color
	 * @param year
	 * @param vin
	 * @param type
	 */
	public Vehicle(int id, String make, String model, String color, int year, String vin, String type) {

		this.id = idCounter + id;
		this.make = make;
		this.model = model;
		this.color = color;
		this.year = year;
		this.vin = vin;
		this.type = type;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make
	 *            the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin
	 *            the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the idCounter
	 */
	public int getIdCounter() {
		return idCounter;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * this object to string value.
	 */
	@Override
	public String toString() {
		return "Vehicle [idCounter=" + idCounter + ", id=" + id + ", make=" + make + ", model=" + model + ", color="
				+ color + ", year=" + year + ", vin=" + vin + ", type=" + type + "]";
	}

}
