package ui;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Restaurant;

//Clase controladora, aqui poner todo lo referente a lo grafico (Es como el Main en nuestros proyectos)
public class RestaurantGUI {

	private Stage mainStage;
	//Relacion entre Restaurant y su contraladora
	private Restaurant laCucharita;
	
	@FXML
    private TextField loginUserField;
	@FXML
    private PasswordField loginPassField;

	//Constructor
	public RestaurantGUI() {
		laCucharita = new Restaurant();
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
				EmployeesMenu();
			} else {
				JOptionPane.showMessageDialog(null, "El usuario o la contraseña es incorrecto");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Por favor llenar todos los campos");
		}
		
    }
	
	/**Metodos de mostrar modulos*/
	
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
	
	//setters
	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}



}
