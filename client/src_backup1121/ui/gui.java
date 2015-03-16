package ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
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


public class gui extends JFrame
{

	private JPanel contentPane;
	private JTextField idField;
	private JLabel idLabel;
	private JPasswordField passwordField;
	private final String NOT_SELECT_OPTION = "Please Select Student or Company Option.";
	private final String INVALID_INPUT = "Please Enter the User ID and Password or Select User Type.";
	private final String LOGIN_SUCCESS = "Login Successful!";
	private final String LOGIN_FAIL = "Login Failed";
	private int buttonstate;
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
					gui frame = new gui();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gui()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		setButtonstate(2);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);

		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				loginButtonClicked(e);
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		loginButton.setBounds(250, 127, 97, 30);
		contentPane.add(loginButton);

		idField = new JTextField();
		idField.setBounds(183, 48, 116, 21);
		contentPane.add(idField);
		idField.setColumns(10);

		idLabel = new JLabel("ID");
		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		idLabel.setBounds(141, 50, 22, 15);
		contentPane.add(idLabel);

		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLabel.setBounds(66, 81, 97, 15);
		contentPane.add(passwordLabel);

		JButton registerButton = new JButton("Register");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registerButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				btnRegisterMouseClicked(arg0);
			}
		});

		registerButton.setBounds(250, 169, 97, 30);
		contentPane.add(registerButton);

		passwordField = new JPasswordField("", 20);
		passwordField.setBounds(183, 79, 116, 21);
		contentPane.add(passwordField);

		JRadioButton student_select = new JRadioButton("Student");
		student_select.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				rdbtnNewRadioButtonMouseClicked(arg0);
			}
		});

		student_select.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonGroup.add(student_select);
		student_select.setBounds(141, 125, 74, 34);
		contentPane.add(student_select);

		JRadioButton company_select = new JRadioButton("Company");
		company_select.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				rdbtnNewRadioButton_1MouseClicked(arg0);
			}
		});
		company_select.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonGroup.add(company_select);
		company_select.setBounds(48, 130, 81, 23);
		contentPane.add(company_select);
	}

	protected void loginButtonClicked(MouseEvent e)
	{
		String userID = idField.getText();
		String pwd = passwordField.getText();
		
		if ( idField.equals("") == false && pwd.equals("") == false && getButtonstate() != 2 )
		{
			JSONObject message = new JSONObject();
			
			try
			{
				message.put("MessageType", "login");
			
				message.put("ID", userID);
				message.put("pwd", pwd);
				if ( getButtonstate() == 0 ) // company
					message.put("usertype", "company");
				if ( getButtonstate() == 1 ) // student
					message.put("usertype", "student");
				
				JSONObject response = Communicator.sendMessage(message);
				
				// wait server 3 seconds
				Thread.sleep(3000);
				
				String result = response.getString("Loginresult");
				
				if ( Boolean.parseBoolean(result) == true && getButtonstate() == 0 )
				{
					JOptionPane.showMessageDialog(new JFrame(), LOGIN_SUCCESS);
					setVisible(false);
					(new maincompany()).setVisible(true);
				}
				else if ( Boolean.parseBoolean(result) == true && getButtonstate() == 1 )
				{
					JOptionPane.showMessageDialog(new JFrame(), LOGIN_SUCCESS);
					setVisible(false);
					(new mainstudent()).setVisible(true);;
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), LOGIN_FAIL);
				}
			}
			catch (JSONException | IOException | InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Send Register form to server
			// sendMessage(message);
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), INVALID_INPUT);
		}
	}

	protected void btnRegisterMouseClicked(MouseEvent arg0)
	{
		if (getButtonstate() == 0) // getButtonstate return to 0 means company  selected
		{
			setVisible(false);
			new register_company();
		}
		else if (getButtonstate() == 1) // else getButtonstate return to 1 means student selected
		{
			setVisible(false);
			new register_student();
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), NOT_SELECT_OPTION);
		}
	}

	protected void rdbtnNewRadioButton_1MouseClicked(MouseEvent arg0)
	{
		setButtonstate(0);
	}

	protected void rdbtnNewRadioButtonMouseClicked(MouseEvent arg0)
	{
		setButtonstate(1);
	}

	public int getButtonstate()
	{
		return buttonstate;
	}

	public void setButtonstate(int buttonstate)
	{
		this.buttonstate = buttonstate;
	}
}
