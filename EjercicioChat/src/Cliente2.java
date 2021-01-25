import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Cliente2 {

	public static void main(String[] args) throws IOException {
		Socket socket = null;
		BufferedReader bfr = null;
		DataOutputStream oos = null;
		DataInputStream isr = null;
		ServerSocket escucha = null;
		Socket mensajeRecibido = null;
		DataInputStream contenidoMensaje = null;
		try {
			escucha = new ServerSocket(9999);
			while(true) {
				socket = new Socket("192.168.56.1", 9877);
				
				//Escucha la petición del servidor
				mensajeRecibido = escucha.accept();
				
				//Escribe el mensaje
				oos = new DataOutputStream(socket.getOutputStream());
				oos.writeUTF("Adios");
				oos.flush();
				
				//Muestra el mensaje que se ha enviado
				isr=new DataInputStream(socket.getInputStream());
				String mensaje = String.valueOf(isr.readUTF());
				System.out.println(mensaje);
				
				contenidoMensaje = new DataInputStream(mensajeRecibido.getInputStream());
				String mensajeRec = String.valueOf(contenidoMensaje.readUTF());
				System.out.println(mensajeRec);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
			throw e;
		}finally {
			if (null != oos) {
				oos.close();
			}
			if (null != bfr) {
				bfr.close();
			}
			if (null != isr) {
				isr.close();
			}
			if (null != socket) {
				socket.close();
			}
		}
	}	
}
