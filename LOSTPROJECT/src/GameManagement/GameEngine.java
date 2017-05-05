package GameManagement;
/*
** GameEngine Class
** asdasd
** GameEngine class is where all of game operations are carried out.
** 
**
** Author: Eren Bilaloglu
** File created:  29.04.2017
** Last modified: 30.04.2017
** Last modified by: Eren Bilaloglu
*/

import GameObjectsManagement.AreaManagement.*;
import GameObjectsManagement.CharacterManagement.*;
import GameObjectsManagement.CharacterManagement.Character;
import GameObjectsManagement.ItemManagement.*;
import DatabaseManagement.DatabaseManager;

import java.awt.image.BufferedImage;
import java.util.*;


public class GameEngine {

	private DatabaseManager databaseManager;
	private MapManager mapManager;
	private UpdateManager updateManager;
	private Area positionOfUser;
	private Player player;

	public GameEngine(){

	}


	public static boolean isUserExists(){
		return DatabaseManager.isUserExists();
	}

	private Item readItem(String itemName, String type){
		return null;
	}


	public void createGameEnvironment(boolean isNewGame){

		DatabaseManager.initUserStaticId();

		databaseManager = new DatabaseManager();
		updateManager = new UpdateManager();
		mapManager = new MapManager();
		updateManager.createGameEnvironment(isNewGame,databaseManager);//creating areas
		positionOfUser = updateManager.getPositionOfUser();//initial position of user


		if(!isNewGame){
			long userId = databaseManager.getUserId();//accessing user preferences and getting user id..
			player = (Player)databaseManager.readData(userId,DatabaseManager.ReadAction.READ_PLAYER);//cloud based data!
		}
		else{
			player = new Player();//passing default values
			player.setCurrentPosition(positionOfUser.getAreaType().getAreaName());
			databaseManager.processData(player, DatabaseManager.WriteAction.WRITE_PLAYER);//creating new player instance in persistency layer
			databaseManager.createUserLocal(player.getCloudId());//creating user local in prefs
		}
	}


	public void navigate(String direction){

		if(direction.equals("left")){
			positionOfUser = positionOfUser.getLeftNeighbour();
		}
		else if(direction.equals("right")){
			positionOfUser = positionOfUser.getRightNeighbour();

		}
		else if(direction.equals("up")){
			positionOfUser = positionOfUser.getUpNeighbour();

		}
		else if(direction.equals("down")){
			positionOfUser = positionOfUser.getDownNeighbour();
		}
		String currentAreaName = positionOfUser.getAreaType().getAreaName();
		player.setCurrentPosition(currentAreaName);
		databaseManager.setWorkingArea(currentAreaName);
		databaseManager.processData(player,DatabaseManager.WriteAction.WRITE_PLAYER);//update player data
		mapManager.processMapp(positionOfUser);//updating map
	}

	//Crafting methods
	/*
	public HashMap<String, Integer> getCraftableItems(String itemName){


		//player should have the item in its inventory before attempting to craft it
		assert this.player.hasItem(itemName, 1);
		
		ArrayList<Item> craftableItemList = ((CraftableItem)this.player.getItem(itemName)).getCraftableItemsList();
		HashMap<String, Integer> map = new HashMap<String, Integer>;

		for(Item item : craftableItemList)
			map.put(item.getName(), item.getQuantity());

		return map;
	}
	*/
	public ArrayList<Item> getCraftableItems(String itemName){
		return ((CraftableItem)this.player.getItem(itemName)).getCraftableItemsList();
	}


