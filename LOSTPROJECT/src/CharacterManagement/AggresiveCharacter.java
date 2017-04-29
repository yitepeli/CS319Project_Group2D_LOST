/**
 * 
 */
package CharacterManagement;

/**
 * @author Yasin
 *
 */
public class AggresiveCharacter extends Character {

	private int attack;
	/**
	 * 
	 */
	public AggresiveCharacter() {
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
	

	public int getAttack(){
		return attack;
	}
	
	public void setAttack(int attack){
		this.attack+=attack;
	}

}
