/**
 * @author Yasin
 * @param <inventory>
 *
 */
public class Player extends AggresiveCharacter {

	private int energy;
	private int thirst;
	private int hunger;
	
	public Player() {
		setEnergy(60);
		updateThirst(15);
		updateHunger(15);
	}

	public boolean cookMeat(){
		if(this.hasItem("Fire") && this.hasItem("Raw Meat"))
			return true;
		return false;
	}
	
	public void craft(String itemName){
		//this.getInventory().getRequiredItemList();
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

}
