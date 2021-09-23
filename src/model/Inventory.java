package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	
	private List<Ingredient> ingredients;
	
	public Inventory() {
		
		ingredients = new ArrayList<Ingredient>();
		
	}
	
	//Este metodo es para agregar ingredientes nuevos, es decir, que no esten ya en el inventario
	
	public void addNewIngredient(String name, MEASUREMENT_TYPE measurement, double amount) {
		ingredients.add(new Ingredient(name, measurement, amount));
	}
	
	// Este metodo revisa si un ingrediente que quiere ser agregado ya existe
	// retorna true si existe y false en el caso contrario
	public boolean ingredientExist(String name) {
		boolean exist = false;
		for (int i = 0; i< ingredients.size() && !exist; i++) {
			if(ingredients.get(i).getName().equals(name)) {
				exist = true;
			}
		}
		return exist;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
}
