// @Marce: implementar funcionalidad
// @Facu: implementar saveMe
package negocio;

public class MovStockMantenimiento extends MovimientoStock {

	private String usuarioRegistrado;
	private String autorizante;
	private String destinoFinal;

	public MovStockMantenimiento() {
		// TODO Auto-generated constructor stub
	}

	// NOTAS_FG: Ver si se puede reemplazar usando el constructor	
	public void registrarMovStockMantenimiento(int cant, String usuarioRegistrado, String autorizante, String destinoFinal) {
		
	}

	// NOTAS_FG: Para qué sirve este metodo? Qué calcula?
	public void calcularMoviento() {
	
	}	
	
	public String getUsuarioRegistrado() {
		return usuarioRegistrado;
	}

	public void setUsuarioRegistrado(String usuarioRegistrado) {
		this.usuarioRegistrado = usuarioRegistrado;
	}

	public String getAutorizante() {
		return autorizante;
	}

	public void setAutorizante(String autorizante) {
		this.autorizante = autorizante;
	}

	public String getDestinoFinal() {
		return destinoFinal;
	}

	public void setDestinoFinal(String destinoFinal) {
		this.destinoFinal = destinoFinal;
	}

	//@Facu: implementar metodo
	public void saveMe() {

	}	

}
