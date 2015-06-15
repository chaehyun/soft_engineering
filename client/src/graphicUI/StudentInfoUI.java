package graphicUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import skills.NonTechSkills;
import skills.TechSkills;
import elements.StudentInfo;

public class StudentInfoUI extends JFrame {

    private JPanel contentPane;
    private JTextField idField;
    private JTextField studentNameField;
    private JTextField contactNumberField;
    private StudentInfo student;
    private String userID;
    private final String MODIFY_SUCCESS = "Modify succeeded.";
    private final String MODIFY_FAIL = "Modify Failed.";
    private final String FIELD_ERROR = "Please fill all of editable field";
    private JTextField sexField;
    private JTextField gradeField;
    private JTextField gpaField;
    private JTextField ageField;

    public StudentInfoUI(String userID) {
	student = new StudentInfo();
	setUserID(userID);
	student.getInfo(getUserID());

	setTitle("My Information");
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setBounds(100, 100, 450, 475);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JLabel labelInformation = new JLabel(
		"Your Information here, you can modify each editable Field");
	labelInformation.setHorizontalAlignment(SwingConstants.CENTER);
	labelInformation.setBounds(20, 20, 400, 20);
	contentPane.add(labelInformation);

	JLabel labelID = new JLabel("ID");
	labelID.setHorizontalAlignment(SwingConstants.RIGHT);
	labelID.setBounds(20, 60, 120, 20);
	contentPane.add(labelID);

	JLabel labelName = new JLabel("Name");
	labelName.setHorizontalAlignment(SwingConstants.RIGHT);
	labelName.setBounds(20, 90, 120, 20);
	contentPane.add(labelName);

	JLabel labelContactNumber = new JLabel("ContactNumber");
	labelContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
	labelContactNumber.setBounds(20, 120, 120, 20);
	contentPane.add(labelContactNumber);

	JLabel labelSex = new JLabel("Sex");
	labelSex.setHorizontalAlignment(SwingConstants.RIGHT);
	labelSex.setBounds(20, 150, 120, 20);
	contentPane.add(labelSex);

	idField = new JTextField();
	idField.setEditable(false);
	idField.setBounds(175, 60, 140, 20);
	contentPane.add(idField);
	idField.setColumns(10);

	studentNameField = new JTextField();
	studentNameField.setBounds(175, 90, 140, 20);
	contentPane.add(studentNameField);
	studentNameField.setColumns(10);

	contactNumberField = new JTextField();
	contactNumberField.setBounds(175, 120, 140, 20);
	contentPane.add(contactNumberField);
	contactNumberField.setColumns(10);

	sexField = new JTextField();
	sexField.setEditable(false);
	sexField.setBounds(175, 150, 140, 20);
	contentPane.add(sexField);
	sexField.setColumns(10);

	JLabel labelGrade = new JLabel("Grade");
	labelGrade.setHorizontalAlignment(SwingConstants.RIGHT);
	labelGrade.setBounds(20, 180, 120, 20);
	contentPane.add(labelGrade);

	JLabel labelGpa = new JLabel("GPA");
	labelGpa.setHorizontalAlignment(SwingConstants.RIGHT);
	labelGpa.setBounds(20, 210, 120, 20);
	contentPane.add(labelGpa);

	JLabel labelAge = new JLabel("Age");
	labelAge.setHorizontalAlignment(SwingConstants.RIGHT);
	labelAge.setBounds(20, 240, 120, 20);
	contentPane.add(labelAge);

	JLabel labelTechSkills = new JLabel("TechSkills");
	labelTechSkills.setHorizontalAlignment(SwingConstants.RIGHT);
	labelTechSkills.setBounds(20, 270, 120, 20);
	contentPane.add(labelTechSkills);

	JLabel labelNonTechSkills = new JLabel("NonTechSkills");
	labelNonTechSkills.setHorizontalAlignment(SwingConstants.RIGHT);
	labelNonTechSkills.setBounds(20, 330, 120, 20);
	contentPane.add(labelNonTechSkills);

	gradeField = new JTextField();
	gradeField.setEditable(false);
	gradeField.setBounds(175, 180, 140, 20);
	contentPane.add(gradeField);
	gradeField.setColumns(10);

	gpaField = new JTextField();
	gpaField.setBounds(175, 210, 140, 20);
	contentPane.add(gpaField);
	gpaField.setColumns(10);

	ageField = new JTextField();
	ageField.setEditable(false);
	ageField.setBounds(175, 240, 140, 20);
	contentPane.add(ageField);
	ageField.setColumns(10);

	JCheckBox javaBox = new JCheckBox("Java");
	javaBox.setBounds(175, 270, 120, 20);
	contentPane.add(javaBox);

	JCheckBox apacheBox = new JCheckBox("Apache Server");
	apacheBox.setBounds(290, 270, 140, 20);
	contentPane.add(apacheBox);

	JCheckBox sqlBox = new JCheckBox("Oracle SQL");
	sqlBox.setBounds(175, 300, 120, 20);
	contentPane.add(sqlBox);

	JCheckBox oopBox = new JCheckBox("OOP Design");
	oopBox.setBounds(290, 300, 140, 20);
	contentPane.add(oopBox);

	JCheckBox teamspiritBox = new JCheckBox("Team Spirit");
	teamspiritBox.setBounds(175, 330, 120, 20);
	contentPane.add(teamspiritBox);

	JCheckBox quicklearningBox = new JCheckBox("Quick Learning");
	quicklearningBox.setBounds(290, 330, 140, 20);
	contentPane.add(quicklearningBox);

	JCheckBox drivinglicenceBox = new JCheckBox("Driving Licence");
	drivinglicenceBox.setBounds(175, 360, 140, 20);
	contentPane.add(drivinglicenceBox);

	JButton btnSubmit = new JButton("Submit");
	btnSubmit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		ArrayList<TechSkills> tmptech = new ArrayList<TechSkills>();
		ArrayList<NonTechSkills> tmpnontech = new ArrayList<NonTechSkills>();
		int techcnt = 0;
		int nontechcnt = 0;

		// TechSkills checker
		if (javaBox.isSelected()) {
		    tmptech.add(TechSkills.JAVA);
		    techcnt++;
		}
		if (apacheBox.isSelected()) {
		    tmptech.add(TechSkills.APACHE);
		    techcnt++;
		}
		if (sqlBox.isSelected()) {
		    tmptech.add(TechSkills.ORACLE);
		    techcnt++;
		}
		if (oopBox.isSelected()) {
		    tmptech.add(TechSkills.OOP);
		    techcnt++;
		}

		// NonTechSkills checker
		if (teamspiritBox.isSelected()) {
		    tmpnontech.add(NonTechSkills.TEAMWORK);
		    nontechcnt++;
		}
		if (quicklearningBox.isSelected()) {
		    tmpnontech.add(NonTechSkills.QUICKLEARNING);
		    nontechcnt++;
		}
		if (drivinglicenceBox.isSelected()) {
		    tmpnontech.add(NonTechSkills.DRIVINGLICENCE);
		    nontechcnt++;
		}
		if (fieldTypeChecker() && techcnt != 0 && nontechcnt != 0) {
		    boolean result = student.setInfo(
			    studentNameField.getText(),
			    contactNumberField.getText(), gpaField.getText(),
			    tmptech, tmpnontech);
		    if (result) {
			JOptionPane.showMessageDialog(new JFrame(),
				MODIFY_SUCCESS);
			setVisible(false);
		    } else {
			JOptionPane
				.showMessageDialog(new JFrame(), MODIFY_FAIL);
		    }
		} else {
		    JOptionPane.showMessageDialog(new JFrame(), FIELD_ERROR);
		}

	    }
	});
	btnSubmit.setBounds(160, 400, 120, 30);
	contentPane.add(btnSubmit);

	JButton btnCancel = new JButton("Cancel");
	btnCancel.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
	    }
	});
	btnCancel.setBounds(300, 400, 120, 30);
	contentPane.add(btnCancel);

	// Display information
	idField.setText(student.getUserId());
	studentNameField.setText(student.getName());
	contactNumberField.setText(student.getContactNumber());
	sexField.setText(student.getSex());
	gradeField.setText(Integer.toString(student.getGrade()));
	gpaField.setText(Float.toString(student.getGpa()));
	ageField.setText(Integer.toString(student.getAge()));
	for (TechSkills t : student.getTechSkills()) {
	    if (t.equals(TechSkills.JAVA)) {
		javaBox.setSelected(true);
	    } else if (t.equals(TechSkills.APACHE)) {
		apacheBox.setSelected(true);
	    } else if (t.equals(TechSkills.ORACLE)) {
		sqlBox.setSelected(true);
	    } else if (t.equals(TechSkills.OOP)) {
		oopBox.setSelected(true);
	    }
	}
	for (NonTechSkills t : student.getNonTechSkills()) {
	    if (t.equals(NonTechSkills.TEAMWORK)) {
		teamspiritBox.setSelected(true);
	    } else if (t.equals(NonTechSkills.QUICKLEARNING)) {
		quicklearningBox.setSelected(true);
	    } else if (t.equals(NonTechSkills.DRIVINGLICENCE)) {
		drivinglicenceBox.setSelected(true);
	    }
	}
    }

    public boolean fieldTypeChecker() {
	boolean result = false;

	if (studentNameField.getText().equals("")) {
	    result = false;
	} else if (contactNumberField.getText().equals("")) {
	    result = false;
	} else if (gpaField.getText().equals("")) {
	    result = false;
	} else {
	    result = true;
	}

	return result;
    }

    public String getUserID() {
	return userID;
    }

    public void setUserID(String userID) {
	this.userID = userID;
    }
}
