/*
** ItemDriver class
** This class is used to test the Item classes
** 
**
** Author: Gunduz Huseyn Lee
** File created:  24.04.2017
** Last modified: 25.04.2017
** Last modified by: Gunduz Huseyn Lee
*/

import java.util.*;

public class ItemDriver{

	public static void main(String[] args){
		ArrayList<String> actions = new ArrayList<String>();
		actions.add("Use");
		actions.add("Take");

		//public Item(int id, String name, String description, double weight, int renewalTime, boolean renewable, boolean visible, ArrayList<String> actionList){
		GameObject object = new Item(1001, "Rock", "It is a simple rock. Nothing really interesting!", 0.5, 3, false, true, actions);
		GameObject object2 = new Item(1001, "Rock", "It is a simple rock. Nothing really interesting!", 0.5, 3, false, true, actions);
		GameObject object3 = new Item(1001, "Rock", "It is a simple rock. Nothing really interesting!", 0.5, 3, false, true, actions);

		ArrayList<GameObject> items = new ArrayList<GameObject>();
		items.add(object);
		items.add(object2);

		System.out.println(items.size());

		boolean bool = items.remove(object3);
		System.out.println(bool);
		System.out.println(items.size());
		System.out.println(object.getName());
		System.out.println(object3);
	}
}
