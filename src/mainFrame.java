
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextArea;
import java.awt.TextField;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class mainFrame {

	private JFrame frmPassSaver;
	private JPasswordField passwordField;
	private JLabel updateTxt;
	private JPanel panel;
	private static loggedin li;
	private static mainFrame window;
	private static passGen pg;
	private boolean nUser = false;
	private JButton newUser;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new mainFrame();
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
	public mainFrame() {
		initialize();
	}

	public void msg(String message) {
		updateTxt.setText(message);
	}

	public void open() {
		window.frmPassSaver.setVisible(true);
		frmPassSaver.dispose();
	}

	public void close() {
		frmPassSaver.dispatchEvent(new WindowEvent(frmPassSaver, WindowEvent.WINDOW_CLOSING));
		li.start(pg);
		window.frmPassSaver.setVisible(false);
		frmPassSaver.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		pg = new passGen();
		frmPassSaver = new JFrame();
		frmPassSaver.setTitle("Pass Saver");
		frmPassSaver.setResizable(false);
		frmPassSaver.setBounds(100, 100, 451, 304);
		frmPassSaver.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmPassSaver.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 435, 155);
		frmPassSaver.getContentPane().add(panel);
		panel.setLayout(null);

		updateTxt = new JLabel("");
		updateTxt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		updateTxt.setBounds(258, 109, 162, 35);
		panel.add(updateTxt);

		TextField UsrnTxt = new TextField();
		UsrnTxt.setBackground(SystemColor.window);
		UsrnTxt.setForeground(SystemColor.desktop);
		UsrnTxt.setBounds(122, 20, 184, 28);
		panel.add(UsrnTxt);

		Label PwLBL = new Label("Password:");
		PwLBL.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		PwLBL.setBounds(10, 48, 109, 45);
		panel.add(PwLBL);

		Label UsernameLBL = new Label("Username: ");
		UsernameLBL.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		UsernameLBL.setBounds(10, 10, 109, 45);
		panel.add(UsernameLBL);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER && !nUser) {
					try {
						pg.login(UsrnTxt.getText(), passwordField.getText());
					} catch (IOException e1) {
						pg.errorlogging();
					}
				}

			}
		});
		passwordField.setBounds(122, 54, 184, 28);
		panel.add(passwordField);

		JButton Login = new JButton("Login");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!nUser) {
					try {
						if(!pg.login(UsrnTxt.getText(), passwordField.getText())) {
							updateTxt.setText("Error On Login");
						}else {
							UsrnTxt.setText("");
							passwordField.setText("");
						}
					} catch (IOException e1) {}
				} else {
					try {
						pg.newUser(UsrnTxt.getText(), passwordField.getText());
						if(!pg.login(UsrnTxt.getText(), passwordField.getText())) {
							updateTxt.setText("Error On Login");
						}
						nUser = false;
						Login.setText("Login");
						newUser.setText("New User");
						UsrnTxt.setText("");
						passwordField.setText("");
					} catch (IOException e1) {}
				}

			}
		});
		Login.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Login.setBounds(10, 106, 109, 38);
		panel.add(Login);

		newUser = new JButton("New User");
		newUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.setText("Confirm");
				newUser.setText("Cancel");
				nUser = true;
			}
		});
		newUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		newUser.setBounds(139, 106, 109, 38);
		panel.add(newUser);

		JLabel logo = new JLabel();
		logo.setBounds(0, 161, 114, 114);
		frmPassSaver.getContentPane().add(logo);
		logo.setIcon(new ImageIcon("res/Lock.jpg"));

		JLabel label = new JLabel();
		label.setBounds(140, 161, 305, 114);
		frmPassSaver.getContentPane().add(label);
		label.setIcon(new ImageIcon("res/password.png"));

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
