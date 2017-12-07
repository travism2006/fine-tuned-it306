package vmspro;
/**
* the class is designed for input and output to files
* this class will read and write object to and from a file 
* by converting them into json objects
* 
* @author Hassan Ammar
*/

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

public class InputHandler {

  public static boolean writeCustomerToFile(List<Customer> customerList) {

    BufferedWriter bufferedWriter = null;
    try {
      File myFile = new File("customer.txt");
      if (!myFile.exists()) {
        myFile.createNewFile();
      }

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

  @SuppressWarnings("deprecation")
  public static List<Customer> readCustomerToObject() {
    InputStream is = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;
    List<Customer> returnCustomerList = new LinkedList<>();
    try {
      is = new FileInputStream("customer.txt");
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

  public static boolean writeVehicleToFile(List<Vehicle> vehicleList) {

    BufferedWriter bufferedWriter = null;
    try {
      File myFile = new File("vehicle.txt");
      if (!myFile.exists()) {
        myFile.createNewFile();
      }

      Writer writer = new FileWriter(myFile);
      bufferedWriter = new BufferedWriter(writer);

      for (Vehicle veh : vehicleList) {

        Gson g = new Gson();
        String x = g.toJson(veh);
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

  @SuppressWarnings("deprecation")
  public static List<Vehicle> readVehicleToObject() {
    InputStream is = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;
    List<Vehicle> returnListVehicle = new LinkedList<>();
    try {
      is = new FileInputStream("vehicle.txt");
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
          Van t = g.fromJson(temp, Van.class);
          returnListVehicle.add(t);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return returnListVehicle;
  }
}
