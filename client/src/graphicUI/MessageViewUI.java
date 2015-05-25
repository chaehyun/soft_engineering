package graphicUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import elements.MessageView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MessageViewUI extends JFrame
{

	private JPanel contentPane;
	private JTextField messageField;
	private String id;
	private MessageView msgView;

	/**
	 * Create the frame.
	 */
	public MessageViewUI(String id)
	{
		setId(id);
		msgView = new MessageView(getId());
		
		setTitle("MessageView");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 430, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		messageField = new JTextField();
		messageField.setBounds(15, 20, 400, 200);
		contentPane.add(messageField);
		messageField.setColumns(10);
		
		JButton btnNextMessage = new JButton("Next Message");
		btnNextMessage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				messageField.setText(msgView.getOneMessage());
			}
		});
		btnNextMessage.setBounds(290, 230, 120, 30);
		contentPane.add(btnNextMessage);
		
		JButton btnPrevMessage = new JButton("Prev Message");
		btnPrevMessage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				messageField.setText(msgView.getPrevMessage());
			}
		});
		btnPrevMessage.setBounds(160, 230, 120, 30);
		contentPane.add(btnPrevMessage);
		
		// Initialize Setting
		messageField.setText(msgView.getOneMessage());
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
