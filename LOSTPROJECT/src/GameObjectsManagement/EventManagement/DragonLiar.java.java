package GameObjectsManagement.EventManagement;

import GameObjectsManagement.AreaManagement.*;
import GameObjectsManagement.CharacterManagement.*;


public class DragonLiar extends Event{
	
	private String areaName = "VolcanoZone1"; 
			
	public DragonLiar(){
		
		setDescription("Welcome to Dragon Liar story event. You need to accomplish several tasks to"
				+ "complete this adventure and finally survive from the island. Dragon is angry now and "
				+ "you need to find the dark diamond to control her. Dark diamond is hidden in one of the animals"
				+ "in the island. You need to kill many animals in order to find it. After you find it, you have to "
				+ "go to volcanic area one more time and throw diamond in volcano. After that, you will be able"
				+ "to control the dragon and fly away from the island!");
			
	}
	
	public String getRequirements(){

			return "You need to have at least 60 health points, a sword and a dragon armor. Also you must have been in the"
				+ "island for at least 15 days.";
	}
	
	public boolean checkRequirements(Player p){
		
		if(p.getHealth() >= 60 && p.getDayCount() >= 15 && p.hasItem("Sword") && p.hasItem("Dragon Armor"))		
			return true;
		
		else
			return false;
	}

	public String playStory(Area a, Player p){
		
		if(a.getAreaType().getAreaName().equals(areaName) && && a.getInventory().hasItem("Dark Diamond"))
			return true;
		
		return false;
		
	}
}
