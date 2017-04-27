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

public class Tool extends BoostingItem{
	//Properties
	private ArrayList<Item> craftableItemsList;
	private ArrayList<Item> requiredItemsList;

	//Constructors
	public CraftableItem(String name){
		super(name);

		craftableItemsList = null;
		requiredItemsList = null;
	}

	public CraftableItem(int id, String name, String description, double weight, int renewalTime, boolean renewable, boolean visible, ArrayList<String> actionList, int hungerPoints, int thirstPoints, int healthPoints, ArrayList<Item> craftableItemsList, ArrayList<Item> requiredItemsList){
		super(id, name, description, weight, renewalTime, renewable, visible, actionList, hungerPoints, thirstPoints, healthPoints);
		this.craftableItemsList = craftableItemsList;
		this.requiredItemsList = requiredItemsList;
	}

	//Methods
	public ArrayList<Item> getCraftableItemsList(){
		return this.craftableItemsList;
	}

	public ArrayList<Item> getRequiredItemsList(){
		return this.requiredItemsList;
	}

	@Override
	public String toString(){
		String result = super.toString();

		if(this.craftableItemsList != null){
			result = result + "\nHere is what you can craft from this Item:\n";
			for(Item item : this.craftableItemsList)
				result = result + item.getName() + "   ";
			result = result + "\n";
		}

		if(this.requiredItemsList != null){
			result = result + "\nHere is what you need to craft this item:\n";
			for(Item item : this.requiredItemsList)
				result = result + item.getName() + "   ";
			result = result + "\n";
		}

		return result;
	}
}