import java.util.*;

public class test{

	public static void main(String[] args){
		ArrayList<String> actions = new ArrayList<String>();
		actions.add("Use");
		actions.add("Take");

		//public Item(int id, String name, String description, double weight, int renewalTime, boolean renewable, boolean visible, ArrayList<String> actionList){
		GameObject object = new Item(1001, "Rock", "It is a simple rock. Nothing really interesting!", 0.5, 3, false, true, actions);
		System.out.println(object.getName());
		System.out.println(object);
	}
}