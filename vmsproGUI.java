package Phase4;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

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
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

/**
 * Gui DDC file for the VMS Pro application.  This is primarily build off of
 * Swing and some AWT-family features which enable the processing of user
 * input and requests for reports (RFR).
 *<i>Note: Modularity is used heavily in this file as part of class
 *	requirements.</i>
 * @author tmitchu2
 * */
public class vmsproGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTable carTable, clientTable;
	private JTable allClientsTable = new JTable(),allCarTable = new JTable();

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
		setTitle("VMS Pro");
		setBounds(100, 100, 1000, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set the icon for VMS Pro
		Image icon = Toolkit.getDefaultToolkit().getImage("VMS_Pro_Icon.ico");
		setIconImage(icon);
		
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
		GroupLayout gl_clientPanel = new GroupLayout(clientPanel);
		gl_clientPanel.setHorizontalGroup(
			gl_clientPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 645, Short.MAX_VALUE)
		);
		gl_clientPanel.setVerticalGroup(
			gl_clientPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 232, Short.MAX_VALUE)
		);
		clientPanel.setLayout(gl_clientPanel);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addComponent(carPanel, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addComponent(clientPanel, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(clientPanel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
						.addComponent(carPanel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(357, Short.MAX_VALUE))
		);
		
		JLabel lblVehicles = new JLabel("Vehicles");
		
		JButton btnManageVehicles = new JButton("Manage Vehicles");
		btnManageVehicles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JPanel mp = new JPanel();
				
				//table and table model for total car management
					//get data from car data text file
				//Object[][] carData = null;
				ArrayList<String> lines = new ArrayList<>();
				String fname = "phase4/Phase4/vehicleData.txt";
				try(BufferedReader br = new BufferedReader(new FileReader(fname)))
				{
					//assume file exists and get all car data from it
					String currLine;
					while((currLine = br.readLine()) != null)
					{
						lines.add(currLine);
						System.out.println(currLine);
					}
				}catch(FileNotFoundException fnf)
				{
					JOptionPane.showConfirmDialog(mp, "File was not found.",
							"Error -File Not Found", JOptionPane.YES_NO_OPTION);
				}
				catch(FileAlreadyExistsException fae)
				{
					JOptionPane.showConfirmDialog(mp, "File exists already!",
							"Error -File Exists Already", JOptionPane.YES_NO_OPTION);
				} catch (IOException e1)
				{e1.printStackTrace();}
				
				allCarTable.setModel(new DefaultTableModel(new Object[][] {},
					new String[]
							{"Lot ID", "VIN", "Make", "Model", "Year", "Color", "Type", "Customer ID"}
				){
					private static final long serialVersionUID = 1L;
					boolean[] columnEditables = new boolean[]{true, true, true, true, true, true, true, true};
					public boolean isCellEditable(int row, int column){return columnEditables[column];}
				});
				allCarTable.getColumnModel().getColumn(0).setPreferredWidth(67);
				allCarTable.getColumnModel().getColumn(1).setPreferredWidth(120);
				allCarTable.getColumnModel().getColumn(2).setPreferredWidth(89);
				allCarTable.getColumnModel().getColumn(3).setPreferredWidth(116);
				allCarTable.getColumnModel().getColumn(4).setPreferredWidth(62);
				allCarTable.getColumnModel().getColumn(5).setPreferredWidth(65);
				allCarTable.getColumnModel().getColumn(6).setPreferredWidth(114);
				allCarTable.getColumnModel().getColumn(7).setPreferredWidth(114);
				readDataToTable(lines, allCarTable);
				//allCarTable.setValueAt(aValue, row, column);
				allCarTable.setVisible(true);
				mp.add(allCarTable);
				
				String[] carMangmtBttns = {"Add", "Edit", "Remove", "Go Back"};
				int carBttnAns = -1;
				do
				{
					carBttnAns = JOptionPane.showOptionDialog(mp, "", "Vehicle Management",
							JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,
							null, carMangmtBttns, carMangmtBttns[3]);
					switch(carBttnAns)
					{
						case 0:
							break;
						case 1:
							break;
						case 2:
							break;
						case 3:
							break;
					}
				}while(carBttnAns != 3);
				
			}
		});
		
		carTable = new JTable();
		carTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] colNames = {"VIN", "Make", "Model"};
		carTable.setModel(new DefaultTableModel(new Object[][] {}, colNames)
		{
			private static final long serialVersionUID = 1L;
			boolean[] colEdits = new boolean[] {true,true,true };
			public boolean isCellEditable(int row, int col){return colEdits[col];}
		});
		
		GroupLayout gl_carPanel = new GroupLayout(carPanel);
		gl_carPanel.setHorizontalGroup(
			gl_carPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_carPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_carPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_carPanel.createSequentialGroup()
							.addComponent(lblVehicles)
							.addPreferredGap(ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
							.addComponent(btnManageVehicles)
							.addGap(21))
						.addGroup(gl_carPanel.createSequentialGroup()
							.addComponent(carTable, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		gl_carPanel.setVerticalGroup(
			gl_carPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_carPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_carPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVehicles, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnManageVehicles))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(carTable, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		carTable.setVisible(true);
		carPanel.add(allCarTable);
		carPanel.setLayout(gl_carPanel);
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
		
		JMenu mnOpen = new JMenu("Open");
		mnFile.add(mnOpen);
		
		JMenuItem mntmTextFile = new JMenuItem("Text File");
		mnOpen.add(mntmTextFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{System.exit(0);}
		});
		mnFile.add(mntmExit);
	}

	/**
	 * 
	 * */
	private void readDataToTable(ArrayList<String> lineData, JTable table)
	{
		//get all line data
		for (int i = 0; i < lineData.size(); i++)
		{
			/*
			 * 0 - lot id
			 * 1 - vin
			 * 2 - make
			 * 3 - model
			 * 4 - year
			 * 5 - color
			 * 6 - type
			 * 7 - customer ID
			 * */
			String[] lineXData = lineData.get(i).split(",");
			String carVin = lineXData[1],
					make= lineXData[2],
					model= lineXData[3],
					color= lineXData[5],
					type= lineXData[6];
			int year = Integer.parseInt(lineXData[4]);
			int custID = Integer.parseInt(lineXData[7]);
			int lotID = Integer.parseInt(lineXData[0]);
			
			//add data to table
			//table.setValueAt(aValue, row, column);
			table.setValueAt(lotID, i, table.getEditingColumn());
			//table.setValueAt(carVin, i, 1);
			//table.setValueAt(make, i, 2);
			//table.setValueAt(model, i, 3);
			//table.setValueAt(year, i, 4);
			//table.setValueAt(color, i, 5);
			//table.setValueAt(type, i, 6);
			//table.setValueAt(custID, i, 7);
		}
	}
}