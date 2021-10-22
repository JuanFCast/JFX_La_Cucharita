package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	//Hola Mundo
	public static void main(String[] args) throws IOException {
		launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
        RestaurantGUI controller = new RestaurantGUI();
        controller.setMainStage(primaryStage);
        controller.LogInMenu();
	}
	
}
