package GUI;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class HelpPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HelpPanel() {
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component verticalGlue = Box.createVerticalGlue();
		add(verticalGlue);
		
		JLabel helpLabel = new JLabel("HELP");
		helpLabel.setFont(new Font("Chiller", Font.BOLD, 59));
		helpLabel.setForeground(Color.WHITE);
		helpLabel.setBackground(Color.BLACK);
		helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(helpLabel);
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		add(verticalGlue_3);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		add(verticalGlue_1);
		
		JLabel lblNewLabel = new JLabel("Open Online Wiki for taking guidelines about game");
		lblNewLabel.setIcon(new ImageIcon(HelpPanel.class.getResource("/GUI/link-512.png")));
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel);
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		add(verticalGlue_4);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		add(verticalGlue_2);
		
		JButton returnMainBtn = new JButton("Main Menu");
		returnMainBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JFrame mainFrame= (JFrame) SwingUtilities.getRoot(returnMainBtn.getParent());
				 mainFrame.getContentPane().removeAll();
				 mainFrame.getContentPane().add(new mainPanel());
				 mainFrame.validate();
				 mainFrame.setVisible(true);
			}
		});
		returnMainBtn.setFont(new Font("Sitka Text", Font.BOLD, 15));
		returnMainBtn.setForeground(Color.WHITE);
		returnMainBtn.setContentAreaFilled(false);
		returnMainBtn.setBackground(Color.BLACK);
		returnMainBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(returnMainBtn);
		
		Component verticalGlue5 = Box.createVerticalGlue();
		add(verticalGlue5);

	}

}
