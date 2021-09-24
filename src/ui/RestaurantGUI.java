package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Dish;
import model.Ingredient;
import model.Inventory;
import model.MEASUREMENT_TYPE;
import model.Restaurant;
import model.User;

//Clase controladora, aqui poner todo lo referente a lo grafico (Es como el Main en nuestros proyectos)
public class RestaurantGUI {
	
	
	//Variables de RestaurantGUI
	@FXML
    private Pane mainPane;

	private Stage mainStage;
	
	//Relacion entre Restaurant y su contraladora
	private Restaurant laCucharita;
	
	//Variables del modulo de autenticacion
	@FXML
    private TextField loginUserField;
	@FXML
    private PasswordField loginPassField;
	
	//Variables del modulo de Empleados
	@FXML
    private TableView<User> tableAccList;
	@FXML
    private TableColumn<User,String> ID;
    @FXML
    private TableColumn<User,String> colUserName;
    @FXML
    private TableColumn<User,String> colBirthday;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField id;
    @FXML
    private DatePicker birthday;
    @FXML
    private PasswordField passwordField;
    
    //Variables del modulo de inventario
    private ObservableList<Ingredient> observableListIngredients;
    // instancia de la clase Inventory
    private Inventory inventory;
    
	//Variables del modulo de carta
	@FXML
    private TableView<Ingredient> tvDishIngredients;
    @FXML
    private TableColumn<Ingredient, String> tcDishIngredientName;
    @FXML
    private TableColumn<Ingredient, Double> tcDishIngredientAmount;
    @FXML
    private TableColumn<Ingredient, MEASUREMENT_TYPE> tcDishIngredientMeasurement;
	@FXML
    private ComboBox<String> cboxIngredientsAvailable;
	@FXML
    private TextField amountOfIngredients;
	@FXML
    private TextField txtFdishName;
	@FXML
    private TextField dishPrice;
	
	private List<Ingredient> auxdishIngredients;
	private ObservableList<Ingredient> obsDishIngredients;
	
	//Variables del modulo de pedidos
	@FXML
    private TableView<Dish> tvDishesAvailable;
    @FXML
    private TableColumn<Dish, String> tcDish;
    @FXML
    private TableColumn<Dish, Double> tcDishPrice;
	
    private ObservableList<Dish> obsDishesAvailable;

	//Constructor de RestaurantGUI
	public RestaurantGUI() {
		laCucharita = new Restaurant();
		inventory = new Inventory();
		auxdishIngredients = new ArrayList<Ingredient>();
	}
	
	/**Metodos de Acciones:*/
	
	//Este metodo evalua si el usuario esta registrado en la lista y si lo esta permite acceder a los demas modulos
	@FXML
    void LogIn(ActionEvent event) throws IOException {
		String user = loginUserField.getText();
		String password = loginPassField.getText();
		
		if(!user.equals("") && !password.equals("")) {
			if(laCucharita.evaluate_If_User_Can_LogIn(user, password)) {
				showMainPane();
			} else {
				printWarning("El usuario o la contraseña es incorrecto");
			}
		} else {
			printWarning("Por favor llenar todos los campos");
		}
    }
	
	@FXML
    void openDishMenuModule(ActionEvent event) throws IOException {
		DishMenu();
    }
	
	@FXML
	void openOrderModule(ActionEvent event) throws IOException {
		OrderMenu();
    }
	
	@FXML
    void evaluateIngredientComboBox(ActionEvent event) {
		String value = cboxIngredientsAvailable.getValue();
		
		if(value.equals("")) {
			amountOfIngredients.setText("0");
		} else {
			for(int i = 0; i < inventory.getIngredients().size(); i++) {
				if(value.equals(inventory.getIngredients().get(i).getName())) {
					String amount = "" + inventory.getIngredients().get(i).getAmount();
					amountOfIngredients.setText(amount);
				}
			}
		}
		
    }
	
	@FXML
	void lessNeededIngredient(ActionEvent event) {
		double amount = Double.parseDouble(amountOfIngredients.getText());
		
		if(amount > 1) {
			amount = lessValue(amount);
		}
		
		String valueInText = "" + amount;
		
		amountOfIngredients.setText(valueInText);
	}

	@FXML
	void plusNeededIngredient(ActionEvent event) {
		double amount = Double.parseDouble(amountOfIngredients.getText());
		double amountTotal = 0;
		
		String value = cboxIngredientsAvailable.getValue();

		for(int i = 0; i < inventory.getIngredients().size(); i++) {
			if(value.equals(inventory.getIngredients().get(i).getName())) {
				String amountTotalText = "" + inventory.getIngredients().get(i).getAmount();
				amountTotal = Double.parseDouble(amountTotalText);
			}
		}

		if(amount < amountTotal) {
			amount = plusValue(amount);
		}

		String valueInText = "" + amount;

		amountOfIngredients.setText(valueInText);
	}
	
