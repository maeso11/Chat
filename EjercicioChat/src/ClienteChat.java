import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteChat {

	public static void main(String[] args) throws Exception {
		Socket socket = null;
		BufferedReader bfr = null;
		ObjectOutputStream oos = null;
		ObjectInputStream isr = null;

		try {
			socket = new Socket("192.168.56.1", 9877);
			
			//Escribe el mensaje
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(new Usuario("maeso", "hola"));
			oos.flush();
			
			//Muestra el mensaje que se ha enviado
			isr=new ObjectInputStream(socket.getInputStream());
			String mensaje = String.valueOf(isr.readObject());
			System.out.println(mensaje);
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
