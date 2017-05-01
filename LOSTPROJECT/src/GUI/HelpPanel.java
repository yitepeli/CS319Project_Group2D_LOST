package GUI;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Desktop;

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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

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
		
		JLabel wikiLabel = new JLabel("Open Online Wiki for taking guidelines about game");
		wikiLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://www.google.com").toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		wikiLabel.setIcon(new ImageIcon(HelpPanel.class.getResource("/GUI/link-512.png")));
		wikiLabel.setBackground(Color.BLACK);
		wikiLabel.setForeground(Color.WHITE);
		wikiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(wikiLabel);
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		add(verticalGlue_4);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		add(verticalGlue_2);
		
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
		add(returnMainBtn);
		
		Component verticalGlue5 = Box.createVerticalGlue();
		add(verticalGlue5);

	}

}
