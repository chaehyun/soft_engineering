package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.DefaultTableModel;

import server.MyServer;
import elements.NonTechSkills;
import elements.Reply;
import elements.Request;
import elements.Student;
import elements.TechSkills;

@SuppressWarnings("serial")
public class SearchStudents extends javax.swing.JFrame implements ItemListener
{
	
	private Request selectedRequest;
	private ArrayList<Student> listOfSelectedStudents;
	
	public SearchStudents(Request request)
	{
		selectedRequest = request;
		initComponents();
		setFields();
		filterStudents();
		updateList();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		filterStudents();
		updateList();
	}
	
	private void updateList()
	{
		
		// System.out.println("update row count begin: " +
		// tableModel.getRowCount());
		
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Name", "GPA", "Pick" })
		{
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.Float.class, java.lang.Boolean.class };
			boolean[] canEdit = new boolean[] { false, false, true };
			
			public Class getColumnClass(int columnIndex)
			{
				return types[columnIndex];
			}
			
			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return canEdit[columnIndex];
			}
		});
		DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
		// for (int i = 0; i < tableModel.getRowCount(); i++)
		// tableModel.removeRow(i);
		
		System.out.println("listOfSelectedStudents: "
				+ listOfSelectedStudents.size());
		System.out.println("update row count before: "
				+ tableModel.getRowCount());
		for (Student student : listOfSelectedStudents)
		{
			tableModel.addRow(new Object[] { student.getName(),
					student.getGpa(), false });
		}
		
		System.out.println("update row count after: "
				+ tableModel.getRowCount());
		
	}
	
	private void setFields()
	{
		jTextFieldMinimumGrade.setText(Integer.toString(selectedRequest
				.getMinimumGrade()));
		
		for (TechSkills skill : selectedRequest.getTechSkills())
		{
			switch (skill)
			{
				case JAVA:
					jCheckBoxJava.setSelected(true);
					break;
				case APACHE:
					jCheckBoxJava.setSelected(true);
					break;
				case ORACLE:
					jCheckBoxJava.setSelected(true);
					break;
				case OOP:
					jCheckBoxJava.setSelected(true);
					break;
				default:
					break;
			}
		}
		
		for (NonTechSkills skill : selectedRequest.getNonTechSkills())
		{
			switch (skill)
			{
				case TEAMWORK:
					jCheckBoxTeamSpirit.setSelected(true);
					break;
				case QUICKLEARNING:
					jCheckBoxQuickLearning.setSelected(true);
					break;
				case DRIVINGLICENCE:
					jCheckBoxDrivingLicence.setSelected(true);
					break;
				default:
					break;
			}
		}
	}
	
	private void filterStudents()
	{
		ArrayList<Student> listOfAllStudents = MyServer.getInstance()
				.getStudents();
		listOfSelectedStudents = new ArrayList<>();
		
		try
		{
			int minimumGrade = Integer.parseInt(jTextFieldMinimumGrade
					.getText());
			
			ArrayList<TechSkills> techSkills = getTechSkillsChecked();
			ArrayList<NonTechSkills> nonTechSkills = getNonTechSkillsChecked();
			
			for (Student student : listOfAllStudents)
			{
				if (student.getGrade() >= minimumGrade)
				{
					boolean techSkillsOkay = true;
					for (TechSkills skill : techSkills)
						if (!student.getTechSkills().contains(skill))
							techSkillsOkay = false;
					boolean nonTechSkillsOkay = true;
					if (techSkillsOkay)
					{
						for (NonTechSkills skill : nonTechSkills)
							if (!student.getNonTechSkills().contains(skill))
								nonTechSkillsOkay = false;
						if (nonTechSkillsOkay)
							listOfSelectedStudents.add(student);
					}
					
				}
			}
			
		}
		catch (NumberFormatException e)
		{
		}
		
		Collections.sort(listOfSelectedStudents);
	}
	
	private ArrayList<TechSkills> getTechSkillsChecked()
	{
		ArrayList<TechSkills> techSkills = new ArrayList<>();
		
		if (jCheckBoxJava.isSelected())
		{
			techSkills.add(TechSkills.JAVA);
		}
		
		if (jCheckBoxApache.isSelected())
		{
			techSkills.add(TechSkills.APACHE);
		}
		
		if (jCheckBoxOracleSQL.isSelected())
		{
			techSkills.add(TechSkills.ORACLE);
		}
		
		if (jCheckBoxOOP.isSelected())
		{
			techSkills.add(TechSkills.OOP);
		}
		
		return techSkills;
	}
	
	private ArrayList<NonTechSkills> getNonTechSkillsChecked()
	{
		ArrayList<NonTechSkills> nonTechSkills = new ArrayList<>();
		
		if (jCheckBoxTeamSpirit.isSelected())
		{
			nonTechSkills.add(NonTechSkills.TEAMWORK);
		}
		
		if (jCheckBoxQuickLearning.isSelected())
		{
			nonTechSkills.add(NonTechSkills.QUICKLEARNING);
		}
		
		if (jCheckBoxDrivingLicence.isSelected())
		{
			nonTechSkills.add(NonTechSkills.DRIVINGLICENCE);
		}
		
		return nonTechSkills;
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
		
		jLabel12 = new javax.swing.JLabel();
		jCheckBoxJava = new javax.swing.JCheckBox();
		jCheckBoxApache = new javax.swing.JCheckBox();
		jCheckBoxOracleSQL = new javax.swing.JCheckBox();
		jCheckBoxOOP = new javax.swing.JCheckBox();
		jScrollPane4 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jLabel13 = new javax.swing.JLabel();
		jCheckBoxTeamSpirit = new javax.swing.JCheckBox();
		jLabel14 = new javax.swing.JLabel();
		jCheckBoxQuickLearning = new javax.swing.JCheckBox();
		jCheckBoxDrivingLicence = new javax.swing.JCheckBox();
		jLabel5 = new javax.swing.JLabel();
		jTextFieldMinimumGrade = new javax.swing.JTextField();
		jButtonSend = new javax.swing.JButton();
		
		jCheckBoxJava.addItemListener(this);
		jCheckBoxApache.addItemListener(this);
		jCheckBoxOracleSQL.addItemListener(this);
		jCheckBoxOOP.addItemListener(this);
		jCheckBoxTeamSpirit.addItemListener(this);
		jCheckBoxQuickLearning.addItemListener(this);
		jCheckBoxDrivingLicence.addItemListener(this);
		jTextFieldMinimumGrade.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				itemStateChanged(null);
			}
		});
		
		jButtonSend.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				DefaultTableModel tableModel = (DefaultTableModel) jTable1
						.getModel();
				System.out.println("length of list: "
						+ listOfSelectedStudents.size());
				System.out.println("row count: " + tableModel.getRowCount());
				for (int i = 0; i < tableModel.getRowCount(); i++)
				{
					if ((boolean) tableModel.getValueAt(i, 2))
					{
						System.out.println(i);
						System.out.println(listOfSelectedStudents == null);
						System.out.println(listOfSelectedStudents.get(i) == null);
						selectedRequest.getReplies().add(
								new Reply(listOfSelectedStudents.get(i)));
					}
				}
				
				SearchStudents.this.setVisible(false);
			}
		});
		
		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
		setResizable(false);
		
		jLabel12.setText("Filter responses:");
		
		jCheckBoxJava.setSelected(false);
		jCheckBoxJava.setText("Java");
		jCheckBoxJava.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jCheckBoxJavaActionPerformed(evt);
			}
		});
		
		jCheckBoxApache.setSelected(false);
		jCheckBoxApache.setText("Apache Server");
		jCheckBoxApache.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jCheckBoxApacheActionPerformed(evt);
			}
		});
		
		jCheckBoxOracleSQL.setSelected(false);
		jCheckBoxOracleSQL.setText("Oracle SQL");
		jCheckBoxOracleSQL
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent evt)
					{
						jCheckBoxOracleSQLActionPerformed(evt);
					}
				});
		
		jCheckBoxOOP.setSelected(false);
		jCheckBoxOOP.setText("Object-Oriented Programming");
		
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Name", "GPA", "Pick" })
		{
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.Float.class, java.lang.Boolean.class };
			boolean[] canEdit = new boolean[] { false, false, true };
			
			public Class getColumnClass(int columnIndex)
			{
				return types[columnIndex];
			}
			
			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return canEdit[columnIndex];
			}
		});
		jScrollPane4.setViewportView(jTable1);
		
		jLabel13.setText("Technical skills");
		
		jCheckBoxTeamSpirit.setSelected(false);
		jCheckBoxTeamSpirit.setText("Team spirit");
		jCheckBoxTeamSpirit
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent evt)
					{
						jCheckBoxTeamSpiritActionPerformed(evt);
					}
				});
		
		jLabel14.setText("Non-technical skills");
		
		jCheckBoxQuickLearning.setSelected(false);
		jCheckBoxQuickLearning.setText("quick learning");
		jCheckBoxQuickLearning
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent evt)
					{
						jCheckBoxQuickLearningActionPerformed(evt);
					}
				});
		
		jCheckBoxDrivingLicence.setSelected(false);
		jCheckBoxDrivingLicence.setText("driving licence");
		jCheckBoxDrivingLicence
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent evt)
					{
						jCheckBoxDrivingLicenceActionPerformed(evt);
					}
				});
		
		jLabel5.setText("Minimum grade:");
		
		jTextFieldMinimumGrade.setText("3");
		
		jButtonSend.setText("Send requests to the students");
		jButtonSend.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButtonSendActionPerformed(evt);
			}
		});
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jCheckBoxJava)
																								.addGap(10,
																										10,
																										10)
																								.addComponent(
																										jCheckBoxApache)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(
																										jCheckBoxOracleSQL)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										jCheckBoxOOP))
																				.addComponent(
																						jLabel13))
																.addGap(0,
																		45,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jScrollPane4,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						0,
																						Short.MAX_VALUE)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		jCheckBoxTeamSpirit)
																																.addGap(10,
																																		10,
																																		10)
																																.addComponent(
																																		jCheckBoxQuickLearning)
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																.addComponent(
																																		jCheckBoxDrivingLicence))
																												.addComponent(
																														jLabel14)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		jLabel12)
																																.addGap(29,
																																		29,
																																		29)
																																.addComponent(
																																		jLabel5)
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																.addComponent(
																																		jTextFieldMinimumGrade,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		37,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)))
																								.addGap(0,
																										0,
																										Short.MAX_VALUE)))
																.addContainerGap())))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jButtonSend).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(19, 19, 19)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel12)
												.addComponent(jLabel5)
												.addComponent(
														jTextFieldMinimumGrade,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(1, 1, 1)
								.addComponent(jLabel13)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jCheckBoxJava)
												.addComponent(
														jCheckBoxOracleSQL)
												.addComponent(jCheckBoxApache)
												.addComponent(jCheckBoxOOP))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel14)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jCheckBoxTeamSpirit)
												.addComponent(
														jCheckBoxDrivingLicence)
												.addComponent(
														jCheckBoxQuickLearning))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jScrollPane4,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										125,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										14, Short.MAX_VALUE)
								.addComponent(jButtonSend).addContainerGap()));
		
		pack();
	}// </editor-fold>//GEN-END:initComponents
	
	private void jCheckBoxApacheActionPerformed(java.awt.event.ActionEvent evt)
	{
		
	}
	
	private void jCheckBoxOracleSQLActionPerformed(
			java.awt.event.ActionEvent evt)
	{
		
	}
	
	private void jCheckBoxJavaActionPerformed(java.awt.event.ActionEvent evt)
	{
		
	}
	
	private void jCheckBoxTeamSpiritActionPerformed(
			java.awt.event.ActionEvent evt)
	{
		
	}
	
	private void jCheckBoxQuickLearningActionPerformed(
			java.awt.event.ActionEvent evt)
	{
		
	}
	
	private void jCheckBoxDrivingLicenceActionPerformed(
			java.awt.event.ActionEvent evt)
	{
		
	}
	
	private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt)
	{
		
	}
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonSend;
	private javax.swing.JCheckBox jCheckBoxJava;
	private javax.swing.JCheckBox jCheckBoxOracleSQL;
	private javax.swing.JCheckBox jCheckBoxApache;
	private javax.swing.JCheckBox jCheckBoxOOP;
	private javax.swing.JCheckBox jCheckBoxTeamSpirit;
	private javax.swing.JCheckBox jCheckBoxQuickLearning;
	private javax.swing.JCheckBox jCheckBoxDrivingLicence;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JTable jTable1;
	private javax.swing.JTextField jTextFieldMinimumGrade;
	// End of variables declaration//GEN-END:variables
	
}
