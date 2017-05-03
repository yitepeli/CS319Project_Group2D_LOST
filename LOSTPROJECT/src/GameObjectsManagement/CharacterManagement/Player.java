/**
 * 
 */
package GameObjectsManagement.CharacterManagement;

import java.util.ArrayList;

import GameObjectsManagement.ItemManagement.*;

/**
 * @author Yasin
 *
 */
public class Player extends AggresiveCharacter {

	private int energy;
	private int thirst;
	private int hunger;
	private String currentArea;

	public Player() {
		setEnergy(60);
		updateThirst(15);
		updateHunger(15);
	}
	
	public Player(String name) {
		this.setName(name);
		setEnergy(60);
		updateThirst(15);
		updateHunger(15);
	}

	public Player(int id, String name, String description) {
		super(id, name, description);
		// TODO Auto-generated constructor stub
	}

	public boolean cookMeat(){
		if(this.hasItem("Fire") && this.hasItem("Raw Meat"))
			return true;
		return false;
	}
	
	public boolean boilWater(){
		
		if(this.hasItem("Fire") && this.hasItem("Water"))
			return true;
		return false;
	}
	
	public void craft(String itemName){
		CraftableItem aimedItem = (CraftableItem)this.getInventory().getItem(itemName);
		ArrayList<Item> requiredItem = aimedItem.getRequiredItemsList();
		for(int i=0; i<requiredItem.size(); i++){
			if(requiredItem.get(i) instanceof Tool){
				return;
			}
			else
				this.getInventory().removeItem(requiredItem.get(i).getName());
		}
		this.addItem(itemName);
	}
	
	
	/**
	 * @return the energy
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	/**
	 * @return the thirst
	 */
	public int getThirst() {
		return thirst;
	}

	/**
	 * @param thirst the thirst to set
	 */
	public void updateThirst(int thirst) {
		this.thirst += thirst;
	}

	/**
	 * @return the hunger
	 */
	public int getHunger() {
		return hunger;
	}

	/**
	 * @param hunger the hunger to set
	 */
	public void updateHunger(int hungerAmount) {
		this.hunger += hunger;
	}


	public void setCurrentPosition(String areaName){
		this.currentArea = areaName;
	}

	public String getCurrentPositionOfUser(){
		return currentArea;
	}

	private Player(Builder builder){
		currentArea = builder.curArea;
		energy = builder.energy;
		hunger = builder.hunger;
		attack = builder.attack;
		defense = builder.defense;
		health = builder.health;
		thirst = builder.thirst;
		objectName = builder.userName;
	}

	public static class Builder{
		private String curArea,userName;
		private int energy, health, hunger, attack, defense,thirst;

		public Builder energy(int energy){
			this.energy = energy;
			return this;
		}

		public Builder userName(String userName){
			this.userName = userName;
			return this;
		}

		public Builder thirst(int thirst){
			this.thirst = thirst;
			return this;
		}

		public Builder health(int health){
			this.health = health;
			return this;
		}

		public Builder hunger(int hunger){
			this.hunger = hunger;
			return this;
		}

		public Builder attack(int attack){
			this.attack = attack;
			return this;
		}

		public Builder defense(int defense){
			this.defense = defense;
			return this;
		}

		public Builder currentArea(String curArea){
			this.curArea = curArea;
			return this;
		}

		public Player build(){
			return new Player(this);
		}
	}

}
