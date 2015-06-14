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

@SuppressWarnings("serial")
public class RequestsUI extends javax.swing.JFrame implements MouseListener
{
	
	DefaultTableModel model;
	
	public RequestsUI()
	{
		initComponents();
		model = (DefaultTableModel) jTable1.getModel();
		loadListItems();
	}
	
	private void loadListItems()
	{
		ArrayList<Request> requestList = MyServer.getInstance().getRequests();
		for (Request request : requestList)
			model.addRow(new Object[] { request.getTitle(),
					request.getDueDate(), request.isAnswered() });
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{
		
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jMenuBar3 = new javax.swing.JMenuBar();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		
		jTable1.setModel(new javax.swing.table.DefaultTableModel(null,
				new String[] { "Title", "Date", "Unanswered" })
		{
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class, java.lang.Boolean.class };
			boolean[] canEdit = new boolean[] { false, false, false };
			
			public Class getColumnClass(int columnIndex)
			{
				return types[columnIndex];
			}
			
			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(jTable1);
		if (jTable1.getColumnModel().getColumnCount() > 0)
		{
			jTable1.getColumnModel().getColumn(0).setResizable(false);
			jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
			jTable1.getColumnModel().getColumn(1).setResizable(false);
			jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
			jTable1.getColumnModel().getColumn(2).setResizable(false);
			jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
		}
		
		setJMenuBar(jMenuBar3);
		
		mntmExitItem = new JMenuItem("Exit");
		
		// onClick Exit menu
		mntmExitItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean terminateResult = MyServer.getInstance().Terminate();
			}
		});
		jMenuBar3.add(mntmExitItem);
		
		// onClick Show Current Users
		mntmShowCurrentUsers = new JMenuItem("Show Current Users");
		mntmShowCurrentUsers.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				(new ShowCurrentUserUI(MyServer.getInstance()
						.printCurrentUsers())).setVisible(true);
			}
		});
		jMenuBar3.add(mntmShowCurrentUsers);
		
		mntmRegisteredCompanyList = new JMenuItem("Registered Company List");
		mntmRegisteredCompanyList.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				(new RegisteredCompanyListUI(MyServer.getInstance()
						.ShowRegisteredCompany())).setVisible(true);
			}
		});
		jMenuBar3.add(mntmRegisteredCompanyList);
		
		menuBar = new JMenuBar();
		jMenuBar3.add(menuBar);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 580,
								Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING,
				javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE));
		
		pack();
		
		jTable1.addMouseListener(this);
		jTable1.getSelectionModel().addListSelectionListener(
				new ListSelectionListener()
				{
					public void valueChanged(ListSelectionEvent event)
					{
						// do some actions here, for example
						// print first column value from selected row
						if (isMousePressed)
						{
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
	
	public void addNewRequest(Request request)
	{
		model.addRow(new Object[] { request.getTitle(), request.getDueDate(),
				request.isAnswered() });
	}
	
	public void updateList()
	{
		jTable1.setModel(new javax.swing.table.DefaultTableModel(null,
				new String[] { "Title", "Date", "Unanswered" })
		{
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class, java.lang.Boolean.class };
			boolean[] canEdit = new boolean[] { false, false, false };
			
			public Class getColumnClass(int columnIndex)
			{
				return types[columnIndex];
			}
			
			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return canEdit[columnIndex];
			}
		});
		model = (DefaultTableModel) jTable1.getModel();
		
		loadListItems();
	}
	
	private boolean isMousePressed = false;
	private JMenuBar menuBar;
	private JMenuItem mntmExitItem;
	private JMenuItem mntmShowCurrentUsers;
	private JMenuItem mntmRegisteredCompanyList;
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		isMousePressed = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		isMousePressed = false;
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}
}
