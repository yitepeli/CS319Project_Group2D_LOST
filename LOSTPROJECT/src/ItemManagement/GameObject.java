package ItemManagement;
/*
** GameObject Class
** A general class for representing the objects in the game universe.
** Has three properties: id, name, and description. All are self explanatory.
**
** Author: Gunduz Huseyn Lee
** File created:  12.04.2017
** Last modified: 24.04.2017
** Last modified by: Gunduz Huseyn Lee
*/

/*
** To Dos:
** 1. Add image property, and add/modify related functions. Can be done after the beta version.
*/


public class GameObject{
	private int id;
	private String name;
	private String description;	

	/**
	* Default constructor for the GameObject class
	*/
	public GameObject(){
		this.id = 0;
		this.name ="";
		this.description = "";
	}

	public GameObject(int id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId(){
		return this.id;
	}

	public String getName(){
		return this.name;
	}

	public String getDescription(){
		return this.description;
	}

	@Override
	public String toString(){
		return "This is a " + this.name + ".\n" + this.description + "\nIts id is " + this.id + ".\n";
	}
}