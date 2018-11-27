import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Choice;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JEditorPane;

public class loggedin {

	private JFrame frmPassSaver;
	private passGen pg;
	private Users user;
	private JComboBox services;
	private JTextField minSpecial, pwAdd, serviceField;
	private JTextPane txtpnLength, minNumbers, minUpper;
	private JRadioButton rdbtnUpperCase, rdbtnNumbers, rdbtnSpecialChars;
	private JButton btnGen, btnCancel, btnNewButton, btnGenerate, btnConfirm;
	private boolean upper = false, special = false, nums = false, delete = false;;
	private JLabel lblMinimum;
	private JButton DeleteService;

	/**
	 * Launch the application.
	 */
	public static void start(passGen pg) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loggedin window = new loggedin(pg);
					window.frmPassSaver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loggedin(passGen pg) {
		this.pg = pg;
		user = pg.user();
		lGinitialize();
		popPw();
	}

	public JFrame jframe() {
		return frmPassSaver;
	}

	private void cancel() {
		rdbtnSpecialChars.setVisible(false);
		txtpnLength.setVisible(false);
		rdbtnSpecialChars.setVisible(false);
		minNumbers.setVisible(false);
		rdbtnUpperCase.setVisible(false);
		rdbtnNumbers.setVisible(false);
		minSpecial.setVisible(false);
		minUpper.setVisible(false);
		btnGen.setVisible(false);
		serviceField.setText("Enter Service Name");
		btnConfirm.setVisible(false);
		pwAdd.setVisible(false);
		pwAdd.setText("Enter Service Password");
		pwAdd.setEditable(false);
		serviceField.setVisible(false);
		btnGenerate.setVisible(false);
		btnNewButton.setVisible(true);
		btnCancel.setVisible(false);
		lblMinimum.setVisible(false);
		minNumbers.setText("0");
		minSpecial.setText("0");
		minUpper.setText("0");
		txtpnLength.setText("Length");
		DeleteService.setVisible(true);
	}
	private void popPw() {
		services.removeAllItems();
		HashMap<String, String> pws = user.getPws();
		for (String service : pws.keySet()) {
			services.addItem(service);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void lGinitialize() {
		frmPassSaver = new JFrame();
		frmPassSaver.setTitle("Pass Saver");
		frmPassSaver.setResizable(false);
		frmPassSaver.setBounds(100, 100, 450, 300);
		frmPassSaver.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmPassSaver.getContentPane().setLayout(null);

		Button LogOut = new Button("Log Out");
		LogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPassSaver.dispatchEvent(new WindowEvent(frmPassSaver, WindowEvent.WINDOW_CLOSING));
				pg.logOut();
				pg.loginScreen();
			}
		});

		minUpper = new JTextPane();
		minUpper.setVisible(false);
		minUpper.setText("0");
		minUpper.setBounds(135, 200, 91, 20);
		frmPassSaver.getContentPane().add(minUpper);

		minSpecial = new JTextField();
		minSpecial.setText("0");
		minSpecial.setVisible(false);
		minSpecial.setBounds(135, 175, 91, 20);
		frmPassSaver.getContentPane().add(minSpecial);
		minSpecial.setColumns(10);
		LogOut.setBounds(10, 0, 70, 22);
		frmPassSaver.getContentPane().add(LogOut);

		Label userLbl = new Label("User:");
		userLbl.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		userLbl.setBounds(10, 20, 55, 45);
		frmPassSaver.getContentPane().add(userLbl);

		JLabel Username = new JLabel(user.getUser());
		Username.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		Username.setBounds(71, 20, 353, 40);
		frmPassSaver.getContentPane().add(Username);

		Label label = new Label("Services");
		label.setBounds(10, 61, 62, 22);
		frmPassSaver.getContentPane().add(label);

		serviceField = new JTextField();
		serviceField.setBackground(Color.WHITE);
		serviceField.setVisible(false);
		serviceField.setBounds(236, 147, 188, 22);
		frmPassSaver.getContentPane().add(serviceField);

