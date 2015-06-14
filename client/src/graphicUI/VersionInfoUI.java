package graphicUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import elements.VersionControl;
import javax.swing.SwingConstants;

public class VersionInfoUI extends JFrame {

    private JPanel contentPane;
    private VersionControl ver;

    public VersionInfoUI() {
    	setResizable(false);
    	ver = new VersionControl();
	setTitle("Version Information");
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setBounds(100, 100, 275, 175);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel labelVersionInfo = new JLabel("Current Version:");
	labelVersionInfo.setBounds(20, 20, 120, 20);
	contentPane.add(labelVersionInfo);
	
	JLabel labelVersion = new JLabel("");
	labelVersion.setBounds(160, 20, 100, 20);
	contentPane.add(labelVersion);
	labelVersion.setText(ver.getCurrentVersion());
	
	JLabel labelResult = new JLabel("");
	labelResult.setHorizontalAlignment(SwingConstants.CENTER);
	labelResult.setBounds(20, 105, 225, 20);
	contentPane.add(labelResult);
	
	JButton btnCheckLatest = new JButton("Check the Latest Vesion");
	btnCheckLatest.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    int result = ver.isVersionValid();
		    switch (result) {
		    case 0:
			labelResult.setText("Latest Version!");
			break;
		    case 1:
			labelResult.setText("Please Client Version Update!");
			break;
		    case 2:
			labelResult.setText("Error: Server does not working!");
			break;
		    default:
			labelResult.setText("Error: Something is wrong");
			break;
		    }
		}
	});
	btnCheckLatest.setBounds(20, 70, 235, 30);
	contentPane.add(btnCheckLatest);
    }
}
