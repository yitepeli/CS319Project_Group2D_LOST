/**
 * 
 */
package GameObjectsManagement.CharacterManagement;

import GameObjectsManagement.ObjectManagement.*;
import GameObjectsManagement.ItemManagement.*;

/**
 * @author Yasin
 *
 */
public class Character extends GameObject {
	
	protected int health;
	protected int defense;
	private SoundEffect sound;
	private Inventory inventory;
	private double escapeChance;

	/**
	 * 
	 */
	public Character() {
		inventory  = new Inventory();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public Character(int id, String name, String description) {
		super(id, name, description);
		// TODO Auto-generated constructor stub
	}
	
	public Character(int id, String name, String description, int health, int defense) {
		super(id, name, description);
		this.health = health;
		this.defense = defense;
		inventory = new Inventory();
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	
	/**
	 * @return the defense
	 */
	public int getDefense() {
		return defense;
	}
	/**
	 * @param defense the defense to set
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	
	/**
	 * @return the sound
	 */
	public SoundEffect getSound() {
		return sound;
	}
	/**
	 * @param sound the sound to set
	 */
	public void setSound(SoundEffect sound) {
		this.sound = sound;
	}
	
	public Item getItem(String itemName){
		return this.inventory.getItem(itemName);
	}
	
	/**
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public void removeItem(Item item){
		inventory.removeItem(item);
	}
	
	public boolean addItem(Item item){
		return this.inventory.addItem(item);
	}
	
	public void updateHealth(int health){
		if(this.health+health>100)
			this.health=100;
		else if(this.health+health<0)
			this.health =0;
		else
			this.health+=health;
	}
	
	public boolean hasItem(String itemName, int amount){
		return inventory.hasItem(itemName, amount);
	}

	public double getEscapeChance() {
		return this.escapeChance;
	}

}
