package vmspro;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

/**
 * JDialog instance class that uses a primary panel to show display all data
 * about vehicles and their corresponding type.
 * @author tmitchu2
 * */
public class DialManageVehicles extends JDialog
{
	private VMSPro sysApp;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton addCarBttn, sortCarsBttn, remvCarBttn, cancelBttn;
	private static JTextArea totalCarArea;
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
					
					if(car != null)
					{
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
									"\n%d\t%s\t%s\t%s\t%d\t%s\t%s\tN\\A", lotid,
									vin, make, model, year, color, "SEDAN")+"\n";
							totalCarArea.append(line);
						}
						else
						{String line = String.format(
								"\n%d\t%s\t%s\t%s\t%d\t%s\t%s\t%d",lotid,vin,
								make, model, year, color, "SEDAN", custID)+"\n";
							totalCarArea.append(line);
						}
					}break;
				
				case "TRUCK":
					car = makeAndAddTruck(newCarForm);
					
					if(car != null)
					{
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
									"\n%d\t%s\t%s\t%s\t%d\t%s\t%s\tN\\A", lotid,
									vin, make, model, year, color, "TRUCK")+"\n";
							totalCarArea.append("\n"+line);
						}
						else
						{String line = String.format(
								"\n%d\t%s\t%s\t%s\t%d\t%s\t%s\t%d",lotid,vin,
								make, model, year, color, "TRUCK", custID)+"\n";
							totalCarArea.append(line);
						}
					}break;
				
				case "VAN":
					car = makeAndAddVan(newCarForm);
					
					if(car != null)
					{
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
									VMSPro_Constants.HasNoClient, lotid,
									vin, make, model, year, color, "VAN")+"\n";
							totalCarArea.append("\n"+line);
						}
						else
						{String line = String.format(
								VMSPro_Constants.HasClient,lotid,vin,
								make, model, year, color, "VAN", custID)+"\n";
							totalCarArea.append("\n"+line);
						}
					}break;
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
		remvCarBttn.addActionListener(new ActionListener()
		{
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
					Vehicle car = sysApp.getVehicleByVIN(ans);
					sysApp.removeCarX(car);
					JOptionPane.showMessageDialog(contentPane,
							"The vehicle with VIN "+ car.getVin()+" has been removed");
					
					//now show empty string where the car's string was in the
					//  jtextarea
					String dat = DialManageVehicles.getTxtArea().getText();
					if(car.getCustomer() == null)
					{
						dat.replace(DialManageVehicles.doAddLineNoClient(
								car.getLotNumber(),car.getVin(), car.getMake(),
								car.getModel(), car.getYear(), car.getColor()), "");
					}
					else
					{
						dat.replace(DialManageVehicles.doAddLineWithClient(
								car.getLotNumber(),car.getVin(), car.getMake(),
								car.getModel(), car.getYear(), car.getColor(),
								car.getCustomer().getCustomerId()), "");
					}
					
				}
			}
		});
		
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
		contentPane.add(sortCarsBttn);
		contentPane.add(remvCarBttn);
		contentPane.add(cancelBttn);
	}
	
	private void fillTable(JTextArea someArea, VMSPro app)
	{
		String line = "";
		LinkedList<Vehicle> cars = app.getCarList();
		Iterator<Vehicle> carIT = cars.iterator();
		while(carIT.hasNext())
		{
			Vehicle car = carIT.next();
			if(car != null)
			{
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
						line+=String.format("\n%d\t%s\t\t%s\t%s\t%d\t%s\t%s\tN\\A", lotid,
								vin, make, model, year, color, "SEDAN",custID)+"\n";
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
		
		if(checkVehicleInput(somePanel))
		{
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
		else return null;
		
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
		
		if(checkVehicleInput(somePanel))
		{
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
		else return null;
		
	}
	
	private Vehicle makeAndAddVan(JPanel somePanel)
	{
		//make sedan specific input field and label
		JLabel cargolbl = new JLabel("Cargo Space:");
		JTextField cargoInput = new JTextField(VMSPro_Constants.pixelColWidth);
		somePanel.add(cargolbl);
		somePanel.add(cargoInput);
		
		if(checkVehicleInput(somePanel))
		{
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
		else return null;
		
	}
	
	/**
	 * Functions as a conditional-continuous method for validating and 
	 * making vehicle objects.
	 * @param somePanel the panel to refer to that is the parent for any JOP
	 * */
	private boolean checkVehicleInput(JPanel somePanel)
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
					{return false;}
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
					{return false;}
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
					{return false;}
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
					{return false;}
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
		return true;
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
	public static JTextArea getTxtArea()
	{return totalCarArea;}
	
	public static String doAddLineNoClient(int lotid, String vin, String make, String model, int year, CarColors color)
	{
		String line = String.format(
				VMSPro_Constants.HasNoClient, lotid,
				vin, make, model, year, color, "VAN")+"\n";
		return line;
		//totalCarArea.append("\n"+line);
	}
	
	public static String doAddLineWithClient(int lotid, String vin, String make, String model, int year, CarColors color, int custID)
	{
		String line = String.format(
				VMSPro_Constants.HasClient, lotid,
				vin, make, model, year, color, "VAN", custID)+"\n";
		return line;
		//totalCarArea.append("\n"+line);
	}
}