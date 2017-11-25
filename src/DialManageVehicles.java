package vmspro;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
			@Override
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
				
				switch(ans)
				{
				case "SEDAN":
					makeAndAddSedan(newCarForm);
					break;
				case "TRUCK":
					//makeAndAddTruck(newCarForm);
					break;
				case "VAN":
					//makeAndAddVan(newCarForm);
					break;
				}
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
		sortCarsBttn = new JButton("Sort");
		remvCarBttn = new JButton("Remove");
		cancelBttn = new JButton("Cancel");
		cancelBttn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Component comp = (Component)e.getSource();
				JDialog jd = (JDialog)SwingUtilities.getRoot(comp);
				jd.dispose();
			}
		});
		
		//pseudo-table
		totalCarArea = new JTextArea(VMSPro_Constants.TxtCarHeader,
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
	
	private void fillTable(JTextArea someArea, VMSPro app)
	{
		String line = "";
		LinkedList<Vehicle> cars = app.getCarList();
		Iterator<Vehicle> carIT = cars.iterator();
		while(carIT.hasNext())
		{
			Vehicle car = carIT.next();
			
			int lotid = car.getLotNumber(), year = car.getYear(),
					custID = car.getCustomer().getCustomerId();
			String make = car.getMake(), model = car.getModel(),
					vin = car.getVin();
			CarColors color = car.getColor();
			if(car instanceof Sedan)
			{
				line+=String.format("%d\t%s\t%s\t%s\t%d\t%s\t%s\t%d", lotid,
						vin, make, model, year, color, "SEDAN",custID)+"\n";
			}
			else if(car instanceof Truck)
			{
				line+=String.format("%d\t%s\t%s\t%s\t%d\t%s\t%s\t%d", lotid,
						vin, make, model, year, color, "TRUCK",custID)+"\n";
			}
			else if(car instanceof Van)
			{
				line+=String.format("%d\t%s\t%s\t%s\t%d\t%s\t%s\t%d", lotid,
						vin, make, model, year, color, "VAN",custID)+"\n";
			}
			
		}
		someArea.setEditable(true);
		someArea.setText(someArea.getText()+line);
		someArea.setEditable(false);
	}
	
	/**Takes the panel's input fields and makes a Sedan and adds to VMS Pro.*/
	private void makeAndAddSedan(JPanel somePanel)
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
		boolean mayConert = Boolean.parseBoolean(
				(String)convertibleSedan.getSelectedItem());
		Vehicle newSedan;
		if(custX != null && !custX.equals(null))
		{newSedan= new Sedan(custX,make, model, color,year, vin,mayConert);}
		newSedan = new Sedan(make, model, color,year, vin,mayConert);
		
		sysApp.addCar(newSedan);
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
				do
				{
					try
					{
						number = Integer.parseInt(custInput.getText());

						if (number <= VMSPro_Constants.MIN_NUMBER_INPUT)
							number = -1;
					}catch(NumberFormatException e)
					{number = -1;} 
				}while(number != -1);
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
}