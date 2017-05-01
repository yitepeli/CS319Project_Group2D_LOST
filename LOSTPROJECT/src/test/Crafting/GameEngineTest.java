//package GameManagement;
/*
** GameEngine Class

** GameEngine class is where all of game operations are carried out.
** 
**
** Author: Eren Bilaloglu
** File created:  29.04.2017
** Last modified: 30.04.2017
** Last modified by: Eren Bilaloglu
*/
package test.Crafting;

import GameObjectsManagement.CharacterManagement.*;
import GameObjectsManagement.CharacterManagement.Character;
import GameObjectsManagement.ItemManagement.*;
import GameObjectsManagement.ObjectManagement.*;

import java.util.*;
import javax.swing.*;
import java.io.*;

public class GameEngineTest {

	
	private Player player;
	
	public GameEngineTest(){
		player = new Player("Mamed");

		String itemDocumentPath = "itemsDocumentation.txt";		
		ArrayList<String> itemsInfo = readFile(itemDocumentPath);

    	ArrayList<Item> items = generateItemsList(itemsInfo);

    	Inventory inventory = new Inventory(items, 100.0);
    	player.setInventory(inventory);

    	//player.addItem("Rock");

    	//System.out.println(player.getInventory().console_listItems());
	}


	public void addItem(String itemName){
		player.addItem(itemName);
	}

	public String displayPlayerItems(){
		return player.getInventory().console_listItems();
	}

	private ArrayList<Item> generateItemsList(ArrayList<String> info){
		ArrayList<Item> items = new ArrayList<Item>();
		int size = info.size();
		//Item types
		//CraftableItem craftableItem;
		//BoostingItem boostingItem;
		//Tool tool;
		//Food food;

		//Item properties
		String [] strs;
		int type;
		String name;
		String description;
		double weight;
		int renewalTime;
		boolean renewable;
		ArrayList<String> actionList = new ArrayList<String>();
		ArrayList<Item> craftableItemsList;
		ArrayList<Item> requiredItemsList;
		int attackPointBonus;
		int defensePointBonus;
		int hungerPoints;
		int thirstPoints;
		int healthPoints;


		for(int i = 0; i < size; i++){
			if(info.get(i).equals("************************")){
				type = Integer.parseInt(info.get(++i).substring(info.get(i).indexOf("::") + 2) );
				name = info.get(++i).substring(info.get(i).indexOf("::") + 2);
				description = info.get(++i).substring(info.get(i).indexOf("::") + 2);
				weight = Double.parseDouble(info.get(++i).substring(info.get(i).indexOf("::") + 2) );
				renewalTime = Integer.parseInt(info.get(++i).substring(info.get(i).indexOf("::") + 2) );
				renewable = Boolean.parseBoolean(info.get(++i).substring(info.get(i).indexOf("::") + 2) );
				//getting actions
				strs = info.get(++i).substring(info.get(i).indexOf("::") + 2).split(", ");
				for(String str : strs)
					actionList.add(str);

				//getting craftable items
				strs = info.get(++i).substring(info.get(i).indexOf("::") + 2).split(", ");
				if(strs[0].equals("empty"))
					craftableItemsList = null;
				else{
					craftableItemsList = new ArrayList<Item>();
					for(String itemName : strs){
						Item item = new Item(itemName);
						craftableItemsList.add(item);
					}
				}

				//getting required items
				strs = info.get(++i).substring(info.get(i).indexOf("::") + 2).split(", ");
				if(strs[0].equals("empty"))
					requiredItemsList = null;
				else{
					requiredItemsList = new ArrayList<Item>();
					for(String itemName : strs){
						Item item = new Item(itemName);
						requiredItemsList.add(item);
					}
				}

				//getting the rest
				attackPointBonus = Integer.parseInt(info.get(++i).substring(info.get(i).indexOf("::") + 2) );
				defensePointBonus = Integer.parseInt(info.get(++i).substring(info.get(i).indexOf("::") + 2) );
				hungerPoints = Integer.parseInt(info.get(++i).substring(info.get(i).indexOf("::") + 2) );
				thirstPoints = Integer.parseInt(info.get(++i).substring(info.get(i).indexOf("::") + 2) );
				healthPoints = Integer.parseInt(info.get(++i).substring(info.get(i).indexOf("::") + 2) );

				//creating the item
				if(type == 1)
					items.add( new CraftableItem(1000, name, description, weight, renewalTime, renewable, false, actionList, craftableItemsList, requiredItemsList) );
				else if(type == 2)
					items.add( new BoostingItem(1000, name, description, weight, renewalTime, renewable, false, actionList, attackPointBonus, defensePointBonus));
				else if(type == 3)
					items.add( new Tool(1000, name, description, weight, renewalTime, renewable, false, actionList,  attackPointBonus, defensePointBonus, craftableItemsList, requiredItemsList));
				else if(type == 4)
					items.add( new Food(1000, name, description, weight, renewalTime, renewable, false, actionList, hungerPoints, thirstPoints, healthPoints));
			}
		}

		return items;
	}


	private ArrayList<String> readFile(String filePath){
        String line = null;
        ArrayList<String> result = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
                result.add(line);
            
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "!");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + filePath + "!");
        }

        return result;
	}
	
	//modify for the area
	public ArrayList<String> getInteractions(String objectName, boolean isArea){
		return null;
	}
	
	public ArrayList<String> getCraftableItems(String itemName){
		
		CraftableItem item = (CraftableItem)player.getInventory().getItem(itemName);
		ArrayList<String> itemListString = new ArrayList<String>();
		ArrayList<Item> itemListObject = item.getCraftableItemsList();
			
		if(player.hasItem(itemName)){
			
			for(int i=0; i<itemListObject.size(); i++)
				itemListString.add(itemListObject.get(i).getName());
		}
			return itemListString;	
	}
	
	public ArrayList<String> getRequiredItems(String itemName){
		          
		CraftableItem item = (CraftableItem)player.getInventory().getItem(itemName);
		ArrayList<String> itemListString = new ArrayList<String>();
		ArrayList<Item> itemListObject = item.getRequiredItemsList();
			
		for(int i=0; i<itemListObject.size(); i++)
			itemListString.add(itemListObject.get(i).getName());
		
		return itemListString;	
	}
	
	public boolean craft(String craftedItemName){
		
		ArrayList<String> requiredItems = getRequiredItems(craftedItemName);
		
		for(int i=0; i<requiredItems.size(); i++)
			if(!player.hasItem(requiredItems.get(i)))
				return false;
		
		player.craft(craftedItemName);
		return true;
		
	}
	
	public boolean use(String itemName){
		return false;
	}
	
	public boolean fight(Character character){
		return false;
		
	}
	
	public boolean isCampfireLit(){		
		return false;		
	}
	
	public boolean makeCampfire(){
		
		return false;
	}
	
	public boolean cookMeat(){
		
		return player.cookMeat();
	}
	
	public boolean boilWater(){
		
		return player.boilWater();
		
	}
	
	public void rest(int duration){
		
		if(duration == 1)
			player.setEnergy(player.getEnergy()+10);
		
		if(duration == 4)
			player.setEnergy(player.getEnergy()+40);
			
		if(duration == 8)
			player.setEnergy(player.getEnergy()+80);
	}
	
	public boolean buildShelter(){
		
		return false;		
		
	}

	
	public boolean isGameOver(){
		
		if(player.getHealth() <= 0)
			return true;
		
		return false;
	}
	
	public String enterEvent(String eventName){
		
		return "";
		
	}
	
}
