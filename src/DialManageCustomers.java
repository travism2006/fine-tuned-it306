package vmspro;

import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DialManageCustomers extends JDialog
{
	private VMSPro sysApp;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton addCarBttn, editCarBttn, sortCarsBttn, remvCarBttn, cancelBttn;
	private JTextArea totalCarArea;
	private JTextField vinInput,makeInput,modelInput,yearInput,custInput;

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
	}

}
