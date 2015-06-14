package graphicUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import elements.Login;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainUI extends JFrame {

    private JPanel contentPane;
    private JTextField idField;
    private JLabel idLabel;
    private JPasswordField passwordField;
    private final String NOT_SELECT_OPTION = "Please Select Student or Company Option.";
    private final String INVALID_INPUT = "Please Enter the User ID and Password or Select User Type.";
    private final String LOGIN_SUCCESS = "Login Successful!";
    private final String SERVER_OUT = "Server does not work";
    private final String VERSION_UPDATE = "There is a Newst Version. Please Update your Program.";
    private final String DUPLICATE_LOGIN = "Your ID is already connected into Server";
    private final String WRONG_PASSWORD = "Your ID or Passsword is not correct.";
    private boolean usertype;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField statusField;

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    MainUI frame = new MainUI();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public MainUI() {
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
	loginButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		login();
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
	registerButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		register();
	    }
	});

	registerButton.setBounds(200, 200, 100, 30);
	contentPane.add(registerButton);

	passwordField = new JPasswordField("", 20);
	passwordField.setBounds(160, 80, 150, 30);
	contentPane.add(passwordField);

	JRadioButton student_select = new JRadioButton("Student");
	student_select.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		student_select.setSelected(true);
		setUsertype(true);
	    }
	});
	student_select.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		setUsertype(true);
	    }
	});

	student_select.setFont(new Font("Tahoma", Font.BOLD, 14));
	buttonGroup.add(student_select);
	student_select.setBounds(190, 120, 110, 35);
	contentPane.add(student_select);

	JRadioButton company_select = new JRadioButton("Company");
	company_select.setSelected(true);
	company_select.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		company_select.setSelected(true);
		setUsertype(false);
	    }
	});
	company_select.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		setUsertype(false);
	    }
	});
	company_select.setFont(new Font("Tahoma", Font.BOLD, 14));
	buttonGroup.add(company_select);
	company_select.setBounds(70, 120, 110, 35);
	contentPane.add(company_select);

	statusField = new JTextField();
	statusField.setEditable(false);
	statusField.setBackground(SystemColor.window);
	statusField.setBounds(0, 264, 370, 28);
	contentPane.add(statusField);
	statusField.setColumns(10);
	statusField.setText("Ready");
    }

    // Register Button Handler
    public void register() {
	boolean userType = isUsertype();

	if (userType == true) {
	    (new RegisterStudentUI()).setVisible(true);
	} else if (userType == false) {
	    (new RegisterCompanyUI()).setVisible(true);
	} else {
	    JOptionPane.showMessageDialog(new JFrame(), NOT_SELECT_OPTION);
	}
    }

    // login Button Event handler
    public void login() {
	String userID = idField.getText();
	String pwd = passwordField.getText();
	String userType;

	if (idField.equals("") == false && pwd.equals("") == false) {
	    int loginResult;

	    if (isUsertype() == true) {
		// userType true means Student
		userType = "student";

		// Start Student Login Request
		Login login = new Login(userID, pwd, userType);
		statusField.setText("Login Request...");
		loginResult = login.loginRequest();

		/*
		 * Return Value Description 0 : no Error, Login Request was
		 * succeeded 1 : Version Invalid 3 : Server Out 4 : Server
		 * Denied with duplicated Login Request 5 : Server Denied with
		 * Wrong Password
		 */
		statusField.setText("Login...");
		switch (loginResult) {
		case 0:
		    JOptionPane.showMessageDialog(new JFrame(), LOGIN_SUCCESS);
		    setVisible(false);
		    (new MainStudentUI(userID)).setVisible(true);
		    break;
		case 1:
		    JOptionPane.showMessageDialog(new JFrame(), VERSION_UPDATE);
		    break;
		case 3:
		    JOptionPane.showMessageDialog(new JFrame(), SERVER_OUT);
		    break;
		case 4:
		    JOptionPane
			    .showMessageDialog(new JFrame(), DUPLICATE_LOGIN);
		    break;
		case 5:
		    JOptionPane.showMessageDialog(new JFrame(), WRONG_PASSWORD);
		    break;
		default:
		    ;
		    break;
		}
	    } else {
		// userType false means Company
		userType = "company";

		// Start Student Login Request
		Login login = new Login(userID, pwd, userType);
		statusField.setText("Login Request...");
		loginResult = login.loginRequest();

		/*
		 * Return Value Description 0 : no Error, Login Request was
		 * succeeded 1 : Version Invalid 3 : Server Out 4 : Server
		 * Denied with duplicated Login Request 5 : Server Denied with
		 * Wrong Password
		 */
		statusField.setText("Login...");
		switch (loginResult) {
		case 0:
		    JOptionPane.showMessageDialog(new JFrame(), LOGIN_SUCCESS);
		    setVisible(false);
		    (new MainCompanyUI(userID)).setVisible(true);
		    break;
		case 1:
		    JOptionPane.showMessageDialog(new JFrame(), VERSION_UPDATE);
		    break;
		case 3:
		    JOptionPane.showMessageDialog(new JFrame(), SERVER_OUT);
		    break;
		case 4:
		    JOptionPane
			    .showMessageDialog(new JFrame(), DUPLICATE_LOGIN);
		    break;
		case 5:
		    JOptionPane.showMessageDialog(new JFrame(), WRONG_PASSWORD);
		    break;
		default:
		    ;
		    break;
		}
	    }
	} else {
	    JOptionPane.showMessageDialog(new JFrame(), INVALID_INPUT);
	}
    }

    public boolean isUsertype() {
	return usertype;
    }

    public void setUsertype(boolean usertype) {
	this.usertype = usertype;
    }
}
