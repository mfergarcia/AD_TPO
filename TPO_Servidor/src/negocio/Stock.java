//PENDIENTE: Revisar todo
package negocio;

public class Stock {

	private String codigoUbicacion;
	private int cantidadReal;
	private int cantidadReservada;
	// estado: "LIBRE", "OCUPADA", "BLOQUEADA"
	private String estado;
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}

	// Crea la ubicacion en stock en estado LIBRE y setea las cantidades a 0	
	public Stock(String codigoUbicacion) {
		this.setCodigoUbicacion(codigoUbicacion);
		this.setCantidadReal(0);
		this.setCantidadReservada(0);
		this.setEstado("LIBRE");
	}
	
	// Verifica si el objeto es una determinada ubicacion
	public boolean sosStock(String codUbicacion) {
		return (this.getCodigoUbicacion().equals(codUbicacion));
	}
	
	// Devuelve la cantidad que se puede reservar en la ubicaci�n
	public int cantidadReservableEnStock() {
		return (this.getCantidadReal() - this.getCantidadReservada());
	}	

	// Reserva cantidad en stock
	public boolean reservarStock(int cantidad) {
		if (cantidadReservableEnStock() > cantidad) {
			this.cantidadReservada = this.cantidadReservada + cantidad;
			return true;
		}
		else
			return false;
	}

	// NOTA_FG: VERRRRRRR
	// La Cantidad Real se actualiza cuando se descuenta el stock (todo o una parte) 
	// o cuando se ingresa stock
	public void actualizarCantidadReal(int cantidad) {
		// Se est� ingresando Stock
		if (this.getEstado().equals("LIBRE")) {
			this.setCantidadReal(cantidad);
			this.setCantidadReservada(0);
			this.setEstado("OCUPADA");
		}
		else {
			if (cantidad == 0) {
				// Se est� liberando la ubicaci�n
				this.setCantidadReservada(0);
				this.setEstado("LIBRE");
			}
			else {
				// Se descuenta una parte del stock, se desbloquea la ubicaci�n
				int nuevaCantReservada = this.getCantidadReservada() - (this.getCantidadReal() - cantidad);
				if (nuevaCantReservada >= 0)
					this.setCantidadReservada(nuevaCantReservada);
				else
					this.setCantidadReservada(0);
				this.setEstado("OCUPADA");
			}	
		}	
		this.setCantidadReal(cantidad);
	}

	public void actualizarEstado(String estado) {
		// Verifico que al pasar a estado LIBRE, se reseteen las cantidades
		if (estado.equals("LIBRE")) {
			this.setCantidadReservada(0);
			this.setCantidadReal(0);
		}
		this.setEstado(estado);
	}
	
	// NOTA_FG: Esto no deber�a estar ac� -> Esta clase no guarda datos como usuario registrado o autorizante
	public void ajustarStockMant(int cant, String usuarioRegistrado, String autorizante, String destinoFinal) {
		
	}
	
	// NOTA_FG: Esto no deber�a estar ac� -> Esta clase no guarda datos como usuario registrado o autorizante
	public void ajustarInventario(int cant, ArticuloEnStock a, String ubicacion) {
	
	}
	
	public String getCodigoUbicacion() {
		return codigoUbicacion;
	}

	// Se declara privado para que no se pueda modificar el codigoUbicacion una vez creado
	private void setCodigoUbicacion(String codigoUbicacion) {
		this.codigoUbicacion = codigoUbicacion;
	}

	public int getCantidadReal() {
		return cantidadReal;
	}

	// Se declara privado porque quiero reservarle la actualizaci�n directa de este valor a la clase Stock
	private void setCantidadReal(int cantidadReal) {
		this.cantidadReal = cantidadReal;
	}

	public int getCantidadReservada() {
		return cantidadReservada;
	}

	// Se declara privado porque quiero reservarle la actualizaci�n directa de este valor a la clase Stock
	private void setCantidadReservada(int cantidadReservada) {
		this.cantidadReservada = cantidadReservada;
	}

	public String getEstado() {
		return estado;
	}

	// Se pasa a privado porque quiero reservarle la actualizaci�n directa de este valor a la clase Stock
	private void setEstado(String estado) {
		this.estado = estado;
	}
	
	//@Facu: implementar metodo
	public void saveMe() {

	}	
}