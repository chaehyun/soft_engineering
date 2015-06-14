package graphicUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import elements.SecurityManager;

public class AskPasswordUI extends JFrame {

    private JPanel contentPane;
    private JPasswordField passwordField;
    private String userID;
    private String userType;
    private int trialCount = 0;
    private final String ACCESS_DENIED = "You already tried to access 3 times. Access denied.";
    private final String VALID_PASSWORD = "Your Password is correct. Access permitted.";
    private final String INVALID_PASSWORD = "Your Password is incorrect.";

    public AskPasswordUI(String userID, String userType) {
    	setUserID(userID);
    	setUserType(userType);
	setTitle("Caution!");
	setVisible(false);
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setBounds(100, 100, 310, 274);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel labelExplain = new JLabel("For security, Enter your password again.");
	labelExplain.setBounds(30, 50, 280, 20);
	contentPane.add(labelExplain);
	
	passwordField = new JPasswordField();
	passwordField.setBounds(30, 130, 250, 30);
	contentPane.add(passwordField);
	
	JLabel labelChance = new JLabel("If trial count 3, access will be denied.");
	labelChance.setBounds(30, 80, 250, 20);
	contentPane.add(labelChance);
	
	JLabel labelPassword = new JLabel("Password:");
	labelPassword.setBounds(30, 110, 250, 20);
	contentPane.add(labelPassword);
	
	JLabel labelTrialCount = new JLabel("Trial count:");
	labelTrialCount.setHorizontalAlignment(SwingConstants.RIGHT);
	labelTrialCount.setBounds(130, 160, 100, 20);
	contentPane.add(labelTrialCount);
	
	JLabel labelTrial = new JLabel("");
	labelTrial.setHorizontalAlignment(SwingConstants.LEFT);
	labelTrial.setBounds(240, 160, 50, 20);
	contentPane.add(labelTrial);
	labelTrial.setText(getTrialCountString());
	
	JButton btnSubmit = new JButton("Submit");
	btnSubmit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (isValidCount() == true) {
			String password = passwordField.getText();
			boolean result;
			result = (new SecurityManager()).isPasswordValidate(getUserID(), password, getUserType());
			if (result == true) {
			    JOptionPane.showMessageDialog(new JFrame(), VALID_PASSWORD);
			}
			else {
			    JOptionPane.showMessageDialog(new JFrame(), INVALID_PASSWORD);
			    setTrialCount();
			    labelTrial.setText(getTrialCountString());
			}
		    }
		    else {
			// Access Deny
			JOptionPane.showMessageDialog(new JFrame(), ACCESS_DENIED);
			setVisible(false);
		    }
		}
	});
	btnSubmit.setBounds(160, 200, 120, 30);
	contentPane.add(btnSubmit);
    }

    public int getTrialCount() {
	return trialCount;
    }
    
    public String getTrialCountString() {
	String cnt;
	
	cnt = Integer.toString(getTrialCount());
	
	return cnt;
    }

    public void setTrialCount() {
	this.trialCount++;
    }
    
    public boolean isValidCount() {
	boolean result = false;
	
	if (getTrialCount() >= 3) {
	    result = false;
	}
	else {
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

    public String getUserType() {
	return userType;
    }

    public void setUserType(String userType) {
	this.userType = userType;
    }
}
