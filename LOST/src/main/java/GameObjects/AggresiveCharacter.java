/**
 * 
 */

/**
 * @author Yasin
 *
 */
public class AggresiveCharacter extends Character {

	private int attack;
	
	public AggresiveCharacter(){
		attack='10'
	}

	public int getAttack(){
		return attack;
	}
	
	public void setAttack(int attack){
		this.attack+=attack;
	}
}

