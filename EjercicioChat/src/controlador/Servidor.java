package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Servidor extends Application {

	@Override
	public void start(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/interfaz/SampleServidor.fxml"));
			Scene scene = new Scene(root,900,600);
			scene.getStylesheets().add(getClass().getResource("/interfaz/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Xuchat Server");
			primaryStage.getIcons().add(new Image("file:"+"img/logoxuchat.png"));
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//M�todo main 
	public static void main(String[] args) {
		launch(args);
	}
}
