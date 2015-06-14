package graphicUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import skills.NonTechSkills;
import skills.TechSkills;
import elements.Company;
import elements.Result;
import elements.Student;

public class MainCompanyUI extends javax.swing.JFrame implements MouseListener {
    // Variables declaration - do not modify
    private javax.swing.JTextField statusField;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JMenu MenuFile;
    private javax.swing.JMenu MenuInformation;
    private javax.swing.JMenu MenuMessages;
    private javax.swing.JMenuItem SubMenuLogoff;
    private javax.swing.JMenuItem SubMenuMessageBox;
    private javax.swing.JMenuItem SubMenuMyinfo;
    private javax.swing.JMenuItem SubMenuProgramInfo;
    private javax.swing.JMenuItem SubMenuSendMessage;
    private javax.swing.JMenuItem SubMenuVersionInfo;
    private javax.swing.JMenuBar TopMenuBar;
    private javax.swing.JButton btnNewRequests;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JLabel labelMyRequests;
    private String userID;
    private ArrayList<Result> results;
    private Company company;
    private final String LOGOFF_SUCCESS = "Logoff Success";
    private final String LOGOFF_FAIL = "Logoff Fail";
    private boolean isMousePressed = false;

    // End of variables declaration

    public MainCompanyUI(String userID) {
	setUserID(userID);
	company = new Company(getUserID());
	initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
	MainPanel = new javax.swing.JPanel();
	jScrollPane1 = new javax.swing.JScrollPane();
	table = new javax.swing.JTable();
	labelMyRequests = new javax.swing.JLabel();
	btnNewRequests = new javax.swing.JButton();
	statusField = new javax.swing.JTextField();
	TopMenuBar = new javax.swing.JMenuBar();
	MenuFile = new javax.swing.JMenu();
	SubMenuMyinfo = new javax.swing.JMenuItem();
	SubMenuLogoff = new javax.swing.JMenuItem();
	MenuMessages = new javax.swing.JMenu();
	SubMenuSendMessage = new javax.swing.JMenuItem();
	SubMenuMessageBox = new javax.swing.JMenuItem();
	MenuInformation = new javax.swing.JMenu();
	SubMenuVersionInfo = new javax.swing.JMenuItem();
	SubMenuProgramInfo = new javax.swing.JMenuItem();

	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	setTitle("MainCompany");
	setBounds(new java.awt.Rectangle(5, 25, 695, 475));
	setResizable(false);

	MainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
	MainPanel.setBounds(new java.awt.Rectangle(690, 480, 690, 480));

	jScrollPane1
		.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

	table.setAutoCreateRowSorter(true);
	table.setModel(new javax.swing.table.DefaultTableModel(
		new Object[][] {}, new String[] { "Title", "Date", "Answered" }) {
	    boolean[] canEdit = new boolean[] { false, false, false };

	    public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	    }
	});
	table.setColumnSelectionAllowed(false);
	jScrollPane1.setViewportView(table);

	table.getColumnModel()
		.getSelectionModel()
		.setSelectionMode(
			javax.swing.ListSelectionModel.SINGLE_SELECTION);

	results = new ArrayList<>();
	try {
	    JSONArray resultsJSON = company.getResult();

	    if (resultsJSON != null) {
		for (int i = 0; i < resultsJSON.length(); i++) {
		    JSONObject resultElement = resultsJSON.getJSONObject(i);

		    String title = resultElement.getString("Title");
		    String startDate = resultElement.getString("Date");
		    boolean complete = resultElement.getBoolean("Complete");

		    ArrayList<Student> studentsList = new ArrayList<>();
		    if (complete) {
			JSONArray studentsArray = resultElement
				.getJSONArray("Students");
			for (int j = 0; j < studentsArray.length(); j++) {
			    JSONObject studentElement = studentsArray
				    .getJSONObject(i);

			    String studentName = studentElement
				    .getString("StudentName");
			    int grade = studentElement.getInt("Grade");
			    int gpa = studentElement.getInt("GPA");
			    String contactNumber = studentElement
				    .getString("ContactNumber");
			    String sex = studentElement.getString("Sex");
			    int age = studentElement.getInt("Age");

			    JSONArray techSkillsJSON = studentElement
				    .getJSONArray("TechSkills");
			    ArrayList<TechSkills> techSkills = new ArrayList<>();
			    for (int k = 0; k < techSkillsJSON.length(); k++)
				techSkills.add(TechSkills
					.valueOf(techSkillsJSON.getString(k)));

			    JSONArray nonTechSkillsJSON = studentElement
				    .getJSONArray("NonTechSkills");
			    ArrayList<NonTechSkills> nonTechSkills = new ArrayList<>();
			    for (int k = 0; k < nonTechSkillsJSON.length(); k++)
				nonTechSkills
					.add(NonTechSkills
						.valueOf(nonTechSkillsJSON
							.getString(k)));

			    studentsList.add(new Student(studentName, grade,
				    gpa, contactNumber, sex, age, techSkills,
				    nonTechSkills));
			}
		    }

		    Result newResult = new Result(title, startDate, complete,
			    studentsList);
		    results.add(newResult);

		    DefaultTableModel tableModel = (DefaultTableModel) table
			    .getModel();
		    tableModel.addRow(new Object[] { newResult.getTitle(),
			    newResult.getStartDate(), newResult.isComplete() });
		    table.addMouseListener(this);
		    table.getSelectionModel().addListSelectionListener(
			    new ListSelectionListener() {
				public void valueChanged(
					ListSelectionEvent event) {
				    // do some actions here, for example
				    // print first column value from selected
				    // row
				    if (isMousePressed) {
					System.out.println("...");
					Result result = results.get(table
						.getSelectedRow());
					if (result.isComplete())
					    new ResponseListUI(result)
						    .setVisible(true);
				    }
				}
			    });

		}
	    }

	} catch (JSONException e) {
	    e.printStackTrace();
	}

