package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.JSONException;
import org.json.JSONObject;

public class register_student extends JFrame
{

	private JPanel contentPane;
	private JTextField studentName;
	private JTextField studentID;
	private JPasswordField passwordField;
	private JPasswordField retypePasswordField;
	private JTextField contactNumber;
	private JTextField gpaField;
	private final String INVALID_PASSWORD = "Check your Password Validation!";
	private boolean passwordvalidation = false;
	private JTextField Validchecker;
	private String studentgrade;
	private boolean studentSex;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public register_student()
	{
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblWelcomeNewCompany = new JLabel(
				"\u2605 Welcome New Student \u2605");
		lblWelcomeNewCompany.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWelcomeNewCompany.setBounds(97, 10, 198, 15);
		contentPane.add(lblWelcomeNewCompany);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(74, 39, 39, 15);
		contentPane.add(lblName);

		studentName = new JTextField();
		studentName.setBounds(132, 37, 116, 21);
		contentPane.add(studentName);
		studentName.setColumns(10);

		JLabel lblId = new JLabel("Student ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(49, 70, 64, 15);
		contentPane.add(lblId);

		studentID = new JTextField();
		studentID.setBounds(132, 66, 116, 21);
		contentPane.add(studentID);
		studentID.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(56, 97, 57, 15);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(132, 93, 116, 21);
		contentPane.add(passwordField);

		JLabel lblRetypePassword = new JLabel("Retype password");
		lblRetypePassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRetypePassword.setBounds(7, 130, 106, 15);
		contentPane.add(lblRetypePassword);

		retypePasswordField = new JPasswordField();
		retypePasswordField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				checkPasswordValidation(e);
			}
		});
		retypePasswordField.setBounds(132, 126, 116, 21);
		contentPane.add(retypePasswordField);

		JLabel lblAddress = new JLabel("Grade");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(74, 162, 39, 15);
		contentPane.add(lblAddress);

		JLabel lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContactNumber.setBounds(15, 222, 98, 15);
		contentPane.add(lblContactNumber);

		contactNumber = new JTextField();
		contactNumber.setBounds(132, 218, 116, 21);
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
		btnRegister.setBounds(250, 355, 97, 28);
		contentPane.add(btnRegister);

		JComboBox gradeBox = new JComboBox();
		gradeBox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				setStudentgrade((String) gradeBox.getSelectedItem());
			}
		});
		gradeBox.setMaximumRowCount(4);
		gradeBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4" }));
		gradeBox.setSelectedIndex(-1);
		gradeBox.setBounds(132, 159, 48, 23);
		contentPane.add(gradeBox);

		JLabel lblGpa = new JLabel("GPA");
		lblGpa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGpa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGpa.setBounds(78, 189, 35, 15);
		contentPane.add(lblGpa);

		gpaField = new JTextField();
		gpaField.setBounds(132, 185, 116, 21);
		contentPane.add(gpaField);
		gpaField.setColumns(10);

		JLabel lblSex = new JLabel("SEX");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSex.setBounds(89, 254, 24, 15);
		contentPane.add(lblSex);

		JRadioButton rdbtnFemale = new JRadioButton("Female\r\n");
		rdbtnFemale.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				setStudentSex(false);
			}
		});
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(132, 249, 84, 23);
		contentPane.add(rdbtnFemale);

		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				setStudentSex(true);
			}
		});
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(214, 249, 64, 23);
		contentPane.add(rdbtnMale);

		Validchecker = new JTextField();
		Validchecker.setBackground(SystemColor.window);
		Validchecker.setHorizontalAlignment(SwingConstants.LEFT);
		Validchecker.setText("Invalid Password!");
		Validchecker.setFont(new Font("Tahoma", Font.PLAIN, 9));
		Validchecker.setEditable(false);
		Validchecker.setBounds(260, 123, 94, 28);
		Validchecker.setBorder(null);
		Validchecker.setVisible(false);
		Validchecker.setForeground(Color.RED);
		contentPane.add(Validchecker);
		Validchecker.setColumns(10);
	}

	protected void checkpasswordvalidation(MouseEvent e)
	{
		if (isPasswordvalidation() == false)
		{
			JOptionPane.showMessageDialog(new JFrame(), INVALID_PASSWORD);
		}
		String name = studentName.getText();
		String userID = studentID.getText();
		String pwd = passwordField.getText();
		String grade = getStudentgrade();
		String gpa = gpaField.getText();
		String contact = contactNumber.getText();
		boolean sex = getStudentSex();

		JSONObject message = new JSONObject();
		try
		{
			message.put("MessageType", "studentregister");
			message.put("StudentName", name);
			message.put("ID", userID);
			message.put("Password", pwd);
			message.put("Grade", grade);
			message.put("Gpa", gpa);
			message.put("Contact number", contact);
			message.put("Male", sex);
		}
		catch (JSONException e1)
		{
			e1.printStackTrace();
		}

		// Send Register form to server
		// sendMessage(message);
	}

	protected void checkPasswordValidation(KeyEvent e)
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

	public String getStudentgrade()
	{
		return studentgrade;
	}

	public void setStudentgrade(String studentgrade)
	{
		this.studentgrade = studentgrade;
	}

	public boolean getStudentSex()
	{
		return studentSex;
	}

	public void setStudentSex(boolean studentSex)
	{
		this.studentSex = studentSex;
	}
}
