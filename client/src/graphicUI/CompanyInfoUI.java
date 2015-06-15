package graphicUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import elements.CompanyInfo;

public class CompanyInfoUI extends JFrame {

    private JPanel contentPane;
    private JTextField idField;
    private JTextField companyNameField;
    private JTextField contactNumberField;
    private JTextArea locationField;
    private CompanyInfo company;
    private String userID;
    private final String MODIFY_SUCCESS = "Modify succeeded.";
    private final String MODIFY_FAIL = "Modify Failed.";
    private final String FIELD_ERROR = "Please fill all of editable field";

    public CompanyInfoUI(String userID) {
    	company = new CompanyInfo();
    	setUserID(userID);
    	company.getInfo(getUserID());

    	setTitle("My Information");
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setBounds(100, 100, 450, 380);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel labelInformation = new JLabel("Your Information here, you can modify each editable Field");
	labelInformation.setHorizontalAlignment(SwingConstants.CENTER);
	labelInformation.setBounds(20, 20, 400, 20);
	contentPane.add(labelInformation);
	
	JLabel labelID = new JLabel("ID");
	labelID.setHorizontalAlignment(SwingConstants.RIGHT);
	labelID.setBounds(20, 60, 120, 20);
	contentPane.add(labelID);
	
	JLabel labelName = new JLabel("CompanyName");
	labelName.setHorizontalAlignment(SwingConstants.RIGHT);
	labelName.setBounds(20, 100, 120, 20);
	contentPane.add(labelName);
	
	JLabel labelContactNumber = new JLabel("ContactNumber");
	labelContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
	labelContactNumber.setBounds(20, 140, 120, 20);
	contentPane.add(labelContactNumber);
	
	JLabel labelLocation = new JLabel("Location");
	labelLocation.setHorizontalAlignment(SwingConstants.RIGHT);
	labelLocation.setBounds(20, 180, 120, 20);
	contentPane.add(labelLocation);
	
	idField = new JTextField();
	idField.setEditable(false);
	idField.setBounds(175, 60, 140, 20);
	contentPane.add(idField);
	idField.setColumns(10);
	
	companyNameField = new JTextField();
	companyNameField.setBounds(175, 100, 140, 20);
	contentPane.add(companyNameField);
	companyNameField.setColumns(10);
	
	contactNumberField = new JTextField();
	contactNumberField.setBounds(175, 140, 140, 20);
	contentPane.add(contactNumberField);
	contactNumberField.setColumns(10);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(175, 180, 140, 80);
	contentPane.add(scrollPane);
	
	locationField = new JTextArea();
	scrollPane.setViewportView(locationField);
	
	JButton btnSubmit = new JButton("Submit");
	btnSubmit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (fieldTypeChecker()) {
			boolean result = company.setInfo(companyNameField.getText(), contactNumberField.getText(), locationField.getText());
			    if (result) {
				JOptionPane.showMessageDialog(new JFrame(), MODIFY_SUCCESS);
				setVisible(false);
			    } else {
				JOptionPane.showMessageDialog(new JFrame(), MODIFY_FAIL);
			    }
		    } else {
			JOptionPane.showMessageDialog(new JFrame(), FIELD_ERROR);
		    }
		    
		}
	});
	btnSubmit.setBounds(140, 300, 120, 30);
	contentPane.add(btnSubmit);
	
	JButton btnCancel = new JButton("Cancel");
	btnCancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    setVisible(false);
		}
	});
	btnCancel.setBounds(280, 300, 120, 30);
	contentPane.add(btnCancel);
	
	// Display information
	idField.setText(company.getUserId());
	companyNameField.setText(company.getName());
	contactNumberField.setText(company.getContactNumber());
	locationField.setText(company.getLocation());
    }

    public boolean fieldTypeChecker() {
	boolean result = false;
	
	if (companyNameField.getText().equals("")) {
	    result = false;
	} else if (contactNumberField.getText().equals("")) {
	    result = false;
	} else if (locationField.getText().equals("")) {
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
