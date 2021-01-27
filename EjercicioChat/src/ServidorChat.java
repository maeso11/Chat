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
		Usuario user;
		
		try {
			socketEscucha = new ServerSocket(9877);
			while (true) {
				try {
					//Escucha la petición del cliente
					conexion = socketEscucha.accept();
					System.out.println("Conexion recibida!");
					
					//Recibe el mensaje y lo lee
					is = conexion.getInputStream();
					ois = new ObjectInputStream(is);
					user = (Usuario) ois.readObject();
					System.out.println("Se ha conectado " + user.getNombre());
					
					//Envia el mensaje al cliente(clase ClienteChat)
					
					os = conexion.getOutputStream();
					pw = new ObjectOutputStream(os);
					pw.writeObject(user);
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
