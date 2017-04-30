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
	List <Event> events;
	Player player;
	
	public GameEngine(){
		//this may change since area class have eventList
		Event event1 = new OldWiseMan();
		Event event2 = new SailingAway();
		Event event3 = new RadioTower();
		Event event4 = new DragonLiar();
		
		events.add(event1);
		events.add(event2);
		events.add(event3);
		events.add(event4);
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
		
	}
	
	public boolean use(String itemName){
		
	}
	
	public boolean fight(Character character){
		
		
	}
	
	public boolean isCampfireLit(){
		
	
		
	}
	
	public boolean makeCampfire(){
		
		
	}
	
	public boolean cookMeat(){
		
		return player.cookMeat();
	}
	
	public boolean boilWater(){
		
		if(player.hasItem("Water") && player.hasItem("Fire")){
			player.addItem("Boiled Water");
			player.removeItem("Water");
			player.removeItem("Fire");
			return true;
		}
			
		return false;
		
	}
	
	public void rest(int duration){
		
		player.setEnergy(player.getEnergy()+duration*2);
		
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
