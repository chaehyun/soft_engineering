package graphicUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class ProgramInfoUI extends JFrame {

    private JPanel contentPane;

    public ProgramInfoUI() {
    	setTitle("Program Infomation");
    	setResizable(false);
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setBounds(100, 100, 370, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(20, 20, 320, 200);
	contentPane.add(scrollPane);
	
	JTextArea txtrClientForCompanystudent = new JTextArea();
	txtrClientForCompanystudent.setText("<Knowledge Based Matching System>\nServer for Office Manager\nDeveloped by Team 89s\n\nKyungpook National University\nClass Software Engineering\n\nProject Repository: \nhttps://github.com/chaehyun/soft_engineering");
	txtrClientForCompanystudent.setEditable(false);
	scrollPane.setViewportView(txtrClientForCompanystudent);
	txtrClientForCompanystudent.setBackground(null);
	JButton btnClose = new JButton("Close");
	btnClose.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    setVisible(false);
		}
	});
	btnClose.setBounds(220, 230, 120, 30);
	contentPane.add(btnClose);
    }
}
