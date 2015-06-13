package graphicUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import skills.NonTechSkills;
import skills.TechSkills;
import communication.Communicator;

public class RequestDetailUI extends JFrame
{

	private JPanel contentPane;
	private JTextField positionField;
	private JTextField paymentField;
	private JTextField req_student;

	private String userID;

	/**
	 * Create the frame.
	 */
	public RequestDetailUI(String UserID)
	{
		userID = UserID;
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 414, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblWelcomeNewCompany = new JLabel("\u2605 New Request \u2605");
		lblWelcomeNewCompany.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWelcomeNewCompany.setBounds(119, 10, 135, 15);
		contentPane.add(lblWelcomeNewCompany);

		JLabel lblsdkfj = new JLabel("Position");
		lblsdkfj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblsdkfj.setBounds(46, 59, 57, 15);
		contentPane.add(lblsdkfj);

		positionField = new JTextField();
		positionField.setBounds(119, 55, 242, 21);
		contentPane.add(positionField);
		positionField.setColumns(10);

		JLabel lbl = new JLabel("Start date");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setBounds(46, 85, 64, 15);
		contentPane.add(lbl);

		JLabel lblPassword = new JLabel("End date");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(46, 110, 57, 15);
		contentPane.add(lblPassword);

		JLabel lblRetypePassword = new JLabel("Payment");
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRetypePassword.setBounds(46, 137, 57, 15);
		contentPane.add(lblRetypePassword);

		JLabel lblAddress = new JLabel("Number of Students");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(46, 162, 116, 15);
		contentPane.add(lblAddress);

		JLabel lblGpa = new JLabel("Due date");
		lblGpa.setHorizontalAlignment(SwingConstants.LEFT);
		lblGpa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGpa.setBounds(46, 189, 57, 15);
		contentPane.add(lblGpa);

		JComboBox start_year = new JComboBox();
		start_year.setFont(new Font("Tahoma", Font.PLAIN, 11));
		start_year.setModel(new DefaultComboBoxModel(new String[] { "2014",
				"2015", "2016", "2017" }));
		start_year.setBounds(119, 84, 74, 23);
		contentPane.add(start_year);

		JComboBox start_month = new JComboBox();
		start_month.setFont(new Font("Tahoma", Font.PLAIN, 11));
		start_month.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		start_month.setBounds(205, 84, 65, 23);
		contentPane.add(start_month);

		JComboBox end_year = new JComboBox();
		end_year.setFont(new Font("Tahoma", Font.PLAIN, 11));
		end_year.setModel(new DefaultComboBoxModel(new String[] { "2014",
				"2015", "2016", "2017", "2018", "2019" }));
		end_year.setBounds(119, 108, 74, 23);
		contentPane.add(end_year);

		JComboBox end_month = new JComboBox();
		end_month.setFont(new Font("Tahoma", Font.PLAIN, 11));
		end_month.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		end_month.setBounds(205, 108, 65, 23);
		contentPane.add(end_month);

		JComboBox start_day = new JComboBox();
		start_day.setFont(new Font("Tahoma", Font.PLAIN, 11));
		start_day.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "27", "28", "29", "30" }));
		start_day.setBounds(282, 85, 64, 23);
		contentPane.add(start_day);

		JComboBox end_day = new JComboBox();
		end_day.setFont(new Font("Tahoma", Font.PLAIN, 11));
		end_day.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3",
				"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "27", "28", "29", "30" }));
		end_day.setBounds(282, 108, 64, 23);
		contentPane.add(end_day);

		JLabel lblSus = new JLabel("Y");
		lblSus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSus.setBounds(192, 88, 24, 15);
		contentPane.add(lblSus);

		JLabel lblY = new JLabel("Y");
		lblY.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblY.setBounds(192, 110, 24, 15);
		contentPane.add(lblY);

		JLabel lblM = new JLabel("M");
		lblM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblM.setBounds(271, 85, 24, 15);
		contentPane.add(lblM);

		JLabel lblM_1 = new JLabel("M");
		lblM_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblM_1.setBounds(271, 110, 24, 15);
		contentPane.add(lblM_1);

		JLabel lblD = new JLabel("D");
		lblD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD.setBounds(349, 85, 24, 15);
		contentPane.add(lblD);

		JLabel lblD_1 = new JLabel("D");
		lblD_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD_1.setBounds(349, 110, 24, 15);
		contentPane.add(lblD_1);

		JLabel lblKrw = new JLabel("KRW");
		lblKrw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKrw.setBounds(235, 137, 41, 15);
		contentPane.add(lblKrw);

		JComboBox due_year = new JComboBox();
		due_year.setFont(new Font("Tahoma", Font.PLAIN, 11));
		due_year.setModel(new DefaultComboBoxModel(new String[] { "2014",
				"2015", "2016", "2017", "2018", "2019" }));
		due_year.setBounds(119, 184, 74, 23);
		contentPane.add(due_year);

		JLabel label = new JLabel("Y");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(192, 186, 24, 15);
		contentPane.add(label);

		JComboBox due_month = new JComboBox();
		due_month.setFont(new Font("Tahoma", Font.PLAIN, 11));
		due_month.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		due_month.setBounds(205, 184, 65, 23);
		contentPane.add(due_month);

		JLabel label_1 = new JLabel("M");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(271, 186, 24, 15);
		contentPane.add(label_1);

		JComboBox due_day = new JComboBox();
		due_day.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3",
				"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "27", "28", "29", "30" }));
		due_day.setBounds(281, 183, 69, 23);
		contentPane.add(due_day);

		JLabel label_2 = new JLabel("D");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(349, 186, 24, 15);
		contentPane.add(label_2);

		JLabel lblP = new JLabel("P");
		lblP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblP.setBounds(245, 162, 24, 15);
		contentPane.add(lblP);

		JLabel lblTechnicalSkill = new JLabel("Technical skills :");
		lblTechnicalSkill.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTechnicalSkill.setBounds(53, 278, 97, 15);
		contentPane.add(lblTechnicalSkill);

		JLabel lblNontechnicalSkills = new JLabel("Non-technical skills :");
		lblNontechnicalSkills.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNontechnicalSkills.setBounds(220, 278, 120, 15);
		contentPane.add(lblNontechnicalSkills);

		JCheckBox chkboxjava = new JCheckBox("Java");
		chkboxjava.setBounds(53, 313, 115, 23);
		contentPane.add(chkboxjava);

		JCheckBox chkapache = new JCheckBox("Apache Server");
		chkapache.setBounds(53, 338, 149, 23);
		contentPane.add(chkapache);

		JCheckBox chkOradeSql = new JCheckBox("Oracle SQL");
		chkOradeSql.setBounds(53, 363, 115, 23);
		contentPane.add(chkOradeSql);

		JCheckBox chkOopDesign = new JCheckBox("OOP Design");
		chkOopDesign.setBounds(53, 388, 115, 23);
		contentPane.add(chkOopDesign);

		JCheckBox chckbxTeamSpirit = new JCheckBox("Team spirit");
		chckbxTeamSpirit.setBounds(220, 313, 115, 23);
		contentPane.add(chckbxTeamSpirit);

		JCheckBox chckbxQuickLearning = new JCheckBox("Quick learning");
		chckbxQuickLearning.setBounds(220, 338, 149, 23);
		contentPane.add(chckbxQuickLearning);

		JCheckBox chckbxDrivingLicence = new JCheckBox("Driving licence");
		chckbxDrivingLicence.setBounds(220, 363, 149, 23);
		contentPane.add(chckbxDrivingLicence);

		paymentField = new JTextField();
		paymentField.setHorizontalAlignment(SwingConstants.RIGHT);
		paymentField.setColumns(10);
		paymentField.setBounds(119, 135, 116, 21);
		contentPane.add(paymentField);

		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGrade.setBounds(46, 214, 61, 16);
		contentPane.add(lblGrade);

		JComboBox gradecomboBox = new JComboBox();
		gradecomboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		gradecomboBox.setModel(new DefaultComboBoxModel(new String[] { "1",
				"2", "3", "4" }));
		gradecomboBox.setBounds(119, 211, 57, 27);
		contentPane.add(gradecomboBox);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setBounds(53, 427, 80, 16);
		contentPane.add(lblDescription);

		JTextPane descriptionPane = new JTextPane();
		descriptionPane.setBackground(Color.WHITE);
		descriptionPane.setBounds(63, 455, 289, 134);
		contentPane.add(descriptionPane);

		JButton btnRegister = new JButton("Send");
		btnRegister.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String position = positionField.getText();
				String startDate = (String) start_year.getSelectedItem() + "-"
						+ (String) start_month.getSelectedItem() + "-"
						+ (String) start_day.getSelectedItem();
				String endDate = (String) end_year.getSelectedItem() + "-"
						+ (String) end_month.getSelectedItem() + "-"
						+ (String) end_day.getSelectedItem();
				String payment = paymentField.getText();
				String numofstudent = (String) req_student.getText();
				String dueDate = (String) due_year.getSelectedItem() + "-"
						+ (String) due_month.getSelectedItem() + "-"
						+ (String) due_day.getSelectedItem();
				JSONArray techskills = new JSONArray();
				JSONArray nontechskills = new JSONArray();
				String grade = (String) gradecomboBox.getSelectedItem();
				String description = descriptionPane.getText();
				try
				{
					JSONObject message = new JSONObject();
					if (chkboxjava.isSelected() == true)
					{
						System.out.println("Java");
						message.append("TechSkills", TechSkills.JAVA.name());
					}
					if (chkapache.isSelected() == true)
						message.append("TechSkills", TechSkills.APACHE.name());
					if (chkOradeSql.isSelected() == true)
						message.append("TechSkills", TechSkills.ORACLE.name());
					if (chkOopDesign.isSelected() == true)
						message.append("TechSkills", TechSkills.OOP.name());

					if (chckbxTeamSpirit.isSelected() == true)
						message.append("NonTechSkills",
								NonTechSkills.TEAMWORK.name());
					if (chckbxQuickLearning.isSelected() == true)
						message.append("NonTechSkills",
								NonTechSkills.QUICKLEARNING.name());
					if (chckbxDrivingLicence.isSelected() == true)
						message.append("NonTechSkills",
								NonTechSkills.DRIVINGLICENCE.name());

					message.put("MessageType", "newrequest");
					message.put("ID", userID);
					message.put("Position", position);
					message.put("StartDate", startDate);
					message.put("EndDate", endDate);
					message.put("Payment", payment);
					message.put("NumberOfStudents",
							Integer.parseInt(numofstudent));
					message.put("DueDate", dueDate);
					message.put("Grade", Integer.parseInt(grade));
					message.put("Description", description);

					JSONObject response = Communicator.sendMessage(message);

					boolean error = response.getBoolean("error");

					if (error)
						JOptionPane.showMessageDialog(new JFrame(),
								"The request have not sent!");
					else
						JOptionPane.showMessageDialog(new JFrame(),
								"The request have sent!");

					setVisible(false);
				}
				catch (JSONException | IOException e1)
				{
					e1.printStackTrace();
				}
				// Send Register form to server
				// sendMessage(message);
			}
		});
		btnRegister.setBounds(272, 609, 97, 29);
		contentPane.add(btnRegister);

		req_student = new JTextField();
		req_student.setHorizontalAlignment(SwingConstants.RIGHT);
		req_student.setBounds(162, 158, 74, 21);
		contentPane.add(req_student);
		req_student.setColumns(10);

	}
}
