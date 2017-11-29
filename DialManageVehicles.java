package vmspro;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

//import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import vmspro.VMSPro_Constants.CarColors;
import vmspro.VMSPro_Constants.CarTypes;

public class DialManageVehicles extends JDialog
{
	private VMSPro sysApp;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton addCarBttn, editCarBttn, sortCarsBttn, remvCarBttn, cancelBttn;
	private JTextArea totalCarArea;
	private JTextField vinInput,makeInput,modelInput,yearInput,custInput;
	private JComboBox<CarColors> colorInput;
	private JComboBox<String> convertibleSedan;

	/**
	 * Create the dialog.
	 */
	public DialManageVehicles(JFrame frame,VMSPro app)
	{
		super(frame);
		setTitle("Manage Vehicles");
		this.setModalityType(ModalityType.DOCUMENT_MODAL);
		this.setType(Type.NORMAL);
		this.setResizable(true);
		
		setBounds(100, 100, 700, 400);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.sysApp = app;
		
		makeMainPanel(app);
		this.setContentPane(contentPane);
	}

	private void makeMainPanel(VMSPro app)
	{
		contentPane = new JPanel();
		
		//buttons
		addCarBttn = new JButton("Add");
		addCarBttn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String[] opts = {CarTypes.SEDAN.toString(),
						CarTypes.TRUCK.toString(),
						CarTypes.VAN.toString()};
				String ans = String.valueOf(
						JOptionPane.showInputDialog(contentPane,
								"What type of vehicle:", "Add Vehicle Type",
								JOptionPane.OK_CANCEL_OPTION, null, opts, opts[0]));
				JPanel newCarForm = makeMainAddCarPanel();
				Vehicle car;
				
				int lotid,year,custID;
				String make,model,vin;
				CarColors color;
				
				switch(ans)
				{
				case "SEDAN":
					car= makeAndAddSedan(newCarForm);
					
					lotid = car.getLotNumber(); year = car.getYear();
					custID = -2;
					if((Customer)car.getCustomer() == null)custID = -1;
					else if(((Customer)car.getCustomer()).equals(null))custID = -1;
					try{
						custID = car.getCustomer().getCustomerId();
					}catch(NullPointerException npe)
					{custID = -1;}
					make = car.getMake(); model = car.getModel();
					vin = car.getVin();
					color = car.getColor();
					
					if(custID == -2 || custID == -1)
					{
						String line = String.format(
								"\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\tN\\A", lotid,
								vin, make, model, year, color, "SEDAN")+"\n";
						totalCarArea.append("\n"+line);
					}
					else
					{String line = String.format(
							"\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\t%d",lotid,vin,
							make, model, year, color, "SEDAN", custID)+"\n";
						totalCarArea.append("\n"+line);
					}
					break;
				
				case "TRUCK":
					car = makeAndAddTruck(newCarForm);
					
					lotid = car.getLotNumber(); year = car.getYear();
					custID = -2;
					if((Customer)car.getCustomer() == null)custID = -1;
					else if(((Customer)car.getCustomer()).equals(null))custID = -1;
					try{
						custID = car.getCustomer().getCustomerId();
					}catch(NullPointerException npe)
					{custID = -1;}
					make = car.getMake(); model = car.getModel();
					vin = car.getVin();
					color = car.getColor();
					
					if(custID == -2 || custID == -1)
					{
						String line = String.format(
								"\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\tN\\A", lotid,
								vin, make, model, year, color, "TRUCK")+"\n";
						totalCarArea.append("\n"+line);
					}
					else
					{String line = String.format(
							"\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\t%d",lotid,vin,
							make, model, year, color, "TRUCK", custID)+"\n";
						totalCarArea.append("\n"+line);
					}
					break;
				
				case "VAN":
					car = makeAndAddVan(newCarForm);
					
					lotid = car.getLotNumber(); year = car.getYear();
					custID = -2;
					if((Customer)car.getCustomer() == null)custID = -1;
					else if(((Customer)car.getCustomer()).equals(null))custID = -1;
					try{
						custID = car.getCustomer().getCustomerId();
					}catch(NullPointerException npe)
					{custID = -1;}
					make = car.getMake(); model = car.getModel();
					vin = car.getVin();
					color = car.getColor();
					
					if(custID == -2 || custID == -1)
					{
						String line = String.format(
								"\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\tN\\A", lotid,
								vin, make, model, year, color, "VAN")+"\n";
						totalCarArea.append("\n"+line);
					}
					else
					{String line = String.format(
							"\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\t%d",lotid,vin,
							make, model, year, color, "VAN", custID)+"\n";
						totalCarArea.append("\n"+line);
					}
					
					break;
				}
				JDialog dial = returnDialog(e);
				dial.requestFocus();
			}

