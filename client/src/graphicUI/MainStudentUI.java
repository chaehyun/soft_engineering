package graphicUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

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
import javax.swing.ListSelectionModel;

public class MainStudentUI extends JFrame implements MouseListener
{

	private JPanel contentPane;
	private JTable table;
	private String userID;

	private ArrayList<Request> requests;

	/**
	 * Create the frame.
	 */
	public MainStudentUI(String UserID)
	{
		userID = UserID;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 238);
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
							new StudentReqDetailUI(selectedRequest, userID)
									.setVisible(true);
							;
						}
					}
				});

		JSONObject message = new JSONObject();
		requests = new ArrayList<>();
		try
		{
			message.put("MessageType", "getrequests");
			message.put("ID", userID);

			JSONObject response = Communicator.sendMessage(message);

			JSONArray requestsJSON = response.getJSONArray("Requests");

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
		catch (JSONException | IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	private boolean isMousePressed;

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
