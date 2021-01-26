import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorChat implements Runnable{
	
	public static void main (String [] args) {
		ServidorChat server = new ServidorChat();
		Thread hilo = new Thread(server);
		
		hilo.start();
	}

	@Override
	public void run() {
		System.out.println("Arrancado el servidor");
		ServerSocket socketEscucha = null;
		Socket conexion=null;
		InputStream is = null;
		ObjectInputStream ois = null;
		OutputStream os = null;
		ObjectOutputStream pw = null;
		Mensaje mensaje;
		Usuario user;
		Socket envia;
		
		try {
			socketEscucha = new ServerSocket(9877);
			while (true) {
				try {
					//Escucha la petición del cliente
					conexion = socketEscucha.accept();
					System.out.println("Conexion recibida!");
					System.out.println("Dirección IP: " + conexion.getInetAddress());
					
					//Recibe el mensaje y lo lee
					is = conexion.getInputStream();
					ois = new ObjectInputStream(is);
					mensaje = (Mensaje) ois.readObject();
					
					//Envia el mensaje al cliente(clase ClienteChat)
					envia = new Socket(mensaje.usuario.getIp(), 9877);
					os = envia.getOutputStream();
					pw = new ObjectOutputStream(os);
					pw.writeObject(mensaje);
					pw.flush();
					
					
				} catch (IOException | ClassNotFoundException e) {
					System.out.println("Error al aceptar conexion "+e.getMessage());
					e.printStackTrace();
				} finally {
					if (null != pw) {
						pw.close();
					}
					if (null != os) {
						os.close();
					}
					if (null != is) {
						is.close();
					}
					if (null != conexion) {
						conexion.close();
					}
				}
			}
		} catch (IOException e) {
			System.out.println("No se pudo poner un socket a escuchar "+e.getMessage());
			e.printStackTrace();

		} finally {
			if (null != socketEscucha) {
				try {
					socketEscucha.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}
