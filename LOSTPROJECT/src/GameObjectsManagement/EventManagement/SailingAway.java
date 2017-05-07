package GameObjectsManagement.EventManagement;

import GameObjectsManagement.AreaManagement.*;
import GameObjectsManagement.CharacterManagement.*;

public class SailingAway extends Event{

    private boolean isEntered;

    public SailingAway(){
        setIsEntered(false);

        setDescription("Welcome to Sailing Away story event. You need to accomplish several tasks to"
                + "complete this adventure and finally survive from the island. First, you need to collect"
                + "5 woods and 5 ropes and bring them to abandoned village. As you can see, on the shore there"
                + "is a wrecked ship. You need to craft a sail and repair the ship in order to use it. Complete these"
                + "tasks and find the ship map that shows the route. Map can be anywhere. Good luck!");
    }

    public boolean checkRequirements(Player p){

        if(p.hasItem("Hammer",1) && p.hasItem("Knife",1))
            return true;

        return false;
    }

    public String getRequirements(){

        return "You need to have a hammer and a knife";
    }

    public boolean playStory(Area a, Player p){

        if(p.hasItem("Boat",1) && p.hasItem("Ship Map",1) && p.hasItem("Sail",1) && p.hasItem("Wood",5) && p.hasItem("Rope",5))
            return true;

        return false;
    }
    public boolean isEntered(){

        return isEntered;
    }

    public void setIsEntered(boolean flag){

        isEntered = flag;
    }
}
