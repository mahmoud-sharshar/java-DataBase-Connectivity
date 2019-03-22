package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;

import java.awt.Label;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class RegistrationUI extends JFrame {

	private JPanel contentPane;
	private JTextField UserNameText;
	private JPasswordField passwordField;
	private JTextField DataBaseText;
	private JButton SignUpButton;
	private JButton ConnectButton;
	private Label label_3;
	private Label label_2;
	private Label label_1;
	private File fileRegistration;
	private JPanel DataBasePanel;
	private Driver driver;

	/**
	 * Create the frame.
	 */
	public RegistrationUI(String path, String driverName, Driver driver) {
		fileRegistration = new File(path);
		this.driver = driver;
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Label label = new Label("Java DataBase connectivity");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setAlignment(Label.CENTER);
		contentPane.add(label, BorderLayout.NORTH);

		JPanel RegistraionPanel = new JPanel();
		contentPane.add(RegistraionPanel, BorderLayout.CENTER);
		RegistraionPanel.setLayout(new BoxLayout(RegistraionPanel, BoxLayout.Y_AXIS));

		JPanel infoPanel = new JPanel();
		RegistraionPanel.add(infoPanel);
		infoPanel.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel UserNamePanel = new JPanel();
		infoPanel.add(UserNamePanel);
		UserNamePanel.setLayout(new BoxLayout(UserNamePanel, BoxLayout.X_AXIS));

		label_1 = new Label("  User Name :   ");
		label_1.setFont(new Font("Dialog", Font.BOLD, 11));
		label_1.setAlignment(Label.CENTER);
		UserNamePanel.add(label_1);

		UserNameText = new JTextField();
		UserNamePanel.add(UserNameText);
		UserNameText.setColumns(10);

		JPanel PasswordPanel = new JPanel();
		infoPanel.add(PasswordPanel);
		PasswordPanel.setLayout(new BoxLayout(PasswordPanel, BoxLayout.X_AXIS));

		label_2 = new Label("  Password  :   ");
		label_2.setFont(new Font("Dialog", Font.BOLD, 12));
		label_2.setAlignment(Label.CENTER);
		PasswordPanel.add(label_2);

		passwordField = new JPasswordField();

		PasswordPanel.add(passwordField);

		DataBasePanel = new JPanel();
		infoPanel.add(DataBasePanel);
		DataBasePanel.setLayout(new BoxLayout(DataBasePanel, BoxLayout.X_AXIS));
		DataBasePanel.setVisible(false);
		label_3 = new Label("DataBase Name");
		label_3.setFont(new Font("Dialog", Font.BOLD, 11));
		label_3.setAlignment(Label.CENTER);
		// label_3.setVisible(false);
		DataBasePanel.add(label_3);

		DataBaseText = new JTextField();
		DataBaseText.setColumns(10);
		// DataBaseText.setVisible(false);
		DataBasePanel.add(DataBaseText);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		RegistraionPanel.add(panel);

		SignUpButton = new JButton("Register");
		SignUpButton.setVisible(false);

		SignUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (inputEntered()) {
					try {
						fileRegistration.createNewFile();
						PrintWriter register = new PrintWriter(new BufferedWriter(new FileWriter(fileRegistration)));
						register.println(UserNameText.getText());
						register.println(passwordField.getText());
						register.close();
						ConnectButton.setVisible(true);
						SignUpButton.setVisible(false);
						DataBasePanel.setVisible(true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		panel.add(SignUpButton);

		ConnectButton = new JButton("Connect");
		ConnectButton.setVisible(false);
		ConnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (inputEntered()) {
					Properties info = new Properties();
					info.put("username", UserNameText.getText());
					info.put("password", passwordField.getText());
					info.put("path", DataBaseText.getText());
					try {
						new CLUI(driver.connect(driverName, info), DataBaseText.getText(), cloneFrame());
						closeFrame();
					} catch (SQLException e) {
						ErrorMessage();
					}

				}

			}
		});

		panel.add(ConnectButton);
		checkFileExist();

	}

	private void closeFrame() {
		this.setVisible(false);
	}

	private void checkFileExist() {

		if (fileRegistration.exists()) {
			ConnectButton.setVisible(true);
			DataBasePanel.setVisible(true);
		} else {
			SignUpButton.setVisible(true);
		}
	}

	private boolean inputEntered() {
		if (UserNameText.getText().equals("") || passwordField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "please enter user name and password", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;
		} else if (ConnectButton.isVisible() && DataBaseText.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "please enter DataBaseName", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private JFrame cloneFrame() {return this;}

	private void ErrorMessage() {
		JOptionPane.showMessageDialog(this, "incorrect userName or password", "Error", JOptionPane.ERROR_MESSAGE);
	}
}
