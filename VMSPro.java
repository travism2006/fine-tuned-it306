package vmspro;
 
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import vmspro.VMSPro_Constants.CarColors;
import vmspro.VMSPro_Constants.CarTypes;

/**
 * For VMS Pro, Java's Swing and AWT features are used.
 * This is the umbrella class that manages each data type that the application
 * will process. Uses Java SE Collections interfaces including <code>Set</code>
 * enabling the use of <code>Iterator</code> to be easily implemented upon
 * general use of the API.
 * @verion 1.0.b
 * @author tmitchu2
 * @author hammar
 * */

public final class VMSPro
{
	private LinkedList<Customer> listCust;
	private Stack<Customer> deletedCust;
	private LinkedList<Vehicle> listCars;
	private Stack<Vehicle> deletedCars;
	
	/**
	 * Default constructor for initializing lists and stacks for
	 * application starting.
	 * */
	public VMSPro()
	{
		this.listCust = new LinkedList<>();
		this.listCars = new LinkedList<>();
		this.deletedCust = new Stack<>();
		this.deletedCars = new Stack<>();
	}
	
	/**
	 * Adds a customer as long as it is a valid customer object.
	 * Checks for null object input.
	 * @param someCust the customer obj to be added to the list
	 * @return the success of adding the customer to the list
	 * */
	public boolean addCustomer(Customer someCust)
	{
		if(someCust == null)return false;
		else if(this.getCustomerList().contains(someCust))return false;
		return this.listCust.add(someCust); 
	}
	
	/**
	 * Adds a car to the list of cars managed. Checks for invalid car input.
	 * @param someCar the car to be added to the car list
	 * @return the success of adding the car to the car list
	 * */
	public boolean addCar(Vehicle someCar)
	{
		if(someCar == null)return false;
		else if(this.getVINList().contains(someCar.getVin()))return false;
		return this.listCars.add(someCar);
	}
	
	/**
	 * Remove the given customer as long as they are already in the system.
	 * Upon removal from list, a stack grabs the removed customer object.
	 * @param someCust the customer to be removed
	 * @param keepCars determines if the cars associated are also deleted
	 * @return whether or not the operation was a success
	 * */
	public boolean removeCustomerX(Customer someCust, boolean keepCars)
	{
		if(someCust == null)return false;
		else if(!this.listCust.contains(someCust))return false;
		
		if(listCust.remove(someCust))
		{
			Iterator<Vehicle> itCars = someCust.getVehicleList().values().iterator();
			while(itCars.hasNext())
			{
				Vehicle carX = itCars.next();
				carX.linkCustomer(null);
				/* ------------------------------------------------------------
				 * If keepCars is true, then the dealership <b>does not</b>
				 * delete the cars. If false, the cars are deleted in addition
				 * to the customer.
				 * ----------------------------------------------------------*/
				if(keepCars == false)
				{
					listCars.remove(carX);
					deletedCars.push(carX);
				}
			}
			someCust.removeAllCars();
			deletedCust.push(someCust);
			return true;
		}
		return false;
	}
	
