package model;

import java.util.ArrayList;
import java.util.List;

public class Dish {

	private String dishName;
	private List<Ingredient> ingredientList; 
	private double price;
	
	public Dish(String dishName, ArrayList<Ingredient> ingredients, double price) {
		ingredientList = ingredients;
		
		this.dishName = dishName;
		this.price = price;
	}
	
	
	
}
