/*
** Food Class
** Food class represents all the consumable items in the game universe that will effect the player's general status. 
** Food extends Item class which in its turn extends GameObject class. 
**
** Author: Gunduz Huseyn Lee
** File created:  25.04.2017
** Last modified: 26.04.2017
** Last modified by: Gunduz Huseyn Lee
*/
//package ItemManagement;

import java.util.*;

public class Food extends Item{
	//Properties
	private int hungerPoints;
	private int thirstPoints;
	private int healthPoints;

	//Constructors
	public Food(String name){
		super(name);

		hungerPoints = 0;
		thirstPoints = 0;
		healthPoints = 0;
	}

	public Food(int id, String name, String description, double weight, int renewalTime, boolean renewable, boolean visible, ArrayList<String> actionList, int hungerPoints, int thirstPoints, int healthPoints){
		super(id, name, description, weight, renewalTime, renewable, visible, actionList);
		this.hungerPoints = hungerPoints;
		this.thirstPoints = thirstPoints;
		this.healthPoints = healthPoints;
	}

	//Methods
	public int getHungerPoints(){
		return this.hungerPoints;
	}

	public int getThirstPoints(){
		return this.thirstPoints;
	}

	public int getHealthPoints(){
		return this.healthPoints;
	}

	@Override
	public String toString(){
		return super.toString() + "You will gain this much food from this Item: " + this.hungerPoints + "\nYou will gain this much liquid from this Item: " + this.thirstPoints + "\nYou will gain this much health from this Item: " + this.healthPoints + "\n";
	}
}