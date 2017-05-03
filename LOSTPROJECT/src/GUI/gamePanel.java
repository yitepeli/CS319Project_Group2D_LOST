package GUI;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import GameManagement.GameEngine;
import GameObjectsManagement.ItemManagement.*;
import GameObjectsManagement.CharacterManagement.Character;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class gamePanel extends JPanel {
	GameEngine newGame;
	private boolean isFromArea;
	private int order;
	private int areaChosen;
	private String currentObjectName;
	private String areaDescription;
	private int playerItemChosen;
	private ArrayList<Item> areaItems;
	private ArrayList<Character> charList;
	/**
	 * Create the panel.
	 */
	private PopUpFrame popFrame;
	private PopUpFrame mapFrame;

	public gamePanel() {
		String userDir = System.getProperty("user.dir");
		currentObjectName="";
		order=0;
		isFromArea = false;
		popFrame = null;
		areaChosen = -1; //0 is item, 1 is character, 2 is special event
		playerItemChosen = -1; //0 Boosting, 1 is Craftable, 2 is none
		//popFrame.setVisible(false);
		newGame = new GameEngine();
		newGame.createGameEnvironment(true);

		areaItems = newGame.getPositionOfUser().getInventory().getStoredItems();
		charList = newGame.getPositionOfUser().getCharacterList();
		System.out.println(newGame.getPositionOfUser().getName());

		areaDescription = "You are currently in the area " +newGame.getPositionOfUser().getAreaType().getAreaName()+"\nYou can collect the items or fight with animals!!!\n\n";
		Object[][] playerItems = {

		};

		Object[] characters = {
				"Canavar",
				"Canavar2",
				"Canavar3",
				"Canavar4",
				"Canavar5",
				"Canavar6",
				"Canavar7",
				"Canavar8"
		};

		Object[] special = {
				"Spe1",
				"Spe2"
		};



		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		JPanel headPanel = new JPanel();
		add(headPanel);
		headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));
		JPanel headLeftPanel = new JPanel();
		//panel_6.setMaximumSize(new Dimension(750, 600));
		//panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout flowLayout = (FlowLayout) headLeftPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		headPanel.add(headLeftPanel);

		Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);


		JButton settingsBtn = new JButton("");
		settingsBtn.setBorderPainted(false);
		settingsBtn.setContentAreaFilled(false);
		settingsBtn.setCursor(cursor);
		settingsBtn.setIcon(new ImageIcon(userDir + "/src/GUI/settingsufak.png"));
		settingsBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if(popFrame==null){
					 popFrame = new PopUpFrame();
					 popFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				 }

				 popFrame.setContentPane(new SettingsPanel());
				 popFrame.setVisible(true);
			}
		});
		headLeftPanel.add(settingsBtn);

		JButton helpBtn = new JButton("");
		helpBtn.setIcon(new ImageIcon(userDir + "/src/GUI/help.png"));
		helpBtn.setContentAreaFilled(false);
		helpBtn.setBorderPainted(false);
		helpBtn.setCursor(cursor);
		helpBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if(popFrame==null){
					 popFrame = new PopUpFrame();
					 popFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				 }

				 popFrame.setContentPane(new HelpPanel());
				 popFrame.setVisible(true);
			}
		});
		headLeftPanel.add(helpBtn);

		JButton homeBtn = new JButton("");
		homeBtn.setBorderPainted(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setCursor(cursor);
		homeBtn.setIcon(new ImageIcon(userDir + "/src/GUI/home.png"));
		homeBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JFrame mainFrame= (JFrame) SwingUtilities.getRoot(helpBtn.getParent());
					CardLayout layout = (CardLayout)mainFrame.getContentPane().getLayout();
					layout.show(mainFrame.getContentPane(), "main");
					mainFrame.validate();
			        mainFrame.setVisible(true);
			}
		});
		headLeftPanel.add(homeBtn);

		JButton mapBtn = new JButton("");
		mapBtn.setBorderPainted(false);
		mapBtn.setContentAreaFilled(false);
		mapBtn.setCursor(cursor);
		mapBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if(popFrame==null){
					 popFrame = new PopUpFrame();
					 popFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				 }
				// mapPanel mappanel= new mapPanel(newGame.getPositionOfUser());

				 //popFrame.setContentPane(mappanel);
				// popFrame.setVisible(true);
			}
		});
		mapBtn.setIcon(new ImageIcon(userDir + "/src/GUI/map.png"));

		headLeftPanel.add(mapBtn);

		JPanel headRightPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) headRightPanel.getLayout();
		//panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		headPanel.add(headRightPanel);

		JLabel timeLabel = new JLabel("12:11");
		timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		timeLabel.setFocusable(false);
		timeLabel.setIcon(new ImageIcon(userDir + "/src/GUI/time.png"));
		timeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		headRightPanel.add(timeLabel);

		JPanel midPanel = new JPanel();
		add(midPanel);
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.X_AXIS));

		JPanel midLeftPanel = new JPanel();
		midLeftPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		midLeftPanel.setMinimumSize(new Dimension(200, 200));
		midPanel.add(midLeftPanel);
		midLeftPanel.setLayout(new BorderLayout(0, 0));

		JTextPane textPane = new JTextPane();
		textPane.setText(areaDescription);
		textPane.setEditable(false);
		textPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		midLeftPanel.add(textPane, BorderLayout.CENTER);

		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		midLeftPanel.add(textArea, BorderLayout.SOUTH);
		textArea.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){
		        	e.consume();
		        	if(currentObjectName==""){
		        		textPane.setText(areaDescription+"You didn't choose anything");
		        	}
		        	else if(isFromArea==true){
		        		if(areaChosen==0){
		        			if(textArea.getText().equals("0"))
			        			textPane.setText(areaDescription+"You took the item \""+currentObjectName+"\" and put it in your bag.\n");
			        		else
			        			textPane.setText(areaDescription+"It is not a valid choice.");

			        		currentObjectName ="";
		        		}
		        		else if(areaChosen==1){
		        			if(textArea.getText().equals("0")){
		        				String result="";
		        				while(!result.equals("You wounded  " + currentObjectName + "!\n" + "You got killed...") &&
		        						!result.equals("You killed " + currentObjectName) &&
		        						!result.equals("You missed your shot! " + currentObjectName + " did not get any damage!\n"
		        								+ "You got killed...")){
		        					//result = newGame.fight(currentObjectName);
				        			textPane.setText(areaDescription+result);
		        				}
		        				if(newGame.isGameOver()){
		        					textPane.setText(areaDescription+result+"\n Oyun Bitti. Kaybettiniz.");
		        				}

		        			}
		        			else if(textArea.getText().equals("1")){
		        				textPane.setText(areaDescription+"You run away from the animal...");
		        			}
		        			else
		        				textPane.setText(areaDescription+"Invalid input");

			        		currentObjectName ="";
		        		}
		        		else if(areaChosen==2){
		        			if(order==0){
		        				if(textArea.getText().equals("1"))
				        			textPane.setText(areaDescription+"You go into event "+currentObjectName);
				        		else if(textArea.getText().equals("2"))
				        			textPane.setText(areaDescription);
		        				order++;
		        			}
		        			else if(order==1){
				        		currentObjectName ="";
		        			}

		        		}
		        		textArea.setText("");

		        	}
		        	else if(isFromArea==false){
		        		if(order==0){
		        			if(playerItemChosen == 0){
		        				if(textArea.getText().equals("1")){
				        			textPane.setText("Info");
		        				}
				        		else if(textArea.getText().equals("2")){
				        			textPane.setText("Drop");
				        		}
				        		else if(textArea.getText().equals("3")){
				        			textPane.setText("Use");
				        		}
		        				currentObjectName="";
		        			}
		        			else if(playerItemChosen == 1){
		        				if(textArea.getText().equals("1")){
				        			textPane.setText("Info");
				        			currentObjectName="";
		        				}
				        		else if(textArea.getText().equals("2")){
				        			textPane.setText("Drop");
				        			currentObjectName="";
				        		}
				        		else if(textArea.getText().equals("3")){
				        			textPane.setText("Craft");
			        				order++;
				        		}
		        			}
		        			else if(playerItemChosen == 2){
		        				if(textArea.getText().equals("1")){
				        			textPane.setText("Info");
		        				}
				        		else if(textArea.getText().equals("2")){
				        			textPane.setText("Drop");
				        		}
		        				currentObjectName="";
		        			}
		        		}
		        		else if(order==1 && playerItemChosen ==2){
		        			ArrayList<String> craftableItemList = newGame.getCraftableItems(currentObjectName);
		        			String s = areaDescription+"Choose the item which you want to craft\n";
		        			for(int i = 0 ; i<craftableItemList.size();i++){
		        				s += i+". "+craftableItemList.get(i)+"\n";
		        			}
		        			textPane.setText(s);
		        		}
		        		else if(order==2){
		        			;
		        		}
		        		textArea.setText("");
		        	}



		        	setVisible(true);

		        }
		    }

		    @Override
		    public void keyTyped(KeyEvent e) {
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {
		    }
		});









		//--------------------------------------------------------------------------------------------------------------------------------
		//Player Panel


		JPanel playerPanel = new JPanel();
		playerPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		midPanel.add(playerPanel);
		playerPanel.setMinimumSize(new Dimension(400,0));
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

		//--------------------------------------------------------------------------------------------------------------------------------
		//Player Stats Panel

		JPanel statBarPanel = new JPanel();
		//statBarPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		playerPanel.add(statBarPanel);
		statBarPanel.setLayout(new BoxLayout(statBarPanel, BoxLayout.Y_AXIS));

		JLabel healthLabel = new JLabel("60");
		ImageIcon healthIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/heart.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		healthLabel.setIcon(healthIcon);
		healthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statBarPanel.add(healthLabel);

		JLabel energyLabel = new JLabel("55");
		ImageIcon energyIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/energy.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		energyLabel.setIcon(energyIcon);
		energyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statBarPanel.add(energyLabel);

		JLabel hungerLabel = new JLabel("15");
		ImageIcon hungerIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/hunger.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		hungerLabel.setIcon(hungerIcon);
		hungerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statBarPanel.add(hungerLabel);

		JLabel thirstLabel = new JLabel("8");
		ImageIcon thirstIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/thirst.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		thirstLabel.setIcon(thirstIcon);
		thirstLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statBarPanel.add(thirstLabel);

		//--------------------------------------------------------------------------------------------------------------------------------
		//Player Items Panel

		JPanel playerItemsPanel = new JPanel();
		playerItemsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		playerPanel.add(playerItemsPanel);
		playerItemsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		for(int i = 0; i< playerItems.length;i++){
			JLabel tempItem = new JLabel(/*(String)areaItems[i][0]*/);
			tempItem.setName((String)playerItems[i][0]);
			tempItem.setIcon(new ImageIcon(((ImageIcon)playerItems[i][1]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			tempItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					isFromArea = false;
					order=0;
					System.out.println(tempItem.getName());
					currentObjectName = tempItem.getName();
					/*if(newGame.getPlayer().getInventory().getItem(tempItem.getName()). instanceof BoostingItem){
						textPane.setText("Choose for " + tempItem.getName()+"\n1. Information\n2.Drop\n3.Use" );
						playerItemChosen = 0;
					}
					else if(newGame.getCraftableItems(tempItem.getName())==null){
						textPane.setText("Choose for " + tempItem.getName()+"\n1. Information\n2.Drop" );
						playerItemChosen = 2;
					}
					else{
						textPane.setText("Choose for " + tempItem.getName()+"\n1. Information\n2.Drop\n3.Craft" );
						playerItemChosen = 1;
					}*/

					//List<String> interactionList = newGame.getInteractions(tempItem.getName(), true);
				}
			});
			playerItemsPanel.add(tempItem);
		}


		//--------------------------------------------------------------------------------------------------------------------------------
		//Footer Panel

		JPanel footerPanel = new JPanel();
		add(footerPanel);
		footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));


		//--------------------------------------------------------------------------------------------------------------------------------
		//Area Objects Panel

		JPanel areaObjectsPanel = new JPanel();
		areaObjectsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		areaObjectsPanel.setLayout(new BoxLayout(areaObjectsPanel, BoxLayout.Y_AXIS));
		footerPanel.add(areaObjectsPanel);


		//--------------------------------------------------------------------------------------------------------------------------------
		//Area Items Panel

		JPanel areaItemsPanel = new JPanel();
		areaItemsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		areaItemsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		areaObjectsPanel.add(areaItemsPanel);

		for(int i = 0; i< areaItems.size();i++){
			JLabel tempItem = new JLabel(/*(String)areaItems[i][0]*/);
			tempItem.setName(areaItems.get(i).getName());
			Item item = areaItems.get(i);
			String iconString = userDir + "/src/images/"+areaItems.get(i).getName().toLowerCase()+".png";
			System.out.println(iconString);
			tempItem.setIcon(new ImageIcon((new ImageIcon(iconString)).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			tempItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					areaChosen = 0;
					order = 0;
					isFromArea = true;
					System.out.println(tempItem.getName());
					currentObjectName = tempItem.getName();
					String s = areaDescription+item.getDescription()+". Choose(Option number) an interaction for " + tempItem.getName()+":\n";
					for(int j=0; j<1;j++){
						s+=j+") "+item.getInteractions().get(j);
					}
					textPane.setText(s);
					//List<String> interactionList = newGame.getInteractions(tempItem.getName(), true);
				}
			});
			areaItemsPanel.add(tempItem);
		}


		//--------------------------------------------------------------------------------------------------------------------------------
		//Area Characters, Special Events Panel

		JPanel areaCharsAndSpecialPanel = new JPanel();
		areaCharsAndSpecialPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		areaCharsAndSpecialPanel.setLayout(new BoxLayout(areaCharsAndSpecialPanel, BoxLayout.X_AXIS));
		areaObjectsPanel.add(areaCharsAndSpecialPanel);


		//--------------------------------------------------------------------------------------------------------------------------------
		//Area Characters Panel

		JPanel areaCharsPanel = new JPanel();
		//areaCharsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		areaCharsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		areaCharsAndSpecialPanel.add(areaCharsPanel);

		for(int i = 0; i< charList.size();i++){
			JLabel tempChar = new JLabel(charList.get(i).getName());
			tempChar.setName(charList.get(i).getName());
			//String iconString = "/images/"+charList.get(i).getName().toLowerCase()+".png";
			//System.out.println(iconString);
			//tempItem.setIcon(new ImageIcon((new ImageIcon(gamePanel.class.getResource(iconString))).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			tempChar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					areaChosen = 1;
					order = 0;
					isFromArea = true;
					System.out.println(tempChar.getName());
					currentObjectName = tempChar.getName();
					String s = areaDescription+"Choose(Option number) an interaction for " + tempChar.getName()+":\n0) Fight\n1) Run away";
					textPane.setText(s);
					//List<String> interactionList = newGame.getInteractions(tempItem.getName(), true);
				}
			});
			areaCharsPanel.add(tempChar);
		}


		//--------------------------------------------------------------------------------------------------------------------------------
		//Area Special Events Panel

		JPanel areaSpecialPanel = new JPanel();
		areaSpecialPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		areaSpecialPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		areaCharsAndSpecialPanel.add(areaSpecialPanel);
		for(int i = 0; i< special.length;i++){
			JLabel tempItem = new JLabel((String)special[i]);
			tempItem.setName((String)special[i]);
			//tempItem.setIcon(new ImageIcon(((ImageIcon)areaItems[i][1]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			tempItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					areaChosen = 2;
					order = 0;
					isFromArea = true;
					System.out.println(tempItem.getName());
					currentObjectName = tempItem.getName();
					textPane.setText(areaDescription+"Choose for " + tempItem.getName()+"\n1) Description\n2) Go into special Event\n" );
					//List<String> interactionList = newGame.getInteractions(tempItem.getName(), true);
				}
			});
			areaSpecialPanel.add(tempItem);
		}


		//--------------------------------------------------------------------------------------------------------------------------------
		//Direction Panel

		JPanel directionPanel = new JPanel();
		directionPanel.setLayout(new BorderLayout(0, 0));
		directionPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		directionPanel.setMaximumSize(new Dimension(200,120));
		footerPanel.add(directionPanel);

		JLabel upLabel = new JLabel();
		ImageIcon upIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/up-128.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		upLabel.setIcon(upIcon);
		upLabel.setHorizontalAlignment(SwingConstants.CENTER);
		upLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newGame.navigate("up");
				System.out.println("up");
				setVisible(true);
			}
		});
		directionPanel.add(upLabel, BorderLayout.NORTH );

		JLabel downLabel = new JLabel();
		ImageIcon downIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/down-64.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		downLabel.setIcon(downIcon);
		downLabel.setHorizontalAlignment(SwingConstants.CENTER);
		downLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newGame.navigate("down");
				System.out.println("down");
				setVisible(true);
			}
		});
		directionPanel.add(downLabel, BorderLayout.SOUTH );

		JLabel leftLabel = new JLabel();
		ImageIcon leftIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/left-64.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		leftLabel.setIcon(leftIcon);
		leftLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newGame.navigate("left");
				System.out.println("left");
				setVisible(true);
			}
		});
		directionPanel.add(leftLabel, BorderLayout.WEST );

		JLabel rightLabel = new JLabel();
		ImageIcon rightIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/rightarrow.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		rightLabel.setIcon(rightIcon);
		rightLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newGame.navigate("right");
				System.out.println("right");
				setVisible(true);
			}
		});
		directionPanel.add(rightLabel, BorderLayout.EAST );

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(newGame.isGameOver())
					System.out.println("Game is over");
			}
		});

	}

}
