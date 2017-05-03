package GameObjectsManagement.ObjectManagement;

/**
 * Author: Onur Sönmez
 * Description: Holder for constant variables in game, instead of including them in classes and providing poor syntax,
 *              this applies more readable and protectable hierarchy. Most of constants will be used in database querying
 *              and do not want to involve them into object-oriented design.
 */


public class Constants {

    //For database querying...
    public final static String OBJECT_NAME = "objectName";
    public final static String OBJECT_ID = "objectId";
    public final static String DESCRIPTION ="description";
    public final static String ACCOMPLISHED_STORY_EVENT = "accomplishedStoryEvent";
    public final static String ACCOMPLISHED_GAME_TIME = "accomplishedGameTime";
    public final static String IS_ACCOMPLISHED = "isAccomplished";
    public final static String REAL_TIME = "realTime";
    public final static String HEALTH = "health";
    public final static String DEFENSE = "defense";
    public final static String ATTACK = "attack";
    public final static String ENERGY = "energy";
    public final static String HUNGER = "hunger";
    public final static String THIRST = "thirst";
    public final static String CURRENT_AREA = "currentArea";
    public final static String USER_PREFS = "lostUserPreferences";
    public final static String SYSTEM_PREFS_SOUND = "systemPreferencesSound";
    public final static String SYSTEM_PREFS_PANEL = "systemPreferencesPanel";
    public final static String INVALID_USER = "invalidUser";
    public final static String USER_ID = "cloudId";

}
