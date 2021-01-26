
public class Mensaje implements java.io.Serializable{
	
	String contenido;
	Usuario usuario;
	

	public Mensaje(String contenido, Usuario usuario) {
		super();
		this.contenido = contenido;
		this.usuario = usuario;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Mensaje [contenido=" + contenido + ", usuario=" + usuario + "]";
	}
	
	
	
}
