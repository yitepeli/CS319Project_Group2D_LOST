package DatabaseManagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import GameObjectsManagement.ItemManagement.Item;


import GameObjectsManagement.ObjectManagement.*;
import GameObjectsManagement.CharacterManagement.Character;
import com.sun.tools.classfile.Annotation;


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
        //.DS_STORE JUST FOR MACBOOK TESTING,(dir, name) -> !name.equals(".DS_Store")
        for (File file: folder.listFiles()) {
            String className = file.getName().replace(".txt","");
            Class<?> cls;//class names are in convenient class name form, so directly convert name to Class!
            try {
                cls = Class.forName("GameObjectsManagement.ItemManagement." + className);//@return BoostingItem, CraftableItem, Item, Character, AggressiveCharacter
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

}
