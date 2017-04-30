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

import GameObjects.Area;
import GameObjects.Event;
import GameObjects.OldWiseMan;
import GameObjects.SailingAway;
import GameObjects.DragonLiar;
import GameObjects.RadioTower;
import DatabaseManagement.DatabaseManager;
import CharacterManagement.Player;
import ItemManagement.Item;
import ItemManagement.CraftableItem;
import java.util.*;
import javax.swing.*;

public class GameEngine {

	DatabaseManager databaseManager;
	MapManager mapManager;
	Area currentArea;
	List <Area> areas;
	Player player;
	RadioTower radioTower;
	DragonLiar dragonLiar;
	SailingAway sailingAway;
	OldWiseMan oldWiseMan;
	
	public GameEngine(){
		
		
			
	}
	
	public List<Map<String,ImageIcon>> getAreaObjects(){
			
	}
	
	public List<Map<String,ImageIcon>> getPlayerObjects(){
		
	}
	
	public List<String> getInteractions(String objectName, boolean isArea){
				
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
		ArrayList<String> itemListString = new ArrayList<String>();
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
	
	public boolean use(String itemName){
		
	}
	
	public boolean fight(Character character){
		
		
	}
	
	public boolean isCampfireLit(){
		
		return currentArea.isCampFireExists();
		
	}
	
	public boolean makeCampfire(){
		
		if(!currentArea.isCampFireExists() && player.hasItem("Fire") && player.hasItem("Wood")){
			currentArea.setCampFireExists(true);
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
		
		if(!currentArea.isShelterExists() && player.hasItem("Wood") && player.hasItem("Branch")
				&& player.hasItem("Stone") && player.hasItem("Ropes")){
			currentArea.setShelterExists(true);
			return true;
		}
		
		return false;		
		
	}
	
	public boolean navigate(String direction){
		

	}
	
	public boolean isGameOver(){
		
		if(player.getHealth() <= 0)
			return true;
		
		return false;
	}
	
	public String enterEvent(String eventName){
		
		if(eventName.equals("Old Wise Man"))
			oldWiseMan.playStory(currentArea, player);
		
	}
	
}
