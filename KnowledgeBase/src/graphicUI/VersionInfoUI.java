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
	setBounds(100, 100, 275, 100);
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
    }
}
