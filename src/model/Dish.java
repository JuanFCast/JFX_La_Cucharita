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
	
	public Dish(String dishName, String prueba, double price) {
		
		this.dishName = dishName;
		this.price = price;
	}

	//Getters y Setters
	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public List<Ingredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
