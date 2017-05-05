//package ItemManagement;
/*

** Tool Class
** Tool class represents all the items that can be crafted, which also affects the player's fighting mechanism. 
** For example, spear is a tool, which icreases the attackPoint of the player, when equipped.
** Tool extends the BoostingItem class, which extends Item class, which in its turn extends GameObject class. 
**
** Author: Gunduz Huseyn Lee
** File created:  25.04.2017
** Last modified: 26.04.2017
** Last modified by: Gunduz Huseyn Lee
*/
package GameObjectsManagement.ItemManagement;

import java.util.*;

public class Tool extends BoostingItem{
	//Properties
	private ArrayList<Item> craftableItemList;
	private ArrayList<Item> requiredItemList;

	//Constructors
	public Tool(String name){
		super(name);

		craftableItemList = null;
		requiredItemList = null;
	}

	public Tool(int id, String name, String description, double weight, int renewalTime, boolean renewable, ArrayList<String> actionList, int attackPointBonus, int defensePointBonus, ArrayList<Item> craftableItemsList, ArrayList<Item> requiredItemsList){
		super(id, name, description, weight, renewalTime, renewable, actionList, attackPointBonus, defensePointBonus);
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