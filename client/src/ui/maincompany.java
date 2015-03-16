package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
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
import communication.Communicator;
import elements.Request;
import elements.Result;
import elements.Student;

public class maincompany extends JFrame implements MouseListener
{

	private JPanel contentPane;
	private JTable table;
	private JButton btnMakeNewRequest;

	private String userID;
	private ArrayList<Result> results;

	/**
	 * Create the frame.
	 */
	public maincompany(String UserID)
	{
		userID = UserID;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 441, 204);
		contentPane.add(scrollPane);

		btnMakeNewRequest = new JButton("Make New Request");
		btnMakeNewRequest.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				new request_detail(userID);
			}
		});

		btnMakeNewRequest.setBounds(148, 226, 143, 29);
		contentPane.add(btnMakeNewRequest);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Title", "Date", "Answered" }));

		JSONObject message = new JSONObject();
		results = new ArrayList<>();
		try
		{
			message.put("MessageType", "getresults");
			message.put("ID", userID);

			JSONObject responseJSON = Communicator.sendMessage(message);

			JSONArray resultsJSON = responseJSON.getJSONArray("Results");

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
									Result result = results.get(table.getSelectedRow());
									if(result.isComplete())
										new response_list(result).setVisible(true);
								}
							}
						});

			}

		}
		catch (JSONException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isMousePressed = false;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

}
