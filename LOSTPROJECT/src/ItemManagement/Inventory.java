/*
** Inventory Class
** inventory class is where player's and the area's items are kept.
** 
**
** Author: Gunduz Huseyn Lee
** File created:  25.04.2017
** Last modified: 25.04.2017
** Last modified by: Gunduz Huseyn Lee
*/
import java.util.*;


public class Inventory{
	//Properties
	private ArrayList<Item> storedItemList;
	private double maxCapacity;
	private double currentCapacity;

	//Constructors
	public Inventory(){
		this.storedItemList = null;
		maxCapacity = 50.0;		//Default value
		currentCapacity = 0.0;
	}

	public Inventory(ArrayList<Item> storedItemList, double maxCapacity, double currentCapacity){
		this.storedItemList = storedItemList;
		this.maxCapacity = maxCapacity;
		this.currentCapacity = currentCapacity;
	}

	//Methods
	//Removing the item from the list
	public void removeItem(String itemName){		
		int index = this.getItemIndex(itemName);
		this.removeItem(index);		
	}

	public void removeItem(Item item){
		int index = this.getItemIndex(item.getName());
		this.removeItem(index);
	}

	private void removeItem(int index){
		int count = this.getCount(index);

		assert this.storedItemList.get(index).isVisible();
		assert count > 0;
		assert index != -1;
		
		//updating the capacity
		//at this point assuming the remove was successfull
		this.currentCapacity -= this.storedItemList.get(index).getWeight();

		if(count > 1)
			this.storedItemList.remove(index);
		//count is 1
		else{
			this.storedItemList.get(index).setVisible(false);
		}
	}

	//addding Item
	public boolean

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
		return this.storedItemList;
	}

	public double getCurrentCapacity(){
		return this.currentCapacity;
	}

	//Private methods
	private int getCount(int index){
		String name = this.storedItemList.get(index).getName();
		int count = 0;
		for(Item tmpItem : this.storedItemList)
			if(tmpItem.getName().equals(name))
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
}

