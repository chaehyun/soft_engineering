package ui;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class request_detail extends JFrame
{

	private JPanel contentPane;
	private JTextField positionField;
	private JTextField paymentField;

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
					request_detail frame = new request_detail();
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
	public request_detail()
	{
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 500);
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
		lblsdkfj.setBounds(53, 58, 57, 15);
		contentPane.add(lblsdkfj);

		positionField = new JTextField();
		positionField.setBounds(119, 55, 116, 21);
		contentPane.add(positionField);
		positionField.setColumns(10);

		JLabel lbl = new JLabel("Start date");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setBounds(53, 83, 64, 15);
		contentPane.add(lbl);

		JLabel lblPassword = new JLabel("End date");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(53, 110, 57, 15);
		contentPane.add(lblPassword);

		JLabel lblRetypePassword = new JLabel("Payment");
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRetypePassword.setBounds(53, 135, 57, 15);
		contentPane.add(lblRetypePassword);

		JLabel lblAddress = new JLabel("Number of Students");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(53, 159, 109, 15);
		contentPane.add(lblAddress);

		JComboBox req_student = new JComboBox();
		req_student.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4" }));
		req_student.setBounds(205, 156, 40, 21);
		contentPane.add(req_student);

		JLabel lblGpa = new JLabel("Due date");
		lblGpa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGpa.setBounds(53, 187, 50, 15);
		contentPane.add(lblGpa);

		JComboBox start_year = new JComboBox();
		start_year.setModel(new DefaultComboBoxModel(new String[] { "2014",
				"2015", "2016", "2017" }));
		start_year.setBounds(119, 80, 57, 21);
		contentPane.add(start_year);

		JComboBox start_month = new JComboBox();
		start_month.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		start_month.setBounds(205, 80, 40, 21);
		contentPane.add(start_month);

		JComboBox end_year = new JComboBox();
		end_year.setModel(new DefaultComboBoxModel(new String[] { "2014",
				"2015", "2016", "2017", "2018", "2019" }));
		end_year.setBounds(119, 107, 57, 21);
		contentPane.add(end_year);

		JComboBox end_month = new JComboBox();
		end_month.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		end_month.setBounds(205, 107, 39, 21);
		contentPane.add(end_month);

		JComboBox start_day = new JComboBox();
		start_day.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "27", "28", "29", "30" }));
		start_day.setBounds(271, 80, 40, 21);
		contentPane.add(start_day);

		JComboBox end_day = new JComboBox();
		end_day.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3",
				"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "27", "28", "29", "30" }));
		end_day.setBounds(271, 107, 39, 21);
		contentPane.add(end_day);

		JLabel lblSus = new JLabel("Y");
		lblSus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSus.setBounds(178, 83, 24, 15);
		contentPane.add(lblSus);

		JLabel lblY = new JLabel("Y");
		lblY.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblY.setBounds(178, 110, 24, 15);
		contentPane.add(lblY);

		JLabel lblM = new JLabel("M");
		lblM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblM.setBounds(246, 83, 24, 15);
		contentPane.add(lblM);

		JLabel lblM_1 = new JLabel("M");
		lblM_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblM_1.setBounds(246, 110, 24, 15);
		contentPane.add(lblM_1);

		JLabel lblD = new JLabel("D");
		lblD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD.setBounds(316, 83, 24, 15);
		contentPane.add(lblD);

		JLabel lblD_1 = new JLabel("D");
		lblD_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD_1.setBounds(316, 110, 24, 15);
		contentPane.add(lblD_1);

		JLabel lblKrw = new JLabel("KRW");
		lblKrw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKrw.setBounds(246, 135, 41, 15);
		contentPane.add(lblKrw);

		JComboBox due_year = new JComboBox();
		due_year.setModel(new DefaultComboBoxModel(new String[] { "2014",
				"2015", "2016", "2017", "2018", "2019" }));
		due_year.setBounds(119, 184, 57, 21);
		contentPane.add(due_year);

		JLabel label = new JLabel("Y");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(178, 187, 24, 15);
		contentPane.add(label);

		JComboBox due_month = new JComboBox();
		due_month.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		due_month.setBounds(205, 184, 39, 21);
		contentPane.add(due_month);

		JLabel label_1 = new JLabel("M");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(246, 187, 24, 15);
		contentPane.add(label_1);

		JComboBox due_day = new JComboBox();
		due_day.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3",
				"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "27", "28", "29", "30" }));
		due_day.setBounds(271, 184, 39, 21);
		contentPane.add(due_day);

		JLabel label_2 = new JLabel("D");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(316, 187, 24, 15);
		contentPane.add(label_2);

		JLabel lblP = new JLabel("P");
		lblP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblP.setBounds(246, 159, 24, 15);
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
		paymentField.setColumns(10);
		paymentField.setBounds(119, 131, 116, 21);
		contentPane.add(paymentField);

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
				String numofstudent = (String) req_student.getSelectedItem();
				String dueDate = (String) due_year.getSelectedItem() + "-"
						+ (String) due_month.getSelectedItem() + "-"
						+ (String) due_day.getSelectedItem();
				String techskills = "";
				String nontechskills = "";
		
				if (chkboxjava.isSelected() == true)
					techskills += "Java, ";
				if (chkapache.isSelected() == true)
					techskills += "Apache Server, ";
				if (chkOradeSql.isSelected() == true)
					techskills += "Oracle SQL, ";
				if (chkOopDesign.isSelected() == true)
					techskills += "OOP Design";
				if (chckbxTeamSpirit.isSelected() == true)
					nontechskills += "Team Spirit, ";
				if (chckbxQuickLearning.isSelected() == true)
					nontechskills += "Quick Learning, ";
				if (chckbxDrivingLicence.isSelected() == true)
					nontechskills += "Driving Licence";
		
				JSONObject message = new JSONObject();
				try
				{
					message.put("MessageType", "newrequest");
					// TODO
					message.put("ID", "samsung");
					message.put("Position", position);
					message.put("StartDate", startDate);
					message.put("EndDate", endDate);
					message.put("Payment", payment);
					message.put("NumberOfStudents",
							Integer.parseInt(numofstudent));
					message.put("DueDate", dueDate);
					// TODO
					message.put("Grade", 3);
					message.put("TechnicalSkills", techskills);
					message.put("NonTechSkills", nontechskills);
		
					JSONObject response = Communicator.sendMessage(message);
		
					System.out.println(response.toString());
				}
				catch (JSONException | IOException e1)
				{
					e1.printStackTrace();
				}
		
				// Send Register form to server
				// sendMessage(message);
			}
		});
		btnRegister.setBounds(253, 412, 97, 29);
		contentPane.add(btnRegister);
	}
}
