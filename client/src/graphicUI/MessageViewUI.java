package graphicUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import elements.MessageView;

public class MessageViewUI extends JFrame
{

	private JPanel contentPane;
	private String id;
	private MessageView msgView;
	private JTextField textFieldFrom;
	private JTextField textFieldTime;
	private JTextArea messageField;
	private final String MSG_SEND_FAIL = "Can not send the message to null user.";

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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 50, 400, 150);
		contentPane.add(scrollPane);
		
		messageField = new JTextArea();
		scrollPane.setColumnHeaderView(messageField);
		messageField.setEditable(false);
		
		JButton btnNextMessage = new JButton("Next Message");
		btnNextMessage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				msgView.increaseMsgIndex();
				showMessages();
			}
		});
		btnNextMessage.setBounds(290, 230, 120, 30);
		contentPane.add(btnNextMessage);
		
		JButton btnPrevMessage = new JButton("Prev Message");
		btnPrevMessage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				msgView.decreaseMsgIndex();
				showMessages();
			}
		});
		btnPrevMessage.setBounds(160, 230, 120, 30);
		contentPane.add(btnPrevMessage);
		
		JLabel lbFromLabel = new JLabel("From:");
		lbFromLabel.setBounds(15, 15, 50, 30);
		contentPane.add(lbFromLabel);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setEditable(false);
		textFieldFrom.setBounds(65, 15, 130, 30);
		contentPane.add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		JLabel lbSentTime = new JLabel("Sent Time :");
		lbSentTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lbSentTime.setBounds(200, 15, 70, 30);
		contentPane.add(lbSentTime);
		
		textFieldTime = new JTextField();
		textFieldTime.setEditable(false);
		textFieldTime.setBounds(270, 15, 145, 30);
		contentPane.add(textFieldTime);
		textFieldTime.setColumns(10);
		
		JButton btnReplyButton = new JButton("Quick Reply");
		btnReplyButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String dst = textFieldFrom.getText();
				if (dst.equals("null") == true)
				{
					JOptionPane.showMessageDialog(new JFrame(), MSG_SEND_FAIL);
				}
				else
				{
					(new MessageSendUI(getId(), textFieldFrom.getText())).setVisible(true);
				}
			}
		});
		btnReplyButton.setBounds(30, 230, 120, 30);
		contentPane.add(btnReplyButton);
		
		showMessages();
		
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	
	public void showMessages()
	{
		if (msgView.getMsgTotal() != 0)
		{
			textFieldFrom.setText(msgView.getOneMessage().getMsgSender());
			messageField.setText(msgView.getOneMessage().getMsgText());
			textFieldTime.setText(msgView.getOneMessage().getMsgSentTime());
		}
		else
		{
			textFieldFrom.setText("null");
			messageField.setText("No Message!");
			textFieldTime.setText(null);
		}
	}
}
