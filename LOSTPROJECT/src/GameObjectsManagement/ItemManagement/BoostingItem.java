//package ItemManagement;
/*
** BoostingItem Class
** BoostingItem class represents all the items that effects the player's fighting abilities.
** BoostingItem extends the Item class which in turn extends GameObject class. 
**
** Author: Gunduz Huseyn Lee
** File created:  25.04.2017
** Last modified: 26.04.2017
** Last modified by: Gunduz Huseyn Lee
*/
package GameObjectsManagement.ItemManagement;

import java.util.*;

public class BoostingItem extends Item{
	//Properties
	private int attackPointBonus;
	private int defensePointBonus;

	//Constructors
	public BoostingItem(String name){
		super(name);

		attackPointBonus = 0;
		defensePointBonus = 0;
	}

	public BoostingItem(int id, String name, String description, double weight, int renewalTime, boolean renewable, ArrayList<String> actionList, int attackPointBonus, int defensePointBonus){
		super(id, name, description, weight, renewalTime, renewable, actionList);
		this.attackPointBonus = attackPointBonus;
		this.defensePointBonus = defensePointBonus;
	}

	//Methods
	public int getAttackPointBonus(){
		return this.attackPointBonus;
	}

	public int getDefensePointBonus(){
		return this.defensePointBonus;
	}

	@Override
	public String toString(){
		return super.toString() + "The item's attack bonus is: " + this.attackPointBonus + "\nThe item's defense bonus is: " + this.defensePointBonus + "\n";
	}

}