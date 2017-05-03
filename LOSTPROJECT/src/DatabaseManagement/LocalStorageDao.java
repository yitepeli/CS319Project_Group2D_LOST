package DatabaseManagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.prefs.*;

import GameObjectsManagement.ItemManagement.Item;


import GameObjectsManagement.ObjectManagement.*;
import GameObjectsManagement.CharacterManagement.Character;


/**
 * Author: Onur Sönmez
 */
public class LocalStorageDao {

    private Gson gson;//Google gson library for json-pojo object mapping!
    private String userDirectory;
    private String path;// File path that includes json libraries

    public LocalStorageDao() {
        gson = new GsonBuilder().create();//creating gson
        userDirectory = System.getProperty("user.dir");
        path = userDirectory+ "/src/JsonFiles/";//path for json files
    }

    /**
     * Json mapping for java root objects,
     * @return Result<itemList,characterList> seperate list of game objects in area!
     */
    public Result parseJSONFiles(String areaName){

        File folder = new File(path + "/Areas/" + areaName +"/");
        ArrayList<Item> itemList = new ArrayList<>();
        ArrayList<Character> characterList = new ArrayList<>();

        for (File file: folder.listFiles()) {
            String className = file.getName().replace(".txt","");
            Class<?> cls;//class names are in convenient class name form, so directly convert name to Class!
            try {
                cls = Class.forName("GameObjectsManagement." + className);
                //@return BoostingItem, CraftableItem, Item, Character, AggressiveCharacter
                Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()));

                stream.filter(e->!e.equals("")).forEach(e->{//Override predicate
                    Object object = null;
                    try{
                        object = gson.fromJson(new FileReader(path + "json/" + e),cls);//map json object into root object
                    }catch (IOException exception){ exception.printStackTrace();}

                    if(object instanceof Item) {
                        itemList.add((Item) object);
                    }
                    if(object instanceof Character)
                        characterList.add((Character)object);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Result(itemList,characterList);
    }


    public GameObject readJSONElement(String jsonName,Class<? extends GameObject> cls){

        GameObject gameObject = null;
        try{
            gameObject = gson.fromJson(path + "json/" + jsonName.replaceAll("\\s","") + ".json",cls);
        }catch (Exception e){ e.printStackTrace();}
        return gameObject;
    }

    /**
     * For Windows
     * systemRoot -> HKEY_LOCAL_MACHINE\SOFTWARE\JavaSoft\Prefs
     * userRoot -> HKEY_CURRENT_USER\Software\JavaSoft\Prefs.
     * For Unix
     * systemRoot -> /etc/.java
     * userRoot -> ${user.home}/.java/.userPrefs
     */

    public void recordUserUniqueId(String userUniqueId,long cloudId){
        Preferences userPrefs = Preferences.userRoot().node("lost/custom/user");//user preferences node
        userPrefs.put(Constants.USER_PREFS,userUniqueId);//putting user unique id into user preferences package
        userPrefs.putLong(Constants.USER_ID,cloudId);
    }

    public static String getUserUniqueId(){
        Preferences userPrefs = Preferences.userRoot().node("lost/custom/user");
        return userPrefs.get(Constants.USER_PREFS,Constants.INVALID_USER);
    }

    public void recordSettings(boolean isSoundActive, int panelOption){
        Preferences systemPrefs = Preferences.systemRoot().node("lost/custom/settings");
        systemPrefs.putBoolean(Constants.SYSTEM_PREFS_SOUND,isSoundActive);
        systemPrefs.putInt(Constants.SYSTEM_PREFS_PANEL,panelOption);
    }

    public boolean isSoundActive(){ return Preferences.systemRoot().getBoolean(Constants.SYSTEM_PREFS_SOUND,true);}

    public int getLastPanelOption(){ return Preferences.systemRoot().getInt(Constants.SYSTEM_PREFS_PANEL,0);}

    public long getUserId(){return Preferences.systemRoot().getLong(Constants.USER_ID,0);}


}
