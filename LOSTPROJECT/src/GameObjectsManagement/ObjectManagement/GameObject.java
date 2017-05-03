//package ItemManagement;
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
package GameObjectsManagement.ObjectManagement;

public class GameObject{

	protected long cloudId;
	protected String objectName;
	protected String description;
	protected boolean onCloud;
	protected int objectId;
	protected String imageUrl;


	/**
	* Default constructor for the GameObject class
	*/
	public GameObject(){
		this.objectId = 0;
		this.objectName ="";
		this.description = "";
	}

	public GameObject(int id, String name, String description){
		this.objectId = id;
		this.objectName = name;
		this.description = description;
	}

	public int getId(){
		return this.objectId;
	}

	public String getName(){
		return this.objectName;
	}

	public String getDescription(){
		return this.description;
	}
	
	public void setId(int id){
		this.objectId = id;
	}

	public void setName(String name){
		this.objectName = name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	@Override
	public String toString(){
		return "This is a " + this.objectName + ".\n" + this.description + "\nIts id is " + this.objectId + ".\n";
	}

	public void setCloudId(long cloudId){
		this.cloudId = cloudId;
	}

	public long getCloudId(){
		return cloudId;
	}

	public void setOnCloud(boolean onCloud){
		this.onCloud = onCloud;
	}

	public boolean isOnCloud(){
		return onCloud;
	}

}