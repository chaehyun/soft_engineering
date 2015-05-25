// This code is almost same: register_company.java
package graphicUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import skills.NonTechSkills;
import skills.TechSkills;

import communication.Communicator;

public class RegisterStudentUI extends JFrame
{

	private JPanel contentPane;
	private JTextField studentName;
	private JTextField studentID;
	private JPasswordField passwordField;
	private JPasswordField retypePasswordField;
	private JTextField contactNumber;
	private JTextField gpaField;
	private JComboBox gradeBox = new JComboBox();
	private JCheckBox chkboxjava = new JCheckBox("Java");
	private JCheckBox chkapache = new JCheckBox("Apache Server");
	private JCheckBox chkOradeSql = new JCheckBox("Oracle SQL");
	private JCheckBox chkOopDesign = new JCheckBox("OOP Design");
	private JCheckBox chckbxTeamSpirit = new JCheckBox("Team spirit");
	private JCheckBox chckbxQuickLearning = new JCheckBox("Quick learning");
	private JCheckBox chckbxDrivingLicence = new JCheckBox("Driving licence");
	private final String INVALID_ID = "Check your Id Validation!";
	private final String INVALID_PASSWORD = "Check your Password Validation!";
	private boolean idValidation = false;
	private boolean passwordvalidation = false;
	private JTextField Validchecker;
	private String studentgrade;
	private boolean studentSex = true;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final String ID_AVAILABLE = "Your ID is available! Use it!";
	private final String ID_DISAVAILABLE = "Your ID is already using. Please try different ID";
	private final String REGISTRATION_SUCCESS = "The registration is completed.";
	private final String REGISTRATION_FAILED = "The registration is failed, ID is taken.";
	private final String SERVER_OUT = "Server does not work";
	private final String EMPTY_FIELD = "Please fill in the checkBox.";
	private JTextField ageField;

	public RegisterStudentUI()
	{
		setResizable(false);
		setTitle("Student Registration");
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 415, 540);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblWelcomeNewCompany = new JLabel(
				"\u2605 Welcome New Student \u2605");
		lblWelcomeNewCompany.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeNewCompany.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWelcomeNewCompany.setBounds(97, 10, 198, 15);
		contentPane.add(lblWelcomeNewCompany);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(30, 35, 110, 25);
		contentPane.add(lblName);

		studentName = new JTextField();
		studentName.setBounds(150, 35, 140, 25);
		contentPane.add(studentName);
		studentName.setColumns(10);

