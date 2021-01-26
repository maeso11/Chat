
public class Usuario implements java.io.Serializable{
	
	String ip;
	String nombre;
	
	
	public Usuario(String ip, String nombre) {
		super();
		this.ip = ip;
		this.nombre = nombre;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Usuario [ip=" + ip + ", nombre=" + nombre + "]";
	}
	
	

}
