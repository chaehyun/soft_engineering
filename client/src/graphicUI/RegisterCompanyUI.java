package graphicUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

import elements.RegisterCompany;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class RegisterCompanyUI extends JFrame
{
	private JPanel contentPane;
	private JTextField companyName;
	private JTextField companyId;
	private JPasswordField passwordField;
	private JPasswordField retypePasswordField;
	private JTextField contactNumber;
	private JTextField Validchecker;
	private JTextArea LocationArea;
	private RegisterCompany reg;
	private final String INVALID_ID = "Check your Id Validation!";
	private final String INVALID_PASSWORD = "Check your Password Validation!";
	private boolean passwordvalidation = false;
	private boolean idValidation = false;
	private final String REGISTRATION_SUCCESS = "The registation is completed.";
	private final String REGISTRATION_FAILED = "The registration is failed, ID is taken.";
	private final String ID_AVAILABLE = "Your ID is available! Use it!";
	private final String ID_DISAVAILABLE = "Your ID is already using. Please try different ID";

	public RegisterCompanyUI()
	{
		setTitle("Company Registration");
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 415, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblWelcomeNewCompany = new JLabel("Register a New Company");
		lblWelcomeNewCompany.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeNewCompany.setBounds(100, 15, 200, 15);
		contentPane.add(lblWelcomeNewCompany);

		JLabel lblName = new JLabel("Company Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(20, 35, 110, 25);
		contentPane.add(lblName);

		companyName = new JTextField();
		companyName.setBounds(140, 35, 130, 25);
		contentPane.add(companyName);
		companyName.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(20, 70, 110, 25);
		contentPane.add(lblId);

		companyId = new JTextField();
		companyId.setBounds(140, 70, 130, 25);
		contentPane.add(companyId);
		companyId.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(20, 105, 110, 25);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField("", 20);
		passwordField.setBounds(140, 105, 130, 25);
		contentPane.add(passwordField);

		JLabel lblRetypePassword = new JLabel("Retype Password");
		lblRetypePassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRetypePassword.setBounds(20, 140, 110, 25);
		contentPane.add(lblRetypePassword);

		retypePasswordField = new JPasswordField("", 20);
		retypePasswordField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				passwdkeyTypedhandler();
			}
		});

		retypePasswordField.setBounds(140, 140, 130, 25);
		contentPane.add(retypePasswordField);

		JLabel lblAddress = new JLabel("Location");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setBounds(20, 175, 110, 25);
		contentPane.add(lblAddress);

		JLabel lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setBounds(20, 285, 110, 25);
		contentPane.add(lblContactNumber);

		contactNumber = new JTextField();
		contactNumber.setBounds(140, 285, 250, 25);
		contentPane.add(contactNumber);
		contactNumber.setColumns(10);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registerCompany();
			}
		});
		
		btnRegister.setBounds(180, 390, 100, 30);
		contentPane.add(btnRegister);

		Validchecker = new JTextField();
		Validchecker.setForeground(Color.RED);
		Validchecker.setText("Invaild Password!");
		Validchecker.setVisible(false);
		Validchecker.setEditable(false);
		Validchecker.setBorder(null);
		Validchecker.setBackground(SystemColor.window);
		Validchecker.setBounds(280, 140, 120, 30);
		contentPane.add(Validchecker);
		Validchecker.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnCancel.setBounds(280, 390, 100, 30);
		contentPane.add(btnCancel);

		JButton btnIdCheck = new JButton("ID Check");
		btnIdCheck.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				idCheck();
			}
		});
		btnIdCheck.setBounds(280, 70, 100, 25);
		contentPane.add(btnIdCheck);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 175, 250, 100);
		contentPane.add(scrollPane);
		
		LocationArea = new JTextArea();
		scrollPane.setViewportView(LocationArea);
	}

	// Register Button event (keyboard, mouse)
	public void registerCompany()
	{
		// Read from UI Textform
		String companyname = companyName.getText();
		String id = companyId.getText();
		String password = passwordField.getText();
		String location = LocationArea.getText();
		String contactnumber = contactNumber.getText();
		reg = new RegisterCompany();
		
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
			// Making a JSONObject and passing to the RegisterCompany
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
				reg.setCompanyInfo(message);

				boolean valid = reg.registerCompany();
				if (valid == true)
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
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void idCheck()
	{
		// Get the current ID from companyID text form
		String tmp_id = companyId.getText();
		boolean valid = (new RegisterCompany()).isIdValidate(tmp_id);

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

	// This method will check current Password and Retype Password
	// This method will run every new key event driven
	public void passwdkeyTypedhandler()
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