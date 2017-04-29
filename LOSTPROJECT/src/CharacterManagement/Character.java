/**
 * 
 */
package CharacterManagement;

import GameObjects.SoundEffect;
import ItemManagement.GameObject;
import ItemManagement.Inventory;

/**
 * @author Yasin
 *
 */
public class Character extends GameObject {
	
	private int health;
	private int defense;
	private SoundEffect sound;
	private Inventory inventory;
	
	/**
	 * 
	 */
	public Character() {
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
	
	public void removeItem(String itemName){
		inventory.removeItem(itemName);
	}
	
	public void addItem(String itemName){
		inventory.addItem(itemName);
	}
	
	public void updateHealth(int health){
		if(this.health+health>100)
			this.health=100;
		else if(this.health+health<0)
			this.health +=0;
		else
			this.health+=health;
	}
	
	public boolean hasItem(String itemName){
		return inventory.hasItem(itemName);
	}

}
