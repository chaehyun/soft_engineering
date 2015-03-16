package ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import elements.Result;
import elements.Student;

public class response_list extends JFrame implements MouseListener
{

	private JPanel contentPane;
	private JTable table;

	Result result;

	/**
	 * Create the frame.
	 */
	public response_list(Result result)
	{
		this.result = result;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 441, 275);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Name", "GPA", "Grade" }));

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (Student student : result.getStudents())
			model.addRow(new Object[] { student.getStudentName(),
					student.getGpa(), student.getGrade() });

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
							new student_data(result.getStudents().get(
									table.getSelectedRow())).setVisible(true);
						}
					}
				});
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

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}
}
