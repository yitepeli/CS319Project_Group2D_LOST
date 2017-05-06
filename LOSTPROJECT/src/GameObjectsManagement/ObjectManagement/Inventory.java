
/*
** Inventory Class

** inventory class is where player's and the area's items are kept.
** 
**
** Author: Gunduz Huseyn Lee
** File created:  25.04.2017
** Last modified: 05.05.2017
** Last modified by: Gunduz Huseyn Lee
*/
package GameObjectsManagement.ObjectManagement;

import GameObjectsManagement.ItemManagement.*;


import java.util.*;


public class Inventory{
	//Properties
	private ArrayList<Item> storedItemList;
	private double maxCapacity;
	private double currentCapacity;

	//Constructors
	public Inventory(){
		this.storedItemList = new ArrayList<>();
		maxCapacity = 50.0;		//Default value
		currentCapacity = 0.0;
	}

	public Inventory(ArrayList<Item> storedItemList, double maxCapacity){
		this.storedItemList = storedItemList;
		this.maxCapacity = maxCapacity;
		this.currentCapacity = this.calculateCurrentCapacity();
	}

	//Methods
	//Removing the item from the list
	public void removeItem(Item item){
		//item must be in the stored item list
		assert this.hasItem(item.getName(), 1);

		this.storedItemList.remove(this.getItemIndex(item.getName()));

		this.currentCapacity = this.currentCapacity - item.getWeight();

		//capacity should never be below 0
		assert this.currentCapacity >= 0;
	}


	//Methods for adding an item
	public boolean addItem(Item item){
		if(this.maxCapacity < this.currentCapacity + item.getWeight())
			return false;

		this.storedItemList.add(item);
		this.currentCapacity = this.currentCapacity + item.getWeight();

		assert this.maxCapacity >= this.currentCapacity;
		return true;

	}


	//hasitem methods
	public boolean hasItem(String itemName, int amount){
		for(Item tmpItem : this.storedItemList)
			if(itemName.equals(tmpItem.getName()) && amount <= this.getCount(itemName))
				return true;
		return false;
	}

	/*
	public boolean hasItems(ArrayList<Item> items){
		for(Item item : items)
			if(!this.hasItem(item))
				return false;
		return true;
	}
	*/

	/**
	**getItem method with String
	*/

	
	public Item getItem(String itemName){
		for(Item item : this.storedItemList)
			if(item.getName().equals(itemName))
				return item;
		return null;
	}
	

	public ArrayList<Item> getStoredItems(){
		return storedItemList;
	}

	public double getCurrentCapacity(){
		return this.currentCapacity;
	}
	
	public double getMaxCapacity(){
		return this.maxCapacity;
	}

	//Private methods
	private int getCount(String itemName){
		int count = 0;
		for(Item tmpItem : this.storedItemList)
			if(itemName.equals(tmpItem.getName()))
				count++;

		return count;
	}


	private int getItemIndex(String itemName){
		int size = this.storedItemList.size();
		for(int i = 0; i < size; i++)
			if(storedItemList.get(i).getName().equals(itemName))
				return i;

		return -1;
	}

	private double calculateCurrentCapacity(){
		double capacity = 0.0;
		for(Item item : this.storedItemList)
			capacity = capacity + item.getWeight();

		return capacity;
	}


	//Listing items for the console
	public String console_listItems(){
		String result = "Here is the items inside the Inventory:\n";
		for(Item item : this.storedItemList)
			result = result + item.getName() + "\n";

		return result;
	}

	public String console_listItemsDetailed(){
		String result = "Here is the items inside the Inventory:\n";
		for(Item item : this.storedItemList)
			result = result + item.toString() + "\n";

		return result;
	}
}

