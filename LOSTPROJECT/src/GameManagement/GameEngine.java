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
import GameObjectsManagement.ObjectManagement.Record;
import GameObjectsManagement.EventManagement.*;
import java.awt.image.BufferedImage;
import java.util.*;


public class GameEngine {

	private DatabaseManager databaseManager;
	private MapManager mapManager;
	private UpdateManager updateManager;
	private Area positionOfUser;
	private Player player;
	//private RadioTower radioTower;
	//private SailingAway sailingAway;
//	private OldWiseMan oldWiseMan;
//	private DragonLiar dragonLiar;
	private ArrayList<Event> eventList;
	private boolean isEntered;

	public GameEngine(){
		databaseManager = new DatabaseManager(); //Yasin aldi bunu buraya
	//	radioTower = new RadioTower();
	//	sailingAway = new SailingAway();
	//	oldWiseMan = new OldWiseMan();
	//	dragonLiar = new DragonLiar();
//		eventList.add(radioTower);
//		eventList.add(sailingAway);
//		eventList.add(oldWiseMan);
//		eventList.add(dragonLiar);
	}

	public void dropItem(String itemName){
		Item item = this.player.getItem(itemName);
		this.player.removeItem(item);
		this.positionOfUser.addItem(item);

		assert this.positionOfUser.hasItem(itemName, 1);
	}

	public boolean takeItem(String itemName){
		Item item = this.positionOfUser.getItem(itemName);
		if(!this.player.addItem(item))
			return false;

		assert this.player.hasItem(itemName, 1);

		this.positionOfUser.removeItem(item);
		return true;
	}


	public static boolean isUserExists(){
		return DatabaseManager.isUserExists();
	}

	private Item readItem(String itemName, String type){
		return databaseManager.readItem(itemName,type);
	}


	public void enterUserName(String name){
		player.setName(name);
	}

	public void save(boolean isSoundActive,int panelOption){

		databaseManager.recordSettings(isSoundActive,panelOption);

		Record record = new Record.Builder()
				.acquiredBy(player.getName())
				.acquiredById(player.getCloudId())
				.gameTime(player.getGameTime())
				.description("Not accomplished yet!")
				.isAccomplished(false)
				.accomplishedStoryEvent("")
				.build();
		databaseManager.processData(record, DatabaseManager.WriteAction.WRITE_RECORD);
	}

	public List<Record> getRecords(){
		return databaseManager.listRecords();
	}

	public void navigate(String direction){

		switch (direction){
			case "left":
				positionOfUser = positionOfUser.getLeftNeighbour();
				break;
			case "right":
				positionOfUser = positionOfUser.getRightNeighbour();
				break;
			case "down":
				positionOfUser = positionOfUser.getDownNeighbour();
				break;
			case "up":
				positionOfUser = positionOfUser.getUpNeighbour();
				break;
		}
		String currentAreaName = positionOfUser.getAreaType().getAreaName();
		player.setCurrentPosition(currentAreaName);
		databaseManager.setWorkingArea(currentAreaName);
		//databaseManager.processData(player,DatabaseManager.WriteAction.WRITE_PLAYER);//update player data
		mapManager.processMap(positionOfUser);//updating map
	}


	public void createGameEnvironment(boolean isNewGame){

		DatabaseManager.initUserStaticId(isNewGame);

		//databaseManager = new DatabaseManager();
		updateManager = new UpdateManager();
		mapManager = new MapManager();

		if(!isNewGame){
			long userId = databaseManager.getUserId();//accessing user preferences and getting user id..
			player = (Player)databaseManager.readData(userId,DatabaseManager.ReadAction.READ_PLAYER);//cloud based data!
			player.setCloudId(userId);
		}
		else{
			player = new Player();//passing default values
			player.setCurrentPosition("Forest1");
			//databaseManager.processData(player, DatabaseManager.WriteAction.WRITE_PLAYER);//creating new player instance in persistency layer
			databaseManager.createUserLocal(player.getCloudId());//creating user local in prefs
		}
		updateManager.createGameEnvironment(isNewGame,databaseManager,player.getCurrentPositionOfUser());
		positionOfUser = updateManager.getPositionOfUser();
		mapManager.processMap(positionOfUser);

		player.addItem(databaseManager.readItem("Tree","CraftableItem"));
		player.addItem(databaseManager.readItem("Axe","Tool"));
		player.addItem(databaseManager.readItem("Wood","CraftableItem"));
	}


	public ArrayList<Item> getCraftableItems(String itemName){
		return ((CraftableItem)this.player.getItem(itemName)).getCraftableItemsList();
	}


