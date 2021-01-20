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
		try {
			socketEscucha = new ServerSocket(9877);
			while (true) {
				try {
					conexion = socketEscucha.accept();
					System.out.println("Conexion recibida!");
					System.out.println("Dirección IP: " + conexion.getInetAddress());
					is = conexion.getInputStream();
					ois = new DataInputStream(is);
					String mensaje = ois.readUTF();
					//Calculo calculo = (Calculo) ois.readObject();
					/* Calculamos el resultado */
					//Integer result = this.calcular(calculo);
					os = conexion.getOutputStream();
					pw = new DataOutputStream(os);
					pw.writeUTF(mensaje);
					pw.flush();
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
