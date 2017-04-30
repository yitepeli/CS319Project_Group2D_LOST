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
import java.util.*;
import javax.swing.*;

public class GameEngine {

	DatabaseManager databaseManager;
	MapManager mapManager;
	Area currentArea;
	List <Area> areas;
	Player player;
	
	public GameEngine(){
		
		
			
	}
	
	public List<Map<String,ImageIcon>> getAreaObjects(){
			
	}
	
	public List<Map<String,ImageIcon>> getPlayerObjects(){
		
	}
	
	public List<String> getInteractions(String objectName, boolean isArea){
				
	}
	
	public List<String> getCraftableItems(String itemName){
		
	}
	
	public boolean craft(String craftedItemName){
		
		if(player.hasItem(craftedItemName))
			player.craft(craftedItemName);
			
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
			events.get(0).playStory(currentArea, player);
			
		if(eventName.equals("Sailing Away"))
			events.get(1).playStory(currentArea, player);
		
		if(eventName.equals("Radio Tower"))
			events.get(2).playStory(currentArea, player);
		
		if(eventName.equals("Dragon Liar"))
			events.get(3).playStory(currentArea, player);
		
		
	}
	
}
