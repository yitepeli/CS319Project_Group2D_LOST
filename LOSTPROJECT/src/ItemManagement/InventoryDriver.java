/*
** Invertory Driver class
** InvetoryDriver class is for testing the inventory, adding removing items, and for checking the crafting mechanism
** 
**
** Author: Gunduz Huseyn Lee
** File created:  26.04.2017
** Last modified: 26.04.2017
** Last modified by: Gunduz Huseyn Lee
*/

import java.util.*;
import java.io.*;

public class InventoryDriver{

	public static void main(String[] args){
		String itemDocumentPath = "itemsDocumentation.txt";
		
		ArrayList<String> itemsInfo = readFile(itemDocumentPath);

    	ArrayList<Item> items = generateItemsList(itemsInfo);

    	Inventory inventory = new Inventory(items, 100.0);

    	inventory.addItem("Rock");

    	System.out.println(inventory.console_listItems());

    	System.out.println(inventory.console_listItemsDetailed());

	}

	private static ArrayList<Item> generateItemsList(ArrayList<String> info){
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


	private static ArrayList<String> readFile(String filePath){
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
}