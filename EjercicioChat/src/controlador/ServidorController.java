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
		String contenido = mensaje.getUsuario() + ": " + mensaje.getMensaje();
		
		return contenido;
		
	}
	public void escucha() throws IOException, ClassNotFoundException {
		System.out.println("Arrancando el servidor");
		ServerSocket socketServidor = null;
		Socket socket = null;
		ObjectOutputStream escribir = null;
		ObjectInputStream leer = null;
		Mensaje mensajeRecibido;
		ArrayList <String>mensajes = new ArrayList<String>();
		try {
			socketServidor = new ServerSocket(9999);

			while (true) {
				try {
					socket = socketServidor.accept();
					System.out.println("conexion recibida");
					leer = new ObjectInputStream(socket.getInputStream());
					mensajeRecibido = (Mensaje) leer.readObject();
					
					if(mensajeRecibido.getTipo().equalsIgnoreCase("enviar")) {
						mensajes.add(mensajeRecibido.getMensaje());
						//System.out.println(mensajeRecibido.getMensaje());
						System.out.println(mensajes);
						//mensajesGuardados.put(mensajeRecibido.getDestino(), mensajes);
						/*System.out.println(mensajesGuardados.keySet());
						for (java.util.Map.Entry<String, ArrayList<String>> entry : mensajesGuardados.entrySet()) {
				            System.out.println(entry.getKey() + ": ");
				            for (String s : entry.getValue()) {
				                System.out.println(s);
				            }
				        }*/
						areaChat.appendText("\n"+mensajeRecibido.getMensaje() + " para " + mensajeRecibido.getDestino());		
					}
					if(mensajeRecibido.getTipo().equalsIgnoreCase("recibir")) {
						//Reenviar mensaje
						
						for(int i = 0; i<mensajes.size(); i++) {
							String mensajeEnviar = (this).escribirMensaje(mensajeRecibido);
							escribir = new ObjectOutputStream(socket.getOutputStream());	
							escribir.writeObject(mensajeEnviar);
							escribir.flush();
							mensajes.remove(i);							
						}

					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
					throw e;
				}finally {
					if(socket != null) {
						socket.close();
					}
					if(escribir != null) {
						escribir.close();
					}
					if(leer != null) {
						leer.close();
					}
				}	
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(socketServidor != null) {
				socketServidor.close();
			}
		}		
	}
	public ServidorController (){
		Thread hilo = new Thread(this);
		hilo.start();
	}
	@Override
	public void run() {
		try {
			escucha();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

