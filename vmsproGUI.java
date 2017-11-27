package vmspro;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

import vmspro.VMSPro_Constants.CarTypes;

import java.awt.Toolkit;
import java.awt.Font;

/**
 * Gui DDC file for the VMS Pro application.  This is primarily build off of
 * Swing and some AWT-family features which enable the processing of user
 * input and requests for reports (RFR).<br>
 *<i>Note: Modularity is used heavily in this file as part of class
 *	requirements.</i>
 * @author tmitchu2
 * */
public class vmsproGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private VMSPro project = new VMSPro();

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
					vmsproGUI frame = new vmsproGUI();
					frame.setVisible(true);
				}catch(Exception e)
				{e.printStackTrace();}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vmsproGUI()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(vmsproGUI.class.getResource("/vmspro/VMS_Pro_Icon.ico")));
		setTitle("VMS Pro");
		setBounds(100, 100, 1000, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//make the subpanel for vehicles
		JPanel carPanel = new JPanel();
		carPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		//make the sub-panel for customers
		JPanel clientPanel = new JPanel();
		clientPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		/*
		 * The use of group layout is due to flexibility of positioning the 
		 * necessary components for the application
		 * */
		JLabel lblCustomers = new JLabel("Customers");
		JButton btnManageCustomers = new JButton("Manage Customers");
		//make grouplayout for customer panel
		
		 btnManageCustomers.addActionListener(new ActionListener()
		 {
			public void actionPerformed(ActionEvent e)
			{
				JFrame fr = getFrame(e);
				DialManageCustomers mngClients = new DialManageCustomers(fr,project);
				mngClients.setVisible(true);
			}});
		
		JTextArea someClientsTxtArea = new JTextArea(10,3);
		someClientsTxtArea.setEditable(false);
		someClientsTxtArea.setLineWrap(true);
		someClientsTxtArea.setWrapStyleWord(true);
		someClientsTxtArea.setText(VMSPro_Constants.TxtSomeCustomerHeader);
		 
		GroupLayout gl_clientPanel = new GroupLayout(clientPanel);
		gl_clientPanel.setHorizontalGroup(
			gl_clientPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_clientPanel.createSequentialGroup()
					.addGroup(gl_clientPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_clientPanel.createSequentialGroup()
							.addComponent(lblCustomers)
							.addGap(154)
							.addComponent(btnManageCustomers))
						.addGroup(gl_clientPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(someClientsTxtArea, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_clientPanel.setVerticalGroup(
			gl_clientPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_clientPanel.createSequentialGroup()
					.addGroup(gl_clientPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomers)
						.addComponent(btnManageCustomers))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(someClientsTxtArea, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_clientPanel.setAutoCreateGaps(true);
		
		JButton btnAllVehicles = new JButton("All Vehicles");
		btnAllVehicles.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e)
					{
						String report = "";
						if(!project.getSedanList().isEmpty())
						{
							Iterator<Sedan> sedanIT = project.getSedanList().iterator();
							while(sedanIT.hasNext())
							{report += sedanIT.next().toString()+";\n";}
							report += "\n\n";
						}
						else
						{report += "--No Sedans to include in this report--\n\n";}
						
						
						if(!project.getTruckList().isEmpty())
						{
							Iterator<Truck> truckIT = project.getTruckList().iterator();
							while(truckIT.hasNext())
							{report += truckIT.next().toString()+";\n";}
							report += "\n\n";
						}
						else{report += "--No Trucks to include in this report--\n\n";}
						
						if(!project.getVanList().isEmpty())
						{
							Iterator<Van> vanIT = project.getVanList().iterator();
							while(vanIT.hasNext())
							{report += vanIT.next().toString()+";\n";}
						}
						else{report += "--No Vans to include in this report--";}
						
						JFrame fr = getFrame(e);
						JOptionPane.showMessageDialog(fr, report);
					}
			
				});
		JLabel lblReports = new JLabel("Reports");
		lblReports.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JButton btnAllCustomers = new JButton("All Customers");
		btnAllCustomers.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String report = "";
				if(!project.getCustomerList().isEmpty())
				{
					Iterator<Customer> clientIT = project.getCustomerList().iterator();
					while(clientIT.hasNext())
					{report += clientIT.next().toString()+";\n";}
					report += "\n\n";
				}
				report += "--There are no Customers to include in this report--";
				JFrame fr = getFrame(e);
				JOptionPane.showMessageDialog(fr, report);
			}
		});
		
		JButton btnTypesOfVehicles = new JButton("Types of Vehicles");
		btnTypesOfVehicles.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String report="";
				JFrame fr = getFrame(e);
				
				if(!project.getCarList().isEmpty())
				{
					String[] opts = { CarTypes.SEDAN.toString(), CarTypes.TRUCK.toString(), CarTypes.VAN.toString() };
					int ans = JOptionPane.showOptionDialog(fr, "What type of vehicle do you want a report on?",
							"Specify The Car Type", JOptionPane.QUESTION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null,
							opts, opts[0]);
					switch (ans) {
					case 0:
						//get all sedan cars and report on them
						Iterator<Sedan> sedans = project.getSedanList().iterator();
						while(sedans.hasNext())
						{report += sedans.next().toString();}
						break;
					case 1:
						//get all truck cars and report
						Iterator<Truck> trucks = project.getTruckList().iterator();
						while(trucks.hasNext())
						{report += trucks.next().toString();}
						break;
					case 2:
						//get all vans and report
						Iterator<Van> vans = project.getVanList().iterator();
						while(vans.hasNext())
						{report += vans.next().toString();}
						break;
					}
				}
				else
				{report+="--There are no cars in the system or they have all been deleted--\nEnding Report!";}
				JOptionPane.showMessageDialog(fr, report);
			}
		});
		
		
		JButton btnUnlinkedVehicles = new JButton("Unlinked Vehicles");
		btnUnlinkedVehicles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String report = "";
				if(!project.getVehicleNoClient().isEmpty())
				{
					Iterator<Vehicle> carsNoClient = project.getVehicleNoClient().iterator();
					while(carsNoClient.hasNext())
					{
						Vehicle car = carsNoClient.next();
						if(car instanceof Sedan)
						{report += ((Sedan)carsNoClient.next()).toString()+";\n";}
						else if(car instanceof Truck)
						{report += ((Truck)carsNoClient.next()).toString()+";\n";}
						else if(car instanceof Van)
						{report += ((Van)carsNoClient.next()).toString()+";\n";}
					}
				}
				else if(project.getVehicleNoClient().isEmpty() && project.getCarList().isEmpty())
				{report += "--No vehicles are in the system or are all deleted--";}
				else
				{report += "--All currently existing vehicles have customers associated to them--";}
				JFrame fr = getFrame(e);
				JOptionPane.showMessageDialog(fr, report);
			}
		});
		
		JButton btnUnlinkedCustomers = new JButton("Unlinked Customers");
		btnUnlinkedCustomers.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String report = "";
				if(!project.getCustomersWithNoCars().isEmpty())
				{
					Iterator<Customer> clientNoCarIT = project.getCustomersWithNoCars().iterator();
					while(clientNoCarIT.hasNext())
					{report += clientNoCarIT.next().toString()+";\n";}
				}
				else if(project.getCustomersWithNoCars().isEmpty() && project.getCustomerList().isEmpty())
				{report += "--No customers are in the system or are all deleted--";}
				else
				{report += "--All currently existing customers have cars associated to them--";}
				JFrame fr = getFrame(e);
				JOptionPane.showMessageDialog(fr, report);
			}
		});
		
		
		//make the Master Panel GroupLayout----------------------------------------------------
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addComponent(carPanel, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addComponent(clientPanel, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(75)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblReports)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAllVehicles)
							.addGap(18)
							.addComponent(btnAllCustomers)
							.addGap(18)
							.addComponent(btnTypesOfVehicles)
							.addGap(18)
							.addComponent(btnUnlinkedVehicles)
							.addGap(18)
							.addComponent(btnUnlinkedCustomers)))
					.addContainerGap(175, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(clientPanel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
						.addComponent(carPanel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
					.addGap(109)
					.addComponent(lblReports)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAllVehicles)
						.addComponent(btnAllCustomers)
						.addComponent(btnTypesOfVehicles)
						.addComponent(btnUnlinkedVehicles)
						.addComponent(btnUnlinkedCustomers))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		//end-----------------------------------------------------------------------------------
		
		JLabel lblVehicles = new JLabel("Vehicles");
		JButton btnManageVehicles = new JButton("Manage Vehicles");
		
		btnManageVehicles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame fr = getFrame(e);
				DialManageVehicles mngCars = new DialManageVehicles(fr,project);
				mngCars.setVisible(true);
			}
		});
		
		JTextArea someCarsTxtArea = new JTextArea(10,3);
		someCarsTxtArea.setEditable(false);
		someCarsTxtArea.setLineWrap(true);
		someCarsTxtArea.setWrapStyleWord(true);
		someCarsTxtArea.setText(VMSPro_Constants.TxtSomeCarHeader);
		
		GroupLayout gl_carPanel = new GroupLayout(carPanel);
		gl_carPanel.setHorizontalGroup(
			gl_carPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_carPanel.createSequentialGroup()
					.addGroup(gl_carPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_carPanel.createSequentialGroup()
							.addComponent(lblVehicles)
							.addGap(188)
							.addComponent(btnManageVehicles))
						.addGroup(gl_carPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(someCarsTxtArea, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_carPanel.setVerticalGroup(
			gl_carPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_carPanel.createSequentialGroup()
					.addGroup(gl_carPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVehicles, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnManageVehicles))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(someCarsTxtArea, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		carPanel.setLayout(gl_carPanel);
		clientPanel.setLayout(gl_clientPanel);
		getContentPane().setLayout(groupLayout);
		
		makeMenuBar();
	}
	
	/**
	 * Private worker method for creating the menu bar of the JFrame.
	 * */
	private void makeMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{System.exit(0);}
		});
		mnFile.add(mntmExit);
	}
	
	/**
	 * Returns the frame found using SwingUtilities based on some ActionEvent e
	 * */
	public static JFrame getFrame(ActionEvent e)
	{
		Component comp = (Component)e.getSource();
		JFrame fr = (JFrame)SwingUtilities.getRoot(comp);
		return fr;
	}
}