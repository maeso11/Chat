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


public class ServidorController implements Runnable{
	
	@FXML private TextArea areaChat;
	//HashMap <String, ArrayList<String>> mensajesGuardados = null;
	
	public String escribirMensaje(Mensaje mensaje) {
		String contenido = mensaje.getMensaje();
		
		return contenido;
		
	}
	public ServidorController (){
		Thread hilo = new Thread(this);
		hilo.start();
	}
	@Override
	public void run() {
		System.out.println("Arrancando el servidor");
		ServerSocket socketServidor = null;
		Socket socket = null;
		ObjectOutputStream escribir = null;
		ObjectInputStream leer = null;
		try {
			
			String nombre, ip, mensaje;
			Mensaje mensajeRecibido;
			socketServidor = new ServerSocket(9999);
			
			while (true) {
				socket = socketServidor.accept();
				System.out.println("conexion recibida");
				leer = new ObjectInputStream(socket.getInputStream());
				mensajeRecibido = (Mensaje) leer.readObject();
				
				ArrayList <String>mensajes = new ArrayList<String>();
				mensajes.add(mensajeRecibido.getMensaje());
				System.out.println(mensajeRecibido.getMensaje());
				//mensajesGuardados.put(mensajeRecibido.getDestino(), mensajes);
				/*System.out.println(mensajesGuardados.keySet());
				for (java.util.Map.Entry<String, ArrayList<String>> entry : mensajesGuardados.entrySet()) {
		            System.out.println(entry.getKey() + ": ");
		            for (String s : entry.getValue()) {
		                System.out.println(s);
		            }
		        }*/
				areaChat.appendText("\n"+mensajeRecibido.getMensaje() + " para " + mensajeRecibido.getDestino());
				
				//Reenviar mensaje
				
				String mensajeEnviar = (this).escribirMensaje(mensajeRecibido);
				escribir = new ObjectOutputStream(socket.getOutputStream());
				
				escribir.writeObject(mensajeEnviar);
				escribir.flush();
				
				escribir.close();
				
				socket.close();
				
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
}

