package ui;

import java.io.IOException;

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
import model.Inventory;
import model.MEASUREMENT_TYPE;
import model.Restaurant;


//Clase controladora, aqui poner todo lo referente a lo grafico (Es como el Main en nuestros proyectos)
public class RestaurantGUI {
	
	///////////////////////////////////////////////////////////list-employees
	@FXML
    private TableView<?> tableAccList;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> colBirthday;
    

   
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
	 * Die�ado por Juan Camilo
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
				JOptionPane.showMessageDialog(null, "El usuario o la contrase�a es incorrecto");
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
	 * Die�ado por Juan Camilo
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
	 * Die�ado por Juan Camilo
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
	}
	
	
	///////////////////////////////////////////////////////////list-employees
	//Este metodo muestra la pantalla del modulo de empleados
	public void OpenEmployees() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("list-employe.fxml"));
		fxmlloader.setController(this);
		Parent log = fxmlloader.load();
		mainPane.getChildren().setAll(log);

		
	}
	
	
	
	//Este metodo hace el registor a un empleado
    @FXML
    public void createAccount(ActionEvent event) {
    	
		
    	
    	if(!txtUserName.getText().equals("") && !id.getText().equals("") &&birthday.getValue()!=null  &&  !passwordField.getText().equals("")){
	    	
    		
    		
    		classroom.createAccount(txtUserName.getText(), passwordField.getText(),txtProfilePhoto.getText(),gend, career,birthday.getValue(), browser.getSelectionModel().getSelectedItem());
    		
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

    }
	
	
	
	public void initializeTableView() {
    	ObservableList<UserAccount> list= FXCollections.observableArrayList(classroom.getAccounts());
    	
    	tableAccList.setItems(list);
    	colUserName.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("userName"));
    	colGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender"));
    	colCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("career"));
    	colBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday"));
    	colBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("browser"));
	
    	
	}
	
	
	///////////////////////////////////////////////////////////list-employees
	
	
	
	
	
	
	
	//setters
	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	//metodos y atributos para la clase inventario

	 	@FXML
	    private TableView<?> tbIngredients;

	    @FXML
	    private TableColumn<?, ?> tcName;

	    @FXML
	    private TableColumn<?, ?> tcAmount;

	    @FXML
	    private TableColumn<?, ?> tcMeasurementType;

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
			}
	    	
	    }

	    
	    // este metodo es para restar en 1 la cantidad del ingrediente seleccionado
	    @FXML
	    void less(ActionEvent event) {

	    }
	    
	    

	    
	    // este metodo es para restar en 1 la cantidad del ingrediente seleccionado
	    @FXML
	    void plus(ActionEvent event) {

	    }
	
}
