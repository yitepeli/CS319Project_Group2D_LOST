package GameObjectsManagement.EventManagement;

import GameObjectsManagement.ObjectManagement.*;
import GameObjectsManagement.AreaManagement.*;
import GameObjectsManagement.CharacterManagement.*;

public abstract class Event extends GameObject {
		
	 protected boolean missionCompleted;
	 protected String successMsg = "Congratulations, you completed the story event and survived from the island!";
	 protected String failureMsg = "You failed, try next time!";
	 public abstract String playStory(Area a, Player p);
}
