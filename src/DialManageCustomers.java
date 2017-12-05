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
	private JButton addClientBttn, editClientBttn, sortClientsBttn, remvClientBttn, cancelBttn;
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
				// TODO::
				JPanel newClientform = makeAddCustomerPanel();
				
				//data per client
				Customer client;
				int custID,zipcode,carSize;
				String city,street,state,fname,lname;
				
				client = makeClient(newClientform);
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
					carVinInput = new JComboBox<String>(
							(String[])sysApp.getVehicleNoClient().toArray());
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
				
				String[] opts = {""};
				JOptionPane.showOptionDialog(null, "", "title",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, opts, opts[0]);
				
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
								"What type of vehicle:", "Edit Customer",
								JOptionPane.OK_CANCEL_OPTION, null, opts, opts[0]));
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
		
		
		
		return null;
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
				boolean goodFName = VMSPro.checkString(fnameInput.getText());
				boolean goodLName = VMSPro.checkString(lnameInput.getText());
				
				
				
				
				
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