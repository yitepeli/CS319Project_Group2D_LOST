package GameManagement;
/*
** GameEngine Class

** GameEngine class is where all of game operations are carried out.
** 
**
** Author: Eren Bilaloglu
** File created:  29.04.2017
** Last modified: 30.04.2017
** Last modified by: Eren Bilaloglu
*/

import GameObjectsManagement.AreaManagement.*;
import GameObjectsManagement.EventManagement.*;
import GameObjectsManagement.CharacterManagement.Character;
import GameObjectsManagement.CharacterManagement.*;

import GameObjectsManagement.ItemManagement.*;

import DatabaseManagement.DatabaseManager;
import com.sun.org.apache.regexp.internal.RE;

import java.util.*;
import javax.swing.*;

public class GameEngine {

	private DatabaseManager databaseManager;
	private MapManager mapManager;
	private UpdateManager updateManager;
	private Area positionOfUser;
	private Player player;

	public GameEngine(){
		String uniqueID = UUID.randomUUID().toString();
		databaseManager = new DatabaseManager(uniqueID);
		updateManager = new UpdateManager();
		mapManager = new MapManager();
	}


	public void createGameEnvironment(boolean isNewGame){

		updateManager.createGameEnvironment(isNewGame,databaseManager);//creating areas
		positionOfUser = updateManager.getPositionOfUser();//initial position of user

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
		databaseManager.processData(player,DatabaseManager.WriteAction.WRITE_PLAYER);
		mapManager.processMapp(positionOfUser);//updating map
	}

	
	public ArrayList<String> getCraftableItems(String itemName){
		
		CraftableItem item = (CraftableItem)player.getInventory().getItem(itemName);
		ArrayList<String> itemListString = new ArrayList<String>();
		ArrayList<Item> itemListObject = item.getCraftableItemsList();
			
		if(player.hasItem(itemName)){
			
			for(int i=0; i<itemListObject.size(); i++)
				itemListString.add(itemListObject.get(i).getName());
		}
			return itemListString;	
	}
	
	public ArrayList<String> getRequiredItems(String itemName){
		          
		CraftableItem item = (CraftableItem)player.getInventory().getItem(itemName);
		ArrayList<String> itemListString = new ArrayList<>();
		ArrayList<Item> itemListObject = item.getRequiredItemsList();
			
		for(int i=0; i<itemListObject.size(); i++)
			itemListString.add(itemListObject.get(i).getName());
		
		return itemListString;	
	}
	
	public boolean craft(String craftedItemName){
		
		ArrayList<String> requiredItems = getRequiredItems(craftedItemName);
		
		for(int i=0; i<requiredItems.size(); i++)
			if(!player.hasItem(requiredItems.get(i)))
				return false;
		
		player.craft(craftedItemName);
		return true;
		
	}

	
	public boolean isCampfireLit(){
		
		return positionOfUser.isCampFireExists();
		
	}
	
	public boolean makeCampfire(){
		
		if(!positionOfUser.isCampFireExists() && player.hasItem("Fire") && player.hasItem("Wood")){
			positionOfUser.setCampFireExists(true);
			return true;
		}
		
		return false;
	}
	
	public boolean cookMeat(){
		
		return player.cookMeat();
	}
	
	public boolean boilWater(){
		
		return player.boilWater();
		
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
		
		if(!positionOfUser.isShelterExists() && player.hasItem("Wood") && player.hasItem("Branch")
				&& player.hasItem("Stone") && player.hasItem("Ropes")){
			positionOfUser.setShelterExists(true);
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
	
/*	public String enterEvent(String eventName){
		
		if(eventName.equals("Old Wise Man"))
			oldWiseMan.playStory(positionOfUser, player);
		
	}*/
	
}
