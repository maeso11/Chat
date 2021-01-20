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
		Socket socket2 = null;
		BufferedReader bfr = null;
		DataOutputStream oos = null;
		DataInputStream isr = null;
		try {
			socket = new Socket("172.16.8.153", 9877);
			socket2 = new Socket("172.16.8.138", 9877);
			//Escribe el mensaje
			oos = new DataOutputStream(socket.getOutputStream());
			oos.writeUTF("Hola");
			oos.flush();
			
			//Recibe el mensaje
			isr=new DataInputStream(socket2.getInputStream());
			String mensaje = String.valueOf(isr.readUTF());
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
