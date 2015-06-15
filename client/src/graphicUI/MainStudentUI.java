package graphicUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import skills.NonTechSkills;
import skills.TechSkills;
import elements.Request;
import elements.Request.State;
import elements.StateManager;
import elements.Student;

public class MainStudentUI extends javax.swing.JFrame implements MouseListener {
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JLabel labelMyRequests;
    private String userID;
    private Student student;
    private final String LOGOFF_SUCCESS = "Logoff Success";
    private final String LOGOFF_FAIL = "Logoff Fail";
    private final String ACCESS_DENIED = "You already tried to access 3 times. Access denied.";
    private ArrayList<Request> requests;
    private boolean isMousePressed = false;
    private AskPasswordUI ask;

    // End of variables declaration

    public MainStudentUI(String userID) {
	setUserID(userID);
	student = new Student(getUserID());
	ask = new AskPasswordUI(getUserID(), "student");
	initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

	MainPanel = new javax.swing.JPanel();
	jScrollPane1 = new javax.swing.JScrollPane();
	table = new javax.swing.JTable();
	labelMyRequests = new javax.swing.JLabel();
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
	setTitle("MainStudent");
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

	requests = new ArrayList<>();
	try {
	    JSONArray requestsJSON = student.getResult();

	    if (requestsJSON != null) {
		for (int i = 0; i < requestsJSON.length(); i++) {
		    JSONObject requestElement = requestsJSON.getJSONObject(i);

		    int id = requestElement.getInt("RequestID");
		    String name = requestElement.getString("Name");
		    String position = requestElement.getString("Position");
		    String startDate = requestElement.getString("StartDate");
		    String endDate = requestElement.getString("EndDate");
		    String payment = requestElement.getString("Payment");
		    State answered = (new StateManager())
			    .toState(requestElement.getString("Answer"));

		    JSONArray techSkillsJSON = requestElement
			    .getJSONArray("TechSkills");
		    ArrayList<TechSkills> techSkills = new ArrayList<>();
		    for (int j = 0; j < techSkillsJSON.length(); j++)
			techSkills.add(TechSkills.valueOf(techSkillsJSON
				.getString(j)));

		    JSONArray nonTechSkillsJSON = requestElement
			    .getJSONArray("NonTechSkills");
		    ArrayList<NonTechSkills> nonTechSkills = new ArrayList<>();
		    for (int j = 0; j < nonTechSkillsJSON.length(); j++)
			nonTechSkills.add(NonTechSkills
				.valueOf(nonTechSkillsJSON.getString(j)));

		    Request newRequest = new Request(id, name, position,
			    startDate, endDate, null, payment, techSkills,
			    nonTechSkills, answered);
		    requests.add(newRequest);

		    ((DefaultTableModel) table.getModel()).addRow(new Object[] {
			    newRequest.getTitle(), newRequest.getStartDate(),
			    newRequest.getState().name() });
		    table.addMouseListener(this);
		    table.getSelectionModel().addListSelectionListener(
			    new ListSelectionListener() {
				public void valueChanged(
					ListSelectionEvent event) {
				    if (isMousePressed) {
					System.out.println("...");
					Request result = requests.get(table
						.getSelectedRow());
					if (result.isComplete() != true) {
					    new StudentReqDetailUI(result,
						    getUserID())
						    .setVisible(true);
					}
				    }
				}
			    });

		}
	    }

	} catch (JSONException e) {
	    e.printStackTrace();
	}

	labelMyRequests.setText("My Requests");

	javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(
		MainPanel);
	MainPanelLayout.setHorizontalGroup(MainPanelLayout.createParallelGroup(
		Alignment.LEADING).addGroup(
		MainPanelLayout
			.createSequentialGroup()
			.addContainerGap()
			.addGroup(
				MainPanelLayout
					.createParallelGroup(Alignment.LEADING)
					.addComponent(jScrollPane1,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
					.addComponent(labelMyRequests))
			.addContainerGap(GroupLayout.DEFAULT_SIZE,
				Short.MAX_VALUE)));
	MainPanelLayout.setVerticalGroup(MainPanelLayout.createParallelGroup(
		Alignment.TRAILING).addGroup(
		Alignment.LEADING,
		MainPanelLayout
			.createSequentialGroup()
			.addContainerGap()
			.addComponent(labelMyRequests)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE,
				437, Short.MAX_VALUE).addContainerGap()));
	MainPanel.setLayout(MainPanelLayout);

	statusField.setEditable(false);
	statusField.setBackground(java.awt.SystemColor.window);
	statusField.setText("Welcome <" + getUserID() + "> ! Server Connected");
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

	MenuInformation.setText("About");

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

    private void SubMenuLogoffActionPerformed(java.awt.event.ActionEvent evt) {
	boolean logoutResult = student.logOut();

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
	if (ask.getTrialCount() >= 3) {
	    JOptionPane.showMessageDialog(new JFrame(), ACCESS_DENIED);
	    SubMenuMyinfo.setEnabled(false);
	}
	else {
	    ask.setVisible(true);   
	}
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

    public String getUserID() {
	return userID;
    }

    public void setUserID(String userID) {
	this.userID = userID;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
	System.out.println("MousePressed");
	this.isMousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	System.out.println("MouseReleased");
	this.isMousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }
}