	@FXML
    void addDishIngredientToList(ActionEvent event) throws IOException {
		String value = cboxIngredientsAvailable.getValue();
		MEASUREMENT_TYPE measurent = null;
		double amount = 0;

		if(!value.equals("")) {
			for(int i = 0; i < inventory.getIngredients().size(); i++) {
				if(value.equals(inventory.getIngredients().get(i).getName())) {
					measurent = inventory.getIngredients().get(i).getMeasurement();
					amount = Double.parseDouble(amountOfIngredients.getText());
				}
			}
			
			auxdishIngredients.add(new Ingredient(value, measurent, amount));
			itializeTableViewOfDishIngredients();
		} else {
			printWarning("Porfavor Escoja un ingrediente a utilizar");
		}
		
		cboxIngredientsAvailable.setValue("");
		
    }
	
	private void itializeTableViewOfDishIngredients() {
		obsDishIngredients = FXCollections.observableArrayList(auxdishIngredients);
    	
    	tvDishIngredients.setItems(obsDishIngredients);
    	tcDishIngredientName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
    	tcDishIngredientAmount.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("amount"));
    	tcDishIngredientMeasurement.setCellValueFactory(new PropertyValueFactory<Ingredient, MEASUREMENT_TYPE>("measurement"));
    }
	
	@FXML
    void addNewDish(ActionEvent event) {
		
		String warning = "";
		
		if(txtFdishName.getText().equals("")) {
			warning += "- Porfavor asignele un nombre al platillo\n";
		}
		
		if(auxdishIngredients.isEmpty()) {
			warning += "- Por favor ingrese los ingredientes que conforman al platillo\n";
		}
		
		if(dishPrice.getText().equals("")) {
			warning += "- Porfavor asignele un precio al platillo\n";
		}
		
		if(warning.equals("")) {
			String dishNameText = txtFdishName.getText();
			double price = Double.parseDouble(dishPrice.getText());
			
			if(laCucharita.add_New_Dish_In_The_Menu(dishNameText, (ArrayList<Ingredient>) auxdishIngredients, price)) {
				printWarning("Se ha agregado correctamente el nuevo platillo");
			} else {
				printWarning("Ha ocurrido un error al momento de registrar el platillo");
			}
		}else {
			printWarning(warning);
		}
		
    }
	
	private void itializeTableViewOfDishesAvailable() {
		obsDishesAvailable = FXCollections.observableArrayList(laCucharita.getDishesAvailable());
    	
		tvDishesAvailable.setItems(obsDishesAvailable);
		tcDish.setCellValueFactory(new PropertyValueFactory<Dish, String>("dishName"));
		tcDishPrice.setCellValueFactory(new PropertyValueFactory<Dish, Double>("price"));
    }
	
	
	
	/**Metodos de mostrar modulos*/
	
	public void showMainPane() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        mainStage.setScene(scene);
        mainStage.setTitle("Menu Principal");
        mainStage.show();
	}
	
	/**
	 * Este metodo muestra en pantalla el modulo de autenticacion
	 * Dieñado por Juan Camilo
	 * */
	public void LogInMenu() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        mainStage.setScene(scene);
        mainStage.setTitle("Modulo de Autenticacion");
        mainStage.show();
	}
	
	/** Este metodo muestra en pantalla el modulo de empleados
	 * Dieñado por Juan Camilo
	 * */
	public void EmployeesMenu() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employees_module.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.setTitle("Modulo de Empleados");
		mainStage.show();
	}
	
	//Este metodo muestra en pantalla el modulo de carta
	public void DishMenu() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("menu_module.fxml"));
		fxmlloader.setController(this);
		Parent log = fxmlloader.load();
		mainPane.getChildren().setAll(log);

		List<String> ingredients = new ArrayList<String>();
		ingredients.add("");
		
		for(int i = 0; i < inventory.getIngredients().size(); i++) {
			ingredients.add(inventory.getIngredients().get(i).getName());
		}
		
		cboxIngredientsAvailable.getItems().addAll(ingredients);
		cboxIngredientsAvailable.setValue("");
		itializeTableViewOfDishIngredients();
	}

	//Este metodo muestra en pantalla el modulo de Pedidos
	public void OrderMenu() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("order_module.fxml"));
    	fxmlloader.setController(this);
    	Parent log = fxmlloader.load();
    	mainPane.getChildren().setAll(log);
    	
    	itializeTableViewOfDishesAvailable();
	}

	//Este metodo muestra la pantalla del modulo de inventario
	public void OpenInventory() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Inventory.fxml"));
    	fxmlloader.setController(this);
    	Parent log = fxmlloader.load();
    	mainPane.getChildren().setAll(log);
    	
    	measurementType.getItems().addAll(MEASUREMENT_TYPE.MILLILITERS, MEASUREMENT_TYPE.GRAMS, MEASUREMENT_TYPE.UNITS, MEASUREMENT_TYPE.KILOGRAMS);
    	
    	itializeTableView();
	}
	
	
	///////////////////////////////////////////////////////////list-employees
	//Este metodo muestra la pantalla del modulo de empleados
	public void OpenEmployees() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("list-employees.fxml"));
		fxmlloader.setController(this);
		Parent log = fxmlloader.load();
		mainPane.getChildren().setAll(log);

		initializeTableViewEmployees();
	}
	

	
	
	//Este metodo hace el registor a un empleado
    @FXML
    public void createAccount(ActionEvent event) {
    	if(!txtUserName.getText().equals("") && !id.getText().equals("") &&birthday.getValue()!=null  &&  !passwordField.getText().equals("")){
    		if(!id.getText().equals("") && !txtUserName.getText().equals("")  &&birthday.getValue()!=null  &&  !passwordField.getText().equals("")){

    			laCucharita.createAccount(id.getText(), txtUserName.getText(), birthday.getValue(),passwordField.getText());

    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Cuenta creada");
    			alert.setHeaderText(null);
    			alert.setContentText("Se ha creado un nuevo empleado!" + "\n" + "Bienvenido " + txtUserName.getText() + "!");

    			alert.showAndWait();

    			txtUserName.clear();
    			id.clear();
    			passwordField.clear();

    			birthday.setValue(null);




    		}else {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Acceso denegado");
    			alert.setHeaderText(null);
    			alert.setContentText("Debes completar cada campo en el formulario");

    			alert.showAndWait();
    		}

    		initializeTableViewEmployees();

    	}
    }
    	
	
	
	
	public void initializeTableViewEmployees() {
		List<User> employees = new ArrayList<User>();
		for (int i = 0; i < laCucharita.getUserList().size(); i++) {
			if (laCucharita.getUserList().get(i).getRole().equals("employee") ) {
				employees.add(laCucharita.getUserList().get(i));
			}
		}
    	ObservableList<User> list= FXCollections.observableArrayList(employees);
    	
    	tableAccList.setItems(list);
    	colUserName.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
    	ID.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
    	colBirthday.setCellValueFactory(new PropertyValueFactory<User,String>("birthday"));
    	
	
    	
	}
	
	
	@FXML
    public void logOut(ActionEvent event) throws IOException {
		LogInMenu();
    }
	
	
	
	
	
	
	

	//setters
	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	//metodos y atributos para la clase inventario

	 	@FXML
	    private TableView<Ingredient> tvIngredients;

	    @FXML
	    private TableColumn<Ingredient, String> tcName;

	    @FXML
	    private TableColumn<Ingredient, Double> tcAmount;

	    @FXML
	    private TableColumn<Ingredient, MEASUREMENT_TYPE> tcMeasurementType;

	    @FXML
	    private TextField txtNameNewIngredient;

	    @FXML
	    private TextField txtAmountNewIngredient;
	    
	    @FXML
	    private ComboBox<MEASUREMENT_TYPE> measurementType;

	    @FXML
	    private Label lbNameIngredient;

	    @FXML
	    private Label lbAmount;

	    @FXML
	    private Label lbMeasurementType;

	  
	    // este metodo es para agregar un nuevo ingrediente desde el inventario
	    @FXML
	    void addNewIngredient(ActionEvent event) {
	    	String name ="";
	    	MEASUREMENT_TYPE type;
	    	double amount = -1;
	    	
	    	name = txtNameNewIngredient.getText();
	    	type = measurementType.getValue();
	    	
	    	//comprueba
	    	if(Double.parseDouble(txtAmountNewIngredient.getText())<0) {
	    		printWarning("The amount can't be a negative number");
	    	}else {
	    		amount = Double.parseDouble(txtAmountNewIngredient.getText());
	    	}
	    	
	    	
	    	if (name.equals("") || type == null || txtAmountNewIngredient.getText().equals("")) {
	    		printWarning("Please, Complete all fields");
			} else if (inventory.ingredientExist(name)){
				printWarning("The ingredient you want to add already exists, try modifying its amount");
			} else {
				inventory.addNewIngredient(name, type, amount);
				printWarning("The new ingredient was successfully registered");
				itializeTableView();
			}
	    	
	    }

	    
	    // Este metodo inicializa la lista que muestra los ingredinetes en el modulo de inventario
	    private void itializeTableView() {
	    	observableListIngredients = FXCollections.observableArrayList(inventory.getIngredients());
	    	
	    	tvIngredients.setItems(observableListIngredients);
	    	tcName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
	    	tcAmount.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("amount"));
	    	tcMeasurementType.setCellValueFactory(new PropertyValueFactory<Ingredient, MEASUREMENT_TYPE>("measurement"));
	    }
	    
	    // este metodo es para restar en 1 la cantidad del ingrediente seleccionado
	    @FXML
	    void less(ActionEvent event) {

	    }
	    
	    

	    
	    // este metodo es para restar en 1 la cantidad del ingrediente seleccionado
	    @FXML
	    void plus(ActionEvent event) {

	    }
	    
	    
	    //Metodos de aumento y decremento
	    public double lessValue(double value) {
	    	return value - 1;
	    }
	    
	    public double plusValue(double value) {
	    	return value + 1;
	    }
	    
	    //Metodo de reportes
	    public void printWarning(String message) {
	    	JOptionPane.showMessageDialog(null, message);
	    }
	
}