			private JPanel makeMainAddCarPanel()
			{
				JPanel addCarPanel = new JPanel();
				addCarPanel.setLayout(new BoxLayout(addCarPanel, BoxLayout.Y_AXIS));
				JLabel vinlbl, makelbl, modellbl, yearlbl,colorlbl,custlbl;
				vinlbl = new JLabel("VIN:");
				makelbl = new JLabel("Make:");
				modellbl = new JLabel("Model:");
				yearlbl = new JLabel("Year:");
				colorlbl = new JLabel("Color:");
				custlbl = new JLabel("Customer ID:");
				
				//input fields for panel
				vinInput = new JTextField(VMSPro_Constants.pixelColWidth);
				makeInput = new JTextField(VMSPro_Constants.pixelColWidth);
				modelInput = new JTextField(VMSPro_Constants.pixelColWidth);
				yearInput = new JTextField(VMSPro_Constants.pixelColWidth);
				colorInput = new JComboBox<>(CarColors.values());
				custInput = new JTextField("0",VMSPro_Constants.pixelColWidth);
				
				//join
				addCarPanel.add(vinlbl);
				addCarPanel.add(vinInput);
				addCarPanel.add(makelbl);
				addCarPanel.add(makeInput);
				addCarPanel.add(modellbl);
				addCarPanel.add(modelInput);
				addCarPanel.add(yearlbl);
				addCarPanel.add(yearInput);
				addCarPanel.add(colorlbl);
				addCarPanel.add(colorInput);
				addCarPanel.add(custlbl);
				addCarPanel.add(custInput);
				addCarPanel.setVisible(true);
				return addCarPanel;
			}
		});
		
		editCarBttn = new JButton("Edit");
		editCarBttn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Iterator<String> vinListIT = sysApp.getVINList().iterator();
				String hold = "";
				while(vinListIT.hasNext())
				{
					String vinX = vinListIT.next();
					hold += vinX+",";
				}
				
				if(!hold.equals(""))
				{
					String[] opts = hold.split(",");
					String ans = String.valueOf(
							JOptionPane.showInputDialog(contentPane,
									"What type of vehicle:", "Edit Vehicle",
									JOptionPane.OK_CANCEL_OPTION, null, opts, opts[0]));
					
					JPanel editPanel = makeEditPanel();
					
					/*get the car of the selected VIN*/
					Vehicle car = sysApp.getVehicleByVIN(ans);
					
					if(car instanceof Sedan)
					{car = editSedanPnl(editPanel, (Sedan) car);}
					else if(car instanceof Truck)
					{car = editTruckPnl(editPanel,(Truck)car);}
					else if(car instanceof Van)
					{car = editVanPnl(editPanel, (Van)car);}
					
					String table = totalCarArea.getText();
					if(car instanceof Sedan)
					{
						ArrayList<String> tableData = (ArrayList<String>) Arrays.asList(table.split("\\n+"));
						//0--header
						//Lot ID \t VIN \t\t Make Model Year Color Type Cust ID
						for (String str : tableData) 
						{
							String[] row = str.split("\\t+");
							if(row[1].equals(car.getVin()))
							{
								row[2] = car.getMake();
								row[3] = car.getModel();
								row[5] = car.getColor().toString();
								if(car.getCustomer() == null)
								{row[7] = "N/A";}
								else if(car.getCustomer().equals(null))
								{row[7] = "N/A";}
								else
								{row[7] = String.format("%d",car.getCustomer().getCustomerId());}
							}
						}
						Iterator<String> it = tableData.iterator();
						String toArea = "";
						while(it.hasNext())
						{toArea += it.next();}
						totalCarArea.setText(toArea);
					}
					else if(car instanceof Truck)
					{
						
					}
					else if(car instanceof Van)
					{
						
					}
				}
				else
				{JDialog dial = returnDialog(e);
				JOptionPane.showMessageDialog(dial,
						"There are no cars to edit, so there are no vins to"
						+ " choose from. Have a nice day! :D");}
			}
			
			private JPanel makeEditPanel()
			{
				JPanel editCarPnl = new JPanel();
				editCarPnl.setLayout(new BoxLayout(editCarPnl, BoxLayout.Y_AXIS));
				JLabel makelbl, modellbl, colorlbl,custlbl;
				makelbl = new JLabel("Make:");
				modellbl = new JLabel("Model:");
				colorlbl = new JLabel("Color:");
				custlbl = new JLabel("Customer ID:");
				
				//input fields for panel
				makeInput = new JTextField(VMSPro_Constants.pixelColWidth);
				modelInput = new JTextField(VMSPro_Constants.pixelColWidth);
				colorInput = new JComboBox<>(CarColors.values());
				custInput = new JTextField("0",VMSPro_Constants.pixelColWidth);
				
				//join
				editCarPnl.add(makelbl);
				editCarPnl.add(makeInput);
				editCarPnl.add(modellbl);
				editCarPnl.add(modelInput);
				editCarPnl.add(colorlbl);
				editCarPnl.add(colorInput);
				editCarPnl.add(custlbl);
				editCarPnl.add(custInput);
				editCarPnl.setVisible(true);
				return editCarPnl;
			}
		});
	
		sortCarsBttn = new JButton("Sort");
		sortCarsBttn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//TODO:FIXME:Ask What Sort--use JOP.showOptionDialog(...)
				//    options = Merge, Quick, Insert, Select->init.val = Merge
			}
		});
		remvCarBttn = new JButton("Remove");
		cancelBttn = new JButton("Cancel");
		cancelBttn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JDialog jd = returnDialog(e);
				jd.dispose();
			}
		});
		
		//pseudo-table
		totalCarArea = new JTextArea(VMSPro_Constants.TxtCarFullHeader,
				VMSPro_Constants.TxtAreaHeight,VMSPro_Constants.TxtAreaWidth);
		fillTable(totalCarArea, app);
		
		totalCarArea.setEditable(false);
		contentPane.add(totalCarArea);
		contentPane.add(addCarBttn);
		contentPane.add(editCarBttn);
		contentPane.add(sortCarsBttn);
		contentPane.add(remvCarBttn);
		contentPane.add(cancelBttn);
	}
	
	private Vehicle editSedanPnl(JPanel somePanel, Sedan someCar)
	{
		//make sedan specific input field and label
		JLabel convertiblelbl = new JLabel("Convertible:");
		String[] convertOrNot = {"True", "False"};
		convertibleSedan = new JComboBox<String>(convertOrNot);
		somePanel.add(convertiblelbl);
		
		/*get bool value and set the input field to that value*/
		boolean converts = someCar.getConvertibleStatus();
		if(converts)
		{convertibleSedan.setSelectedIndex(0);}
		else {convertibleSedan.setSelectedIndex(1);}
		somePanel.add(convertibleSedan);
		
		checkEditCarPanel(somePanel, someCar);
		
		makeInput.setText(someCar.getMake());
		modelInput.setText(someCar.getModel());
		
		CarColors color = someCar.getColor();
		if(color==CarColors.RED)
		{colorInput.setSelectedIndex(0);}
		else if(color==CarColors.BLACK)
		{colorInput.setSelectedIndex(1);}
		else if(color==CarColors.WHITE)
		{colorInput.setSelectedIndex(2);}
		else if(color==CarColors.BLUE)
		{colorInput.setSelectedIndex(3);}
		
		Customer custX = someCar.getCustomer();
		int custID = 0;
		try{
			custID = custX.getCustomerId();
		}catch(NullPointerException e1)
		{custID = 0;}
		custInput.setText(String.format("%d", custID));
		
		//see if new customer id number entered is in the system
		try{
			custX = sysApp.getCustomerByID(
					Integer.parseInt(custInput.getText()));
		}catch(NumberFormatException e)
		{custX = null;}
		
		//sedan field specific
		boolean mayConvert = Boolean.parseBoolean(
				(String)convertibleSedan.getSelectedItem());
		
		//after getting input above, now update the car
		someCar.setColor((CarColors) colorInput.getSelectedItem());
		someCar.setConvertibleStatus(mayConvert);
		someCar.setMake(makeInput.getText());
		someCar.setModel(modelInput.getText());
		someCar.linkCustomer(custX);
		return someCar;
		
	}
	
	private Vehicle editTruckPnl(JPanel somePanel, Truck someCar)
	{
		double tow = someCar.getTowCapacity();
		double carry = someCar.getCarryCapacity();
		
		//make sedan specific input field and label
		JLabel carrylbl = new JLabel("Carrying Capacity:");
		JLabel towlbl = new JLabel("Towing Capacity:");
		JTextField carryInput = new JTextField(VMSPro_Constants.pixelColWidth);
		JTextField towInput = new JTextField(VMSPro_Constants.pixelColWidth);
		somePanel.add(carrylbl);
		somePanel.add(carryInput);
		somePanel.add(towlbl);somePanel.add(towInput);
		carryInput.setText(String.format("%.2f", carry));
		towInput.setText(String.format("%.2f", tow));
		
		checkEditCarPanel(somePanel, someCar);
		
		String make = makeInput.getText();
		String model = modelInput.getText();
		CarColors color = (CarColors) colorInput.getSelectedItem();
		Customer custX;
		try
		{
			custX = sysApp.getCustomerByID(
					Integer.parseInt(custInput.getText()));
		}catch(NumberFormatException e)
		{custX = null;}
		
		//truck specific updated fields
		try{
			tow = Double.parseDouble(towInput.getText());
		} catch (NumberFormatException e)
		{tow = -1.0;}
		try{
			carry = Double.parseDouble(carryInput.getText());
		}catch(NumberFormatException e)
		{carry = -1.0;}
		
		someCar.linkCustomer(custX);
		someCar.setColor(color);
		someCar.setMake(make);someCar.setModel(model);
		someCar.setCarryCapacity(carry);
		someCar.setTowCapacity(tow);
		return someCar;
		
	}
	
	private Vehicle editVanPnl(JPanel somePanel, Van someCar)
	{
		JLabel cargolbl = new JLabel("Cargo Space:");
		JTextField cargoInput = new JTextField(VMSPro_Constants.pixelColWidth);
		somePanel.add(cargolbl);somePanel.add(cargoInput);
		
		//get data and show currently stored info on this car
		cargoInput.setText(String.format("%.2f", someCar.getCargoSpace()));
		makeInput.setText(someCar.getMake());
		modelInput.setText(someCar.getModel());
		custInput.setText(String.format("%d", someCar.getCustomer().getCustomerId()));
		
		//validate the vehicle type fields (make, model, color, customer)
		checkEditCarPanel(somePanel, someCar);
		
		
		String model = modelInput.getText();
		String make = makeInput.getText();
		someCar.setMake(make);someCar.setModel(model);
		
		
		return someCar;
		
	}
	
	private void fillTable(JTextArea someArea, VMSPro app)
	{
		String line = "";
		LinkedList<Vehicle> cars = app.getCarList();
		Iterator<Vehicle> carIT = cars.iterator();
		while(carIT.hasNext())
		{
			Vehicle car = carIT.next();
			
			int lotid = car.getLotNumber(), year = car.getYear(),
					custID = -2;
			if((Customer)car.getCustomer() == null)custID = -1;
			else if(((Customer)car.getCustomer()).equals(null))custID = -1;
			try{
				custID = car.getCustomer().getCustomerId();
			}catch(NullPointerException e)
			{custID = -1;}
			String make = car.getMake(), model = car.getModel(),
					vin = car.getVin();
			CarColors color = car.getColor();
			if(car instanceof Sedan)
			{
				if(custID == -2 || custID == -1)
					line+=String.format("\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\tN\\A", lotid,
						vin, make, model, year, color, "SEDAN")+"\n";
				else
				{
					
				}
			}
			else if(car instanceof Truck)
			{
				if(custID == -2 || custID == -1)
				{line+=String.format("\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\tN\\A", lotid,
						vin, make, model, year, color, "TRUCK")+"\n";}
				else
				{
					line+=String.format("\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\t%d", lotid,
							vin, make, model, year, color, "TRUCK",custID)+"\n";
				}
			}
			else if(car instanceof Van)
			{
				if(custID == -2 || custID == -1)
				{line+=String.format("\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\tN\\A", lotid,
							vin, make, model, year, color, "VAN")+"\n";}
				else
				{line+=String.format("\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\t%d", lotid,
						vin, make, model, year, color, "VAN",custID)+"\n";}
				
			}
			
		}
		someArea.setEditable(true);
		someArea.append(line);
		someArea.setEditable(false);
	}
	
	/**Takes the panel's input fields and makes a Sedan and adds to VMS Pro.*/
	private Vehicle makeAndAddSedan(JPanel somePanel)
	{
		//make sedan specific input field and label
		JLabel convertiblelbl = new JLabel("Convertible:");
		String[] convertOrNot = {"True", "False"};
		convertibleSedan = new JComboBox<String>(convertOrNot);
		somePanel.add(convertiblelbl);
		somePanel.add(convertibleSedan);
		
		checkVehicleInput(somePanel);
		
		String vin = vinInput.getText();
		String make = makeInput.getText();
		String model = modelInput.getText();
		int year;
		try {
			year = Integer.parseInt(yearInput.getText());
		} catch (NumberFormatException e1)
		{year = 1;}
		CarColors color = (CarColors) colorInput.getSelectedItem();
		Customer custX;
		try
		{
			custX = sysApp.getCustomerByID(
					Integer.parseInt(custInput.getText()));
		} catch (NumberFormatException e)
		{custX = null;}
		boolean mayConvert = Boolean.parseBoolean(
				(String)convertibleSedan.getSelectedItem());
		Vehicle newSedan;
		if(custX != null && !custX.equals(null))
		{newSedan= new Sedan(custX,make, model, color,year, vin,mayConvert);}
		newSedan = new Sedan(make, model, color,year, vin,mayConvert);
		
		sysApp.addCar(newSedan);
		return newSedan;
	}
	
	private Vehicle makeAndAddTruck(JPanel somePanel)
	{
		//make sedan specific input field and label
		JLabel carrylbl = new JLabel("Carrying Capacity:");
		JLabel towlbl = new JLabel("Towing Capacity:");
		JTextField carryInput = new JTextField(VMSPro_Constants.pixelColWidth);
		JTextField towInput = new JTextField(VMSPro_Constants.pixelColWidth);
		somePanel.add(carrylbl);
		somePanel.add(carryInput);
		somePanel.add(towlbl);somePanel.add(towInput);
		
		checkVehicleInput(somePanel);
		
		String vin = vinInput.getText();
		String make = makeInput.getText();
		String model = modelInput.getText();
		int year;
		try{
			year = Integer.parseInt(yearInput.getText());
		} catch (NumberFormatException e1)
		{year = 1;}
		CarColors color = (CarColors) colorInput.getSelectedItem();
		Customer custX;
		try{
			custX = sysApp.getCustomerByID(
					Integer.parseInt(custInput.getText()));
		}catch(NumberFormatException e)
		{custX = null;}
		
		double tow;
		try{
			tow = Double.parseDouble(towInput.getText());
		} catch (NumberFormatException e)
		{tow = -1.0;}
		double carry;
		try{
			carry = Double.parseDouble(carryInput.getText());
		}catch(NumberFormatException e)
		{carry = -1.0;}
		
		Vehicle newTruck;
		if(custX != null && custX.equals(null))
		{newTruck  = new Truck(make, model, color,year, vin,custX, tow,carry);}
		newTruck = new Truck(make, model, color, year, vin, tow, carry);
		sysApp.addCar(newTruck);
		return newTruck;
	}
	
	private Vehicle makeAndAddVan(JPanel somePanel)
	{
		//make sedan specific input field and label
		JLabel cargolbl = new JLabel("Cargo Space:");
		JTextField cargoInput = new JTextField(VMSPro_Constants.pixelColWidth);
		somePanel.add(cargolbl);
		somePanel.add(cargoInput);
		
		checkVehicleInput(somePanel);
		
		String vin = vinInput.getText();
		String make = makeInput.getText();
		String model = modelInput.getText();
		int year;
		try{
			year = Integer.parseInt(yearInput.getText());
		}catch(NumberFormatException e1)
		{year = 1;}
		CarColors color = (CarColors) colorInput.getSelectedItem();
		Customer custX;
		try{
			custX = sysApp.getCustomerByID(
					Integer.parseInt(custInput.getText()));
		}catch(NumberFormatException e)
		{custX = null;}
		double someCargo;
		try{
			someCargo = Double.parseDouble(cargoInput.getText());
		}catch(NumberFormatException e)
		{someCargo = -1.0;}
		
		Vehicle newVan;
		if(custX != null && custX.equals(null))
		{newVan = new Van(custX,make,model,color,year,vin,someCargo);}
		newVan = new Van(make, model, color, year, vin, someCargo);
		this.sysApp.addCar(newVan);
		return newVan;
	}
	
	/**
	 * Functions as a conditional-continuous method for validating and 
	 * making vehicle objects.
	 * @param somePanel the panel to refer to that is the parent for any JOP
	 * */
	private void checkVehicleInput(JPanel somePanel)
	{
		int create = -1;
		do
		{
			create = JOptionPane.showConfirmDialog(null, somePanel,
					"Add Vehicle and Type", JOptionPane.OK_CANCEL_OPTION);
			if(create == JOptionPane.OK_OPTION)
			{
				//Check the VIN
				boolean goodVIN = VMSPro.checkVIN(vinInput.getText());
				while(!goodVIN)
				{/*if invalid VIN, check if the user wants to try again*/
					int resp = JOptionPane.showConfirmDialog(somePanel,
							"Bad VIN input, do you want to try again?", "Invalid VIN Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, somePanel,
								"Add Vehicle and Type", JOptionPane.OK_CANCEL_OPTION);
						goodVIN = VMSPro.checkVIN(vinInput.getText());
					}
					else if(resp == JOptionPane.NO_OPTION)
					{goodVIN = true;}
				}
				
				/*CAR MAKE CHECK*/
				boolean goodMake = VMSPro.checkString(makeInput.getText());
				while(!goodMake)
				{/*if invalid car make, check if the user wants to try again*/
					int resp = JOptionPane.showConfirmDialog(somePanel,
							"Bad Manufacturer input, do you want to try again?", "Invalid Make Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, somePanel,
								"Add Vehicle and Type", JOptionPane.OK_CANCEL_OPTION);
						goodMake = VMSPro.checkString(makeInput.getText());
					}
					else if(resp == JOptionPane.NO_OPTION)
					{goodMake = true;}
				}
				
				//check the model of the car input
				boolean goodModel = VMSPro.checkString(modelInput.getText());
				while(!goodModel)
				{/*if invalid car make, check if the user wants to try again*/
					int resp = JOptionPane.showConfirmDialog(somePanel,
							"Bad Manufacturer input, do you want to try again?", "Invalid Make Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, somePanel,
								"Add Vehicle and Type", JOptionPane.OK_CANCEL_OPTION);
					}else if(resp == JOptionPane.NO_OPTION)
					{goodModel = true;}
				}
				
				/*Check the input for the year of the car*/
				boolean goodYear = VMSPro.checkNumber(yearInput.getText());
				while(!goodYear)
				{/*if invalid car year, check if the user wants to try again*/
					int resp = JOptionPane.showConfirmDialog(somePanel,
							"Bad year input, do you want to try again?", "Invalid Year Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, somePanel,
								"Add Vehicle and Type", JOptionPane.OK_CANCEL_OPTION);
					}else if(resp == JOptionPane.NO_OPTION)
					{goodYear = true;}
				}
				
				//check for the customer id field if valid or not
				int number = -1;
				do{
					try{
						number = Integer.parseInt(custInput.getText());

						if(number < VMSPro_Constants.MIN_NUMBER_INPUT)number = -1;
						
					}catch(NumberFormatException e)
					{number = -1;} 
				}while(number == -1);
				create = JOptionPane.CANCEL_OPTION;
			}
		}while(create != JOptionPane.CANCEL_OPTION);
	}
	
	/*for editing cars only*/
	private void checkEditCarPanel(JPanel somePanel, Vehicle car)
	{
		int create = -1;
		do
		{
			create = JOptionPane.showConfirmDialog(null, somePanel,
					"Edit Vehicle "+car.getLotNumber(), JOptionPane.OK_CANCEL_OPTION);
			if(create == JOptionPane.OK_OPTION)
			{
				/*CAR MAKE CHECK*/
				boolean goodMake = VMSPro.checkString(makeInput.getText());
				while(!goodMake)
				{/*if invalid car make, check if the user wants to try again*/
					int resp = JOptionPane.showConfirmDialog(somePanel,
							"Bad Manufacturer input, do you want to try again?", "Invalid Make Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, somePanel,
								"Edit Vehicle "+car.getLotNumber(), JOptionPane.OK_CANCEL_OPTION);
						goodMake = VMSPro.checkString(makeInput.getText());
					}
					else if(resp == JOptionPane.NO_OPTION)
					{goodMake = true;}
				}
				
				//check the model of the car input
				boolean goodModel = VMSPro.checkString(modelInput.getText());
				while(!goodModel)
				{/*if invalid car make, check if the user wants to try again*/
					int resp = JOptionPane.showConfirmDialog(somePanel,
							"Bad Manufacturer input, do you want to try again?", "Invalid Make Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, somePanel,
								"Add Vehicle and Type", JOptionPane.OK_CANCEL_OPTION);
					}else if(resp == JOptionPane.NO_OPTION)
					{goodModel = true;}
				}
				
				//check for the customer id field if valid or not
				int number = -1;
				do{
					try{
						number = Integer.parseInt(custInput.getText());
						if(number < VMSPro_Constants.MIN_NUMBER_INPUT)number = -1;
					}catch(NumberFormatException e)
					{number = -1;} 
				}while(number == -1);
				create = JOptionPane.CANCEL_OPTION;
			}
		}while(create != JOptionPane.CANCEL_OPTION);
	}
	
	/**
	 * Return the VMS pro object to capture any changes.
	 * @return the VMS pro instance that was linked to this dialog
	 * */
	public VMSPro returnVMSProApp()
	{return this.sysApp;}
	
	/**Returns the captured actionevent e's root dialog*/
	public JDialog returnDialog(ActionEvent e)
	{
		Component comp = (Component)e.getSource();
		return (JDialog)SwingUtilities.getRoot(comp);
	}
	
	/**Returns the txt area in the dialog for accessing and filling of the
	 * primary "table" in the main window.*/
	public JTextArea getTxtArea()
	{return totalCarArea;}
}