package vmspro;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;


/**
* This class is designed for input and output to files
* this class will read and write object to and from a file 
* by converting them into json objects
* @author hammar
*/

public final class FileHandler
{
	/**
	 * Writes to a specific VMS Pro system file for all customer data.
	 * @return that the operation was a success
	 * */
	public static boolean writeToCustomerFile(List<Customer> customerList)
	{
		BufferedWriter bufferedWriter = null;
		try {
			File myFile = new File("Client_VMSPro_Data.txt");
			myFile.createNewFile();
			Writer writer = new FileWriter(myFile);
			bufferedWriter = new BufferedWriter(writer);

			for (Customer cust : customerList) {

				Gson g = new Gson();
				String x = g.toJson(cust);
				bufferedWriter.write(x);
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null)
					bufferedWriter.close();
			} catch (Exception ex) {

			}
		}
		return true;
	}
	
	/**
	 * Creates a list instance of the data that is captured from the file.
	 * @return the list of customer data
	 * */
	@SuppressWarnings("deprecation")
	public static List<Customer> readToCustomerObject()
	{
		InputStream is = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		List<Customer> returnCustomerList = new LinkedList<>();
		try {
			is = new FileInputStream("Client_VMSPro_Data.txt");
			bis = new BufferedInputStream(is);
			dis = new DataInputStream(bis);
			String temp = null;
			while ((temp = dis.readLine()) != null) {

				Gson g = new Gson();
				Customer c = g.fromJson(temp, Customer.class);
				returnCustomerList.add(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnCustomerList;
	}
	
	
	/**
	 * Writes the vehicle list to file specified in VMS Pro Constants.
	 * @param vehicleList the list to write
	 * @return the sucess or failure of operation
	 * */
	public static boolean writeVehicleToFile(List<Vehicle> vehicleList)
	{

		BufferedWriter bufferedWriter = null;
		try {
			File myFile2 = new File("Vehicle_VMSPro_Data.txt");
			myFile2.createNewFile();
			Writer writer = new FileWriter(myFile2);
			bufferedWriter = new BufferedWriter(writer);

			for (Vehicle car : vehicleList)
			{
				Gson g = new Gson();
				String x = g.toJson(car);
				bufferedWriter.write(x);
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null)
					bufferedWriter.close();
			}catch(Exception ex){}
		}

		return true;
	}
	
	
	/**
	 * Reads the Car data system file into the project as a list instance.
	 * @return the list from reading the file
	 * */
	@SuppressWarnings("deprecation")
	public static List<Vehicle> readVehicleToObject()
	{
		String carFile = "Vehicle_VMSPro_Data.txt";
		InputStream is = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		List<Vehicle> returnListVehicle = new LinkedList<>();
		//boolean fnot = true;
		
			try {
				is = new FileInputStream(carFile);
				bis = new BufferedInputStream(is);
				dis = new DataInputStream(bis);
				String temp = null;
				while ((temp = dis.readLine()) != null) {
					Gson g = new Gson();
					if (temp.contains("isConvertible")) {
						Sedan s = g.fromJson(temp, Sedan.class);
						returnListVehicle.add(s);
					}
					if (temp.contains("cargoSpace")) {
						Van t = g.fromJson(temp, Van.class);
						returnListVehicle.add(t);
					}
					if (temp.contains("towCapacity")) {
						Truck t = g.fromJson(temp, Truck.class);
						returnListVehicle.add(t);
					}
				}
			} catch (FileNotFoundException e)
			{
				File f = new File(carFile);
				try
				{f.createNewFile();}
				catch(IOException e1)
				{e1.printStackTrace();}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		
		return returnListVehicle;
	}
}