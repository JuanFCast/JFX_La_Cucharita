package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Clase principal del model, porfavor aqui colocar todos los metodos referentes al restaurante
public class Restaurant {

	//Lista de Usuarios del Restaurante (Empleados y moderadores)
	private List<User> userList;
	private List<Dish> dishesAvailable;
	private List<Order> order;
	private List<DishOrder> miniOrder;
	
	//Constructor
	public Restaurant() {
		userList = new ArrayList<User>();
		dishesAvailable = new ArrayList<Dish>();
		order = new ArrayList<Order>();
		miniOrder = new ArrayList<DishOrder>();
		
		//Creacion de Usuarios administradores
		userList.add(new User("123", "Administrador", "123", "Administrador"));
		userList.add(new User("1151969753", "Juan Camilo Ramirez", "Urs43M1n0ris", "Administrador"));
		
		
	}
	
	//METODOS
	
	
	//Este metodo añade un nuevo usuario al sistema
	public void createAccount(String id, String name, LocalDate birthday, String passwordfield) {
		User userAcc= new User(id, name, birthday, passwordfield);
		userList.add(userAcc);
		
	}
	
	
	// Este metodo revisa si un ingrediente que quiere ser agregado ya existe
	// retorna true si existe y false en el caso contrario
	public boolean employeeExist(String id) {
		boolean exist = false;
		for (int i = 0; i< userList.size() && !exist; i++) {
			if(userList.get(i).getId().equals(id)) {
				exist = true;
			}
		}
		return exist;
	}
	
	
	//Este metodo evalua si el usuario se encuentra registrado y sus datos coinciden para asi poder permitirle iniciar sesion
	public boolean evaluate_If_User_Can_LogIn(String user, String password) {
		boolean confirmation = false;
		
		for(int i = 0; i < userList.size(); i++) {
			if((userList.get(i).getId().equals(user)) && (userList.get(i).getPassword().equals(password))) {
				confirmation = true;
			}
		}
		
		return confirmation;
	}
	
	//Este metodo añade un platillo en la lista de carta
	public boolean add_New_Dish_In_The_Menu(String dishName, ArrayList<Ingredient> ingredients, double price) {
		if(dishesAvailable.add(new Dish(dishName, ingredients, price))) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addDishToOrder(Dish dish, int amount, double totalPrice) {
		if(miniOrder.add(new DishOrder(dish, amount, totalPrice))){
			return true;
		}else {
			return false;
		}
	}
	
	public boolean addOrder(String UUID, ArrayList<DishOrder> dishesOrdered) {
		if(order.add(new Order(UUID, dishesOrdered))){
			return true;
		}else {
			return false;
		}
	}
	
	
	
	//Este metodo es el que crea el archivo serializado de ingredientes
			//Archive serialized
		public void saveEmployees() throws FileNotFoundException, IOException{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("data/Employees.LaCucharita")));
			oos.writeObject(userList);
			oos.close();
		}
		
		
		//Este metodo importa el archivo serializado con los datos de ingredients, la @SuppressWarnings hace referencia
		//a que se puede dar el caso que se carge un archivo que no sea de tipo Ingredint, pero como estamos seguros que no
		//pasara, podemos suprimir ese warning
		@SuppressWarnings("unchecked")
		public boolean loadEmployees() throws FileNotFoundException, IOException, ClassNotFoundException {
			File f = new File("data/Employees.LaCucharita");
			boolean isLoaded = false;
			if(f.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				userList = (List<User>) ois.readObject();
				ois.close();
				isLoaded = true;
			}
			return isLoaded;
		}
	
	
	//Getters y Setters
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Dish> getDishesAvailable() {
		return dishesAvailable;
	}

	public List<Order> getOrder() {
		return order;
	}

	public List<DishOrder> getMiniOrder() {
		return miniOrder;
	}

	public void setMiniOrder(List<DishOrder> miniOrder) {
		this.miniOrder = miniOrder;
	}
	
	
	
}
