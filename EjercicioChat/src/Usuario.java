
public class Usuario implements java.io.Serializable{
	
	String nombre;
	String mensaje;
	
	
	public Usuario(String nombre, String mensaje) {
		super();
		this.nombre = nombre;
		this.mensaje = mensaje;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", mensaje=" + mensaje + "]";
	}
	
	
	

}
