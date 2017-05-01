//GUI Yasin

package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.SwingConstants;

public class GUIManager extends JFrame {

	private CardLayout cards;
	private boolean isSoundActive;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIManager frame = new GUIManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIManager() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		cards = new CardLayout(0, 0);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(cards);
		
		mainPanel mainPanel = new mainPanel();
		HelpPanel helpPanel = new HelpPanel();
		SettingsPanel settingsPanel = new SettingsPanel();
		gamePanel gamePanel = new gamePanel();
		recordsPanel recordsPanel = new recordsPanel();
		creditsPanel creditsPanel = new creditsPanel();
		contentPane.add(mainPanel, "main");
		contentPane.add(settingsPanel, "settings");
		contentPane.add(gamePanel, "game");
		contentPane.add(helpPanel, "help");
		contentPane.add(recordsPanel, "records");
		contentPane.add(creditsPanel, "credits");
		
		cards.show(contentPane, "main");
		
		
		setContentPane(contentPane);
		
		//mainPanel mainPanel = new mainPanel();
		//contentPane.add(mainPanel, BorderLayout.CENTER);
		
		
		//JPanel panel_1 = new JPanel();
		//contentPane.add(panel_1, BorderLayout.NORTH);
		
	}

	/**
	 * @return the isSoundActive
	 */
	public boolean isSoundActive() {
		return isSoundActive;
	}

	/**
	 * @param isSoundActive the isSoundActive to set
	 */
	public void setSoundActive(boolean isSoundActive) {
		this.isSoundActive = isSoundActive;
	}
	
	

}
