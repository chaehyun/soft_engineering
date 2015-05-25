package graphicUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;
import elements.VersionControl;

public class MainUI extends JFrame
{

	private JPanel contentPane;
	private JTextField idField;
	private JLabel idLabel;
	private JPasswordField passwordField;
	private final String NOT_SELECT_OPTION = "Please Select Student or Company Option.";
	private final String INVALID_INPUT = "Please Enter the User ID and Password or Select User Type.";
	private final String LOGIN_SUCCESS = "Login Successful!";
	private final String LOGIN_FAIL = "Login Failed";
	private final String SERVER_OUT = "Server does not work";
	private final String VERSION_UPDATE = "There is a Newst Version. Please Update your Program.";
	private final String VERSION_NOUPDATE = "You are now using the newsest version.";
	private boolean usertype;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainUI frame = new MainUI();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public MainUI()
	{
		setResizable(false);
		setTitle("Knowledge Based Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);

		JButton loginButton = new JButton("Login");
		loginButton.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				loginButtonPressed(e);
			}
		});
		loginButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				loginButtonClicked(e);
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginButton.setBounds(200, 160, 100, 30);
		contentPane.add(loginButton);

		idField = new JTextField();
		idField.setBounds(160, 40, 150, 30);
		contentPane.add(idField);
		idField.setColumns(10);

		idLabel = new JLabel("USER ID");
		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		idLabel.setBounds(30, 40, 100, 30);
		contentPane.add(idLabel);

		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLabel.setBounds(30, 80, 100, 30);
		contentPane.add(passwordLabel);

		JButton registerButton = new JButton("Register");
		registerButton.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				btnRegisterPressed(e);
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		registerButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				btnRegisterMouseClicked(arg0);
			}
		});

		registerButton.setBounds(200, 200, 100, 30);
		contentPane.add(registerButton);

		passwordField = new JPasswordField("", 20);
		passwordField.setBounds(160, 80, 150, 30);
		contentPane.add(passwordField);

		JRadioButton student_select = new JRadioButton("Student");
		student_select.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				student_select.setSelected(true);
				setUsertype(true);
			}
		});
		student_select.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				setUsertype(true);
			}
		});

		student_select.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(student_select);
		student_select.setBounds(190, 120, 110, 35);
		contentPane.add(student_select);

		JRadioButton company_select = new JRadioButton("Company");
		company_select.setSelected(true);
		company_select.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				company_select.setSelected(true);
				setUsertype(false);
			}
		});
		company_select.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				setUsertype(false);
			}
		});
		company_select.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(company_select);
		company_select.setBounds(70, 120, 110, 35);
		contentPane.add(company_select);
	}

	// Register Button Handler
	protected void btnRegisterMouseClicked(MouseEvent arg0)
	{
		boolean userType = isUsertype();

		if (userType == true)
		{
			(new RegisterStudentUI()).setVisible(true);
		}
		else if (userType == false)
		{
			(new RegisterCompanyUI()).setVisible(true);
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), NOT_SELECT_OPTION);
		}
	}

	protected void btnRegisterPressed(KeyEvent e)
	{
		btnRegisterMouseClicked(null);
	}

	// login Button Event handler
	protected void loginButtonPressed(KeyEvent e)
	{
		String userID = idField.getText();
		String pwd = passwordField.getText();
		boolean userType = isUsertype();
		
		VersionControl current = new VersionControl();
		String currentVersion = current.getCurrentVersion();

		if (idField.equals("") == false && pwd.equals("") == false)
		{
			JSONObject message = new JSONObject();
			JSONObject versionMessage = new JSONObject();

			try
			{
				// JSON Object put
				// JSONObject message
				// {
				// "MessageType", "keyvalue or variable",
				// ...
				// }
				
				
				versionMessage.put("MessageType", "VersionCheck");
				versionMessage.put("ClientVersion", currentVersion);
				
				JSONObject versionCheck = Communicator.sendMessage(versionMessage);
				
				boolean resultVersionCheck = versionCheck.getBoolean("valid");
				
				//there is no update.
				if(resultVersionCheck == true)
				{
					//do nothing. or show "You are now using the Newest Version."
					JOptionPane.showMessageDialog(new JFrame(), VERSION_NOUPDATE);
				}
				//there is a newest version. need to update.
				else
				{
					//Pop up to let the users know there is an updated version.
					JOptionPane.showMessageDialog(new JFrame(), VERSION_UPDATE);
				}
				
				
				// Start Login Process	
				message.put("MessageType", "login");
				message.put("ID", userID);
				message.put("pwd", pwd);
				
				if (userType == false)
				{
					message.put("usertype", "company");
				}
				else
				{
					message.put("usertype", "student");
				}

				JSONObject response = Communicator.sendMessage(message);

				boolean result = response.getBoolean("valid");

				// userType false means Company
				if (result == true && userType == false)
				{
					JOptionPane.showMessageDialog(new JFrame(), LOGIN_SUCCESS);
					setVisible(false);
					(new MainCompanyUI(userID)).setVisible(true);
				}
				// userType true means Student
				else if (result == true && userType == true)
				{
					JOptionPane.showMessageDialog(new JFrame(), LOGIN_SUCCESS);
					setVisible(false);
					(new MainStudentUI(userID)).setVisible(true);
				}
				// Login Process Failed
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), LOGIN_FAIL);
				}
			}
			catch (JSONException | IOException e1)
			{
				JOptionPane.showMessageDialog(new JFrame(), SERVER_OUT);
				e1.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), INVALID_INPUT);
		}
	}

	// login Button Click Event handler
	protected void loginButtonClicked(MouseEvent e)
	{
		loginButtonPressed(null);
	}

	public boolean isUsertype()
	{
		return usertype;
	}

	public void setUsertype(boolean usertype)
	{
		this.usertype = usertype;
	}
}
