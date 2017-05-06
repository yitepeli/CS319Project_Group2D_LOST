package GameObjectsManagement.EventManagement;

import GameObjectsManagement.AreaManagement.*;
import GameObjectsManagement.CharacterManagement.*;

public class SailingAway extends Event{
	
	public SailingAway(){
		
		setDescription("Welcome to Sailing Away story event. You need to accomplish several tasks to"
				+ "complete this adventure and finally survive from the island. First, you need to collect
				40 woods and 20 ropes and bring them to abandoned village. As you can see, on the shore there
				is a wrecked ship. You need to craft a sail and repair the ship in order to use it. Complete these
				tasks and find the ship map that shows the route. Map can be anywhere. Good luck!");
	}
	
	public boolean checkRequirements(Player p){
				
		if(p.hasItem("Hammer") && p.hasItem("Knife"))
			return true;
		
		return false;
	}
	
	public String getRequirements(){

		return "You need to have a hammer and a knife";
	}

	public boolean playStory(Area a, Player p){
		
		if(p.hasItem("Boat") && p.hasItem("Ship Map") && p.hasItem("Sail") && p.hasItem("Wood") && p.hasItem("Rope"))
			return true;
		
		return false;
	}
}
