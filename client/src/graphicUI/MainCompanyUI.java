package graphicUI;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import skills.NonTechSkills;
import skills.TechSkills;
import elements.Company;
import elements.MessageSend;
import elements.Result;
import elements.Student;
import javax.swing.JTextField;

public class MainCompanyUI extends JFrame implements MouseListener
{

	private JPanel contentPane;
	private JTable table;
	private JButton btnMakeNewRequest;

	private String userID;
	private ArrayList<Result> results;
	private Company company;
	
	private final String LOGOFF_SUCCESS = "Logoff Success";
	private final String LOGOFF_FAIL = "Logoff Fail";

	/**
	 * Create the frame.
	 */
	public MainCompanyUI(String UserID)
	{
		setTitle("MainCompany");
		setUserID(UserID);
		company = new Company(getUserID());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 340);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mntmModifyInformation = new JMenuItem("Modify Information");
		mntmModifyInformation.setBackground(SystemColor.window);
		menuBar.add(mntmModifyInformation);
		
		mntmLogout = new JMenuItem("Exit");
		mntmLogout.setBackground(SystemColor.window);
		mntmLogout.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				boolean logoutResult = company.logOut();
				
				if (logoutResult == true)
				{
					statusField.setText("LogOff Successful, Terminate Program.");
					JOptionPane.showMessageDialog(new JFrame(), LOGOFF_SUCCESS);
					System.exit(0);
				}
				else
				{
					statusField.setText("LogOff Fail.");
					JOptionPane.showMessageDialog(new JFrame(), LOGOFF_FAIL);
				}
			}
		});
		
		mntmMessagebox = new JMenuItem("MessageBox");
		mntmMessagebox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				(new MessageViewUI(getUserID())).setVisible(true);
			}
		});
		mntmMessagebox.setBackground(SystemColor.window);
		menuBar.add(mntmMessagebox);
		
		mntmSendMsg = new JMenuItem("Send Msg");
		mntmSendMsg.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				(new MessageSendUI(getUserID())).setVisible(true);
			}
		});
		mntmSendMsg.setBackground(SystemColor.window);
		menuBar.add(mntmSendMsg);
		menuBar.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 440, 205);
		contentPane.add(scrollPane);

		btnMakeNewRequest = new JButton("Make New Request");
		btnMakeNewRequest.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				new RequestDetailUI(userID);
			}
		});

		btnMakeNewRequest.setBounds(148, 226, 143, 29);
		contentPane.add(btnMakeNewRequest);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Title", "Date", "Answered" }));
		
		statusField = new JTextField();
		statusField.setEditable(false);
		statusField.setBackground(SystemColor.window);
		statusField.setBounds(0, 268, 457, 28);
		contentPane.add(statusField);
		statusField.setColumns(10);
		statusField.setText("Connected");

		results = new ArrayList<>();
		try
		{
			JSONArray resultsJSON = company.getResult();
			
			if (resultsJSON != null)
			{
				for (int i = 0; i < resultsJSON.length(); i++)
				{
					JSONObject resultElement = resultsJSON.getJSONObject(i);
	
					String title = resultElement.getString("Title");
					String startDate = resultElement.getString("Date");
					boolean complete = resultElement.getBoolean("Complete");
	
					ArrayList<Student> studentsList = new ArrayList<>();
					if (complete)
					{
						JSONArray studentsArray = resultElement
								.getJSONArray("Students");
						for (int j = 0; j < studentsArray.length(); j++)
						{
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
								techSkills.add(TechSkills.valueOf(techSkillsJSON
										.getString(k)));
	
							JSONArray nonTechSkillsJSON = studentElement
									.getJSONArray("NonTechSkills");
							ArrayList<NonTechSkills> nonTechSkills = new ArrayList<>();
							for (int k = 0; k < nonTechSkillsJSON.length(); k++)
								nonTechSkills.add(NonTechSkills
										.valueOf(nonTechSkillsJSON.getString(k)));
	
							studentsList.add(new Student(studentName, grade, gpa,
									contactNumber, sex, age, techSkills,
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
							new ListSelectionListener()
							{
								public void valueChanged(ListSelectionEvent event)
								{
									// do some actions here, for example
									// print first column value from selected row
									if (isMousePressed)
									{
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

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	private boolean isMousePressed = false;
	private JMenuBar menuBar;
	private JMenuItem mntmModifyInformation;
	private JMenuItem mntmLogout;
	private JMenuItem mntmMessagebox;
	private JMenuItem mntmSendMsg;
	private JTextField statusField;

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
