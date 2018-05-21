
package negocio;

import dao.StockDAO;
import entities.StockEntity;

public class Stock {

	private String codigoUbicacion;
	private int cantidadReal;
	private int cantidadReservada;
	// estado: "LIBRE", "OCUPADA", "BLOQUEADA"
	private String estado;
	
	public Stock() {
		
	}

	// Crea la ubicacion en stock en estado LIBRE y setea las cantidades a 0	
	public Stock(String codigoUbicacion) {
		this.setCodigoUbicacion(codigoUbicacion);
		this.setCantidadReal(0);
		this.setCantidadReservada(0);
		this.setEstado("LIBRE");
	}
	
	public Stock(StockEntity se) {
		this.setCodigoUbicacion(se.getCodUbicacion());
		this.setCantidadReal(se.getCantidadReal());
		this.setCantidadReservada(se.getCantidadReservada());
		this.setEstado(se.getEstado());
	}

	// Verifica si el objeto es una determinada ubicacion
	public boolean sosStock(String codUbicacion) {
		return (this.getCodigoUbicacion().equals(codUbicacion));
	}
	
	// Devuelve la cantidad que se puede reservar en la ubicación
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

	// La Cantidad Real se actualiza cuando se descuenta el stock (todo o una parte) 
	// o cuando se ingresa stock
	public void actualizarCantidadReal(int cantidad) {
		if (cantidad == 0) {
			// Se está liberando la ubicación
			this.setCantidadReservada(0);
			this.setEstado("LIBRE");
		}
		else {
			// Se está agregando o descontando stock, se desbloquea la ubicación
			if (this.getCantidadReal() == 0)
				// Se está agregando stock por primera vez
				this.setCantidadReservada(0);
			else {
				int nuevaCantReservada = this.getCantidadReservada() - (this.getCantidadReal() - cantidad);
				if (nuevaCantReservada > 0)
					this.setCantidadReservada(nuevaCantReservada);
				else
					this.setCantidadReservada(0);
			}	
			this.setEstado("OCUPADA");
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

	// Se declara privado porque quiero reservarle la actualización directa de este valor a la clase Stock
	private void setCantidadReal(int cantidadReal) {
		this.cantidadReal = cantidadReal;
	}

	public int getCantidadReservada() {
		return cantidadReservada;
	}

	// Se declara privado porque quiero reservarle la actualización directa de este valor a la clase Stock
	private void setCantidadReservada(int cantidadReservada) {
		this.cantidadReservada = cantidadReservada;
	}

	public String getEstado() {
		return estado;
	}

	// Se pasa a privado porque quiero reservarle la actualización directa de este valor a la clase Stock
	private void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void saveMe() {
		StockDAO.getInstance().grabar(this);
	}	

	//@Facu: implementar metodo
	public void updateMe() {
		StockDAO.getInstance().update(this);
	}	
		
}