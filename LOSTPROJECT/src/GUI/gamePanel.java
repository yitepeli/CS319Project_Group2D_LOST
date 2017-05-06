package GUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Container;
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
import GameObjectsManagement.EventManagement.Event;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class gamePanel extends JPanel {
	JPanel thisPanel = this;
	GameEngine newGame;
	private boolean isFromArea;
	private int order;
	private int areaChosen;
	private String currentObjectName;
	private String areaDescription;
	private int playerItemChosen;
	private ArrayList<Item> areaItems;
	private ArrayList<Character> charList;
	private String textResult;
	private boolean isNameDefined;
	private boolean inFight;
	private boolean inEvent;
	private String event;
	/**
	 * Create the panel.
	 */
	private PopUpFrame popFrame;
	private PopUpFrame mapFrame;

	public gamePanel(GameEngine game, String s) {
		JTextPane textPane = new JTextPane();;
		inFight=false;
		inEvent=false;
		isNameDefined=true;
		textResult=s;
		this.newGame=game;
		String userDir = System.getProperty("user.dir");
		currentObjectName="";
		order=0;
		isFromArea = false;
		popFrame = null;
		areaChosen = -1; //0 is item, 1 is character, 2 is special event
		playerItemChosen = -1; //0 Boosting, 1 is Craftable, 2 is none
		//popFrame.setVisible(false);
		/*if(newGame==null){
			newGame = new GameEngine();
				newGame.createGameEnvironment(true);
				isNameDefined=false;
				textResult = "Please enter your name: \n";
		}*/
		
		
	    

		areaItems = newGame.getPositionOfUser().getInventory().getStoredItems();
		charList = newGame.getPositionOfUser().getCharacterList();
		event = newGame.getEvent();
		System.out.println(newGame.getPositionOfUser().getName());

		areaDescription = "You are currently in the area " +newGame.getPositionOfUser().getAreaType().getAreaName()+"\nYou can collect the items or fight with animals!!!\n\n";
		if(textResult=="")
			textResult=areaDescription;
		
		ArrayList<Item> playerItems = newGame.getPlayer().getInventory().getStoredItems();
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
				 GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(settingsBtn.getParent());
				 popFrame.setSize(new Dimension(800,900));
				 popFrame.setContentPane(new SettingsPanel(mainFrame));
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

				 popFrame.setSize(new Dimension(800,900));
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
				 GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(helpBtn.getParent());
				 mainFrame.goMain(textResult);	
			}
		});
		headLeftPanel.add(homeBtn);
		
		JButton saveBtn = new JButton("");
		saveBtn.setBorderPainted(false);
		saveBtn.setContentAreaFilled(false);
		saveBtn.setCursor(cursor);
		saveBtn.setIcon(new ImageIcon(userDir + "/src/GUI/save.png"));
		saveBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(saveBtn.getParent());
				 newGame.save(mainFrame.isSoundActive(), mainFrame.getSizeOption());
				 //mainFrame.goMain(textResult);	
			}
		});
		headLeftPanel.add(saveBtn);

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
				 mapPanel mappanel= new mapPanel(newGame);

				 popFrame.setContentPane(mappanel);
				 popFrame.setSize(new Dimension(800,900));
				 popFrame.setVisible(true);
			}
		});
		mapBtn.setIcon(new ImageIcon(userDir + "/src/GUI/map.png"));

		headLeftPanel.add(mapBtn);

		JPanel headRightPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) headRightPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		headPanel.add(headRightPanel);

		JButton campFire = new JButton("");
		campFire.setBorderPainted(false);
		campFire.setContentAreaFilled(false);
		campFire.setCursor(cursor);
		campFire.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 boolean b = newGame.makeCampfire();
				 if(b){
					 GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(campFire.getParent());
					 mainFrame.updateGamePanel(newGame,areaDescription);
				 }
					
				 else{
					 textResult = areaDescription+"You don't have enought item to make fire";
					 textPane.setText(textResult);
				 }
			}
		});
		if(newGame.isCampfireLit())
			campFire.setVisible(false);
		campFire.setIcon(new ImageIcon(userDir + "/src/GUI/campfire.png"));
		headRightPanel.add(campFire);

		JButton cook = new JButton("");
		cook.setBorderPainted(false);
		cook.setContentAreaFilled(false);
		cook.setCursor(cursor);
		cook.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 boolean b = newGame.cookMeat();
				 if(b){
					 GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(cook.getParent());
					 mainFrame.updateGamePanel(newGame,areaDescription);
				 }
					
				 else{
					 textResult = areaDescription+"You don't have enought item to cook meat";
					 textPane.setText(textResult);
				 }
					 
			}
		});
		if(!newGame.isCampfireLit())
			cook.setVisible(false);
		cook.setIcon(new ImageIcon(userDir + "/src/GUI/cook.png"));
		headRightPanel.add(cook);

		JButton boil = new JButton("");
		boil.setBorderPainted(false);
		boil.setContentAreaFilled(false);
		boil.setCursor(cursor);
		boil.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 boolean b = newGame.boilWater();
				 if(b){
					 GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(boil.getParent());
					 mainFrame.updateGamePanel(newGame,areaDescription);
				 }
					
				 else{
					 textResult = areaDescription+"You don't have enought item to boil water";
					 textPane.setText(textResult);
				 }
			}
		});
		if(!newGame.isCampfireLit())
			boil.setVisible(false);
		boil.setIcon(new ImageIcon(userDir + "/src/GUI/boil.png"));
		headRightPanel.add(boil);

		JButton shelter = new JButton("");
		shelter.setBorderPainted(false);
		shelter.setContentAreaFilled(false);
		shelter.setCursor(cursor);
		shelter.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 boolean b = newGame.buildShelter();
				 if(b){
					 GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(shelter.getParent());
					 mainFrame.updateGamePanel(newGame,areaDescription);
				 }
					
				 else{
					 textResult = areaDescription+"You don't have enought item to build shelter";
					 textPane.setText(textResult);
				 }
			}
		});
		if(newGame.getPositionOfUser().isShelterExists())
			shelter.setVisible(false);
		
		shelter.setIcon(new ImageIcon(userDir + "/src/GUI/shelter.png"));
		headRightPanel.add(shelter);

		JButton rest = new JButton("");
		rest.setBorderPainted(false);
		rest.setContentAreaFilled(false);
		rest.setCursor(cursor);
		rest.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 newGame.rest(4);
				 textResult = areaDescription+"You had rest and gained energy";
				 textPane.setText(textResult);
			}
		});

		rest.setIcon(new ImageIcon(userDir + "/src/GUI/rest.png"));
		headRightPanel.add(rest);
		
		JLabel timeLabel = new JLabel(""+newGame.getPlayer().getGameTime());
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

		
		textPane.setText(textResult);
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
		        	boolean doesNeedUpdate=false;
		        	e.consume();
		        	if(isNameDefined){
		        		if(currentObjectName==""){
			        		textResult = areaDescription+"You didn't choose anything";
			        		textPane.setText(textResult);
			        	}
			        	else if(isFromArea==true ||inFight){
			        		if(areaChosen==0){
			        			if(textArea.getText().equals("0")){
			        				if(newGame.takeItem(currentObjectName)){
			        					textResult = areaDescription+"You took the item \""+currentObjectName+"\" and put it in your bag.\n";
			        					doesNeedUpdate=true;
			        				}
			        					
			        				else
			        					textResult = areaDescription+"You cannot take the item because your inventory has reached its capacity.";
			        				textPane.setText(textResult);
			        			}
				        		else{
				        			textResult = areaDescription+"It is not a valid choice.";
				        			textPane.setText(textResult);
				        		}
				        			

				        		currentObjectName ="";
			        		}
			        		else if(areaChosen==1||inFight){
			        			if(textArea.getText().equals("0")){
				        			inFight=true;
				        			
			        				//fightResult="";
			        				String result="";
			        				String total="Fight report: \n";
		        					if(!result.equals("You wounded  " + currentObjectName + "!\n" + "You got killed...") &&
			        						!result.equals("You killed " + currentObjectName) &&
			        						!result.equals("You missed your shot! " + currentObjectName + " did not get any damage!\n"
			        								+ "You got killed...")
			        								&& !result.equals("Dead man cannot fight")){
		        						result = newGame.fight(currentObjectName);
			        					total+=result;

			        					if(!result.equals("You wounded  " + currentObjectName + "!\n" + "You got killed...") &&
				        						!result.equals("You killed " + currentObjectName) &&
				        						!result.equals("You missed your shot! " + currentObjectName + " did not get any damage!\n"
				        								+ "You got killed...")
				        								&& !result.equals("Dead man cannot fight")){
			        						textResult = areaDescription+result+"\nChoose(Option number) an interaction for " + currentObjectName+":\n0) Fight\n1) Run away";
						        			textPane.setText(textResult);
						        			doesNeedUpdate=false;
				        					System.out.println(result);
			        					}
			        					else{
			        						textResult = areaDescription+result;
						        			textPane.setText(textResult);
			        						doesNeedUpdate=true;
			        						currentObjectName ="";
			        					}
		        					}	
			        				if(newGame.isGameOver()){
			        					textResult = areaDescription+result+"\n Oyun Bitti. Kaybettiniz.";
			        					if(newGame.isGameOver()){
			        						JOptionPane.showMessageDialog(null,"Game is over");
			        						 GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(saveBtn.getParent());
							        			textPane.setText(textResult);
			        						 mainFrame.goMain(textResult);	
			        					}
			        					else
						        			textPane.setText(textResult);
			        				}

			        			}
			        			
			        			
			        			else if(textArea.getText().equals("1")){
			        				textResult = areaDescription+"You run away from the animal...";
				        			textPane.setText(textResult);
				        			currentObjectName ="";
			        			}
			        			else{
			        				textResult = areaDescription+"Invalid input";
				        			textPane.setText(textResult);
				        			currentObjectName ="";
			        			}

				        		
			        		}
			        		else if(areaChosen==2){
			        			if(order==0){
			        				if(textArea.getText().equals("1")){
			        					textResult = areaDescription+"You go into event "+currentObjectName;
					        			textPane.setText(textResult);
					        		}
					        		else if(textArea.getText().equals("2")){
					        			textResult = areaDescription;
					        			textPane.setText(textResult);
					        		}
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
			        					newGame.dropItem(currentObjectName);
				        				textResult = areaDescription+"You drop the item \""+currentObjectName+"\""+"\n";
				        				doesNeedUpdate=true;
				        				textPane.setText(textResult);
					        		}
					        		else if(textArea.getText().equals("2")){
					        			newGame.use(currentObjectName);
				        				textResult = areaDescription+"You used the item \""+currentObjectName+"\""+"\n";
					        			textPane.setText(textResult);
					        			doesNeedUpdate=true;
					        		}
			        				currentObjectName="";
			        			}
			        			else if(playerItemChosen == 1){
					        		if(textArea.getText().equals("1")){
					        			newGame.dropItem(currentObjectName);
				        				textResult = areaDescription+"You drop the item \""+currentObjectName+"\""+"\n";
				        				doesNeedUpdate=true;
				        				textPane.setText(textResult);
					        		}
					        		else if(textArea.getText().equals("2")){
					        			ArrayList<Item> craftableItemList = newGame.getCraftableItems(currentObjectName);
					        			String s = areaDescription+"Choose the item which you want to craft\n";
					        			int size = craftableItemList.size();
					        			for(int i = 0 ; i < size; i++){
					        				s += (i + 1) + ". "+craftableItemList.get(i).getName() + " (" + craftableItemList.get(i).getQuantity() + ")\n";
					        			}
					        			textResult = areaDescription+s;
					        			textPane.setText(textResult);
					        			order++;
					        		}
					        		else{
					        			textResult = areaDescription + "Invalid input";
					        			textPane.setText(textResult);
					        			order=0;
					        			currentObjectName="";
					        		}
					        			
			        			}
			        			else if(playerItemChosen == 2){
			        				if(textArea.getText().equals("1")){
			        					newGame.dropItem(currentObjectName);
				        				textResult = areaDescription+"You drop the item \""+currentObjectName+"\""+"\n";
				        				doesNeedUpdate=true;
				        				textPane.setText(textResult);
					        		}
					        		order=0;
			        				currentObjectName="";
			        			}
			        		}
			        		else if(order==1 && playerItemChosen ==1){
			        			int index = Integer.parseInt(textArea.getText());
			        			ArrayList<Item> craftableItemList = newGame.getCraftableItems(currentObjectName);
			        			assert (index > 0) && (index <= craftableItemList.size());

			        			Item item = craftableItemList.get(index - 1);

			        			boolean crafted = newGame.craft(item.getName(), item.getQuantity(), item.getType(),false);

			        			if(crafted){
			        				textResult = areaDescription +item.getName() + "crafted succesfully!";
			        				textPane.setText(textResult);
			        				GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(saveBtn.getParent());
			        				mainFrame.updateGamePanel(newGame, textResult);
			        			}
			        			else
			        				textPane.setText(item.getName() + "could not be crafted!");
			        		}
			        		textArea.setText("");
			        	}
		        	}
		        	else{
		        		if(textArea.getText().equals("")){
		        			textResult = "Please enter a name to start the game:\n";
		        			textPane.setText(textResult);
		        		}else if(!currentObjectName.equals("")){
		        			textResult = "Please enter a name to start the game:\n";
		        			textPane.setText(textResult);
		        			currentObjectName="";
		        		}
		        		else{
		        			newGame.getPlayer().setName(textArea.getText());
		        			isNameDefined=true;
		        			textResult = areaDescription;
		        			textPane.setText("Your name is defined succesfully.\n"+textResult);
		        			textArea.setText("");
		        		}
		        	}



		        	//setVisible(true);
		        	if(!newGame.isGameOver()&&doesNeedUpdate){
			        	GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(textArea.getParent());
						mainFrame.updateGamePanel(newGame,textResult);
		        		
		        	}
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

		JLabel healthLabel = new JLabel(""+newGame.getPlayer().getHealth());
		ImageIcon healthIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/heart.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		healthLabel.setIcon(healthIcon);
		healthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statBarPanel.add(healthLabel);

		JLabel energyLabel = new JLabel(""+newGame.getPlayer().getEnergy());
		ImageIcon energyIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/energy.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		energyLabel.setIcon(energyIcon);
		energyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statBarPanel.add(energyLabel);

		JLabel hungerLabel = new JLabel(""+newGame.getPlayer().getHunger());
		ImageIcon hungerIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/hunger.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		hungerLabel.setIcon(hungerIcon);
		hungerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statBarPanel.add(hungerLabel);

		JLabel thirstLabel = new JLabel(""+newGame.getPlayer().getThirst());
		ImageIcon thirstIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/thirst.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		thirstLabel.setIcon(thirstIcon);
		thirstLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statBarPanel.add(thirstLabel);

		
		//-------------------------------------------------------------------------------------------------------------------------
		//Player inventory capacity
		JPanel playerCapacityPanel = new JPanel();
		playerCapacityPanel.setLayout(new BoxLayout(playerCapacityPanel, BoxLayout.Y_AXIS));
		playerCapacityPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		JLabel capacityLabel = new JLabel("Capacity: "+newGame.getPlayer().getInventory().getCurrentCapacity()+"/"+newGame.getPlayer().getInventory().getMaxCapacity());
		playerCapacityPanel.add(capacityLabel);
		capacityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(playerCapacityPanel);
		//--------------------------------------------------------------------------------------------------------------------------------
		//Player Items Panel

		JPanel playerItemsPanel = new JPanel();
		playerItemsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		playerPanel.add(playerItemsPanel);
		playerItemsPanel.setLayout(new GridLayout(0, 3, 0, 0));
		for(int i = 0; i< playerItems.size();i++){
			JLabel tempItem = new JLabel();
			tempItem.setName(playerItems.get(i).getName());
			Item pitem = playerItems.get(i);
			String iconString = userDir + "/src/images/"+playerItems.get(i).getName().toLowerCase()+".png";
			tempItem.setIcon(new ImageIcon((new ImageIcon(iconString)).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			tempItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					inFight=false;
					isFromArea = false;
					order=0;
					System.out.println(tempItem.getName());
					currentObjectName = tempItem.getName();
					String s = areaDescription+pitem.getDescription()+". Choose(Option number) an interaction for " + tempItem.getName()+":\n";
					ArrayList<String> interactions = pitem.getInteractions();
					playerItemChosen=2;
					for(int j=1; j<interactions.size();j++){
						if(interactions.get(j).equals("craft"))
							playerItemChosen=1;
						if(interactions.get(j).equals("use"))
							playerItemChosen=0;
						s+=j+") "+interactions.get(j)+"\n";
					}
					textResult = s;
					textPane.setText(textResult);
					
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
					inFight=false;
					areaChosen = 0;
					order = 0;
					isFromArea = true;
					System.out.println(tempItem.getName());
					currentObjectName = tempItem.getName();
					String s = areaDescription+item.getDescription()+". Choose(Option number) an interaction for " + tempItem.getName()+":\n";
					for(int j=0; j<1;j++){
						s+=j+") "+item.getInteractions().get(j);
					}
					textResult = s;
					textPane.setText(textResult);
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
			JLabel tempChar = new JLabel();
			tempChar.setName(charList.get(i).getName());
			String iconString = "src/images/"+charList.get(i).getName().replaceAll("\\s","").toLowerCase()+"Icon.png";
			System.out.println(iconString);
			tempChar.setIcon(new ImageIcon((new ImageIcon(iconString)).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			tempChar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					inFight=false;
					
					areaChosen = 1;
					order = 0;
					isFromArea = true;
					System.out.println(tempChar.getName());
					currentObjectName = tempChar.getName();
					File f = new File(userDir + "/src/Sound/Animal Sound Effects/"+currentObjectName+".wav");
        			if(f.exists()){	
        				AudioInputStream audioIn;
	        			try {
	        				audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
	        				Clip clip = AudioSystem.getClip();
	        			    clip.open(audioIn);
	        			    clip.start();
	        			} catch (MalformedURLException e1) {
	        				// TODO Auto-generated catch block
	        				e1.printStackTrace();
	        			} catch (UnsupportedAudioFileException e1) {
	        				// TODO Auto-generated catch block
	        				e1.printStackTrace();
	        			} catch (IOException e1) {
	        				// TODO Auto-generated catch block
	        				e1.printStackTrace();
	        			} catch (LineUnavailableException e1) {
	        				// TODO Auto-generated catch block
	        				e1.printStackTrace();
	        			}  
        			}
					String s = areaDescription+"Choose(Option number) an interaction for " + tempChar.getName()+":\n0) Fight\n1) Run away";
					textResult = s;
					textPane.setText(textResult);
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
			JLabel eventItem = new JLabel(event);
			eventItem.setName(event);
			eventItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					areaChosen = 2;
					order = 0;
					isFromArea = true;
					currentObjectName = event;
					textResult = areaDescription+newGame.enterEvent(event);
					textPane.setText(textResult );
					//List<String> interactionList = newGame.getInteractions(tempItem.getName(), true);
				}
			});
			areaSpecialPanel.add(eventItem);
		
		
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
				GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(helpBtn.getParent());
				mainFrame.updateGamePanel(newGame,"");
			}
		});
		if(!newGame.isUpAvailable()){
			upLabel.setVisible(false);
		}
		else
			upLabel.setVisible(true);
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
				GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(helpBtn.getParent());
				mainFrame.updateGamePanel(newGame,"");
			}
		});
		if(!newGame.isDownAvailable()){
			downLabel.setVisible(false);
		}
		else
			downLabel.setVisible(true);
		directionPanel.add(downLabel, BorderLayout.SOUTH );

		JLabel leftLabel = new JLabel();
		ImageIcon leftIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/left-64.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		leftLabel.setIcon(leftIcon);
		leftLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newGame.navigate("left");
				System.out.println("left");
				GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(helpBtn.getParent());
				mainFrame.updateGamePanel(newGame,"");
			}
		});
		if(!newGame.isLeftAvailable()){
			leftLabel.setVisible(false);
		}
		else
			leftLabel.setVisible(true);
		directionPanel.add(leftLabel, BorderLayout.WEST );

		JLabel rightLabel = new JLabel();
		ImageIcon rightIcon = new ImageIcon(new ImageIcon(userDir + "/src/GUI/rightarrow.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		rightLabel.setIcon(rightIcon);
		rightLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newGame.navigate("right");
				System.out.println("right");
				GUIManager mainFrame= (GUIManager) SwingUtilities.getRoot(helpBtn.getParent());
				mainFrame.updateGamePanel(newGame,"");
				
			}
		});
		if(!newGame.isRightAvailable()){
			rightLabel.setVisible(false);
		}
		else
			rightLabel.setVisible(true);
		directionPanel.add(rightLabel, BorderLayout.EAST );

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				repaint();
				if(newGame.isGameOver())
					System.out.println("Game is over");
			}
		});

	}

	public void setNameDefined(boolean isNameDefined) {
		this.isNameDefined = isNameDefined;
	}

}