		Button Show = new Button("Show Password");
		Show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				serviceField.setVisible(true);
				btnConfirm.setVisible(false);
				pwAdd.setVisible(false);
				serviceField.setText(user.retrieve((String) services.getSelectedItem()));
				rdbtnSpecialChars.setVisible(false);
				txtpnLength.setVisible(false);
				rdbtnSpecialChars.setVisible(false);
				minNumbers.setVisible(false);
				rdbtnUpperCase.setVisible(false);
				rdbtnNumbers.setVisible(false);
				minSpecial.setVisible(false);
				minUpper.setVisible(false);
				btnGenerate.setVisible(false);
				btnNewButton.setVisible(true);
				btnGen.setVisible(false);
				btnCancel.setVisible(true);
				lblMinimum.setVisible(false);
			}
		});
		Show.setBounds(236, 86, 188, 45);
		frmPassSaver.getContentPane().add(Show);

		services = new JComboBox();
		services.setBounds(20, 89, 172, 20);
		frmPassSaver.getContentPane().add(services);

		pwAdd = new JTextField();
		pwAdd.setBounds(236, 175, 188, 20);
		pwAdd.setVisible(false);
		frmPassSaver.getContentPane().add(pwAdd);
		pwAdd.setColumns(10);

		btnConfirm = new JButton("Confirm");
		btnConfirm.setVisible(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(delete) {
					System.out.println((String) services.getSelectedItem());
					user.remove((String) services.getSelectedItem());
				}else {
					String service = serviceField.getText();
					if (!pwAdd.getText().equals("Enter Service Password") && !pwAdd.getText().equals("")) {
						String password = pwAdd.getText();
						try {
							user.store(service, password);
						} catch (IOException e1) {
							System.out.println("error with storing password ");
						}
					}
				}
				popPw();
				user.save(pg.wr);
				cancel();
			}
		});
		btnConfirm.setBounds(337, 200, 87, 23);
		frmPassSaver.getContentPane().add(btnConfirm);

		txtpnLength = new JTextPane();
		txtpnLength.setVisible(false);
		txtpnLength.setText("Length");
		txtpnLength.setBounds(20, 147, 70, 20);
		frmPassSaver.getContentPane().add(txtpnLength);

		rdbtnUpperCase = new JRadioButton("Upper Case");
		rdbtnUpperCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upper = !upper;
			}
		});
		rdbtnUpperCase.setVisible(false);
		rdbtnUpperCase.setBounds(10, 200, 119, 23);
		frmPassSaver.getContentPane().add(rdbtnUpperCase);

		rdbtnNumbers = new JRadioButton("Numbers");
		rdbtnNumbers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nums = !nums;
			}
		});
		rdbtnNumbers.setVisible(false);
		rdbtnNumbers.setBounds(10, 226, 119, 23);
		frmPassSaver.getContentPane().add(rdbtnNumbers);

		btnNewButton = new JButton("New Service");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel.setVisible(true);
				serviceField.setText("Enter Service Name");
				btnConfirm.setVisible(true);
				pwAdd.setVisible(true);
				pwAdd.setText("Enter Service Password");
				pwAdd.setEditable(true);
				serviceField.setVisible(true);
				btnGenerate.setVisible(true);
				btnNewButton.setVisible(false);
				DeleteService.setVisible(false);
			}
		});
		btnNewButton.setBounds(20, 115, 172, 22);
		frmPassSaver.getContentPane().add(btnNewButton);

		btnGenerate = new JButton("Generate password");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSpecialChars.setVisible(true);
				txtpnLength.setVisible(true);
				rdbtnSpecialChars.setVisible(true);
				minNumbers.setVisible(true);
				rdbtnUpperCase.setVisible(true);
				rdbtnNumbers.setVisible(true);
				minSpecial.setVisible(true);
				minUpper.setVisible(true);
				btnGen.setVisible(true);
				lblMinimum.setVisible(true);
				DeleteService.setVisible(false);
			}
		});
		btnGenerate.setBounds(20, 115, 172, 23);
		btnGenerate.setVisible(false);
		frmPassSaver.getContentPane().add(btnGenerate);

		btnGen = new JButton("Generate");
		btnGen.setVisible(false);
		btnGen.setBounds(236, 200, 91, 23);
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int length = 7, minUp = 0, minSp = 0, minNum = 0;
				
				try {					
					length = Integer.parseInt(txtpnLength.getText());
				} catch (NumberFormatException e1) {
					length = 7;
					System.out.println("error in len");
				}
				try {					
					minUp = Integer.parseInt(minUpper.getText());
				} catch (NumberFormatException e1) {
					minUp = 0;
					System.out.println("error in upper");
				}
				try {					
					minSp = Integer.parseInt(minSpecial.getText());
				} catch (NumberFormatException e1) {
					minSp = 0;
					System.out.println("error in spec");
				}
				try {					
					minNum = Integer.parseInt(minNumbers.getText());
				} catch (NumberFormatException e1) {
					minNum = 0;
					System.out.println("error in up");
				}
				
				if(minUp != 0 || minSp != 0 || minNum != 0) {
					pwAdd.setText(user.Generate(upper, special, nums, length, minUp, minNum, minSp));
				}else {
					pwAdd.setText(user.Generate(upper, special, nums, length));
				}

			}
		});
		frmPassSaver.getContentPane().add(btnGen);

		rdbtnSpecialChars = new JRadioButton("Special Chars");
		rdbtnSpecialChars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				special = !special;
			}
		});
		rdbtnSpecialChars.setVisible(false);

		minNumbers = new JTextPane();
		minNumbers.setVisible(false);
		minNumbers.setText("0");
		minNumbers.setBounds(135, 226, 91, 20);
		frmPassSaver.getContentPane().add(minNumbers);
		rdbtnSpecialChars.setBounds(10, 174, 119, 23);
		frmPassSaver.getContentPane().add(rdbtnSpecialChars);

		btnCancel = new JButton("Cancel");
		btnCancel.setVisible(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancel.setBounds(286, 237, 89, 23);
		frmPassSaver.getContentPane().add(btnCancel);
		
		lblMinimum = new JLabel("Minimum");
		lblMinimum.setBounds(135, 151, 70, 14);
		lblMinimum.setVisible(false);
		frmPassSaver.getContentPane().add(lblMinimum);
		
		DeleteService = new JButton("Delete Service");
		DeleteService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete = true;
				btnConfirm.setVisible(true);
			}
		});
		DeleteService.setBounds(20, 142, 172, 22);
		frmPassSaver.getContentPane().add(DeleteService);
	}
}
