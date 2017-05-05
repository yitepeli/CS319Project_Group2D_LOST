package GameManagement;

/**
 * Created by onursonmez on 02/05/2017.
 */
import GameObjectsManagement.AreaManagement.Area;
import GameObjectsManagement.ItemManagement.Item;
import GameObjectsManagement.CharacterManagement.Character;

import java.util.ArrayList;


public class Test {
    public static void main(String args[]){


        System.out.println(GameEngine.isUserExists());
        GameEngine gameEngine = new GameEngine();
        gameEngine.createGameEnvironment(false);//is new game
        System.out.println(gameEngine.getPlayer().getCloudId());
        Area area = gameEngine.getPositionOfUser();

        System.out.println(area.getInventory() == null);

        ArrayList<Item> itemList = area.getInventory().getStoredItems();

        ArrayList<Character> characters = area.getCharacterList();

        System.out.println("Items in " + area.getAreaType().getAreaName());
        itemList.forEach(e->System.out.println(e.getName()));/*Printing all items in  area!*/

        System.out.println();

        System.out.println("Characters in " + area.getAreaType().getAreaName());
        characters.forEach(e->System.out.println(e.getName()));

        area = area.getRightNeighbour();

        characters = area.getCharacterList();
        itemList = area.getInventory().getStoredItems();

        System.out.println();


        System.out.println("Items in " + area.getAreaType().getAreaName());
        itemList.forEach(e->System.out.println(e.getName()));/*Printing all items in  area!*/

        System.out.println();

        System.out.println("Characters in " + area.getAreaType().getAreaName());
        characters.forEach(e->System.out.println(e.getName()));


    }



}
