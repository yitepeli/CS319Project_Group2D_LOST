package DatabaseManagement;

import java.util.ArrayList;

import GameObjectsManagement.CharacterManagement.Character;
import GameObjectsManagement.ItemManagement.Item;


public class Result {

    private ArrayList<Item> itemList;
    private ArrayList<Character> characterList;

    public Result(ArrayList<Item> itemList, ArrayList<Character> characterList){
        this.itemList = itemList;
        this.characterList = characterList;
    }

    public ArrayList<Item> getItemList(){
        return itemList;
    }

    public ArrayList<Character> getCharacterList(){
        return characterList;
    }

}
