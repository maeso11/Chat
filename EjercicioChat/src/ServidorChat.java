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
		DataInputStream ois = null;
		OutputStream os = null;
		DataOutputStream pw = null;
		Socket mensajeEnviado = null;
		DataOutputStream enviar = null;
		
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
					ois = new DataInputStream(is);
					String mensaje = ois.readUTF();
					
					//Envia el mensaje al cliente(clase ClienteChat)
					os = conexion.getOutputStream();
					pw = new DataOutputStream(os);
					pw.writeUTF(mensaje);
					pw.flush();
					
					//Envia el mensaje al cliente(clase Cliente2)
					mensajeEnviado = new Socket("192.168.137", 9999);
					enviar = new DataOutputStream(mensajeEnviado.getOutputStream());
					enviar.writeUTF(mensaje);
					enviar.flush();
					
					
				} catch (IOException e) {
					System.out.println("Error al aceptar conexion "+e.getMessage());
					e.printStackTrace();
					throw e;
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
					if (null != mensajeEnviado) {
						mensajeEnviado.close();
					}
					if (null != enviar) {
						enviar.close();
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
