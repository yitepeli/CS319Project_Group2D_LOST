package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class mainPanel extends JPanel {
	Image image;
	/**
	 * Create the panel.
	 */
	public mainPanel() {

		image = null;

		try {
			image = ImageIO.read(new File(getClass().getResource("bg.jpg").toURI()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setBackground(Color.BLACK);
		setBorder(null);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component verticalGlue_7 = Box.createVerticalGlue();
		add(verticalGlue_7);
		
		JLabel nameLabel = new JLabel("LOST");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Chiller", Font.BOLD, 150));
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(nameLabel);
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		add(verticalGlue_4);
		
		
		Font font = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20);
		JButton newGameBtn = new JButton("New Game");
		newGameBtn.setForeground(Color.WHITE);
		newGameBtn.setFont(font);
		newGameBtn.setOpaque(false);
		newGameBtn.setContentAreaFilled(false);
		newGameBtn.setBorderPainted(false);
		newGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(newGameBtn);
		
		//Component verticalGlue = Box.createVerticalGlue();
		//panel.add(verticalGlue);
		
		JButton loadGameBtn = new JButton("Load Game");
		loadGameBtn.setForeground(Color.WHITE);
		loadGameBtn.setFont(font);
		loadGameBtn.setOpaque(false);
		loadGameBtn.setContentAreaFilled(false);
		loadGameBtn.setBorderPainted(false);
		loadGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(loadGameBtn);
		
		//Component verticalGlue_1 = Box.createVerticalGlue();
		//panel.add(verticalGlue_1);
		
		JButton settingsBtn = new JButton("Settings");
		settingsBtn.setForeground(Color.WHITE);
		settingsBtn.setFont(font);
		settingsBtn.setOpaque(false);
		settingsBtn.setContentAreaFilled(false);
		settingsBtn.setBorderPainted(false);
		settingsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		settingsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame= (JFrame) SwingUtilities.getRoot(settingsBtn.getParent());
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(new SettingsPanel());
				mainFrame.validate();
				mainFrame.setVisible(true);
				
			}
		});
		add(settingsBtn);
		
		//Component verticalGlue_5 = Box.createVerticalGlue();
		//panel.add(verticalGlue_5);
		
		JButton recordsBtn = new JButton("Records");
		recordsBtn.setForeground(Color.WHITE);
		recordsBtn.setFont(font);
		recordsBtn.setOpaque(false);
		recordsBtn.setContentAreaFilled(false);
		recordsBtn.setBorderPainted(false);
		recordsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(recordsBtn);
		
		//Component verticalGlue_6 = Box.createVerticalGlue();
		//panel.add(verticalGlue_6);
		
		JButton creditsBtn = new JButton("Credits");
		creditsBtn.setForeground(Color.WHITE);
		creditsBtn.setFont(font);
		creditsBtn.setOpaque(false);
		creditsBtn.setContentAreaFilled(false);
		creditsBtn.setBorderPainted(false);
		creditsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		creditsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame= (JFrame) SwingUtilities.getRoot(creditsBtn.getParent());
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(new creditsPanel());
				mainFrame.validate();
		        mainFrame.setVisible(true);
				
			}
		});
		add(creditsBtn);
		
		//Component verticalGlue_2 = Box.createVerticalGlue();
		//panel.add(verticalGlue_2);
		
		JButton helpBtn = new JButton("Help");
		helpBtn.setForeground(Color.WHITE);
		helpBtn.setFont(font);
		helpBtn.setOpaque(false);
		helpBtn.setContentAreaFilled(false);
		helpBtn.setBorderPainted(false);
		helpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		helpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame= (JFrame) SwingUtilities.getRoot(helpBtn.getParent());
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(new HelpPanel());
				mainFrame.validate();
		        mainFrame.setVisible(true);
				
			}
		});
		add(helpBtn);
		
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		add(verticalGlue_3);
		
		
		
		
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}
