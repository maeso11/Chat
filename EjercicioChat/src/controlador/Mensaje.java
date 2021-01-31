package controlador;

import java.io.Serializable;

public class Mensaje implements Serializable {
	
	private String destino;
	private String mensaje;
	
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}	
