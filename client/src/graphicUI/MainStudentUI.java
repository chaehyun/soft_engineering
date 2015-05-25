package graphicUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import skills.NonTechSkills;
import skills.TechSkills;
import elements.Request;
import elements.Student;

public class MainStudentUI extends JFrame implements MouseListener
{

	private JPanel contentPane;
	private JTable table;
	private String id;
	private Student student;
	private final String LOGOFF_SUCCESS = "Logoff Success";
	private final String LOGOFF_FAIL = "Logoff Fail";

	private ArrayList<Request> requests;

	/**
	 * Create the frame.
	 */
	public MainStudentUI(String UserID)
	{
		setId(UserID);
		student = new Student(getId());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 238);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mntmModifyInformation = new JMenuItem("Modify Information");
		menuBar.add(mntmModifyInformation);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				boolean logoutResult = student.logOut();
				
				if (logoutResult == true)
				{
					JOptionPane.showMessageDialog(new JFrame(), LOGOFF_SUCCESS);
					System.exit(0);
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), LOGOFF_FAIL);
				}
			}
		});
		menuBar.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 432, 262);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Title", "Date", "Response" })
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		});

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
							Request selectedRequest = requests.get(table
									.getSelectedRow());
							new StudentReqDetailUI(selectedRequest, id)
									.setVisible(true);
							;
						}
					}
				});

		
		try
		{
			JSONArray requestsJSON = student.getResult();

			if (requestsJSON != null)
			{
				for (int i = 0; i < requestsJSON.length(); i++)
				{
					JSONObject requestElement = requestsJSON.getJSONObject(i);
	
					int id = requestElement.getInt("RequestID");
					String name = requestElement.getString("Name");
					String position = requestElement.getString("Position");
					String startDate = requestElement.getString("StartDate");
					String endDate = requestElement.getString("EndDate");
					String payment = requestElement.getString("Payment");
	
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
						nonTechSkills.add(NonTechSkills.valueOf(nonTechSkillsJSON
								.getString(j)));
	
					Request newRequest = new Request(id, name, position, startDate,
							endDate, null, payment, techSkills, nonTechSkills,
							Request.State.Unanswered);
					requests.add(newRequest);
	
					((DefaultTableModel) table.getModel()).addRow(new Object[] {
							newRequest.getTitle(), newRequest.getStartDate(),
							newRequest.getState().name() });
	
				}
			}

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	private boolean isMousePressed;
	private JMenuBar menuBar;
	private JMenuItem mntmModifyInformation;
	private JMenuItem mntmExit;

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

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}
