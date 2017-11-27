package Phase4;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
//import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Addtional class for VMS Pro to increase code base organization.  The main
 * purpose of this class is to avoid one behemoth file in the vmsproGUI.java.
 * The secondary purpose is that this should improve the direction of the call
 * stack for each successive request made by the user via the main window.
 * @version 1.0a
 * @author tmitchu2
 * */
public class ManageVehicles extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton addCarBttn, editCarBttn, sortCarsBttn, remvCarBttn, cancelBttn;
	private JTable totalCarTable;
	//private File carFile;
	//private LinkedList<String> fileData;
	private JTextField vinInput,makeInput,modelInput,yearInput,custInput;
	private JComboBox<VMSPro_Constants.CarColors> colorInput;
	private JComboBox<String> convertibleSedan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					ManageVehicles frame = new ManageVehicles();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManageVehicles()
	{
		setTitle("Manage Vehicles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		
		//use and initialize fields
		//carFile = new File("phase4/Phase4/"+VMSPro_Constants.CarFileName);
		//fileData = new LinkedList<>();
		
		makeMainPanel();
		
		setContentPane(contentPane);
	}

	private void makeMainPanel()
	{
		contentPane = new JPanel();
		
		//buttons
		addCarBttn = new JButton("Add");
		addCarBttn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String[] opts = {VMSPro_Constants.CarTypes.SEDAN.toString(),
						VMSPro_Constants.CarTypes.TRUCK.toString(),
						VMSPro_Constants.CarTypes.VAN.toString()};
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
					break;
				case "VAN":
					JLabel cargolbl = new JLabel("Cargo Space:");
					JTextField cargoInput = new JTextField(VMSPro_Constants.pixelColWidth);
					newCarForm.add(cargolbl);newCarForm.add(cargoInput);
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
				colorInput = new JComboBox<>(VMSPro_Constants.CarColors.values());
				custInput = new JTextField(VMSPro_Constants.pixelColWidth);
				
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
			{System.exit(0);}
		});
		
		//table
		totalCarTable = new JTable();
		
		contentPane.add(totalCarTable);
		contentPane.add(addCarBttn);
		contentPane.add(editCarBttn);
		contentPane.add(sortCarsBttn);
		contentPane.add(remvCarBttn);
		contentPane.add(cancelBttn);
	}

	private void makeAndAddSedan(JPanel somePanel)
	{
		//make sedan specific input field and label
		JLabel convertiblelbl = new JLabel("Convertible:");
		String[] convertOrNot = {"True", "False"};
		convertibleSedan = new JComboBox<String>(convertOrNot);
		somePanel.add(convertiblelbl);
		somePanel.add(convertibleSedan);
		
		checkVehicleInput(somePanel);
		
		//Vehicle newSedan = new Sedan(vin, make, model, year, color, customerID);
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
				boolean goodVIN = VMSProApp.checkVIN(vinInput.getText());
				if(!goodVIN)
				{/*if invalid VIN, check if the user wants to try again*/
					int resp = JOptionPane.showConfirmDialog(somePanel,
							"Bad VIN input, do you want to try again?", "Invalid VIN Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, somePanel,
								"Add Vehicle and Type", JOptionPane.OK_CANCEL_OPTION);
					}
				}
				/*CAR MAKE CHECK*/
				boolean goodMake = VMSProApp.checkString(makeInput.getText());
				if(!goodMake)
				{/*if invalid VIN, check if the user wants to try again*/
					int resp = JOptionPane.showConfirmDialog(somePanel,
							"Bad Manufacturer input, do you want to try again?", "Invalid Make Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, somePanel,
								"Add Vehicle and Type", JOptionPane.OK_CANCEL_OPTION);
					}
				}
				
				
				
				
				
				
				
				
				
				create = JOptionPane.CANCEL_OPTION;
			}
		}while(create != JOptionPane.CANCEL_OPTION);
		
		boolean goodModel = VMSProApp.checkString(modelInput.getText());
		boolean goodYear = VMSProApp.checkCarYear(yearInput.getText());
		//boolean goodCustomerID = VMSProApp.checkCustomerID(custInput.getText());
	}

	/**
	 * Returns the JTable field for viewing and filling of data for other
	 * JTables throughout the application.
	 * @return the jtable field
	 * */
	public JTable getTable() {return this.totalCarTable;}
		
}