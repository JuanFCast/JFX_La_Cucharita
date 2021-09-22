package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	
	private List<Ingredient> ingredients;
	
	public Inventory() {
		
		ingredients = new ArrayList<Ingredient>();
		
	}
	
	//Este metodo es para agregar ingredientes nuevos, es decir, que no esten ya en el inventario
	
	public void addNewIngredient(String name, MEASUREMENT_TYPE measurement, int amount) {
		ingredients.add(new Ingredient(name, measurement, amount));
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
}
