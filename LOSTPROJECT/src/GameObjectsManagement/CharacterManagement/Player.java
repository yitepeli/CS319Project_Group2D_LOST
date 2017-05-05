/**
 * 
 */
package GameObjectsManagement.CharacterManagement;

import java.util.ArrayList;

import GameObjectsManagement.ItemManagement.*;
import GameObjectsManagement.ObjectManagement.Record;

/**
 * @author Yasin
 *
 */
public class Player extends AggresiveCharacter {



	private int energy;
	private int thirst;
	private int hunger;
	private String currentArea;
	private Record userRecord;
    private double gameTime;

	public Player() {
		super();
		setEnergy(100);
		setHealth(100);
		setAttack(15);
		setDefense(10);
		this.thirst=0;
		this.hunger=0;
		updateThirst(15);
		updateHunger(15);

	}
	
	public Player(String name) {
		this.setName(name);
		setEnergy(60);
		updateThirst(15);
		updateHunger(15);
	}
	
	public Player(int id, String name, String description, int health,
			int defense, int attack, int energy, int thirst, int hunger) {
		this.energy = energy;
		this.thirst = thirst;
		this.hunger = hunger;
		// TODO Auto-generated constructor stub
	}

	public boolean cookMeat(){
		
		return false;
	}
	public void updateGameTime(double incAmount){
		this.gameTime += incAmount;
	}
	public double getGameTime(){
		return gameTime;
	}
	
	public boolean boilWater(){
		
		
		return false;
	}
	
	public void craft(CraftableItem item, int amount){
		ArrayList<Item> requiredItemsList = item.getRequiredItemsList();

		for(Item tmpItem : requiredItemsList)
			for(int i = 0; i < tmpItem.getQuantity(); i++)
				this.removeItem(tmpItem);

		for(int i = 0; i < amount; i++)
			this.addItem(item);

		assert this.getInventory().hasItem(item.getName(), 1);
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

	public void setRecord(Record record){
		this.userRecord = record;
	}

	public Record getUserRecord(){
		return userRecord;
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
