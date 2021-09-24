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
import model.Ingredient;
import model.Inventory;
import model.MEASUREMENT_TYPE;
import model.Restaurant;
import model.User;


//Clase controladora, aqui poner todo lo referente a lo grafico (Es como el Main en nuestros proyectos)
public class RestaurantGUI {
	
	///////////////////////////////////////////////////////////list-employees
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
    

   
    ///////////////////////////////////////////////////////////list-employees
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Variables
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
	

	//Variables del modulo de carta
	@FXML
    private ComboBox<String> cboxIngredientsAvailable;
	@FXML
    private TextField amountOfIngredients;
	
	private ObservableList<Ingredient> observableListIngredients;

	
	// instancia de la clase Inventory
	private Inventory inventory;

	//Constructor
	public RestaurantGUI() {
		laCucharita = new Restaurant();
		inventory = new Inventory();
		
	}
	
	/**Metodos de Acciones:*/
	
	/**
	 * Este metodo evalua si el usuario esta registrado en la lista y si lo esta permite acceder a los demas modulos
	 * Dieñado por Juan Camilo
	 * @throws IOException 
	 * */
	@FXML
    void LogIn(ActionEvent event) throws IOException {
		String user = loginUserField.getText();
		String password = loginPassField.getText();
		
		if(!user.equals("") && !password.equals("")) {
			if(laCucharita.evaluate_If_User_Can_LogIn(user, password)) {
				showMainPane();
			} else {
				JOptionPane.showMessageDialog(null, "El usuario o la contraseña es incorrecto");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Por favor llenar todos los campos");
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
		
		for(int i = 0; i < inventory.getIngredients().size(); i++) {
			if(value.equals(inventory.getIngredients().get(i).getName())) {
				String amount = "" + inventory.getIngredients().get(i).getAmount();
				amountOfIngredients.setText(amount);
			}
		}
		
		
    }
	
	/**Metodos de mostrar modulos
	 * @throws IOException */
	
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
		
		for(int i = 0; i < inventory.getIngredients().size(); i++) {
			ingredients.add(inventory.getIngredients().get(i).getName());
		}
		
		cboxIngredientsAvailable.getItems().addAll(ingredients);
	}

	//Este metodo muestra en pantalla el modulo de Pedidos
	public void OrderMenu() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("order_module.fxml"));
    	fxmlloader.setController(this);
    	Parent log = fxmlloader.load();
    	mainPane.getChildren().setAll(log);
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
	
	
	///////////////////////////////////////////////////////////list-employees
	
	
	
	
	
	
	

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
	    		JOptionPane.showMessageDialog(null, "The amount can't be a negative number");
	    	}else {
	    		amount = Double.parseDouble(txtAmountNewIngredient.getText());
	    	}
	    	
	    	
	    	if (name.equals("") || type == null || txtAmountNewIngredient.getText().equals("")) {
	    		JOptionPane.showMessageDialog(null, "Please, Complete all fields");
			} else if (inventory.ingredientExist(name)){
				JOptionPane.showMessageDialog(null, "The ingredient you want to add already exists, try modifying its amount");
			} else {
				inventory.addNewIngredient(name, type, amount);
				JOptionPane.showMessageDialog(null, "The new ingredient was successfully registered");
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
	
}
