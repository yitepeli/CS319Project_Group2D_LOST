/**
 * 
 */
package GameObjectsManagement.CharacterManagement;

/**
 * @author Yasin
 *
 */
public class AggresiveCharacter extends Character {

	protected int attack;
	/**
	 * 
	 */
	public AggresiveCharacter() {
		super();
		attack=10;
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public AggresiveCharacter(int id, String name, String description) {
		super(id, name, description);
		// TODO Auto-generated constructor stub
	}	
	
	public AggresiveCharacter(int id, String name, String description, int health, int defense, int attack) {
		super(id, name, description, health, defense);
		this.attack = attack;
		// TODO Auto-generated constructor stub
	}
	

	public int getAttack(){
		return attack;
	}
	
	public void setAttack(int attack){
		this.attack+=attack;
	}

}
