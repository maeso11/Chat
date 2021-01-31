package controlador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ClienteController {
	
	@FXML private ImageView btnExit, btnEnviar;
	@FXML private Pane pInicio, pChat;
	@FXML private Button btnIngresar, btnRecibir;
	@FXML private TextField tfUsuario, tfContra, mensajeChat, tfDestino;
	@FXML private ComboBox usuariosLinea;
	@FXML private Label lblError, lblError1, nombreUser, ipUser, lblUser;
	@FXML private TextArea campoChat;
	Socket socket;

	 

	@FXML
	public void initialize() {}
	
	//Botón que envia el mensaje 
	@FXML
	public void enviar(MouseEvent ev) throws IOException {	
		Socket socket = null;
		ObjectOutputStream escribir = null;
		try {
			
			socket = new Socket("localhost", 9999);//Direccion ip donde se encuentra el servidor
			
			Mensaje datos = new Mensaje();
			datos.setMensaje(mensajeChat.getText());
			datos.setDestino(tfDestino.getText());
			escribir = new ObjectOutputStream(socket.getOutputStream());
			escribir.writeObject(datos);
			escribir.flush();
		}catch(IOException e) {
			e.printStackTrace();
			throw e;
		}finally {
			socket.close();
			escribir.close();
		}
	}
	
	//Botón que recibe los mensajes
	@FXML
	public void recibir(MouseEvent ev) throws IOException, ClassNotFoundException {
		Socket socket = null;
		ObjectInputStream leer = null;
		try {
			
			socket = new Socket("localhost", 9999);//Direccion ip donde se encuentra el servidor
			leer = new ObjectInputStream(socket.getInputStream());
			Mensaje mensajeRecibido=(Mensaje) leer.readObject();
			
			campoChat.appendText("/n" + nombreUser.getText() + mensajeRecibido.getMensaje());
		
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}finally {
			socket.close();
			leer.close();
		}
	}
	
	//Boton salir al inicio
	public void exit() {
		if (pChat.isVisible() == true) {
			pChat.setVisible(false);
			pInicio.setVisible(true);
			tfUsuario.setText("");
			tfContra.setText("");
		}	
	}
	
	
	//Comprueba si la contraseña es correcta
	public void ingresar(MouseEvent ev) {
		lblError1.setVisible(false);
		lblError.setVisible(false);
		if (tfContra.getText().equals("1234")) {
			if (tfUsuario.getText().equals("")) {
				lblError1.setVisible(true);
			} else {
				if (pInicio.isVisible() == true) {
					pChat.setVisible(true);
					pInicio.setVisible(false);
				
					lblUser.setText(tfUsuario.getText());
					lblError.setVisible(false);
					//usuariosLinea.setItems(list);
				}	
			}
		} else {
			lblError.setVisible(true);
		} 
	}

}