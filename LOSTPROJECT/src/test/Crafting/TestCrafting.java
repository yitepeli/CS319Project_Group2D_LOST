package test.Crafting;

import GameObjectsManagement.ItemManagement.*;

public class TestCrafting{

	public static void main(String[] args){
		//System.out.println("nehrem");
		GameEngineTest game = new GameEngineTest();
		//System.out.println(game.getName());
		game.addItem("Rock");
		game.addItem("Wood");
		game.addItem("Wood");
		System.out.println(game.displayPlayerItems());

		game.craft("Spear");

		System.out.println(game.displayPlayerItems());
	}
}
