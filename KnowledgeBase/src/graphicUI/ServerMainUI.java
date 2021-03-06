package graphicUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import server.MyServer;
import elements.Request;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JMenu;

@SuppressWarnings("serial")
public class ServerMainUI extends javax.swing.JFrame implements MouseListener {

    DefaultTableModel model;

    public ServerMainUI() {
	setResizable(false);
	setTitle("KnowledgeBased Server");
	initComponents();
	model = (DefaultTableModel) jTable1.getModel();
	loadListItems();
    }

    private void loadListItems() {
	ArrayList<Request> requestList = MyServer.getInstance().getRequests();
	for (Request request : requestList)
	    model.addRow(new Object[] { request.getTitle(),
		    request.getDueDate(), request.isAnswered() });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

	jScrollPane1 = new javax.swing.JScrollPane();
	jTable1 = new javax.swing.JTable();
	jMenuBar3 = new javax.swing.JMenuBar();

	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	jTable1.setModel(new javax.swing.table.DefaultTableModel(null,
		new String[] { "Title", "Date", "Unanswered" }) {
	    Class[] types = new Class[] { java.lang.String.class,
		    java.lang.String.class, java.lang.Boolean.class };
	    boolean[] canEdit = new boolean[] { false, false, false };

	    public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	    }

	    public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	    }
	});
	jScrollPane1.setViewportView(jTable1);
	if (jTable1.getColumnModel().getColumnCount() > 0) {
	    jTable1.getColumnModel().getColumn(0).setResizable(false);
	    jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
	    jTable1.getColumnModel().getColumn(1).setResizable(false);
	    jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
	    jTable1.getColumnModel().getColumn(2).setResizable(false);
	    jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
	}

	setJMenuBar(jMenuBar3);

	menuFile = new JMenu("File");
	jMenuBar3.add(menuFile);

	subMenuTerminate = new JMenuItem("Terminate Server");
	menuFile.add(subMenuTerminate);

	// onClick Exit menu
	subMenuTerminate.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	    }
	});

	menuView = new JMenu("View");
	jMenuBar3.add(menuView);

	// onClick Show Current Users
	subMenuConnectedUser = new JMenuItem("Connected User");
	subMenuConnectedUser.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		(new ShowCurrentUserUI(MyServer.getInstance()
			.printCurrentUsers())).setVisible(true);
	    }
	});
	menuView.add(subMenuConnectedUser);

	subMenuCompanyList = new JMenuItem("Company List");
	subMenuCompanyList.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    (new RegisteredCompanyListUI(MyServer.getInstance()
				.showRegisteredCompany())).setVisible(true);
		}
	});
	menuView.add(subMenuCompanyList);
	
	subMenuStudentList = new JMenuItem("Student List");
	subMenuStudentList.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    (new RegisteredStudentListUI(MyServer.getInstance()
				.showRegisteredStudent())).setVisible(true);
		}
	});
	menuView.add(subMenuStudentList);

	menuAbout = new JMenu("About");
	jMenuBar3.add(menuAbout);

	subMenuVersionInfo = new JMenuItem("Version Infomation");
	subMenuVersionInfo.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		(new VersionInfoUI()).setVisible(true);
	    }
	});
	menuAbout.add(subMenuVersionInfo);

	subMenuProgramInfo = new JMenuItem("Program Information");
	subMenuProgramInfo.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		(new ProgramInfoUI()).setVisible(true);
	    }
	});
	menuAbout.add(subMenuProgramInfo);

	menuBar = new JMenuBar();
	jMenuBar3.add(menuBar);

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
		getContentPane());
	layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
		.addGroup(
			layout.createSequentialGroup()
				.addGap(14)
				.addComponent(jScrollPane1,
					GroupLayout.PREFERRED_SIZE, 742,
					GroupLayout.PREFERRED_SIZE)
				.addContainerGap(24, Short.MAX_VALUE)));
	layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
		.addGroup(
			layout.createSequentialGroup()
				.addGap(14)
				.addComponent(jScrollPane1,
					GroupLayout.PREFERRED_SIZE, 566,
					GroupLayout.PREFERRED_SIZE)
				.addContainerGap(18, Short.MAX_VALUE)));
	getContentPane().setLayout(layout);

	pack();

	jTable1.addMouseListener(this);
	jTable1.getSelectionModel().addListSelectionListener(
		new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
			// do some actions here, for example
			// print first column value from selected row
			if (isMousePressed) {
			    Request selectedRequest = MyServer.getInstance()
				    .getRequests()
				    .get(jTable1.getSelectedRow());
			    System.out.println(selectedRequest.getTitle());
			    new RequestDetailsUI(selectedRequest)
				    .setVisible(true);
			    // jTable1.getSelectionModel().clearSelection();
			}
		    }
		});
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    // End of variables declaration//GEN-END:variables

    public void addNewRequest(Request request) {
	model.addRow(new Object[] { request.getTitle(), request.getDueDate(),
		request.isAnswered() });
    }

    public void updateList() {
	jTable1.setModel(new javax.swing.table.DefaultTableModel(null,
		new String[] { "Title", "Date", "Unanswered" }) {
	    Class[] types = new Class[] { java.lang.String.class,
		    java.lang.String.class, java.lang.Boolean.class };
	    boolean[] canEdit = new boolean[] { false, false, false };

	    public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	    }

	    public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	    }
	});
	model = (DefaultTableModel) jTable1.getModel();

	loadListItems();
    }

    private boolean isMousePressed = false;
    private JMenuBar menuBar;
    private JMenuItem subMenuTerminate;
    private JMenuItem subMenuConnectedUser;
    private JMenuItem subMenuCompanyList;
    private JMenu menuFile;
    private JMenu menuView;
    private JMenu menuAbout;
    private JMenuItem subMenuVersionInfo;
    private JMenuItem subMenuProgramInfo;
    private JMenuItem subMenuStudentList;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
	isMousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	isMousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
