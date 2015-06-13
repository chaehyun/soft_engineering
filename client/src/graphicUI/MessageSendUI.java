package graphicUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import elements.MessageSend;

public class MessageSendUI extends JFrame
{

	private JPanel contentPane;
	private JTextField destinationField;
	private JTextField messageField;
	private MessageSend msgSend;
	private String id;
	
	private final String MSG_SEND_SUCCESS = "Message was sent successfully.";
	private final String MSG_SEND_FAIL = "Message was not sent.";
	
	public MessageSendUI(String Id)
	{
		setId(Id);
		msgSend = new MessageSend(getId());
		
		setTitle("Send Message");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 434, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		destinationField = new JTextField();
		destinationField.setBounds(100, 30, 300, 25);
		contentPane.add(destinationField);
		destinationField.setColumns(10);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTo.setBounds(10, 30, 80, 20);
		contentPane.add(lblTo);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMessage.setBounds(10, 70, 80, 20);
		contentPane.add(lblMessage);
		
		messageField = new JTextField();
		messageField.setBounds(100, 70, 300, 165);
		contentPane.add(messageField);
		messageField.setColumns(10);
		
		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				boolean result;
				
				result = msgSend.sendMsg(destinationField.getText(), messageField.getText());
				
				if (result == true)
				{
					JOptionPane.showMessageDialog(new JFrame(), MSG_SEND_SUCCESS);
					setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), MSG_SEND_FAIL);
				}
			}
		});
		btnSendMessage.setBounds(280, 240, 120, 30);
		contentPane.add(btnSendMessage);
	}
	
	// Override Method
	public MessageSendUI(String Id, String to)
	{
		setId(Id);
		msgSend = new MessageSend(getId());
		
		setTitle("Send Message");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 434, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		destinationField = new JTextField();
		destinationField.setBounds(100, 30, 300, 25);
		contentPane.add(destinationField);
		destinationField.setColumns(10);
		destinationField.setText(to);
		destinationField.setEditable(false);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTo.setBounds(10, 30, 80, 20);
		contentPane.add(lblTo);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMessage.setBounds(10, 70, 80, 20);
		contentPane.add(lblMessage);
		
		messageField = new JTextField();
		messageField.setBounds(100, 70, 300, 165);
		contentPane.add(messageField);
		messageField.setColumns(10);
		
		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				boolean result;
				
				result = msgSend.sendMsg(destinationField.getText(), messageField.getText());
				
				if (result == true)
				{
					JOptionPane.showMessageDialog(new JFrame(), MSG_SEND_SUCCESS);
					setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), MSG_SEND_FAIL);
				}
			}
		});
		btnSendMessage.setBounds(280, 240, 120, 30);
		contentPane.add(btnSendMessage);
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
