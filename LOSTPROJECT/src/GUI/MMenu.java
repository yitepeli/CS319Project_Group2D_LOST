//GUI Yasin

package GUI;

import java.awt.BorderLayout;
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

public class MMenu extends JFrame {
	
	Image image;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MMenu frame = new MMenu();
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
	public MMenu() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel(){
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
		};
		panel.setBackground(Color.BLACK);
		panel.setBorder(null);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		Component verticalGlue_7 = Box.createVerticalGlue();
		panel.add(verticalGlue_7);
		
		JLabel nameLabel = new JLabel("LOST");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Chiller", Font.BOLD, 150));
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(nameLabel);
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		panel.add(verticalGlue_4);
		
		
		Font font = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20);
		JButton newGameBtn = new JButton("New Game");
		newGameBtn.setForeground(Color.WHITE);
		newGameBtn.setFont(font);
		newGameBtn.setOpaque(false);
		newGameBtn.setContentAreaFilled(false);
		newGameBtn.setBorderPainted(false);
		newGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(newGameBtn);
		
		//Component verticalGlue = Box.createVerticalGlue();
		//panel.add(verticalGlue);
		
		JButton loadGameBtn = new JButton("Load Game");
		loadGameBtn.setForeground(Color.WHITE);
		loadGameBtn.setFont(font);
		loadGameBtn.setOpaque(false);
		loadGameBtn.setContentAreaFilled(false);
		loadGameBtn.setBorderPainted(false);
		loadGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(loadGameBtn);
		
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
				contentPane.removeAll();
				contentPane.add(new SettingsPanel());
				validate();
		          setVisible(true);
				
			}
		});
		panel.add(settingsBtn);
		
		//Component verticalGlue_5 = Box.createVerticalGlue();
		//panel.add(verticalGlue_5);
		
		JButton recordsBtn = new JButton("Records");
		recordsBtn.setForeground(Color.WHITE);
		recordsBtn.setFont(font);
		recordsBtn.setOpaque(false);
		recordsBtn.setContentAreaFilled(false);
		recordsBtn.setBorderPainted(false);
		recordsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(recordsBtn);
		
		//Component verticalGlue_6 = Box.createVerticalGlue();
		//panel.add(verticalGlue_6);
		
		JButton creditsBtn = new JButton("Credits");
		creditsBtn.setForeground(Color.WHITE);
		creditsBtn.setFont(font);
		creditsBtn.setOpaque(false);
		creditsBtn.setContentAreaFilled(false);
		creditsBtn.setBorderPainted(false);
		creditsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(creditsBtn);
		
		//Component verticalGlue_2 = Box.createVerticalGlue();
		//panel.add(verticalGlue_2);
		
		JButton helpBtn = new JButton("Help");
		helpBtn.setForeground(Color.WHITE);
		helpBtn.setFont(font);
		helpBtn.setOpaque(false);
		helpBtn.setContentAreaFilled(false);
		helpBtn.setBorderPainted(false);
		helpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(helpBtn);
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		panel.add(verticalGlue_3);
		
		//JPanel panel_1 = new JPanel();
		//contentPane.add(panel_1, BorderLayout.NORTH);
		
	}
	
	

}
