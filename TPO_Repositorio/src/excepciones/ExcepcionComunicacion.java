package excepciones;

public class ExcepcionComunicacion extends Exception {

	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public ExcepcionComunicacion() {
		// TODO Auto-generated constructor stub
	}

	public ExcepcionComunicacion(String mensaje) {
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
