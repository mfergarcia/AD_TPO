package excepciones;

public class ExcepcionSistema extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public ExcepcionSistema() {
		// TODO Auto-generated constructor stub
	}

	public ExcepcionSistema(String mensaje) {
		// TODO Auto-generated constructor stub
		this.setMensaje(mensaje);
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
