package interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InicioController {
	
	public void entrar(ActionEvent ev){
		
		Stage primaryStage = new Stage();	
		((Node) ev.getSource()).getScene().getWindow().hide();
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Chat.fxml"));
			Scene scene = new Scene(root,650,690);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("img/logoxuchat.png"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
