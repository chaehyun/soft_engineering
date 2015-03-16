package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.JSONException;
import org.json.JSONObject;

public class register_company extends JFrame
{

	private JPanel contentPane;
	private JTextField companyName;
	private JTextField companyId;
	private JPasswordField passwordField;
	private JPasswordField retypePasswordField;
	private JTextField companyLocation;
	private JTextField contactNumber;
	private JTextField Validchecker;
	private final String INVALID_PASSWORD = "Check your Password Validation!";
	private boolean passwordvalidation = false;

	/**
	 * Create the frame.
	 */
	public register_company()
	{
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblWelcomeNewCompany = new JLabel(
				"\u2605 Welcome New Company \u2605");
		lblWelcomeNewCompany.setFont(new Font("�����ü", Font.BOLD,
				12));
		lblWelcomeNewCompany.setBounds(97, 10, 198, 15);
		contentPane.add(lblWelcomeNewCompany);

		JLabel lblName = new JLabel("Company Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(28, 37, 92, 15);
		contentPane.add(lblName);

		companyName = new JTextField();
		companyName.setBounds(132, 35, 116, 21);
		contentPane.add(companyName);
		companyName.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(66, 66, 54, 15);
		contentPane.add(lblId);

		companyId = new JTextField();
		companyId.setBounds(132, 60, 116, 21);
		contentPane.add(companyId);
		companyId.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(66, 91, 57, 15);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField("", 20);
		passwordField.setBounds(132, 87, 116, 21);
		contentPane.add(passwordField);

		JLabel lblRetypePassword = new JLabel("Retype password");
		lblRetypePassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRetypePassword.setBounds(16, 118, 106, 15);
		contentPane.add(lblRetypePassword);

		retypePasswordField = new JPasswordField("", 20);
		retypePasswordField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				passwdkeyTypedhandler(e);
			}
		});

		retypePasswordField.setBounds(132, 114, 116, 21);
		contentPane.add(retypePasswordField);

		JLabel lblAddress = new JLabel("Company Location");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(16, 147, 106, 15);
		contentPane.add(lblAddress);

		companyLocation = new JTextField();
		companyLocation.setBounds(132, 142, 116, 54);
		contentPane.add(companyLocation);
		companyLocation.setColumns(10);

		JLabel lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContactNumber.setBounds(22, 212, 98, 15);
		contentPane.add(lblContactNumber);

		contactNumber = new JTextField();
		contactNumber.setBounds(132, 208, 116, 21);
		contentPane.add(contactNumber);
		contactNumber.setColumns(10);

		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				checkpasswordvalidation(e);
			}
		});
		btnRegister.setBounds(249, 243, 97, 29);
		contentPane.add(btnRegister);

		Validchecker = new JTextField();
		Validchecker.setForeground(Color.RED);
		Validchecker.setFont(new Font("Tahoma", Font.PLAIN, 9));
		Validchecker.setText("Invaild Password!");
		Validchecker.setVisible(false);
		Validchecker.setEditable(false);
		Validchecker.setBorder(null);
		Validchecker.setBackground(SystemColor.window);
		Validchecker.setBounds(260, 113, 117, 28);
		contentPane.add(Validchecker);
		Validchecker.setColumns(10);
	}

	protected void checkpasswordvalidation(MouseEvent e)
	{
		if (isPasswordvalidation() == false)
		{
			JOptionPane.showMessageDialog(new JFrame(), INVALID_PASSWORD);
		}
		String name = companyName.getText();
		String userID = companyId.getText();
		String pwd = passwordField.getText();
		String location = companyLocation.getText();
		String contact = contactNumber.getText();

		JSONObject message = new JSONObject();
		try
		{
			message.put("MessageType", "companyregister");
			message.put("CompanyName", name);
			message.put("ID", userID);
			message.put("password", pwd);
			message.put("Location", location);
			message.put("Contact number", contact);
		}
		catch (JSONException e1)
		{
			e1.printStackTrace();
		}
		// Send Register form to server
		// sendMessage(message);
	}

	protected void passwdkeyTypedhandler(KeyEvent e)
	{
		String pw1 = new String(passwordField.getText());
		String pw2 = new String(retypePasswordField.getText());

		if (pw1.equals(pw2) == true)
		{
			Validchecker.setForeground(Color.GREEN);
			Validchecker.setText("Valid Password!");
			setPasswordvalidation(true);
			Validchecker.setVisible(true);
		}
		else
		{
			Validchecker.setForeground(Color.RED);
			Validchecker.setText("Invalid Password!");
			setPasswordvalidation(false);
			Validchecker.setVisible(true);
		}

		if (pw2.equals("") == true)
		{
			Validchecker.setVisible(false);
		}
	}

	public boolean isPasswordvalidation()
	{
		return passwordvalidation;
	}

	public void setPasswordvalidation(boolean passwordvalidation)
	{
		this.passwordvalidation = passwordvalidation;
	}
}