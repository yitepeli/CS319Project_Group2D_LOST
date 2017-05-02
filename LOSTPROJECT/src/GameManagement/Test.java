package GameManagement;

/**
 * Created by onursonmez on 02/05/2017.
 */
import GameObjectsManagement.AreaManagement.Area;
import GameObjectsManagement.ItemManagement.Item;

import java.util.ArrayList;


public class Test {
    public static void main(String args[]){

        GameEngine gameEngine = new GameEngine();
        gameEngine.createGameEnvironment(true);//is new game

        Area area = gameEngine.getPositionOfUser();

        ArrayList<Item> itemList = area.getInventory().getStoredItems();

        itemList.forEach(e->System.out.println(e.getName()));/*Printing all items in  area!*/


    }
}
