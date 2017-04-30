//GUI YAsin

package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.Dimension;

public class SettingsPanel extends JPanel {
	
	boolean isMuted=false;

	/**
	 * Create the panel.
	 */
	public SettingsPanel() {
		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component verticalGlue = Box.createVerticalGlue();
		add(verticalGlue);
		
		JLabel settingsLabel = new JLabel("SETTINGS");
		settingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		settingsLabel.setBackground(Color.BLACK);
		settingsLabel.setForeground(Color.WHITE);
		settingsLabel.setFont(new Font("Chiller", Font.BOLD, 59));
		add(settingsLabel);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		add(verticalGlue_1);
		
		JPanel panel = new JPanel();
		panel.setRequestFocusEnabled(false);
		panel.setBackground(Color.BLACK);
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton soundBtn = new JButton("");
		panel.add(soundBtn);
		soundBtn.setRequestFocusEnabled(false);
		soundBtn.setBorderPainted(false);
		//soundBtn.setSelectedIcon(new ImageIcon(SettingsPanel.class.getResource("/GUI/speaker1.png")));
		soundBtn.setContentAreaFilled(false);
		soundBtn.setOpaque(false);
		soundBtn.setForeground(new Color(255, 255, 255));
		soundBtn.setBackground(new Color(0, 0, 0));
		soundBtn.setIcon(new ImageIcon(SettingsPanel.class.getResource("/GUI/speaker1.png")));
		soundBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		soundBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isMuted){
					soundBtn.setIcon(new ImageIcon(SettingsPanel.class.getResource("/GUI/mutedspeaker.png")));
					isMuted=false;
				}
				else{
					soundBtn.setIcon(new ImageIcon(SettingsPanel.class.getResource("/GUI/speaker1.png")));
					isMuted=true;
				}
			}
		});
		
		soundBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		add(verticalGlue_2);
		
		JComboBox SizeComboBox = new JComboBox();
		SizeComboBox.setRequestFocusEnabled(false);
		SizeComboBox.setBackground(Color.WHITE);
		SizeComboBox.setMaximumSize(new Dimension(100, 200));
		SizeComboBox.setPreferredSize(new Dimension(26, 20));
		add(SizeComboBox);
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		add(verticalGlue_4);
		
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
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		add(verticalGlue_3);

	}

}
