package graphicUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class RegisteredCompanyListUI extends JFrame
{
	private JPanel contentPane;
	private String companylist;
	
	/**
	 * Create the frame.
	 */
	public RegisteredCompanyListUI(String companyList)
	{
		setCompanylist(companyList);
		
		setTitle("Registerd Company List");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 420, 250);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setText(getCompanylist());
	}

	public String getCompanylist()
	{
		return companylist;
	}

	public void setCompanylist(String companylist)
	{
		this.companylist = companylist;
	}
}
