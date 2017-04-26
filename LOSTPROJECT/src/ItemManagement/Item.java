/*
** Item Class
** Base class for all of the items existing in our game universe. Extends the GameObject class, and thus inherits its properties.
**
** Author: Gunduz Huseyn Lee
** File created:  13.04.2017
** Last modified: 25.04.2017
** Last modified by: Gunduz Huseyn Lee
*/
package ItemManagement;
import java.util.*;

public class Item extends GameObject{
	//Properties
	private double weight;
	private int renewalTime;
	private boolean renewable;	
	private boolean visible;	
	private ArrayList<String> actionList;

	//Constructors
	public Item(String name){
		super(0, name, "");
		this.weight = 0.0;
		this.renewalTime = 0;
		this.renewable = false;
		this.visible = false;
		this.actionList = null;
	}

	public Item(int id, String name, String description, double weight, int renewalTime, boolean renewable, boolean visible, ArrayList<String> actionList){
		super(id, name, description);
		this.weight = weight;
		this.renewalTime = renewalTime;
		this.renewable = renewable;
		this.visible = visible;
		this.actionList = actionList;
	}

	//Methods
	public String console_displayInteractions(){
		String result = "\nHere is what you can do with " + this.getName() + ":\n";
		int size = this.actionList.size();
		//actionList should not be empty!
		assert size > 0;
		for(int i = 0; i < size; i++)
			result = result + (i + 1) + "." + this.actionList.get(i) + "\n";

		return result;
	}

	public ArrayList<String> getInteractions(){
		return this.actionList;
	}

	public double getWeight(){
		return this.weight;
	}

	public boolean isRenewable(){
		return this.renewable;
	}

	public int getRenewableTime(){
		return this.renewalTime;
	}

	public boolean isVisible(){
		return this.visible;
	}

	public void setVisible(boolean visible){
		this.visible = visible;
	}

	@Override
	public String toString(){
		return super.toString() + "\nDebugging information:\nWeight of the item is: " + weight + "\nVisibility: " + visible + this.console_displayInteractions();
	}


}