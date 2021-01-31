package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ServidorController {
	
	@FXML private TextArea areaChat;
	
	public void guardarMensaje () {
		
	}
	@FXML
	public void initialize() throws Exception {
		System.out.println("Arrancando el servidor");
		ServerSocket socketServidor = null;
		Socket socket = null;
		ObjectOutputStream escribir = null;
		ObjectInputStream leer = null;
		HashMap <String, ArrayList<String>> mensajesGuardados = null;
		try {
			
			String nombre, ip, mensaje;
			Mensaje mensajeRecibido;
			socketServidor = new ServerSocket(9999);
			
			while (true) {
				socket = socketServidor.accept();
				System.out.println("conexion recibida");
				leer = new ObjectInputStream(socket.getInputStream());
				mensajeRecibido = (Mensaje) leer.readObject();
				
				ArrayList <String>mensajes = new ArrayList<>();
				mensajes.add(mensajeRecibido.getMensaje());
				mensajesGuardados.put(mensajeRecibido.getDestino(), mensajes);
				
				for (java.util.Map.Entry<String, ArrayList<String>> entry : mensajesGuardados.entrySet()) {
		            System.out.println(entry.getKey() + ": ");
		            for (String s : entry.getValue()) {
		                System.out.println(s);
		            }
		        }
				//areaChat.appendText("\n"+nombre + ": " + mensaje + " para " + ip);
				
				//Reenviar mensaje
				
				//Socket enviaDestinatario = new Socket(ip, 9090);
				
				ObjectOutputStream paqueteReenvio = new ObjectOutputStream(socket.getOutputStream());
				
				paqueteReenvio.writeObject(mensajeRecibido);
				
				paqueteReenvio.close();
				
				socket.close();
				
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} 
	}
}

