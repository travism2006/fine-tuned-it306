package vmspro;

import static org.junit.Assert.*;
import org.junit.Test;
import vmspro.VMSPro_Constants.CarColors;

public class VmsProTests
{
	/**
	 * Tests for stack getting populated with the
	 * last car that was in the system.
	 * */
	@Test
	public void testDeletedCarStack()
	{
		VMSPro project = new VMSPro();
		project.addCar(new Sedan("honda", "accord", CarColors.BLACK, 2014, "1234567890qwerasd", true));
		project.addCar(new Truck("uber", "gm", CarColors.RED, 2005, "0987654321rewqdsa", 2000, 400));
		project.removeLastVehicle();
		assertEquals(project.getDeletedCarStack().size(),1);
		assertEquals(project.getCarList().size(),1);
	}
	
	/**
	 * Tests for the customer stack getting pushed onto and then returning
	 * that customer back into the customer list for VMS Pro users to update.
	 * */
	@Test
	public void testCustomerStackUndo()
	{
		VMSPro app = new VMSPro();
		//add one client
		app.addCustomer(new Customer("will", "synder"));
		//set will's address
		app.getCustomerList().get(0).setAddress(new Address("30 forty", "rye", "VA", 44044));
		
		//remove and push to stack
		app.removeLastCustomer(true);
		
		//verify that the stack has him captured and the removal = size 0 now
		assertEquals(app.getDeletedCustomersStack().size(),1);
		assertEquals(app.getCustomerList().size(),0);
		
		//undo the removal and add back into VMS Pro
		app.addCustomer(app.getDeletedCustomersStack().pop());
		
		//verify the undo of the removal from VMS Pro
		assertEquals(app.getDeletedCustomersStack().size(),0);
		assertEquals(app.getCustomerList().size(),1);
	}
	
	
	/**
	 * Tests for the linking of a new sedan to a new customer object.
	 * */
	@Test
	public void testUnlinkedToLinked()
	{
		VMSPro app = new VMSPro();
		Sedan sids = new Sedan("gm", "B12", CarColors.BLUE, 2014, "qwaszxerdfcvtyghb", false);
		app.addCar(sids);
		Customer bil = new Customer("bil", "might", new Address("12 main street",
				"falls plaza", "GA", 30303));
		bil.addCarTitle(sids);
		app.addCustomer(bil);
		
		assertEquals(app.getCustomersWithNoCars().size(),0);
		assertEquals(app.getVehicleNoClient().size(),0);
		assertEquals(app.getCarList().size(), 1);
		assertEquals(app.getCustomerList().size(),1);
	}

	/**
	 * Tests for a customer having many (at least 2) different vehicles.
	 * */
	@Test
	public void testNoUnlinkedData()
	{
		VMSPro app = new VMSPro();
		Customer bill = new Customer("bill", "dude", new Address("123 clover drive", "uber drive", "GW", 90903));
		app.addCustomer(bill);
		Sedan bSedan = new Sedan(bill, "gm", "ram", CarColors.WHITE, 2013, "7890654321erwqasd", false);
		Van bVan = new Van(bill, "volks", "wagen", CarColors.RED, 2015, "1230984567qwreasd", 700);
		app.addCar(bVan);
		app.addCar(bSedan);
		bill.addCarTitle(bVan);
		bill.addCarTitle(bSedan);
		
		assertEquals(app.getCarList().size(),2);
		assertEquals(app.getCustomerList().size(),1);
		assertEquals(app.getCustomersWithNoCars().size(),0);
		assertEquals(app.getVehicleNoClient().size(),0);
	}
	
	/**
	 * Tests search for Truck x in list is truly there.
	 * */
	@Test
	public void testSearchForTruck()
	{
		VMSPro app = new VMSPro();
		app.addCar(new Truck("dodge", "ram", CarColors.BLACK, 2015, "1234509876rewqasd", 900, 400));
		
		assertEquals(app.getCarList().size(),1);
		
		String vin = app.getVINList().get(0);
		assertTrue(vin.equals("1234509876rewqasd"));
	}
}