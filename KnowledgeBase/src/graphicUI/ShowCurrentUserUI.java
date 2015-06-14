package graphicUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class ShowCurrentUserUI extends JFrame {
    private JPanel contentPane;
    private String currentUserList;

    /**
     * Create the frame.
     */
    public ShowCurrentUserUI(String userList) {
	setCurrentUserList(userList);

	setTitle("Current User");
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

	textArea.setText(getCurrentUserList());
    }

    public String getCurrentUserList() {
	return currentUserList;
    }

    public void setCurrentUserList(String currentUserList) {
	this.currentUserList = currentUserList;
    }

}
