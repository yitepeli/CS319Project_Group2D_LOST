package GUI;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Cursor;

public class gamePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public gamePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		JPanel headPanel = new JPanel();
		add(headPanel);
		headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));
		JPanel panel_6 = new JPanel();
		//panel_6.setMaximumSize(new Dimension(750, 600));
		//panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		headPanel.add(panel_6);
		
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		
		JButton settingsBtn = new JButton("");
		settingsBtn.setBorderPainted(false);
		settingsBtn.setContentAreaFilled(false);
		settingsBtn.setCursor(cursor);
		settingsBtn.setIcon(new ImageIcon(gamePanel.class.getResource("/GUI/settingsufak.png")));
		panel_6.add(settingsBtn);
		
		JButton helpBtn = new JButton("");
		helpBtn.setIcon(new ImageIcon(gamePanel.class.getResource("/GUI/help.png")));
		helpBtn.setContentAreaFilled(false);
		helpBtn.setBorderPainted(false);
		helpBtn.setCursor(cursor);
		panel_6.add(helpBtn);
		
		JButton homeBtn = new JButton("");
		homeBtn.setBorderPainted(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setCursor(cursor);
		homeBtn.setIcon(new ImageIcon(gamePanel.class.getResource("/GUI/home.png")));
		panel_6.add(homeBtn);
		
		JButton mapBtn = new JButton("");
		mapBtn.setBorderPainted(false);
		mapBtn.setContentAreaFilled(false);
		mapBtn.setCursor(cursor);
		mapBtn.setIcon(new ImageIcon(gamePanel.class.getResource("/GUI/map.png")));
		panel_6.add(mapBtn);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
		//panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		headPanel.add(panel_7);
		
		JLabel timeLabel = new JLabel("12:11");
		timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		timeLabel.setFocusable(false);
		timeLabel.setIcon(new ImageIcon(gamePanel.class.getResource("/GUI/time.png")));
		timeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_7.add(timeLabel);
		
		JPanel midPanel = new JPanel();
		add(midPanel);
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_3.setMinimumSize(new Dimension(200, 200));
		midPanel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.add(textArea, BorderLayout.SOUTH);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.add(textPane, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		midPanel.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JLabel healthLabel = new JLabel("60");
		ImageIcon healthIcon = new ImageIcon(new ImageIcon(gamePanel.class.getResource("/GUI/heart.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		healthLabel.setIcon(healthIcon);
		healthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_5.add(healthLabel);
		
		JLabel energyLabel = new JLabel("55");
		ImageIcon energyIcon = new ImageIcon(new ImageIcon(gamePanel.class.getResource("/GUI/energy.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		energyLabel.setIcon(energyIcon);
		energyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_5.add(energyLabel);
		
		JLabel hungerLabel = new JLabel("15");
		ImageIcon hungerIcon = new ImageIcon(new ImageIcon(gamePanel.class.getResource("/GUI/hunger.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		hungerLabel.setIcon(hungerIcon);
		hungerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_5.add(hungerLabel);
		
		JLabel thirstLabel = new JLabel("8");
		ImageIcon thirstIcon = new ImageIcon(new ImageIcon(gamePanel.class.getResource("/GUI/thirst.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		thirstLabel.setIcon(thirstIcon);
		thirstLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_5.add(thirstLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.add(panel);
		
		JPanel footerPanel = new JPanel();
		add(footerPanel);
		footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		footerPanel.add(panel_8);
		
		
		
		
		
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(new BorderLayout(0, 0));
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		footerPanel.add(panel_9);
		
		JLabel upLabel = new JLabel();
		ImageIcon upIcon = new ImageIcon(gamePanel.class.getResource("/GUI/up-128.png"));
		upLabel.setIcon(upIcon);
		upLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(upLabel, BorderLayout.NORTH );
		
		JLabel downLabel = new JLabel();
		ImageIcon downIcon = new ImageIcon(gamePanel.class.getResource("/GUI/down-64.png"));
		downLabel.setIcon(downIcon);
		downLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(downLabel, BorderLayout.SOUTH );
		
		JLabel leftLabel = new JLabel();
		ImageIcon leftIcon = new ImageIcon(gamePanel.class.getResource("/GUI/left-64.png"));
		leftLabel.setIcon(leftIcon);
		panel_9.add(leftLabel, BorderLayout.WEST );
		
		JLabel rightLabel = new JLabel();
		ImageIcon rightIcon = new ImageIcon(gamePanel.class.getResource("/GUI/rightarrow.png"));
		rightLabel.setIcon(rightIcon);
		panel_9.add(rightLabel, BorderLayout.EAST );

	}

}
