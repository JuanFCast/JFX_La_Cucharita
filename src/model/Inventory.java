package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	//Este metodo es el que crea el archivo serializado de ingredientes
		//Archive serialized
	public void saveIngredients() throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("data/Ingredients.LaCucharita")));
		oos.writeObject(ingredients);
		oos.close();
	}
	
	
	//Este metodo importa el archivo serializado con los datos de ingredients, la @SuppressWarnings hace referencia
	//a que se puede dar el caso que se carge un archivo que no sea de tipo Ingredint, pero como estamos seguros que no
	//pasara, podemos suprimir ese warning
	@SuppressWarnings("unchecked")
	public boolean loadIngredients() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File("data/Ingredients.LaCucharita");
		boolean isLoaded = false;
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			ingredients = (List<Ingredient>) ois.readObject();
			ois.close();
			isLoaded = true;
		}
		return isLoaded;
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
