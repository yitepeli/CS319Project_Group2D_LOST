package GameObjectsManagement.EventManagement;

import GameObjectsManagement.AreaManagement.*;
import GameObjectsManagement.CharacterManagement.*;

public class RadioTower extends Event {
	
	private String areaName = "Iceland1";
	private boolean isEntered;

	public RadioTower(){
		
		setIsEntered(false);
		setDescription("Welcome to Radio Tower story event. You need to accomplish several tasks to"
				+ "complete this adventure and finally survive from the island. As you saw, the tower "
				+ "needs to be repaired. Now you have to go and find metal wires to repair the tower. The cave might "
				+ "be the place to look. After you find the wires, go and fix the tower. Repairing the tower won't be easy,"
				+ "it will take 2 nights in iced area. When the tower is ready, you need to find a radiotelephone "
				+ "for sending your signal. If you can find it and send your signal, you are free to go!");
	}
		
	public String getRequirements(){
		
		return "You need to have at least 60 health points and polar bear fur. Also you must have been in the"
				+ "island for at least 8 days.";
	}
	
	public boolean checkRequirements(Player p){
		
		if(p.getHealth() >= 60 && p.getGameTime() >= 8 && p.hasItem("Bear Skin",1))
			return true;
			
		return false;
	}
	
	
	public boolean playStory(Area a, Player p){
				
		if(a.getAreaType().getAreaName().equals(areaName) && a.getInventory().hasItem("Radio Tower",1) && p.hasItem("Radiotelephone",1))
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