		JLabel lblId = new JLabel("Student ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(30, 70, 110, 25);
		contentPane.add(lblId);

		studentID = new JTextField();
		studentID.setBounds(150, 70, 140, 25);
		contentPane.add(studentID);
		studentID.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(30, 105, 110, 25);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(150, 105, 140, 25);
		contentPane.add(passwordField);

		JLabel lblRetypePassword = new JLabel("Retype Password");
		lblRetypePassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRetypePassword.setBounds(30, 140, 110, 25);
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
		retypePasswordField.setBounds(150, 140, 140, 25);
		contentPane.add(retypePasswordField);

		JLabel lblAddress = new JLabel("Grade");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(30, 175, 110, 25);
		contentPane.add(lblAddress);

		JLabel lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContactNumber.setBounds(30, 245, 110, 25);
		contentPane.add(lblContactNumber);

		contactNumber = new JTextField();
		contactNumber.setBounds(150, 245, 140, 25);
		contentPane.add(contactNumber);
		contactNumber.setColumns(10);

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
		gradeBox.setBounds(150, 175, 140, 25);
		contentPane.add(gradeBox);

		JLabel lblGpa = new JLabel("GPA");
		lblGpa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGpa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGpa.setBounds(30, 210, 110, 25);
		contentPane.add(lblGpa);

		gpaField = new JTextField();
		gpaField.setBounds(150, 210, 50, 25);
		contentPane.add(gpaField);
		gpaField.setColumns(10);

		JLabel lblSex = new JLabel("SEX");
		lblSex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSex.setBounds(30, 280, 110, 25);
		contentPane.add(lblSex);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnFemale.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				rdbtnFemale.setSelected(true);
				setStudentSex(false);
			}
		});
		rdbtnFemale.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				setStudentSex(false);
			}
		});
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(220, 280, 80, 25);
		contentPane.add(rdbtnFemale);

		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnMale.setSelected(true);
		rdbtnMale.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				rdbtnMale.setSelected(true);
				setStudentSex(true);
			}
		});
		rdbtnMale.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				setStudentSex(true);
			}
		});
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(150, 280, 64, 23);
		contentPane.add(rdbtnMale);

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
		btnRegister.setBounds(180, 460, 100, 30);
		contentPane.add(btnRegister);

		Validchecker = new JTextField();
		Validchecker.setBackground(SystemColor.window);
		Validchecker.setHorizontalAlignment(SwingConstants.LEFT);
		Validchecker.setText("Invalid Password!");
		Validchecker.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Validchecker.setEditable(false);
		Validchecker.setBounds(300, 140, 110, 25);
		Validchecker.setBorder(null);
		Validchecker.setVisible(false);
		Validchecker.setForeground(Color.RED);
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
		btnCancel.setBounds(280, 460, 100, 30);
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
		btnIdCheck.setBounds(300, 70, 100, 25);
		contentPane.add(btnIdCheck);

		chkboxjava.setBounds(80, 345, 130, 25);
		contentPane.add(chkboxjava);

		chkapache.setBounds(80, 370, 130, 25);
		contentPane.add(chkapache);

		chkOradeSql.setBounds(80, 395, 130, 25);
		contentPane.add(chkOradeSql);

		chkOopDesign.setBounds(80, 420, 115, 25);
		contentPane.add(chkOopDesign);

		chckbxTeamSpirit.setBounds(220, 345, 130, 25);
		contentPane.add(chckbxTeamSpirit);

		chckbxQuickLearning.setBounds(220, 370, 130, 25);
		contentPane.add(chckbxQuickLearning);

		chckbxDrivingLicence.setBounds(220, 395, 130, 25);
		contentPane.add(chckbxDrivingLicence);

		JLabel lblTechSkills = new JLabel("Tech Skills");
		lblTechSkills.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTechSkills.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTechSkills.setBounds(30, 315, 110, 25);
		contentPane.add(lblTechSkills);

		JLabel lblNewLabel = new JLabel("Non-Tech Skills");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(220, 315, 110, 25);
		contentPane.add(lblNewLabel);

		JLabel lblAge = new JLabel("Age");
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAge.setBounds(200, 210, 30, 25);
		contentPane.add(lblAge);

		ageField = new JTextField();
		ageField.setBounds(240, 210, 50, 25);
		contentPane.add(ageField);
		ageField.setColumns(10);
	}

	protected void registerButtonClicked(MouseEvent e)
	{
		registerButtonPressed(null);
	}

	protected void registerButtonPressed(KeyEvent e)
	{
		boolean idValidate = isIdValidation();
		boolean pwValidate = isPasswordvalidation();
		int chkTechSkill = 0;
		int chkNontechSkill = 0;

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
			String name = studentName.getText();
			String studentid = studentID.getText();
			String studentpwd = passwordField.getText();
			String contactnumber = contactNumber.getText();
			String gpafield = (String) gpaField.getText();
			String grade = ((String) gradeBox.getSelectedItem());
			String sex;
			int age = Integer.parseInt(ageField.getText());

			if (getStudentSex() == true)
				sex = "male";
			else
				sex = "female";

			JSONObject message = new JSONObject();
			try
			{
				message.put("MessageType", "studentregister");
				message.put("StudentName", name);
				message.put("ID", studentid);
				message.put("Password", studentpwd);
				message.put("Grade", Integer.parseInt(grade));
				message.put("Gpa", Float.parseFloat(gpafield));
				message.put("Age", age);
				message.put("Contact number", contactnumber);
				message.put("Sex", sex);

				if (chkboxjava.isSelected() == true)
				{
					message.append("TechSkills", TechSkills.JAVA.name());
					chkTechSkill++;
				}
				if (chkapache.isSelected() == true)
				{
					message.append("TechSkills", TechSkills.APACHE.name());
					chkTechSkill++;
				}
				if (chkOradeSql.isSelected() == true)
				{
					message.append("TechSkills", TechSkills.ORACLE.name());
					chkTechSkill++;
				}
				if (chkOopDesign.isSelected() == true)
				{
					message.append("TechSkills", TechSkills.OOP.name());
					chkTechSkill++;
				}

				if (chckbxTeamSpirit.isSelected() == true)
				{
					message.append("NonTechSkills",
							NonTechSkills.TEAMWORK.name());
					chkNontechSkill++;
				}
				if (chckbxQuickLearning.isSelected() == true)
				{
					message.append("NonTechSkills",
							NonTechSkills.QUICKLEARNING.name());
					chkNontechSkill++;
				}
				if (chckbxDrivingLicence.isSelected() == true)
				{
					message.append("NonTechSkills",
							NonTechSkills.DRIVINGLICENCE.name());
					chkNontechSkill++;
				}
				
				if (chkTechSkill == 0 || chkNontechSkill == 0)
				{
					JOptionPane.showMessageDialog(new JFrame(),
							EMPTY_FIELD);
				}

				JSONObject response = Communicator.sendMessage(message);

				boolean valid = response.getBoolean("valid");
				if (valid)
				{
					JOptionPane.showMessageDialog(new JFrame(),
							REGISTRATION_SUCCESS);
					setVisible(false);
				}
				else
				{
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

	protected void idCheckPressed(KeyEvent e)
	{
		idCheckClicked(null);
	}

	protected void idCheckClicked(MouseEvent arg0)
	{
		String tmp_id = studentID.getText();
		JSONObject message = new JSONObject();

		try
		{
			message.put("MessageType", "studentidValidation");
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

	protected void checkPasswordValidation(KeyEvent e)
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

	public boolean isIdValidation()
	{
		return idValidation;
	}

	public void setIdValidation(boolean idValidation)
	{
		this.idValidation = idValidation;
	}
}