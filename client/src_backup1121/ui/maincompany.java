package ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class maincompany extends JFrame
{

	private JPanel contentPane;
	private JTable table;
	private JButton btnMakeNewRequest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					maincompany frame = new maincompany();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public maincompany()
	{
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
				new request_detail();
			}
		});
		
		btnMakeNewRequest.setBounds(148, 226, 143, 29);
		contentPane.add(btnMakeNewRequest);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null } }, new String[] { "Title", "Date",
				"Response" }));

		
	}
}
