package Phase4;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JTable;
//import javax.swing.JButton;

@SuppressWarnings("serial")
public class vmsproUi extends JFrame
{
	private JPanel contentPane;
	private JTable carTable;
	private JTable custTable;

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
					vmsproUi frame = new vmsproUi();
					frame.setSize(1000, 650);
					frame.setVisible(true);
				}catch(Exception e)
				{e.printStackTrace();}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vmsproUi()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setTitle("VMS Pro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.activeCaption);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenFromFile = new JMenuItem("Open From File");
		mnFile.add(mntmOpenFromFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{System.exit(0);}
		});
		mnFile.add(mntmExit);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		
		
		JPanel carPanel = new JPanel();
		//carPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
		//carPanel.setSize(100, 100);
		
		JPanel clientPanel = new JPanel();
		
		JLabel lblEports = new JLabel("Reports");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(carPanel, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
							.addComponent(clientPanel, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(93)
							.addComponent(lblEports)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(carPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(clientPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblEports)
					.addContainerGap(176, Short.MAX_VALUE))
		);
		
		JLabel lblCustomers = new JLabel("Customers");
		
		custTable = new JTable();
		
		GroupLayout gl_clientPanel = new GroupLayout(clientPanel);
		gl_clientPanel.setHorizontalGroup(
			gl_clientPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_clientPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_clientPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCustomers)
						.addComponent(custTable, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		gl_clientPanel.setVerticalGroup(
			gl_clientPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_clientPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCustomers)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(custTable, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		
		JLabel lblVehicles = new JLabel("Vehicles");
		
		carTable = new JTable();
		GroupLayout gl_carPanel = new GroupLayout(carPanel);
		gl_carPanel.setHorizontalGroup(
			gl_carPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_carPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_carPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVehicles)
						.addComponent(carTable, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		gl_carPanel.setVerticalGroup(
			gl_carPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_carPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVehicles)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(carTable, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		carPanel.setLayout(gl_carPanel);
		clientPanel.setLayout(gl_clientPanel);
		
		contentPane.setLayout(gl_contentPane);
		contentPane.add(carPanel);
		contentPane.add(clientPanel);
		
		setContentPane(contentPane);
	}
}
