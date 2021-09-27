package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Dish;
import model.DishOrder;
import model.Ingredient;
import model.Inventory;
import model.MEASUREMENT_TYPE;
import model.ORDER_STATUS;
import model.Order;
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
    private static Inventory inventory;
    
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
	
	//Variables del modulo de pedidos para Clientes
	@FXML
    private Label dishNameInOrderMenu;
	@FXML
    private TableView<Dish> tvDishesAvailable;
    @FXML
    private TableColumn<Dish, String> tcDish;
    @FXML
    private TableColumn<Dish, Double> tcDishPrice;
    @FXML
    private TextField txtFieldAmountDishToOrder;
    @FXML
    private Label labDishPriceText;
    @FXML
    private Label labDishPriceValue;
    @FXML
    private Button bttnAddToCart;
    @FXML
    private Button bttnPlusToOrder;
    @FXML
    private Button bttnLessToOrder;
    @FXML
    private ImageView imgvOrderPicture;
    
    private Dish dishSelected;
	
    private ObservableList<Dish> obsDishesAvailable;
    
    //Variables del modulo de carrito
    @FXML
    private TableView<DishOrder> tvOrderInCart;
    @FXML
    private TableColumn<DishOrder, String> tcDishInCart;
    @FXML
    private TableColumn<DishOrder, Integer> tcAmountDishInCart;
    @FXML
    private TableColumn<DishOrder, Double> tcTotalPriceDishInCart;
    @FXML
    private Label labTotalToPay;
    @FXML
    private ImageView imgvPictureOrder;
    @FXML
    private Label labDishOrder;
    @FXML
    private TextField txtFDishOrderAmount;
    @FXML
    private Button plusAmountOrder;
    @FXML
    private Button lessAmountOrder;
    @FXML
    private Label labOrderAmounttxt;
    
    private DishOrder dishOrderSelected;
    
    private ObservableList<DishOrder> obsDishOrder;
    

    //Variables de cambiar contraseña
    @FXML
    private TextField idconfirm;
    
    @FXML
    private PasswordField passwordConfirm;

    @FXML
    private PasswordField passwordNew;


    //Variables del modulo de pedidos para Empleados
    @FXML
    private TableView<Order> tvOrders;
    @FXML
    private TableColumn<Order, String> tcUUIDCODE;
    @FXML
    private TableColumn<Order, ORDER_STATUS> tcOrderStatus;
    @FXML
    private Label labUUIDCODE;
    @FXML
    private Label labOrderDate;
    @FXML
    private ComboBox<ORDER_STATUS> combBoxStatus;
    @FXML
    private TableView<DishOrder> tvDishesOrder;
    @FXML
    private TableColumn<DishOrder, String> tcDishNameOrdered;
    @FXML
    private TableColumn<DishOrder, Integer> tcAmountDishesOrder;
    @FXML
    private TableColumn<DishOrder, Double> tcTotalPriceOrder;
    @FXML
    private Label labTotalPriceToPay;
    
    private Order orderSelected;
    
    private ObservableList<Order> obsOrders;
    private ObservableList<DishOrder> obsDishesInOrder;
    
	//Constructor de RestaurantGUI
	public RestaurantGUI() {
		laCucharita = new Restaurant();
		inventory = new Inventory();
		auxdishIngredients = new ArrayList<Ingredient>();
	}
	
	//Este metodo exporta la informacion serializada
	@FXML
    void exportData(ActionEvent event) throws FileNotFoundException, IOException {
		inventory.saveIngredients();
		laCucharita.saveEmployees();
		printWarning("Information has been exported");
    }

	//Este metodo importa la informacion serializada
    @FXML
    void importData(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException{
    	inventory.loadIngredients();
    	laCucharita.loadEmployees();

    	printWarning("The information has been imported");

    	printWarning("Se ha importado la informacion");
		

    }
	
	/**Metodos de Acciones:*/
	
	//Este metodo evalua si el usuario esta registrado en la lista y si lo esta permite acceder a los demas modulos
	@FXML
    public void LogIn(ActionEvent event) throws IOException {
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
	void openMenu(ActionEvent event) throws IOException {
		menuModule();
    }
	
	@FXML
    void evaluateIngredientComboBox(ActionEvent event) {
		String value = cboxIngredientsAvailable.getValue();
		
		if(value.equals("")) {
			amountOfIngredients.setText("0");
		} else {
			for(int i = 0; i < inventory.getIngredients().size(); i++) {
				if(value.equals(inventory.getIngredients().get(i).getName())) {
					if(inventory.getIngredients().get(i).getAmount() >= 1) {
						amountOfIngredients.setText("1.0");
					} else {
						String amount = "" + inventory.getIngredients().get(i).getAmount();
						amountOfIngredients.setText(amount);
					}
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
			printWarning("Please choose an ingredient to use");
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
    void addNewDish(ActionEvent event) throws IOException {
		
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
				
				auxdishIngredients = new ArrayList<Ingredient>();
				DishMenu();
				
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
	
	@FXML
    void dishChoose(MouseEvent event) {
		if(tvDishesAvailable.getSelectionModel().getSelectedItem() != null) {
			
			dishSelected = tvDishesAvailable.getSelectionModel().getSelectedItem();
			
			dishNameInOrderMenu.setText(dishSelected.getDishName());
			txtFieldAmountDishToOrder.setText("1.0");
			labDishPriceValue.setText("" + dishSelected.getPrice());
			
			dishNameInOrderMenu.setVisible(true);
			txtFieldAmountDishToOrder.setVisible(true);
		    labDishPriceText.setVisible(true);
		    labDishPriceValue.setVisible(true);
		    bttnAddToCart.setVisible(true);
		    bttnPlusToOrder.setVisible(true);
		    bttnLessToOrder.setVisible(true);
		    imgvOrderPicture.setVisible(true);
		}
    }
	
	@FXML
    void lessDishToOrder(ActionEvent event) {
		double amount = Double.parseDouble(txtFieldAmountDishToOrder.getText());

		if(amount > 1) {
			amount = lessValue(amount);
		}

		String valueInText = "" + amount;

		txtFieldAmountDishToOrder.setText(valueInText);
    }

    @FXML
    void plusDishToOrder(ActionEvent event) {
    	double amount = Double.parseDouble(txtFieldAmountDishToOrder.getText());

    	amount = plusValue(amount);

		String valueInText = "" + amount;

		txtFieldAmountDishToOrder.setText(valueInText);
    }
    
    @FXML
    void openCart(ActionEvent event) throws IOException {
    	showCart();
    }
    
    @FXML
    void openOrderModule(ActionEvent event) throws IOException {
    	OrderMenu();
    }
    
    private void itializeTableViewOfOrders() {
    	obsOrders = FXCollections.observableArrayList(laCucharita.getOrder()); /////ESTOOOOOY AAAAAACAAAAAAAAA
    	
		tvOrders.setItems(obsOrders);
		tcUUIDCODE.setCellValueFactory(new PropertyValueFactory<Order, String>("UUID"));
		tcOrderStatus.setCellValueFactory(new PropertyValueFactory<Order, ORDER_STATUS>("status"));
    }
    
    @FXML
    void addToCart(ActionEvent event) throws IOException {
    	int amount = (int) Double.parseDouble(txtFieldAmountDishToOrder.getText());
    	if(laCucharita.addDishToOrder(dishSelected, amount, dishSelected.getPrice()*amount)) {
    		menuModule();
    	}else {
    		printWarning("Ha ocurrido un error, no se ha podido agregar el platillo al carrito");
    	}
    	
    }
    
    private void itializeTableViewOfItemsInCart() {
		obsDishOrder = FXCollections.observableArrayList(laCucharita.getMiniOrder()); /////ESTOOOOOY AAAAAACAAAAAAAAA
    	
		tvOrderInCart.setItems(obsDishOrder);
		tcDishInCart.setCellValueFactory(new PropertyValueFactory<DishOrder, String>("dishName"));
		tcAmountDishInCart.setCellValueFactory(new PropertyValueFactory<DishOrder, Integer>("amountOrderedDish"));
		tcTotalPriceDishInCart.setCellValueFactory(new PropertyValueFactory<DishOrder, Double>("totalPrice"));
    }
	
    public double totalToPay() {
    	double total = 0;
    	
    	for(int i = 0; i < laCucharita.getMiniOrder().size(); i++) {
    		if(laCucharita.getMiniOrder().get(i).getTotalPrice() != 0) {
    			total = total + laCucharita.getMiniOrder().get(i).getTotalPrice();
    		}
    	}
    	
    	return total;
    }
    
    @FXML
    void dishOrderChoose(MouseEvent event) {
    	if(tvOrderInCart.getSelectionModel().getSelectedItem() != null) {
    		
    		dishOrderSelected = tvOrderInCart.getSelectionModel().getSelectedItem();
        	
        	labDishOrder.setText(dishOrderSelected.getDishName());
        	txtFDishOrderAmount.setText("" + dishOrderSelected.getAmountOrderedDish());
        	
        	imgvPictureOrder.setVisible(true);
            labDishOrder.setVisible(true);
            txtFDishOrderAmount.setVisible(true);
            plusAmountOrder.setVisible(true);
            lessAmountOrder.setVisible(true);
            labOrderAmounttxt.setVisible(true);
    	}
    }
    
    @FXML
    void lessAmountOrder(ActionEvent event) {
    	double amount = Double.parseDouble(txtFDishOrderAmount.getText());
    	
    	if(amount > 1) {
    		amount = lessValue(amount);
    	}

		String valueInText = "" + amount;

		txtFDishOrderAmount.setText(valueInText);
    }

    @FXML
    void plusAmountOrder(ActionEvent event) {
    	double amount = Double.parseDouble(txtFDishOrderAmount.getText());

    	amount = plusValue(amount);

		String valueInText = "" + amount;

		txtFDishOrderAmount.setText(valueInText);
    }
    
    @FXML
    void applyChangestoOrder(ActionEvent event) throws IOException {
    	int newAmount = (int)Double.parseDouble(txtFDishOrderAmount.getText());
    	
    	for(int i = 0; i < laCucharita.getMiniOrder().size(); i++) {
    		if(dishOrderSelected == laCucharita.getMiniOrder().get(i)) {
    			laCucharita.getMiniOrder().get(i).setAmountOrderedDish(newAmount);
    			laCucharita.getMiniOrder().get(i).setTotalPrice(newAmount*laCucharita.getMiniOrder().get(i).getOrderedDish().getPrice());
    		}
    	}
    	
    	showCart();
    }
    
    @FXML
    void deleteThisOrder(ActionEvent event) throws IOException {
    	for(int i = 0; i < laCucharita.getMiniOrder().size(); i++) {
    		if(dishOrderSelected == laCucharita.getMiniOrder().get(i)) {
    			laCucharita.getMiniOrder().remove(i);
    		}
    	}
    	
    	showCart();
    }
    
    @FXML
    void newOrder(ActionEvent event) {
    	String UUIDCODE = generateRandomUUID();
    	
    	if(!laCucharita.getMiniOrder().isEmpty()) {
    		if(laCucharita.addOrder(UUIDCODE, (ArrayList<DishOrder>) laCucharita.getMiniOrder())) {
        		printWarning("Tu pedido ha pasado a estar en proceso, porfavor estar al tanto de su estado en el menu: Estado del Pedido");
        		laCucharita.setMiniOrder(new ArrayList<DishOrder>());
        	} else {
        		printWarning("No se ha podido registrar tu pedido, porfavor ponte en contacto con nosotros");
        	}
    		
    	} else {
    		printWarning("No hay ningun platillo seleccionado aun");
    	}
    	
    	
    }
    
    public String generateRandomUUID() {
    	String theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; 
    	StringBuilder builder = new StringBuilder();
    	
    	for (int i = 0; i < 10; i++) { 
            int myindex  = (int)(theAlphaNumericS.length() * Math.random()); 

            // add the characters
            builder.append(theAlphaNumericS.charAt(myindex)); 
        }
    	
    	return builder.toString();
    }
	
    @FXML
    void orderChoose(MouseEvent event) {
    	
    	if(tvOrders.getSelectionModel().getSelectedItem() != null) {
    		
    		orderSelected = tvOrders.getSelectionModel().getSelectedItem();
        	double totalPrice = 0;
        	
        	for (int i = 0; i < orderSelected.getOrderedDishes().size(); i++) {
        		totalPrice = totalPrice + orderSelected.getOrderedDishes().get(i).getTotalPrice();
    		}
        	
        	labUUIDCODE.setText(orderSelected.getUUID());
        	labOrderDate.setText(orderSelected.getOrderDate());
        	labTotalPriceToPay.setText("" + totalPrice);
        	
        	if(orderSelected.getStatus().equals(ORDER_STATUS.PENDING)) {
        		combBoxStatus.setValue(ORDER_STATUS.PENDING);
        	} else if(orderSelected.getStatus().equals(ORDER_STATUS.IN_PROCESS)) {
        		combBoxStatus.setValue(ORDER_STATUS.IN_PROCESS);
        	} else if(orderSelected.getStatus().equals(ORDER_STATUS.DELIVERED)){
        		combBoxStatus.setValue(ORDER_STATUS.DELIVERED);
        	} else {
        		
        	}
        	
        	itializeTableViewOfDishesInOrder();
    	}
    }
    
    @FXML
    void evaluateStatusComboBox(ActionEvent event) throws IOException {
    	List<Ingredient> totalIngredientsList = new ArrayList<Ingredient>();
    	boolean orderApproval = false;
    	
    	//Proceso que permite evaluar si se puede aceptar un pedido
    	if(combBoxStatus.getValue().equals(ORDER_STATUS.IN_PROCESS)) {
    		
    		//Proceso que llena una lista con todos los ingredientes que se usaran para preparar la orden que se selecciono
    		for(int i = 0; i < orderSelected.getOrderedDishes().size(); i++) {
				for (int j = 0; j < orderSelected.getOrderedDishes().get(i).getOrderedDish().getIngredientList().size(); j++) {
					if(!totalIngredientsList.isEmpty()) {
						for (int j2 = 0; j2 < totalIngredientsList.size(); j2++) {
							if(totalIngredientsList.get(j2).getName().equals(orderSelected.getOrderedDishes().get(i).getOrderedDish().getIngredientList().get(j).getName())) {
								
								double accumulatedIngredients = totalIngredientsList.get(j2).getAmount() + orderSelected.getOrderedDishes().get(i).getOrderedDish().getIngredientList().get(j).getAmount() * orderSelected.getOrderedDishes().get(i).getAmountOrderedDish();
								totalIngredientsList.get(j2).setAmount(accumulatedIngredients);
								
							} else {
								
								String name = orderSelected.getOrderedDishes().get(i).getOrderedDish().getIngredientList().get(j).getName();
								MEASUREMENT_TYPE type = orderSelected.getOrderedDishes().get(i).getOrderedDish().getIngredientList().get(j).getMeasurement();
								
								double amount = orderSelected.getOrderedDishes().get(i).getOrderedDish().getIngredientList().get(j).getAmount() * orderSelected.getOrderedDishes().get(i).getAmountOrderedDish();
								
								totalIngredientsList.add(new Ingredient(name, type, amount));
							}
						}
					} else {
						
						String name = orderSelected.getOrderedDishes().get(i).getOrderedDish().getIngredientList().get(j).getName();
						MEASUREMENT_TYPE type = orderSelected.getOrderedDishes().get(i).getOrderedDish().getIngredientList().get(j).getMeasurement();
						
						double amount = orderSelected.getOrderedDishes().get(i).getOrderedDish().getIngredientList().get(j).getAmount() * orderSelected.getOrderedDishes().get(i).getAmountOrderedDish();
						
						totalIngredientsList.add(new Ingredient(name, type, amount));
					}
					
				}
			}
    		
    		int count = 0;
    		//Proceso que verifica si el stock en inventario permite aceptar el pedido
    		for (int i = 0; i < totalIngredientsList.size(); i++) {
				for (int j = 0; j < inventory.getIngredients().size(); j++) {
					if(totalIngredientsList.get(i).getName().equals(inventory.getIngredients().get(j).getName())) {
						if(totalIngredientsList.get(i).getAmount() <= inventory.getIngredients().get(j).getAmount()) {
							orderApproval = true;
						} else {
							count++;
							orderApproval = false;
						}
					}
				}
			}
    		
    		if(count != 0) {
    			orderApproval = false;
    		}
    		
    		boolean sentinel = false;
    		if(orderApproval == true) {
    			for (int i = 0; i < laCucharita.getOrder().size() && sentinel == false; i++) {
					if(orderSelected.equals(laCucharita.getOrder().get(i))) {
						sentinel = true;
						
						if(laCucharita.getOrder().get(i).getStatus().equals(ORDER_STATUS.PENDING)) {
	    					
							laCucharita.getOrder().get(i).setStatus(ORDER_STATUS.IN_PROCESS);
							printWarning("El pedido seleccionado ha pasado a estar en proceso");
							
							
							for (int j = 0; j < inventory.getIngredients().size(); j++) {
								boolean sentinel2 = false;
								
								int j2 = 0;
								while(j2 < totalIngredientsList.size() && sentinel2 == false) {
									if(totalIngredientsList.get(j2).getName().equals(inventory.getIngredients().get(j).getName())) {
										sentinel2 = true;
										
										printWarning("" + inventory.getIngredients().get(j).getAmount());
										printWarning("" + totalIngredientsList.get(j2).getAmount());
										
										double newAmountIngredient = inventory.getIngredients().get(j).getAmount() - totalIngredientsList.get(j2).getAmount();
										//Aquiiiiiiiiiiiiiiiiiii estooooooooooooooooooooyyyyyyyyyyyyyyyyyyyyyyy ATT: JUANK
										inventory.getIngredients().get(j).setAmount(newAmountIngredient);
										
									} else {
										j2++;
									}
									
								}
							}
							
	    				}  else if(laCucharita.getOrder().get(i).getStatus().equals(ORDER_STATUS.IN_PROCESS)) {
	    					printWarning("El pedido seleccionado ya paso a estar en proceso");
	    				} else if(laCucharita.getOrder().get(i).getStatus().equals(ORDER_STATUS.DELIVERED)) {
	    					printWarning("El pedido seleccionado ya fue entregado");
	    				} else {
	    					printWarning("El pedido seleccionado ya ha sido rechazado previamente");
	    				}
						
					}
				}
    			
    			OrderMenu();
    			
    		} else {
    			printWarning("El pedido seleccionado supera la cantidad en stock");
    		}
    		
    	} else if(combBoxStatus.getValue().equals(ORDER_STATUS.DELIVERED)) {
    		boolean sentinel = false;
    		
    		for (int i = 0; i < laCucharita.getOrder().size() && sentinel == false; i++) {
				if(orderSelected.equals(laCucharita.getOrder().get(i))) {
					sentinel = true;
					
					if(laCucharita.getOrder().get(i).getStatus().equals(ORDER_STATUS.IN_PROCESS)) {
						laCucharita.getOrder().get(i).setStatus(ORDER_STATUS.DELIVERED);
						OrderMenu();
					}  else {
						printWarning("El pedido seleccionado no se encuentra en proceso, porfavor verifica que este se encuentre en proceso");
					}
				}
			}
			
    	} else if(combBoxStatus.getValue().equals(ORDER_STATUS.PENDING)) {
    		
    		boolean sentinel = false;

    		for (int i = 0; i < laCucharita.getOrder().size() && sentinel == false; i++) {
    			if(orderSelected.equals(laCucharita.getOrder().get(i))) {
    				sentinel = true;

    				if(laCucharita.getOrder().get(i).getStatus().equals(ORDER_STATUS.PENDING)) {
    					printWarning("El pedido seleccionado ya se encuentra en pendiente por atender");
    				}  else if(laCucharita.getOrder().get(i).getStatus().equals(ORDER_STATUS.IN_PROCESS)) {
    					printWarning("El pedido seleccionado ya paso a estar en proceso");
    				} else if(laCucharita.getOrder().get(i).getStatus().equals(ORDER_STATUS.DELIVERED)) {
    					printWarning("El pedido seleccionado ya fue entregado");
    				} else {
    					printWarning("El pedido seleccionado ya ha sido rechazado previamente");
    				}
    			}
    		}
    		
    	}
    	
    }
    
    private void itializeTableViewOfDishesInOrder() {
    	obsDishesInOrder = FXCollections.observableArrayList(orderSelected.getOrderedDishes()); /////ESTOOOOOY AAAAAACAAAAAAAAA
    	
		tvDishesOrder.setItems(obsDishesInOrder);
		tcDishNameOrdered.setCellValueFactory(new PropertyValueFactory<DishOrder, String>("dishName"));
		tcAmountDishesOrder.setCellValueFactory(new PropertyValueFactory<DishOrder, Integer>("amountOrderedDish"));
		tcTotalPriceOrder.setCellValueFactory(new PropertyValueFactory<DishOrder, Double>("totalPrice"));
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
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("register_dish_module.fxml"));
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
	public void menuModule() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("menu_module.fxml"));
    	fxmlloader.setController(this);
    	Parent log = fxmlloader.load();
    	mainPane.getChildren().setAll(log);
    	
    	itializeTableViewOfDishesAvailable();
	}
	
	public void showCart() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("cart.fxml"));
    	fxmlloader.setController(this);
    	Parent log = fxmlloader.load();
    	mainPane.getChildren().setAll(log);
    	
    	itializeTableViewOfItemsInCart();
    	labTotalToPay.setText("" + totalToPay());
	}
	
	public void OrderMenu() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("order_module.fxml"));
    	fxmlloader.setController(this);
    	Parent log = fxmlloader.load();
    	mainPane.getChildren().setAll(log);
    	
    	combBoxStatus.getItems().addAll(ORDER_STATUS.PENDING, ORDER_STATUS.IN_PROCESS, ORDER_STATUS.DELIVERED);
    	itializeTableViewOfOrders();
	}

	//Este metodo muestra la pantalla del modulo de inventario
	public void OpenInventory() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Inventory.fxml"));
    	fxmlloader.setController(this);
    	Parent log = fxmlloader.load();
    	mainPane.getChildren().setAll(log);
    	
    	measurementType.getItems().addAll(MEASUREMENT_TYPE.MILLILITERS, MEASUREMENT_TYPE.GRAMS, MEASUREMENT_TYPE.UNITS, MEASUREMENT_TYPE.KILOGRAMS);
    	
    	itializeTableViewInventory();
    	
    	
    	
	}
	
	//Este metodo muestra la pantalla del modulo de empleados
	public void OpenEmployees() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("list-employees.fxml"));
		fxmlloader.setController(this);
		Parent log = fxmlloader.load();
		mainPane.getChildren().setAll(log);

		initializeTableViewEmployees();
	}
	
	//Este metodo hace el registro a un empleado
    @FXML
    public void createAccount(ActionEvent event) {
    	String cc ="";
    	cc = id.getText();

    	
    	if (laCucharita.employeeExist(cc)){
			printWarning("La CC del empleado ya existe, por favor intente agregando una CC diferente");
			
		}else if(!id.getText().equals("") && !txtUserName.getText().equals("")  &&birthday.getValue()!=null  &&  !passwordField.getText().equals("")){

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
	
	
	@FXML
    public void logOut(ActionEvent event) throws IOException {
		LogInMenu();
    }
	
	
	
	//Este metodo envia al usuario a otra ventana para cambiar la contraseña
	@FXML
    public void changePassword(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("change_Password.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        mainStage.setScene(scene);
        mainStage.setTitle("Password change module");
        mainStage.show();
    }
	
	
	//Cambia la contraseña del arreglo
	@FXML
	public void changePasswordEmployee(ActionEvent event)throws IOException {
		String user = idconfirm.getText();
		String password = passwordConfirm.getText();
		String passwordN = passwordNew.getText();

		if(!user.equals("") && !password.equals("")) {
			if(laCucharita.evaluate_If_User_Can_LogIn(user, password)) {
				for (int i = 0; i < laCucharita.getUserList().size(); i++) {
					if (user.equals(laCucharita.getUserList().get(i).getId()) && password.equals(laCucharita.getUserList().get(i).getPassword())) {
						laCucharita.getUserList().get(i).setPassword(passwordN);
					}
				}
				printWarning("Se realizo exitosamente el cambio");

			} else {
				printWarning("El usuario o la contraseña es incorrecto");
			}
		} else {
			printWarning("Por favor llenar todos los campos");
		}




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
	    public void addNewIngredient(ActionEvent event) {
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
			} else if (amount!=-1){
				inventory.addNewIngredient(name, type, amount);

				printWarning("The new ingredient was successfully registered");
				itializeTableViewInventory();

			}
	    	
	    }

	    
	    // Este metodo inicializa la lista que muestra los ingredinetes en el modulo de inventario
	    public void itializeTableViewInventory() {
	    	
	    	sortByName();
	    	
	    	observableListIngredients = FXCollections.observableArrayList(inventory.getIngredients());
	    	
	    	tvIngredients.setItems(observableListIngredients);
	    	tcName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
	    	tcAmount.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("amount"));
	    	tcMeasurementType.setCellValueFactory(new PropertyValueFactory<Ingredient, MEASUREMENT_TYPE>("measurement"));
	    }
	    
	    // este metodo es para restar en 1 la cantidad del ingrediente seleccionado
	    @FXML
	    public void less(ActionEvent event) throws IOException {
	    	if(tvIngredients.getSelectionModel().getSelectedItem()==null) {
	    		
	    		printWarning("First select an ingredient from the list");
	    		
	    	}else if(tvIngredients.getSelectionModel().getSelectedItem().getAmount()>0) {
	    		
	    	tvIngredients.getSelectionModel().getSelectedItem().setAmount(tvIngredients.getSelectionModel().getSelectedItem().getAmount()-1);
	    	printWarning("decreased -1");
	    	
	    	}else {
	    		
	    		printWarning("cannot have negative amounts");
	    		
	    	}	 
	    	OpenInventory();
	    }
	    	    
	    // este metodo es para restar en 1 la cantidad del ingrediente seleccionado
	    @FXML
	    public void plus(ActionEvent event) throws IOException {
	    	if(tvIngredients.getSelectionModel().getSelectedItem()!=null) {
	    		tvIngredients.getSelectionModel().getSelectedItem().setAmount(tvIngredients.getSelectionModel().getSelectedItem().getAmount()+1);
		    	printWarning("increased +1");
		    	OpenInventory();
	    	}else {
	    		printWarning("First select an ingredient from the list");
	    	}
	    	
	    }
	    
	    //Este boton recarga el modulo inventario manualmente
	    @FXML
	    void refresh(ActionEvent event) throws IOException {
	    	OpenInventory();
	    }
	    
	    // este metodo ordena el arreglo de ingredientes por nombre
	    public void sortByName() {
	    	Collections.sort(inventory.getIngredients());
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
