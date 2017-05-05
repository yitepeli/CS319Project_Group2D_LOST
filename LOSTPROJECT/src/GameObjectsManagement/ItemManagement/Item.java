/*
** Item Class
** Base class for all of the items existing in our game universe. Extends the GameObject class, and thus inherits its properties.
**
** Author: Gunduz Huseyn Lee
** File created:  13.04.2017
** Last modified: 05.05.2017
** Last modified by: Gunduz Huseyn Lee
*/
package GameObjectsManagement.ItemManagement;

import GameObjectsManagement.ObjectManagement.*;

import java.util.*;

public class Item extends GameObject{
	//Properties
	private double weight;
	private int renewalTime;
	private int quantity;
	private boolean renewable;	
	private ArrayList<String> actionList;



	//Constructors
	public Item(String name){
		super(0, name, "");
		this.weight = 0.0;
		this.renewalTime = 0;
		this.renewable = false;
		this.actionList = null;
	}

	public Item(int id, String name, String description, double weight, int renewalTime, boolean renewable, ArrayList<String> actionList){
		super(id, name, description);
		this.weight = weight;
		this.renewalTime = renewalTime;
		this.renewable = renewable;
		this.actionList = actionList;
	}

	public Item(int id, String name, String description){
		super(id,name,description);
	}

	//Methods
	public String console_displayInteractions(){
		String result = "\nHere is what you can do with " + this.getName() + ":\n";
		int size = this.actionList.size();
		//actionList should not be empty!
		assert size > 0;
		for(int i = 0; i < size; i++)
			result = result + (i + 1) + "." + this.actionList.get(i) + "\n";

		return result;
	}

	public ArrayList<String> getInteractions(){
		return this.actionList;
	}

	public double getWeight(){
		return this.weight;
	}

	public boolean isRenewable(){
		return this.renewable;
	}

	public int getRenewableTime(){
		return this.renewalTime;
	}

	public int getQuantity(){
		return this.quantity;
	}

	@Override
	public String toString(){
		return super.toString() + "\nWeight of the item is: " + weight + this.console_displayInteractions();
	}


}