	/**
	 * Remove the given vehicle as long as it is already in the system.
	 * Upon removal from list, a stack grabs the removed vehicle object.
	 * @param someCar the customer to be removed
	 * @return whether or not the operation was a success
	 * */
	public boolean removeCarX(Vehicle someCar)
	{
		if(someCar == null)return false;
		else if(!this.listCars.contains(someCar))return false;
		
		if(listCars.remove(someCar))
		{
			if(someCar.getCustomer() != null)
			{
				Customer custX = someCar.getCustomer();
				custX.removeCar(someCar);
				someCar.linkCustomer(null);
				deletedCars.push(someCar);
				return true;
			}
			else
			{
				someCar.linkCustomer(null);
				deletedCars.push(someCar);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the very first customer in the list as long as the list is not
	 * empty.  Upon successful removal, the customer is pushed onto the stack
	 * that tracks all deleted customers.
	 * @param keepCars determines if linked cars are deleted or not
	 * @return the success or failure of removal
	 * */
	public boolean removeFirstCustomer(boolean keepCars)
	{
		try
		{
			Customer cust = listCust.removeFirst();
			Iterator<Vehicle> itCars = cust.getVehicleList().values().iterator();
			while(itCars.hasNext())
			{
				Vehicle carX = itCars.next();
				carX.linkCustomer(null);
				/* ------------------------------------------------------------
				 * If keepCars is true, then the dealership <b>does not</b>
				 * delete the cars. If false, the cars are deleted in addition
				 * to the customer.
				 * ----------------------------------------------------------*/
				if(keepCars == false)
				{
					listCars.remove(carX);
					deletedCars.push(carX);
				}
			}
			deletedCust.push(cust);
			return true;
		}catch(NoSuchElementException e)
		{return false;}
	}
	
	/**
	 * Removes the very last customer in the list as long as the list is not
	 * empty.  Upon successful removal, the customer is pushed onto the stack
	 * that tracks all deleted customers.
	 * @param keepCars determines if linked cars are deleted or not
	 * @return the success or failure of removal
	 * */
	public boolean removeLastCustomer(boolean keepCars)
	{
		try
		{
			Customer cust = listCust.removeLast();
			Iterator<Vehicle> itCars = cust.getVehicleList().values().iterator();
			while(itCars.hasNext())
			{
				Vehicle carX = itCars.next();
				carX.linkCustomer(null);
				/* ------------------------------------------------------------
				 * If keepCars is true, then the dealership <b>does not</b>
				 * delete the cars. If false, the cars are deleted in addition
				 * to the customer.
				 * ----------------------------------------------------------*/
				if(keepCars == false)
				{
					listCars.remove(carX);
					deletedCars.push(carX);
				}
			}
			deletedCust.push(cust);
			return true;
		}catch(NoSuchElementException e)
		{return false;}
	}
	
	/**
	 * Removes the very first vehicle in the list as long as the list is not
	 * empty.  Upon successful removal, the car is pushed onto the stack
	 * that tracks all deleted vehicles.
	 * @return the success or failure of removal
	 * */
	public boolean removeFirstVehicle()
	{
		try
		{
			Vehicle car = listCars.removeFirst();
			Customer cust = car.getCustomer();
			if(cust != null)cust.removeCar(car);
			//possible redundancy--car.linkCustomer(null);
			deletedCars.push(car);
			return true;
		}catch(NoSuchElementException e)
		{return false;}
	}
	
	/**
	 * Removes the very last vehicle in the list as long as the list is not
	 * empty.  Upon successful removal, the car is pushed onto the stack
	 * that tracks all deleted vehicles.
	 * @return the success or failure of removal
	 * */
	public boolean removeLastVehicle()
	{
		try
		{
			Vehicle car = listCars.removeLast();
			Customer cust = car.getCustomer();
			if(cust != null)cust.removeCar(car);
			//possible redundancy--car.linkCustomer(null);
			deletedCars.push(car);
			return true;
		}catch(NoSuchElementException e)
		{return false;}
	}

	/**
	 * Grabs all <code>toString()</code> information from the customer object
	 * and adds additional system meta data about it.
	 * @param someCust the customer obj to extract <code>toString()</code> from
	 * @return a general report on this customer
	 * */
	public String getCustomerXReport(Customer someCust)
	{
		if(someCust == null)return null;
		else if(listCust.contains(someCust))
		{
			Iterator<Customer> custIT = listCust.iterator();
			int counter = 0;
			while(custIT.hasNext())
			{
				counter++;
				Customer client = custIT.next();
				if(client.equals(someCust))
				{break;}
			}
			String toStr = String.format(
					"Position in VMS Pro: %d\n\nCustomer Details:\n%s",
					counter, someCust.toString());
			return toStr;
		}
		else if(deletedCust.contains(someCust))
		{
			Iterator<Customer> custIT = deletedCust.iterator();
			int counter = 0;
			while(custIT.hasNext())
			{
				counter++;
				Customer client = custIT.next();
				if(client.equals(someCust))
				{break;}
			}
			String toStr = String.format(
					"Position in VMS Pro: %d\n\nCustomer Details:\n(*deleted customer*)"
					+ "\n%s",
					counter, someCust.toString());
			return toStr;	
		}
		return null;
	}
	
	/**
	 * Grabs all <code>toString()</code> information from the vehicle object
	 * and adds additional system meta data about it.
	 * @param someCar the vehicle obj to extract <code>toString()</code> from
	 * @return a general report on this vehicle
	 * */
	public String carXReport(Vehicle someCar)
	{
		if(someCar == null)return null;
		else if(listCars.contains(someCar))
		{
			Iterator<Vehicle> carsIT = listCars.iterator();
			int counter = 0;
			while(carsIT.hasNext())
			{
				counter++;
				Vehicle car = carsIT.next();
				if(car.equals(someCar))
				{break;}
			}
			String toStr = null;
			if(someCar instanceof Sedan)
			{
				toStr= String.format(
						"Position in VMS Pro: %d\n\nVehicle(Sedan) Details:\n%s",
						counter, ((Sedan)someCar).toString());
			}
			else if(someCar instanceof Truck)
			{
				toStr= String.format(
						"Position in VMS Pro: %d\n\nVehicle(Truck) Details:\n%s",
						counter, ((Truck)someCar).toString());
			}
			else if(someCar instanceof Van)
			{
				toStr= String.format(
						"Position in VMS Pro: %d\n\nVehicle(Van) Details:\n%s",
						counter, ((Van)someCar).toString());
			}
			return toStr;
		}
		else if(deletedCars.contains(someCar))
		{
			Iterator<Vehicle> carsIT = deletedCars.iterator();
			int counter = 0;
			while(carsIT.hasNext())
			{
				counter++;
				Vehicle car = carsIT.next();
				if(car.equals(someCar))
				{break;}
			}
			String toStr = null;
			if(someCar instanceof Sedan)
			{
				toStr= String.format(
						"Position in VMS Pro: %d\n\nVehicle(Sedan*deleted*) Details:\n%s",
						counter, ((Sedan)someCar).toString());
			}
			else if(someCar instanceof Truck)
			{
				toStr= String.format(
						"Position in VMS Pro: %d\n\nVehicle(Truck*deleted*) Details:\n%s",
						counter, ((Truck)someCar).toString());
			}
			else if(someCar instanceof Van)
			{
				toStr= String.format(
						"Position in VMS Pro: %d\n\nVehicle(Van*deleted*) Details:\n%s",
						counter, ((Van)someCar).toString());
			}
			return toStr;	
		}
		return null;
	}

	/**
	 * Gets all vehicles with the specified color and makes a general summary
	 * of this color.
	 * @return the string repot of this color for all vehicles
	 * */
	public String carColorXReport(CarColors someColor)
	{
		int colorCounter = 0;
		Iterator<Vehicle> carsIT = listCars.iterator();
		LinkedList<Vehicle> colorCars = new LinkedList<>();
		while(carsIT.hasNext())
		{
			Vehicle vehicle = carsIT.next();
			if(vehicle.getColor() == someColor)
			{colorCars.add(vehicle);colorCounter++;}
		}
		
		String out = String.format("Color: %s\nOccurred: %d times\n",
				someColor.toString(), colorCounter);
		Iterator<Vehicle> coloredCars = colorCars.iterator();
		while (coloredCars.hasNext())
		{
			Vehicle car = (Vehicle) coloredCars.next();
			String vin = car.getVin(), make= car.getMake(), model=car.getModel();
			int year = car.getYear();
			if(car instanceof Sedan)
			{
				out += String.format(
						"Type: Sedan\nVIN: %s, Make: %s, Model: %s, Year: %d",
						vin, make, model, year);
			}
			else if(car instanceof Truck)
			{
				out += String.format(
						"Type: Truck\nVIN: %s, Make: %s, Model: %s, Year: %d",
						vin, make, model, year);
			}
			else if(car instanceof Van)
			{
				out += String.format(
						"Type: Van\nVIN: %s, Make: %s, Model: %s, Year: %d",
					vin, make, model, year);
			}
		}
		return out;
	}
	
	/**
	 * Returns the first customer that has a matching id.
	 * Primarily used by GUI to identify customers against input jtextfields.
	 * @param someID the id to check with
	 * @return the matching customer object or null
	 * */
	public Customer getCustomerByID(int someID)
	{
		if(someID < VMSPro_Constants.MIN_NUMBER_INPUT)return null;
		
		Iterator<Customer> customers = listCust.iterator();
		while(customers.hasNext())
		{
			Customer cust = (Customer)customers.next();
			if(cust.getCustomerId() == someID)
			{return cust;}
		}
		return null;
	}
	
	/**
	 * Returns the first vehicle that has a matching VIN.
	 * Primarily used by GUI to identify cars against input jtextfields.
	 * @param someVIN the VIN to check with
	 * @return the matching vehicle object or null
	 * */
	public Vehicle getVehicleByVIN(String someVIN)
	{
		if(!VMSPro.checkString(someVIN))return null;
		
		//otherwise valid VIN was given
		Iterator<Vehicle> carItr = listCars.iterator();
		while(carItr.hasNext())
		{
			Vehicle car = (Vehicle)carItr.next();
			if(car.getVin().equals(someVIN))return car;
		}
		return null;
	}

	/**
	 * Returns the list of the customers currently available.
	 * @return the list reference of all customers
	 * */
	public LinkedList<Customer> getCustomerList()
	{return this.listCust;}
	
	/**
	 * Returns the list of the cars currently available.
	 * @return the list reference of all cars
	 * */
	public LinkedList<Vehicle> getCarList()
	{return this.listCars;}
	
	/**
	 * Returns the stack of the customers currently unavailable.
	 * @return the stack reference of all customers
	 * */
	public Stack<Customer> getDeletedCustomersStack()
	{return this.deletedCust;}
	
	/**
	 * Returns the stack of the cars currently unavailable.
	 * @return the stack reference of all cars
	 * */
	public Stack<Vehicle> getDeletedCarStack()
	{return this.deletedCars;}
	
	/**
	 * Return a sublist of customers that have no associated vehicle object.
	 * @return the sublist of customers without linked cars
	 * */
	public LinkedList<Customer> getCustomersWithNoCars()
	{
		LinkedList<Customer> custNoCars = new LinkedList<>();
		Iterator<Customer> custIT = this.listCust.iterator();
		while(custIT.hasNext())
		{
			Customer client = custIT.next();
			if(client.getVehicleList().isEmpty())
			{custNoCars.add(client);}
		}
		return custNoCars;
	}
	
	/**
	 * Return a sublist of vehicles that have no associated customer object.
	 * @return the sublist of vehicles without linked customer
	 * */
	public LinkedList<Vehicle> getVehicleNoClient()
	{
		LinkedList<Vehicle> carNoClientList = new LinkedList<>();
		Iterator<Vehicle> carIT = this.listCars.iterator();
		while(carIT.hasNext())
		{
			Vehicle car = carIT.next();
			if(car.getCustomer()==null)
			{carNoClientList.add(car);}
			else if(car.getCustomer().equals(null))
			{carNoClientList.add(car);}
		}
		return carNoClientList;
	}
	
	/**
	 * Returns a <code>List</code> of the currently existing cars' VIN field
	 * within the VMS Pro appication.<br>
	 * <i>Note: a set of VINs is possible since no two vins can be identical</i>
	 * @return <code>List</code> of all curent vehicle VINs
	 * */
	public List<String> getVINList()
	{
		List<String> vins = new LinkedList<String>();
		for (int i = 0; i < this.listCars.size(); i++)
		{vins.add(listCars.get(i).getVin());}
		return vins;
	}
	
	/**
	 * Returns a <code>List</code> of the currently existing cars' <i>make</i>
	 * field within the VMS Pro appication.<br>
	 * <i>The use of <code>Set</code> is not used due to lack of support for
	 * each vehicle instance that has a specific make value since it is
	 * possible for one 'make' value to be seen several times across
	 * different vehicle instances.</i>
	 * @return <code>List</code> of all curent vehicle make(s)
	 * */
	public List<String> getListMakes()
	{
		List<String> makes = new LinkedList<String>();
		for (int i = 0; i < this.listCars.size(); i++)
		{makes.add(listCars.get(i).getMake());}
		return makes;
	}
	
	/**
	 * Returns a <code>List</code> of the currently existing cars' <i>model</i>
	 * field within the VMS Pro appication.<br>
	 * <i>The use of <code>Set</code> is not used due to lack of support for
	 * each vehicle instance that has a specific make value since it is
	 * possible for one 'make' value to be seen several times across
	 * different vehicle instances.</i>
	 * @return <code>List</code> of all curent vehicle model(s)
	 * */
	public List<String> getModelList()
	{
		List<String> models = new LinkedList<String>();
		for (int i = 0; i < this.listCars.size(); i++)
		{models.add(listCars.get(i).getModel());}
		return models;
	}
	
	/**
	 * Returns a <code>List</code> of cars that are instances of Sedan car type.
	 * @return <code>List</code> of all curent vehicles that are Sedans
	 * */
	public List<Sedan> getSedanList()
	{
		List<Sedan> sedans = new LinkedList<Sedan>();
		for (int i = 0; i < this.listCars.size(); i++)
		{
			if(listCars.get(i) instanceof Sedan)
			{sedans.add((Sedan) listCars.get(i));}
		}
		return sedans;
	}
	
	/**
	 * Returns a <code>List</code> of cars that are instances of Truck car type.
	 * @return <code>List</code> of all curent vehicles that are Trucks
	 * */
	public List<Truck> getTruckList()
	{
		List<Truck> trucks = new LinkedList<Truck>();
		for (int i = 0; i < this.listCars.size(); i++)
		{
			if(listCars.get(i) instanceof Truck)
			{trucks.add((Truck) listCars.get(i));}
		}
		return trucks;
	}
	
	/**
	 * Returns a <code>List</code> of cars that are instances of Van car type.
	 * @return <code>List</code> of all curent vehicles that are Vans
	 * */
	public List<Van> getVanList()
	{
		List<Van> vans = new LinkedList<Van>();
		for (int i = 0; i < this.listCars.size(); i++)
		{
			if(listCars.get(i) instanceof Van)
			{vans.add((Van) listCars.get(i));}
		}
		return vans;
	}
	
	/**
	 * Returns a <code>List</code> of the currently existing customers' ID field
	 * within the VMS Pro appication.
	 * @return <code>List</code> of all curent customer IDs
	 * */
	public List<Integer> getCustomerIDSet()
	{
		List<Integer> ids = new LinkedList<Integer>();
		for (int i = 0; i < this.listCust.size(); i++)
		{ids.add(listCust.get(i).getCustomerId());}
		return ids;
	}
	
	/**
	 * Returns a <code>List</code> of the currently existing customers' first
	 * name within the VMS Pro appication.
	 * @return <code>List</code> of all curent customer first names
	 * */
	public List<String> getFirstNameList()
	{
		List<String> fnamelist= new LinkedList<String>();
		for (int i = 0; i < this.listCust.size(); i++)
		{fnamelist.add(listCust.get(i).getFirstName());}
		return fnamelist;
	}
	
	/**
	 * Returns a <code>List</code> of the currently existing customers' last
	 * name within the VMS Pro appication.
	 * @return <code>List</code> of all curent customer last names
	 * */
	public List<String> getLastNameList()
	{
		List<String> lnameList= new LinkedList<String>();
		for (int i = 0; i < this.listCust.size(); i++)
		{lnameList.add(listCust.get(i).getLastName());}
		return lnameList;
	}
	
	/**
	 * Return a report given the type of vehicle of interest.
	 * @param typeX the type of vehicle to report on
	 * @return the string report on the type of vehicle
	 * */
	public String reportCarType(CarTypes typeX)
	{
		String out = "";
		switch(typeX)
		{
			case SEDAN:
				List<Sedan> sedans = this.getSedanList();
				Iterator<Sedan> itSedan = sedans.iterator();
				out = "";
				
				while(itSedan.hasNext())
				{out+=itSedan.next().toString()+"\n";}
				
				return out;
			case TRUCK:
				List<Truck> trucks = this.getTruckList();
				Iterator<Truck> itTruck = trucks.iterator();
				out = "";
				
				while(itTruck.hasNext())
				{out+=itTruck.next().toString()+"\n";}
				
				return out;
			case VAN:
				List<Van> vans = this.getVanList();
				Iterator<Van> itVan = vans.iterator();
				out = "";
				
				while(itVan.hasNext())
				{out+=itVan.next().toString()+"\n";}
				
				return out;
			default:
				return "";
		}
	}
	
	
	//basic search options
	/**
	 * Goes through the list of customrs and tries to find the matching
	 * customer with the given first and last name. If the list is empty,
	 * bad input is passed, returns null.
	 * @param fname the first name to check against
	 * @param lname the last name to check against
	 * @return the customer that has matching first and last name, or null
	 * */
	public Customer findCustomerByName(String fname, String lname)
	{
		if(!VMSPro.checkString(fname))return null;
		else if(!VMSPro.checkString(lname))return null;
		Iterator<Customer> custIT = listCust.iterator();
		while(custIT.hasNext())
		{
			Customer cust = custIT.next();
			if(cust.getFirstName().equals(fname) && cust.getLastName().equals(lname))
			{return cust;}
		}
		return null;
	}
	
	/**
	 * Returns the string that shows all data currently stored in the app.
	 * @return the string that shows all data currently stored in the app
	 * */
	public String toString()
	{
		String out = "";
		
		for(Customer cust: listCust)
		{out += cust.toString();}
		
		out += "\n\n";
		for(Vehicle car: listCars)
		{
			if(car instanceof Sedan)
			{
				Sedan sidsCar = (Sedan)car;
				out += sidsCar.toString();
			}else if(car instanceof Truck)
			{
				Truck tammysTruck = (Truck)car;
				out += tammysTruck.toString();
			}else if(car instanceof Van)
			{
				Van vincentsVan = (Van)car;
				out += vincentsVan.toString();
			}
		}
		
		out += "\n\n";
		for (Customer cust : deletedCust)
		{out += cust.toString();}
		
		out += "\n\n";
		for (Vehicle car : deletedCars)
		{
			if(car instanceof Sedan)
			{
				Sedan sidsCar = (Sedan)car;
				out += sidsCar.toString();
			}else if(car instanceof Truck)
			{
				Truck tammysTruck = (Truck)car;
				out += tammysTruck.toString();
			}else if(car instanceof Van)
			{
				Van vincentsVan = (Van)car;
				out += vincentsVan.toString();
			}
		}
		return out;
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
		
		for(int i = 0; i< someStr.length(); i++)
		{
			if(!Character.isLetterOrDigit(someStr.charAt(i)))
			{return false;}
		}
		return true;
	}
	
	/**
	 * Determines the validity of a given string
	 * @param someStr the string to test on
	 * @return whether it passes the tests or not
	 * */
	public static boolean checkStreetCity(String someStr)
	{
		if(someStr == null)
		{return false;}
		else if(someStr.equals(""))
		{return false;}
		else if(someStr.equals(null))
		{return false;}
		
		for(int i = 0; i< someStr.length(); i++)
		{
			if(!Character.isLetterOrDigit(someStr.charAt(i)) &&
					someStr.charAt(i) !=' ')
			{return false;}
		}
		return true;
	}
	
	/**
	 * Determines if the passed argument is valid and has the required length
	 * to be a correct VIN.
	 * @param someStr the string to be checked against
	 * @return whether or not the given str is a valid VIN
	 * */
	public static boolean checkVIN(String someStr)
	{
		if(!checkString(someStr))return false;
		else if(someStr.length() != VMSPro_Constants.VIN_LENGTH)
		{return false;}
		return true;
	}
	
	/**
	 * Checks if the string can be cast as int, but will throw
	 * NumberFormatException if this cannot be done.
	 * @param text the str to be validated and parsed
	 * @return whether or not the input str is strictly a number
	 * @throws NumberFormatException if the input text cannot be parsed as int
	 * */
	public static boolean checkNumber(String text)
	{
		if(VMSPro.checkString(text))
		{
			try
			{
				int number = Integer.parseInt(text);
				
				if(number > VMSPro_Constants.MIN_NUMBER_INPUT)
				{return true;}
				return false;
			}
			catch(NumberFormatException e)
			{return false;}
		}
		return false;
	}
}