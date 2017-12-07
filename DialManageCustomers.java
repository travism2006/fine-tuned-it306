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

/**
 * A JDialog instance that uses a main panel to show all client data
 * as well as details regarding ownerships and empty titles.
 * @author tmitchu2
 * */
public class DialManageCustomers extends JDialog
{
	private VMSPro sysApp;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton addClientBttn, sortClientsBttn, remvClientBttn, cancelBttn;
	private static JTextArea totalCustomerArea;
	private JTextField fnameInput,lnameInput,zipcodeInput,streetInput, stateInput,cityInput;
	private JComboBox<String> carVinInput;

	/**
	 * Create the dialog.
	 */
	public DialManageCustomers(JFrame frame,VMSPro app)
	{
		super(frame);
		setTitle("Manage Customers");
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
		addClientBttn = new JButton("Add");
		addClientBttn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JPanel newClientform = makeAddCustomerPanel();
				
				//data per client
				Customer client;
				int custID,zipcode,carSize;
				String city,street,state,fname,lname;
				
				client = makeClient(newClientform);
				if(client != null)
				{
					custID = client.getCustomerId();
					fname = client.getFirstName();
					lname = client.getLastName();
					carSize = client.getVehicleList().size();
					zipcode = client.getPrimaryAddress().getZip();
					city = client.getPrimaryAddress().getCity();
					state = client.getPrimaryAddress().getState();
					street = client.getPrimaryAddress().getStreetAddress();
					
					String line = "\n"+String.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%d", custID,
							fname, lname,carSize,street,city,state,zipcode);
					totalCustomerArea.append(line);
				}
			}
			
			private JPanel makeAddCustomerPanel()
			{
				JPanel addClientPanel = new JPanel();
				addClientPanel.setLayout(new BoxLayout(addClientPanel, BoxLayout.Y_AXIS));
				
				//make all jlabels for each field
				JLabel fnamelbl,lnamelbl,streetlbl,citylbl,statelbl,ziplbl;
				fnamelbl = new JLabel("First Name: ");
				lnamelbl = new JLabel("Last Name: ");
				streetlbl = new JLabel("Street Address: ");
				citylbl = new JLabel("City: ");
				statelbl = new JLabel("State: ");
				ziplbl = new JLabel("Zipcode (5 digits):  ");
				JLabel carVinlbl = new JLabel("Vehicle VIN: ");
				
				//input fields for customer objects
				fnameInput = new JTextField(VMSPro_Constants.pixelColWidth);
				lnameInput = new JTextField(VMSPro_Constants.pixelColWidth);
				streetInput = new JTextField(VMSPro_Constants.pixelColWidth);
				cityInput = new JTextField(VMSPro_Constants.pixelColWidth);
				stateInput = new JTextField(VMSPro_Constants.pixelColWidth);
				zipcodeInput = new JTextField(VMSPro_Constants.pixelColWidth);
				if(sysApp.getVehicleNoClient() != null &&
						!sysApp.getVehicleNoClient().isEmpty())
				{
					String[] ops = new String[sysApp.getVehicleNoClient().size()];
					for(int i = 0; i < ops.length; i++)
					{
						//ops[i] = 
					}
					carVinInput = new JComboBox<String>(ops);
				}
				
				//join
				addClientPanel.add(fnamelbl);
				addClientPanel.add(fnameInput);
				addClientPanel.add(lnamelbl);
				addClientPanel.add(lnameInput);
				addClientPanel.add(streetlbl);
				addClientPanel.add(streetInput);
				addClientPanel.add(citylbl);
				addClientPanel.add(cityInput);
				addClientPanel.add(statelbl);
				addClientPanel.add(stateInput);
				addClientPanel.add(ziplbl);
				addClientPanel.add(zipcodeInput);
				if(carVinInput != null)
				{
					addClientPanel.add(carVinlbl);
					addClientPanel.add(carVinInput);
				}
				
				addClientPanel.setVisible(true);
				return addClientPanel;
			}
		});
		
		sortClientsBttn = new JButton("Sort");
		
		remvClientBttn = new JButton("Remove");
		remvClientBttn.addActionListener(new ActionListener()
		{public void actionPerformed(ActionEvent e)
		{
			Iterator<Customer> custList = sysApp.getCustomerList().iterator();
			String hold = "";
			while(custList.hasNext())
			{
				int custID = custList.next().getCustomerId();
				hold += custID+",";
			}
			
			if(!hold.equals(""))
			{
				String[] opts = hold.split(",");
				String ans = String.valueOf(
						JOptionPane.showInputDialog(contentPane,
								"Chose (by ID) what customer you want to remove:",
								"Edit Customer",JOptionPane.OK_CANCEL_OPTION,
								null, opts, opts[0]));
				Customer cust = sysApp.getCustomerByID(Integer.parseInt(ans));
				
				int keepCars = JOptionPane.showConfirmDialog(returnDialog(e),
						"Delete all associated vehicles with this customer?",
						"Remove Customer Vehicles", JOptionPane.YES_NO_OPTION);
				boolean delCars = false;
				if(keepCars == JOptionPane.YES_OPTION)
				{delCars = true;}
				else if(keepCars == JOptionPane.NO_OPTION)
				{delCars = false;}
				sysApp.removeCustomerX(cust, delCars);
				JOptionPane.showMessageDialog(contentPane,
						"The customer with ID "+ cust.getCustomerId()+" has been removed");
				
				//now show empty string where the car's string was in the
				//  jtextarea
				String dat = DialManageCustomers.getTxtArea().getText();
				dat.replace(null, "");
				
			}
			else
			{
				JOptionPane.showMessageDialog(returnDialog(e),
						"There are no customers to delete from the VMS Pro at this time.");
			}
		}
		});
		
		cancelBttn = new JButton("Cancel");
		cancelBttn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JDialog jd = returnDialog(e);
				jd.dispose();
			}
		});
		
		totalCustomerArea = new JTextArea(VMSPro_Constants.TxtCustomerHeader,
				VMSPro_Constants.TxtAreaHeight,VMSPro_Constants.TxtAreaWidth);
		fillTable(totalCustomerArea, app);
		
		totalCustomerArea.setEditable(false);
		contentPane.add(totalCustomerArea);
		contentPane.add(addClientBttn);
		contentPane.add(sortClientsBttn);
		contentPane.add(remvClientBttn);
		contentPane.add(cancelBttn);
	}
	
	/**Given a panel, grabs all required data, validates fields, then
	 * creates and returns a customer object. Behaves similar to the <i>
	 * Builder</i> design pattern.*/
	private Customer makeClient(JPanel panel)
	{
		if(checkClientFields(panel))
		{
			String first = fnameInput.getText();
			String last = lnameInput.getText();
			String street= streetInput.getText();
			String city = cityInput.getText();
			String state = stateInput.getText();
			int zip = -11111;
			try 
			{zip= Integer.parseInt(zipcodeInput.getText());}
			catch (NumberFormatException e)
			{zip = -11111;}
			Customer cust;
			if(zip == -11111)
			{return null;}
			else
			{
				cust = new Customer(first, last,
						new Address(street,city,state,zip));
				sysApp.addCustomer(cust);
				return cust;
			}
		}
		else return null;
	}
	
	private boolean checkClientFields(JPanel panel)
	{
		int create = -1;
		do
		{
			create = JOptionPane.showConfirmDialog(null, panel,
					"Add Customer", JOptionPane.OK_CANCEL_OPTION);
			if (create == JOptionPane.OK_OPTION)
			{
				boolean goodFName = VMSPro.checkString(fnameInput.getText().trim());
				boolean goodLName = VMSPro.checkString(lnameInput.getText().trim());
				boolean goodStreet = VMSPro.checkStreetCity(streetInput.getText().trim());
				boolean goodzipcode= VMSPro.checkNumber(zipcodeInput.getText());
				boolean goodState = VMSPro.checkString(stateInput.getText());
				boolean goodCity = VMSPro.checkStreetCity(cityInput.getText());
				
				//catch bad f name
				while(!goodFName)
				{
					int resp = JOptionPane.showConfirmDialog(panel,
							"Bad first name input, do you want to try again?",
							"Invalid First Name Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, panel,
								"Add Customer", JOptionPane.OK_CANCEL_OPTION);
						goodFName = VMSPro.checkString(fnameInput.getText());
					}
					else if(resp == JOptionPane.NO_OPTION)
					{return false;}
				}
				
				//catch bad L name
				while(!goodLName)
				{
					int resp = JOptionPane.showConfirmDialog(panel,
							"Bad last name input, do you want to try again?",
							"Invalid Last Name Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, panel,
								"Add Customer", JOptionPane.OK_CANCEL_OPTION);
						goodLName = VMSPro.checkString(lnameInput.getText());
					}
					else if(resp == JOptionPane.NO_OPTION)
					{return false;}
				}
				
				//catch bad street address
				while(!goodStreet)
				{
					int resp = JOptionPane.showConfirmDialog(panel,
							"Bad street address input, do you want to try again?",
							"Invalid Street Address Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, panel,
								"Add Customer", JOptionPane.OK_CANCEL_OPTION);
						goodStreet = VMSPro.checkString(streetInput.getText());
					}
					else if(resp == JOptionPane.NO_OPTION)
					{return false;}
				}
				
				//catch bad city
				while(!goodCity)
				{
					int resp = JOptionPane.showConfirmDialog(panel,
							"Bad city input, do you want to try again?",
							"Invalid City Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, panel,
								"Add Customer", JOptionPane.OK_CANCEL_OPTION);
						goodCity = VMSPro.checkString(cityInput.getText());
					}
					else if(resp == JOptionPane.NO_OPTION)
					{return false;}
				}
				
				//catch bad state
				while(!goodState)
				{
					int resp = JOptionPane.showConfirmDialog(panel,
							"Bad state input, do you want to try again?",
							"Invalid State Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, panel,
								"Add Customer", JOptionPane.OK_CANCEL_OPTION);
						goodState = VMSPro.checkString(stateInput.getText());
					}
					else if(resp == JOptionPane.NO_OPTION)
					{return false;}
				}
				
				//catch bad zipcode
				while(!goodzipcode)
				{
					int resp = JOptionPane.showConfirmDialog(panel,
							"Bad zipcode input, do you want to try again?",
							"Invalid Zipcode Entered", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION)
					{
						create = JOptionPane.showConfirmDialog(null, panel,
								"Add Customer", JOptionPane.OK_CANCEL_OPTION);
					}else if(resp == JOptionPane.NO_OPTION)
					{return false;}
				}
			}
			create = JOptionPane.CANCEL_OPTION;
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
	{return totalCustomerArea;}
	
	/**Writes data to table or updates data upon editing completion.*/
	private void fillTable(JTextArea someArea, VMSPro app)
	{
		String line = "";
		LinkedList<Customer> clients = app.getCustomerList();
		Iterator<Customer> clientIT = clients.iterator();
		while(clientIT.hasNext())
		{
			Customer cust = clientIT.next();
			if(cust != null)
			{
				int id = cust.getCustomerId();
				String firstN = cust.getFirstName();
				String lastN = cust.getLastName();
				int carCount = cust.getVehicleList().size();
				Address adrs = cust.getPrimaryAddress();
				line += String.format(VMSPro_Constants.HasCar, id,firstN,lastN,
						carCount, adrs.toString()) + "\n";
			}
		}
		someArea.setEditable(true);
		someArea.append(line);
		someArea.setEditable(false);
	}
}