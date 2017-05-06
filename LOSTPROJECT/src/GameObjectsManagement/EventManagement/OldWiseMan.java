package GameObjectsManagement.EventManagement;

import GameObjectsManagement.AreaManagement.*;
import GameObjectsManagement.CharacterManagement.*;

public class OldWiseMan extends Event{
	
	private String areaName = "DarkForest1";
	private boolean isEntered;
	
	public OldWiseMan(){
		
		setIsEntered(false);
		setDescription("Welcome to Old Wise Man story event. You need to accomplish several tasks to"
				+ "complete this adventure and finally survive from the island. Old wise man has not eaten proper"
				+ "food recently. Your first task is to bring him a yummy polar bear meat. His other request is getting"
				+ "back his family members. There are two corpses in volcanic area. You need to bring them to him and make "
				+ "him happy. If you complete these tasks, old wise man will be grateful to teleport you outside the island.");
	}
	
	public String getRequirements(){
		
		return "You must have been in the island for at least 8 days.";
	}

	public boolean checkRequirements(Player p){

		if(p.getGameTime() < 10)
			return false;
		
		return true;
	}
	

	public boolean playStory(Area a, Player p){
		
		if(a.getInventory().hasItem("Polar Bear Meat",1) && a.getInventory().hasItem("Corpse",1))
			return true;
		return false;
		
	}
	
	public void setIsEntered(boolean flag){
		
		isEntered = flag;
	}
	
	public boolean isEntered(){
		
		return isEntered;
	}
}
