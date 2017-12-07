package vmspro;

import java.util.HashMap;

import vmspro.VMSPro_Constants.CarColors;

/**
 * A DDC that defines this parent class 'Vehicle'. Has all assignments for any
 * arbitrary vehicle, leting child classes do independent field initialization.
 * 
 * @author hammar
 * @author tmitchu2
 */

public class Vehicle implements VMSPro_Standard_Behavior {
  private int                        lotNumber;
  private static int                 carTotal = 0;
  private String                     make;
  private String                     model;
  private CarColors                  color;
  private int                        year;
  private static final int           MIN_YEAR = 0;
  private String                     vin;
  private HashMap<Integer, Customer> cust;

  /**
   * Default constructor for id and lot id tracking.
   */
  public Vehicle() {
    carTotal++;
    this.lotNumber = carTotal;
    this.cust = new HashMap<>();
  }

  /**
   * Specific constructor for assignment of inputs to respective fields.
   * 
   * @param someMake
   *          the manufacturer of this car
   * @param someModel
   *          the model of the car obj
   * @param someColor
   *          the color chosen for this car
   * @param someYear
   *          the year of this car
   * @param someVIN
   *          the vin for the car, once set here never changed later
   */
  public Vehicle(String someMake, String someModel, CarColors someColor, int someYear, String someVIN) {
    this();
    this.make = someMake;
    this.model = someModel;
    this.color = someColor;
    this.year = someYear;
    this.vin = someVIN;
  }

  /**
   * Specific constructor for assignment of inputs to respective fields.
   * 
   * @param someMake
   *          the manufacturer of this car
   * @param someModel
   *          the model of the car obj
   * @param someColor
   *          the color chosen for this car
   * @param someYear
   *          the year of this car
   * @param someVIN
   *          the vin for the car, once set here never changed later
   * @param someCust
   *          the customer to be linked to
   */
  public Vehicle(String someMake, String someModel, CarColors someColor, int someYear, String someVIN,
      Customer someCust) {
    this(someMake, someModel, someColor, someYear, someVIN);
    this.make = someMake;
    this.model = someModel;
    this.color = someColor;
    this.year = someYear;
    this.vin = someVIN;
    this.cust.put(someCust.getCustomerId(), someCust);
  }

  /**
   * Validates that the string is valid, unique, and assigns to the correct
   * field.
   * 
   * @param someMake
   *          the make to set
   * @return shows success or failure of assignment
   */
  public boolean setMake(String someMake) {
    if (VMSPro.checkString(someMake)) {
      this.make = someMake;
      return true;
    }
    return false;
  }

  /**
   * Validates the string input and assigns it.
   * 
   * @param model
   *          the model to set
   * @return the success or failure of assignment
   */
  public boolean setModel(String someModel) {
    if (VMSPro.checkString(someModel)) {
      this.model = someModel;
      return true;
    }
    return false;
  }

  /**
   * Uses the enumeration of colors supported to assign the respective field.
   * 
   * @param color
   *          the color to set
   */
  public void setColor(CarColors someColor) {
    this.color = someColor;
  }

  /**
   * Verifies that the number is valid and then assigns it the to correct field
   * for the vehicle.
   * 
   * @param someYear
   *          the year to set
   * @return the success or failure of assignment
   */
  public boolean setYear(int someYear) {
    if (someYear > MIN_YEAR) {
      this.year = someYear;
      return true;
    }
    return false;
  }

  /**
   * Validating method for associating a single customer to this car. Does not
   * allow null pointers to a customer object.
   * 
   * @param someCust
   *          the customer to be linked to
   */
  public void linkCustomer(Customer someCust) {
    if (someCust != null) {
      this.cust.put(someCust.getCustomerId(), someCust);
    }

  }

  /**
   * Returns the customer linked to this vehicle.
   * 
   * @return the customer linked to this vehicle
   */
  public Customer getCustomer() {
    if (this.cust.isEmpty())
      return null;
    else if (this.cust.values().contains(null))
      return null;

    Object client = cust.values().toArray()[0];
    if (client == null)
      return null;
    else if (client.equals(null))
      return null;
    return (Customer) client;
  }

  /**
   * Returns the lot # of this car.
   * 
   * @return the lot # of this car
   */
  public int getLotNumber() {
    return this.lotNumber;
  }

  /**
   * Returns the VIN of this car.
   * 
   * @return the vin
   */
  public String getVin() {
    return this.vin;
  }

  /**
   * Returns the year of this car.
   * 
   * @return the year of this car
   */
  public int getYear() {
    return this.year;
  }

  /**
   * Returns the color of this car.
   * 
   * @return the color of this car
   */
  public CarColors getColor() {
    return this.color;
  }

  /**
   * Returns the make of this car.
   * 
   * @return the make of this car
   */
  public String getMake() {
    return this.make;
  }

  /**
   * Returns the manufacturer of this car.
   * 
   * @return the manufacturer of this car
   */
  public String getModel() {
    return this.model;
  }

  /**
   * Returns the total number of cars.
   * 
   * @return the total number of cars
   */
  public static int getCarTotal() {
    return carTotal;
  }

  /**
   * Returns a string representation of the vehicle's basic information.
   * 
   * @return a string representation of the vehicle's basic information
   */
  @Override
  public String toString() {
    String out = String.format("Lot#: %d, VIN: %s, Make: %s, Model: %s, Year: %d, Color: %s", this.lotNumber, this.vin,
        this.make, this.model, this.year, this.color);
    return out;
  }

  /**
   * Uses the VIN field of a car to check if any two cars are the same.
   * 
   * @param someCar
   *          the car to compare VINs against
   * @return whether or not the given car and this car are the same based on VIN
   */
  public boolean equals(Vehicle someCar) {
    if (this.getVin() == someCar.getVin())
      return true;
    return false;
  }
}