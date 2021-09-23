package model;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private List<Dish> menu;
	
	public Menu() {
		menu = new ArrayList<Dish>();
	}
	
	//Este metodo agrega un nuevo 
	public boolean addNewDish(String dishName, ArrayList<Ingredient> ingredients, double price) {
		if(menu.add(new Dish(dishName, ingredients, price))) {
			return true;
		} else {
			return false;
		}
	}

	public List<Dish> getMenu() {
		return menu;
	}
	
}