	public boolean craft(String itemName, int amount, String type){
		CraftableItem item = (CraftableItem)this.readItem(itemName, type);
		assert item != null;

		ArrayList<Item> requiredItemsList = item.getRequiredItemsList();
		
		assert requiredItemsList != null;
		for(Item tmpItem : requiredItemsList)
			if(!this.player.hasItem(tmpItem.getName(), tmpItem.getQuantity()))
				return false;

		player.craft(item, amount);

		return true;		
	}
	
	
	public String fight(String characterName){
		Character character=null;
		for(int i=0;i<getPositionOfUser().getCharacterList().size();i++){
			if(getPositionOfUser().getCharacterList().get(i).getName().equals(characterName))
				character = getPositionOfUser().getCharacterList().get(i);
		}
		//just in case
		if(player.getHealth() <= 0 || character.getHealth() <= 0)
			return "Dead man cannot fight";
	
		else{			
			Random randomGen = new Random();
			int missedShotPlayer = randomGen.nextInt(1+(int)((1-character.getEscapeChance())*10))+1; //character's chance of escape from attack
			int missedShotCharacter = randomGen.nextInt(1+(int)((1-player.getEscapeChance())*10))+1; //player's chance of escape from attack
			
			if(missedShotPlayer == 1){							
				if(character instanceof AggresiveCharacter){			
					if(missedShotCharacter == 1)
						return "You missed your shot! " + character.getName() + " did not get any damage!\n"
								+ character.getName() + "missed its shot! You did not get any damage!";
					
					else{
						if(player.getDefense() == ((AggresiveCharacter)character).getAttack())
							player.updateHealth(-10);
						
						if(player.getDefense() > ((AggresiveCharacter)character).getAttack())
							player.updateHealth(-Math.abs((((AggresiveCharacter)character).getAttack()-player.getDefense()))/2);
						
						if(player.getDefense() < ((AggresiveCharacter)character).getAttack())
							player.updateHealth(-((AggresiveCharacter)character).getAttack());		
						
						if(player.getHealth() > 0)
							return "You missed your shot! " + character.getName() + " did not get any damage!\n"
									+ "You got wounded!";				
						else
							return "You missed your shot! " + character.getName() + " did not get any damage!\n"
									+ "You got killed...";			
						}
					}	
				
				else
					return "You missed your shot! " + character.getName() + " did not get any damage!";
				}
			
			else{			
				if(player.getAttack() == character.getDefense())
					character.updateHealth(-10);
				
				if(player.getAttack() < character.getDefense())
					character.updateHealth(-Math.abs((player.getAttack()-character.getDefense()))/2);
				
				if(player.getAttack() > character.getDefense())
					character.updateHealth(-player.getAttack());
				
				//if character dies, player gets all of its items
				if(character.getHealth() <= 0){
					ArrayList<Item> characterItems = character.getInventory().getStoredItems();
					for(int i=0; i<characterItems.size(); i++)
						player.addItem(characterItems.get(i));	
					
					return "You killed " + character.getName();
				}
				
				else{			
					if(character instanceof AggresiveCharacter){						
						if(missedShotCharacter == 1)
							return "You wounded  " + character.getName() + "!\n"
							+ character.getName() + "missed its shot! You did not get any damage!";
						
						else{						
							if(player.getDefense() == ((AggresiveCharacter)character).getAttack())
								player.updateHealth(-10);
							
							if(player.getDefense() > ((AggresiveCharacter)character).getAttack())
								player.updateHealth(-Math.abs((((AggresiveCharacter)character).getAttack()-player.getDefense()))/2);
							
							if(player.getDefense() < ((AggresiveCharacter)character).getAttack())
								player.updateHealth(-((AggresiveCharacter)character).getAttack());		
							
							if(player.getHealth() > 0)
								return "You wounded  " + character.getName() + "!\n" + "You got wounded!";	
							
							else
								return "You wounded  " + character.getName() + "!\n" + "You got killed...";	
						}
					}	
				
					else
						return "You wounded  " + character.getName() + "!\n";
				}
			}					
		}	
	}

	
	public boolean isCampfireLit(){
		
		return positionOfUser.isCampFireExists();
		
	}
	
	public boolean makeCampfire(){	
		return false;
	}
	
	public boolean cookMeat(){
		
		return false;
	}
	
	public boolean boilWater(){
		
		return false;
		
	}
	
	public void rest(int duration){
		
		if(duration == 1)
			player.setEnergy(player.getEnergy()+10);
		
		if(duration == 4)
			player.setEnergy(player.getEnergy()+40);
			
		if(duration == 8)
			player.setEnergy(player.getEnergy()+80);
	}
	
	public boolean buildShelter(){
		return false;		
		
	}

	
	public boolean isGameOver(){
		
		if(player.getHealth() <= 0)
			return true;
		
		return false;
	}

	public DatabaseManager getDatabaseManager(){
		return databaseManager;
	}

	public Area getPositionOfUser(){
		return positionOfUser;
	}



	public BufferedImage getMap(){
		return mapManager.getProcessedMap();
	}


	public Player getPlayer() {
		return player;
	}


	public boolean isLeftAvailable(){return positionOfUser.hasLeftNeighbour(); }
	public boolean isRightAvailable(){return positionOfUser.hasRightNeighbour(); }
	public boolean isUpAvailable(){return positionOfUser.hasUpNeighbour();}
	public boolean isDownAvailable(){return positionOfUser.hasDownNeighbour();}
	
/*	public String enterEvent(String eventName){
		
		if(eventName.equals("Old Wise Man"))
			oldWiseMan.playStory(positionOfUser, player);
		
	}*/
	
}
