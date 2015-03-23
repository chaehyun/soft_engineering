package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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

import communication.Communicator;

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
	private final String INVALID_ID = "Check your Id Validation!";
	private final String INVALID_PASSWORD = "Check your Password Validation!";
	private boolean passwordvalidation = false;
	private boolean idValidation = false;
	private final String REGISTRATION_SUCCESS = "The registation is completed.";
	private final String REGISTRATION_FAILED = "The registration is failed, ID is taken.";
	private final String ID_AVAILABLE = "Your ID is available! Use it!";
	private final String ID_DISAVAILABLE = "Your ID is already using. Please try different ID";
	private final String SERVER_OUT = "Server does not work";

	/**
	 * Create the frame.
	 */
	public register_company()
	{
		setTitle("Company Registration");
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 415, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblWelcomeNewCompany = new JLabel(
				"\u2605 Welcome New Company \u2605");
		lblWelcomeNewCompany.setFont(new Font("ĺŤ ěŹ™ě�™ĺŤ ěŹ™ě�™ĺŤ ě‹śďż˝",
				Font.BOLD, 12));
		lblWelcomeNewCompany.setBounds(97, 10, 198, 15);
		contentPane.add(lblWelcomeNewCompany);

		JLabel lblName = new JLabel("Company Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(30, 35, 110, 25);
		contentPane.add(lblName);

		companyName = new JTextField();
		companyName.setBounds(150, 35, 120, 25);
		contentPane.add(companyName);
		companyName.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(30, 70, 110, 25);
		contentPane.add(lblId);

		companyId = new JTextField();
		companyId.setBounds(150, 70, 120, 25);
		contentPane.add(companyId);
		companyId.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(30, 105, 110, 25);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField("", 20);
		passwordField.setBounds(150, 105, 120, 25);
		contentPane.add(passwordField);

		JLabel lblRetypePassword = new JLabel("Retype Password");
		lblRetypePassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRetypePassword.setBounds(30, 140, 110, 25);
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

		retypePasswordField.setBounds(150, 140, 120, 25);
		contentPane.add(retypePasswordField);

		JLabel lblAddress = new JLabel("Company Location");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(30, 175, 110, 25);
		contentPane.add(lblAddress);

		companyLocation = new JTextField();
		companyLocation.setBounds(150, 175, 120, 100);
		contentPane.add(companyLocation);
		companyLocation.setColumns(10);

		JLabel lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContactNumber.setBounds(30, 285, 110, 25);
		contentPane.add(lblContactNumber);

		contactNumber = new JTextField();
		contactNumber.setBounds(150, 285, 120, 25);
		contentPane.add(contactNumber);
		contactNumber.setColumns(10);

		JButton btnRegister = new JButton("Register");
		btnRegister.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				registerButtonPressed(e);
			}
		});
		btnRegister.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				registerButtonClicked(e);
			}
		});
		btnRegister.setBounds(180, 390, 100, 30);
		contentPane.add(btnRegister);

		Validchecker = new JTextField();
		Validchecker.setForeground(Color.RED);
		Validchecker.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Validchecker.setText("Invaild Password!");
		Validchecker.setVisible(false);
		Validchecker.setEditable(false);
		Validchecker.setBorder(null);
		Validchecker.setBackground(SystemColor.window);
		Validchecker.setBounds(280, 140, 120, 30);
		contentPane.add(Validchecker);
		Validchecker.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				setVisible(false);
			}
		});
		btnCancel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				setVisible(false);
			}
		});
		btnCancel.setBounds(280, 390, 100, 30);
		contentPane.add(btnCancel);

		JButton btnIdCheck = new JButton("ID Check");
		btnIdCheck.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				idCheckClicked(arg0);
			}
		});
		btnIdCheck.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				idCheckPressed(e);
			}
		});
		btnIdCheck.setBounds(280, 70, 100, 25);
		contentPane.add(btnIdCheck);
	}

	// Register Button event (keyboard, mouse)
	protected void registerButtonClicked(MouseEvent e)
	{
		// isIdValidation() method will check current new ID from server
		boolean idValidate = isIdValidation();
		// isPasswordvalidation() method will return boolean type value
		// true means validate and false means invalidate
		// this method based on password form and retype password form
		boolean pwValidate = isPasswordvalidation();

		// Print Error message
		if (idValidate == false)
		{
			JOptionPane.showMessageDialog(new JFrame(), INVALID_ID);
		}

		if (pwValidate == false)
		{
			JOptionPane.showMessageDialog(new JFrame(), INVALID_PASSWORD);
		}

		if (pwValidate == true && idValidate == true)
		{
			// Read from UI Textform
			String companyname = companyName.getText();
			String id = companyId.getText();
			String password = passwordField.getText();
			String location = companyLocation.getText();
			String contactnumber = contactNumber.getText();

			// Making a JSONObject and pass to the server
			JSONObject message = new JSONObject();
			try
			{
				message.put("MessageType", "companyregister");
				message.put("CompanyName", companyname);
				message.put("ID", id);
				message.put("Password", password);
				message.put("Location", location);
				message.put("Contact number", contactnumber);

				// Server will return the results. (boolean type)
				JSONObject response = Communicator.sendMessage(message);

				boolean valid = response.getBoolean("valid");
				if (valid)
				{
					// Registration UI will disappeared
					JOptionPane.showMessageDialog(new JFrame(),
							REGISTRATION_SUCCESS);
					setVisible(false);
				}
				else
				{
					// Print Error message
					JOptionPane.showMessageDialog(new JFrame(),
							REGISTRATION_FAILED);
				}
			}
			catch (JSONException | IOException e1)
			{
				JOptionPane.showMessageDialog(new JFrame(), SERVER_OUT);
				e1.printStackTrace();
			}
		}
	}

	protected void registerButtonPressed(KeyEvent e)
	{
		registerButtonClicked(null);
	}

	protected void idCheckPressed(KeyEvent e)
	{
		idCheckClicked(null);
	}

	protected void idCheckClicked(MouseEvent arg0)
	{
		// Get the current ID from companyID text form
		String tmp_id = companyId.getText();
		JSONObject message = new JSONObject();

		// Server will check current ID Validation
		try
		{
			message.put("MessageType", "companyidValidation");
			message.put("ID", tmp_id);

			JSONObject response = Communicator.sendMessage(message);

			boolean valid = response.getBoolean("valid");
			if (valid)
			{
				setIdValidation(true);
				JOptionPane.showMessageDialog(new JFrame(), ID_AVAILABLE);
			}
			else
			{
				setIdValidation(false);
				JOptionPane.showMessageDialog(new JFrame(), ID_DISAVAILABLE);
			}

		}
		catch (JSONException | IOException e1)
		{
			JOptionPane.showMessageDialog(new JFrame(), SERVER_OUT);
			e1.printStackTrace();
		}
	}

	// This method will check current Password and Retype Password
	// This method will run every new key event driven
	protected void passwdkeyTypedhandler(KeyEvent e)
	{
		String pw1 = new String(passwordField.getText());
		String pw2 = new String(retypePasswordField.getText());

		if (pw1.equals(pw2) == true)
		{
			Validchecker.setForeground(Color.BLUE);
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

	public boolean isIdValidation()
	{
		return idValidation;
	}

	public void setIdValidation(boolean idValidation)
	{
		this.idValidation = idValidation;
	}
}