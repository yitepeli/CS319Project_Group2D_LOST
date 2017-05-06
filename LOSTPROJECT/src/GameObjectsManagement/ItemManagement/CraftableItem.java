/*
** CraftableItem Class
** CraftableItem class represents all the items that can be crafted into another item. These items has no additional effects on the player.
** CraftableItem extends the Item class which in turn extends GameObject class. 
**
** Author: Gunduz Huseyn Lee
** File created:  24.04.2017
** Last modified: 26.04.2017
** Last modified by: Gunduz Huseyn Lee
*/
//package ItemManagement;
package GameObjectsManagement.ItemManagement;



import java.util.*;

public class CraftableItem extends Item{
	//Properties
	private ArrayList<Item> craftableItemList;
	private ArrayList<Item> requiredItemList;


	//Constructors
	public CraftableItem(String name){
		super(name);

		craftableItemList = null;
		requiredItemList = null;
	}

	public CraftableItem(int id, String name, String description, double weight, int renewalTime, boolean renewable, ArrayList<String> actionList,  ArrayList<Item> craftableItemsList, ArrayList<Item> requiredItemsList){
		super(id, name, description, weight, renewalTime, renewable, actionList);
		this.craftableItemList = craftableItemsList;
		this.requiredItemList = requiredItemsList;
	}

	//Methods
	public ArrayList<Item> getCraftableItemsList(){
		return this.craftableItemList;
	}

	public ArrayList<Item> getRequiredItemsList(){
		return this.requiredItemList;
	}

	@Override
	public String toString(){
		String result = super.toString();

		if(this.craftableItemList != null){
			result = result + "\nHere is what you can craft from this Item:\n";
			for(Item item : this.craftableItemList)
				result = result + item.getName() + "   ";
			result = result + "\n";
		}

		if(this.requiredItemList != null){
			result = result + "\nHere is what you need to craft this item:\n";
			for(Item item : this.requiredItemList)
				result = result + item.getName() + "   ";
			result = result + "\n";
		}

		return result;
	}
}