	public boolean craft(String itemName, int amount, String type, boolean isArea){
		CraftableItem item = (CraftableItem)this.readItem(itemName, type);
		assert item != null;


		ArrayList<Item> requiredItemsList = item.getRequiredItemsList();
		
		assert requiredItemsList != null;
		for(Item tmpItem : requiredItemsList)
			if(!this.player.hasItem(tmpItem.getName(), tmpItem.getQuantity()))
				return false;

		if(!isArea)
			return player.craft(item, amount);		
		else{
			for(Item tmpItem : requiredItemsList)
				for(int i = 0; i < tmpItem.getQuantity(); i++)
					this.player.removeItem(tmpItem);

			this.positionOfUser.addItem(item);

			assert this.positionOfUser.hasItem(item.getName(), 1);

			return true;
		}
	}
	
	
	public String fight(String characterName){
		Character character = null;

		for(int i = 0; i < getPositionOfUser().getCharacterList().size(); i++){
			if(getPositionOfUser().getCharacterList().get(i).getName().equals(characterName))
				character = getPositionOfUser().getCharacterList().get(i);
		}

		//health of the characters should not be below zero!
		assert (player.getHealth() <= 0 || character.getHealth() <= 0);
		assert character != null;

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
				if(character.getInventory() != null){
					ArrayList<Item> characterItems = character.getInventory().getStoredItems();
					for(int i=0; i<characterItems.size(); i++)
						positionOfUser.addItem(characterItems.get(i));
				}
				//remove from area
				getPositionOfUser().getCharacterList().remove(character);
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
						
						else if(player.getDefense() > ((AggresiveCharacter)character).getAttack())
							player.updateHealth(-Math.abs((((AggresiveCharacter)character).getAttack()-player.getDefense()))/2);
						
						else if(player.getDefense() < ((AggresiveCharacter)character).getAttack())
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

	public void use(String itemName){
		assert this.player.hasItem(itemName, 1);
		Item item = this.player.getItem(itemName);		
		assert item != null;

		if(this.player.getItem(itemName) instanceof Food)
			this.eat(item);
		else if(this.player.getItem(itemName) instanceof BoostingItem)
			this.equip(item);
	}

	private void eat(Item fitem){
		Food item = (Food)fitem;
		this.player.setHunger(this.player.getHunger() - item.getHungerPoints());
		this.player.setThirst(this.player.getThirst() - item.getThirstPoints());
		this.player.setHealth(this.player.getHealth() + item.getHealthPoints());

		this.player.removeItem(item);
	}

	private void equip(Item fitem){
		BoostingItem item = (BoostingItem)fitem;
		this.player.updateDefense(item.getDefensePointBonus());
		this.player.updateAttack(item.getAttackPointBonus());
	}
	
	public boolean isCampfireLit(){		
		return positionOfUser.isCampFireExists();		
	}
	
	public boolean makeCampfire(){	
		assert !positionOfUser.isCampFireExists();

		if(this.craft("Campfire", 1, "CraftableItem", true)){
			this.positionOfUser.setCampFireExists(true);
			return true;
		}

		return false;
	}
	
	public boolean cookMeat(){
		assert this.positionOfUser.isCampFireExists();

		if(this.player.hasItem("Meat", 1)){
			this.player.removeItem(this.player.getItem("Meat"));
			this.player.addItem(this.readItem("CookedMeat", "Food"));
			return true;
		}
	}
	
	public boolean boilWater(){
		assert this.positionOfUser.isCampFireExists();

		if(this.player.hasItem("Dirty Water", 1)){
			this.player.removeItem(this.player.getItem("Dirty Water"));
			this.player.addItem(this.readItem("Water", "Food"));
			return true;
		}

		return false;		
	}
	
	public void rest(int duration){
		int restAmount = 10;

		if(this.positionOfUser.isShelterExists())
			restAmount = restAmount + 5;

		player.setEnergy(player.getEnergy() + duration * restAmount);
	}

	public boolean isShelterExists(){
		return this.positionOfUser.isShelterExists();
	}
	
	public boolean buildShelter(){
		assert !positionOfUser.isShelterExists();

		if(this.craft("Shelter", 1, "CraftableItem", true)){
			this.positionOfUser.setShelterExists(true);
			return true;
		}

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

	/**
	 * @return the eventList
	 */
	public ArrayList<Event> getEventList() {
		return eventList;
	}

	
/*	public String enterEvent(String eventName){
		
		if(eventName.equals("Radio Tower")){
			if(radioTower.checkRequirements(player))
				return "You entered Radio Tower story event" + radioTower.getDescription();			
			else
				return radioTower.getRequirements();
		}
		
		if(eventName.equals("Sailing Away")){
			if(sailingAway.checkRequirements(player))
				return "You entered Sailing Away story event" + sailingAway.getDescription();		
			else
				return sailingAway.getRequirements();
		}
		
		if(eventName.equals("Dragon Liar")){
			if(radioTower.checkRequirements(player))
				return "You entered Dragon Liar story event" + dragonLiar.getDescription();
			else
				return dragonLiar.getRequirements();		
		}
		
		if(eventName.equals("Old Wise Man")){
			if(radioTower.checkRequirements(player))
				return "You entered Old Wise Man story event" + oldWiseMan.getDescription();
			else
				return oldWiseMan.getRequirements();
		}
		
		return "You did not enter in any story event";		
	}*/
	
}
