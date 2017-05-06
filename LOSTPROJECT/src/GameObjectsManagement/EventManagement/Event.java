package GameObjectsManagement.EventManagement;

import GameObjectsManagement.ObjectManagement.*;
import GameObjectsManagement.AreaManagement.*;
import GameObjectsManagement.CharacterManagement.*;

public abstract class Event extends GameObject {

	 public abstract boolean playStory(Area a, Player p);
	 public abstract void setIsEntered(boolean flag);
	 public abstract boolean isEntered();
	 
}
