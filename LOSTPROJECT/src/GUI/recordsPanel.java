package GUI;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.Box;

import GameObjectsManagement.ObjectManagement.Record;

import java.awt.CardLayout;
import java.util.List;

public class recordsPanel extends JPanel {
	private JTable table;
	private List<Record> data;

	/**
	 * Create the panel.
	 */
	public recordsPanel(List<Record> records) {
		data = records;
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		
		JLabel recordsLabel = new JLabel("RECORDS");
		recordsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		recordsLabel.setFont(new Font("Chiller", Font.BOLD, 59));
		recordsLabel.setForeground(Color.WHITE);
		recordsLabel.setBackground(Color.BLACK);
		recordsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(recordsLabel, BorderLayout.NORTH);
		
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(0, 0, 0));
		add(panel, BorderLayout.CENTER);
		
		String[] columnNames = {"Name",
                "Score",
                "Time",
                "Last Event",};
		Object[][] datas = {
				{"Yasin", new Integer(102), "16.48", "Dragon"},
				{"Yasin", new Integer(98), "17.48", "OldWiseMan"},
				{"Yasin", new Integer(88), "17.53", "RadioTelephone"},
				{"Yasin", new Integer(87), "17.12", "Dragon"},
				{"Yasin", new Integer(84), "26.32", "Radio"},
				{"Yasin", new Integer(78), "21.21", "Dragon"},
				{"Yasin", new Integer(76), "30.05", "Dragon"}
		};
		table = new JTable(datas, columnNames);
		panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(table);
		table.setBackground(new Color(0, 0, 0));
		table.setForeground(new Color(255, 255, 255));
		table.getTableHeader().setBackground(new Color(0, 0, 0));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		
		JButton returnMainBtn = new JButton("Go Back");
		returnMainBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JFrame mainFrame= (JFrame) SwingUtilities.getRoot(returnMainBtn.getParent());
				 if(mainFrame.getName()=="popUpFrame"){
						mainFrame.dispose();
				 }
				 else{
					 CardLayout layout = (CardLayout)mainFrame.getContentPane().getLayout();
					 layout.show(mainFrame.getContentPane(), "main");
					 mainFrame.validate();
					 mainFrame.setVisible(true);
				 }
			}
		});
		returnMainBtn.setFont(new Font("Sitka Text", Font.BOLD, 15));
		returnMainBtn.setForeground(Color.WHITE);
		returnMainBtn.setContentAreaFilled(false);
		returnMainBtn.setBackground(Color.BLACK);
		returnMainBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(returnMainBtn, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new CardLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, "name_326175652991758");
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, "name_326177213323890");
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, "name_326178590373905");
	}

}