	labelMyRequests.setText("My Requests");

	btnNewRequests.setText("New Requests");
	btnNewRequests.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		btnNewRequestsActionPerformed(evt);
	    }
	});

	javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(
		MainPanel);
	MainPanel.setLayout(MainPanelLayout);
	MainPanelLayout
		.setHorizontalGroup(MainPanelLayout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				MainPanelLayout
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						MainPanelLayout
							.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
								MainPanelLayout
									.createSequentialGroup()
									.addGap(0,
										0,
										0)
									.addGroup(
										MainPanelLayout
											.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
											.addComponent(
												labelMyRequests)
											.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
									.addGap(0,
										0,
										Short.MAX_VALUE))
							.addComponent(
								btnNewRequests,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
					.addContainerGap()));
	MainPanelLayout
		.setVerticalGroup(MainPanelLayout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				MainPanelLayout
					.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelMyRequests)
					.addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(
						jScrollPane1,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						378,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						12, Short.MAX_VALUE)
					.addComponent(
						btnNewRequests,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						47,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap()));

	statusField.setEditable(false);
	statusField.setBackground(java.awt.SystemColor.window);
	statusField.setText("Welcome! " + getUserID() + "! Server Connected.");
	statusField.setBorder(null);
	statusField.setDragEnabled(false);

	MenuFile.setText("File");

	SubMenuMyinfo.setText("My Information");
	SubMenuMyinfo.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		SubMenuMyinfoActionPerformed(evt);
	    }
	});
	MenuFile.add(SubMenuMyinfo);

	SubMenuLogoff.setText("Log Out");
	SubMenuLogoff.setToolTipText("Log out from Server");
	SubMenuLogoff.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		SubMenuLogoffActionPerformed(evt);
	    }
	});
	MenuFile.add(SubMenuLogoff);

	TopMenuBar.add(MenuFile);

	MenuMessages.setText("Messages");

	SubMenuSendMessage.setText("Send Message");
	SubMenuSendMessage
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			SubMenuSendMessageActionPerformed(evt);
		    }
		});
	MenuMessages.add(SubMenuSendMessage);

	SubMenuMessageBox.setText("Message Box");
	SubMenuMessageBox
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			SubMenuMessageBoxActionPerformed(evt);
		    }
		});
	MenuMessages.add(SubMenuMessageBox);

	TopMenuBar.add(MenuMessages);

	MenuInformation.setText("Information");

	SubMenuVersionInfo.setText("Version Info");
	SubMenuVersionInfo
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			SubMenuVersionInfoActionPerformed(evt);
		    }
		});
	MenuInformation.add(SubMenuVersionInfo);

	SubMenuProgramInfo.setText("Program Info");
	SubMenuProgramInfo
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			SubMenuProgramInfoActionPerformed(evt);
		    }
		});
	MenuInformation.add(SubMenuProgramInfo);

	TopMenuBar.add(MenuInformation);

	setJMenuBar(TopMenuBar);

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
		getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(layout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			javax.swing.GroupLayout.Alignment.TRAILING,
			layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(
					layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(statusField)
						.addGroup(
							layout.createSequentialGroup()
								.addComponent(
									MainPanel,
									javax.swing.GroupLayout.PREFERRED_SIZE,
									javax.swing.GroupLayout.DEFAULT_SIZE,
									javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0,
									0,
									Short.MAX_VALUE)))
				.addContainerGap()));
	layout.setVerticalGroup(layout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(MainPanel,
					javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE,
					Short.MAX_VALUE)
				.addPreferredGap(
					javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(statusField,
					javax.swing.GroupLayout.PREFERRED_SIZE,
					19,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

	pack();
    }// </editor-fold>

    @Override
    public void mousePressed(MouseEvent e) {
	this.isMousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	this.isMousePressed = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    private void SubMenuLogoffActionPerformed(java.awt.event.ActionEvent evt) {
	boolean logoutResult = company.logOut();

	if (logoutResult == true) {
	    statusField.setText("LogOff Successful, Terminate Program.");
	    JOptionPane.showMessageDialog(new JFrame(), LOGOFF_SUCCESS);
	    System.exit(0);
	} else {
	    statusField.setText("LogOff Fail.");
	    JOptionPane.showMessageDialog(new JFrame(), LOGOFF_FAIL);
	}
    }

    private void SubMenuMyinfoActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
    }

    private void SubMenuSendMessageActionPerformed(
	    java.awt.event.ActionEvent evt) {
	(new MessageSendUI(getUserID())).setVisible(true);
    }

    private void SubMenuMessageBoxActionPerformed(java.awt.event.ActionEvent evt) {
	(new MessageViewUI(getUserID())).setVisible(true);
    }

    private void SubMenuVersionInfoActionPerformed(
	    java.awt.event.ActionEvent evt) {
	(new VersionInfoUI()).setVisible(true);
    }

    private void SubMenuProgramInfoActionPerformed(
	    java.awt.event.ActionEvent evt) {
	(new ProgramInfoUI()).setVisible(true);
    }

    private void btnNewRequestsActionPerformed(java.awt.event.ActionEvent evt) {
	(new RequestDetailUI(getUserID())).setVisible(true);
    }

    public String getUserID() {
	return userID;
    }

    public void setUserID(String userID) {
	this.userID = userID;
    }
}
