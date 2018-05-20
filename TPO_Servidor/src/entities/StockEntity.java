package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import negocio.Stock;

@Entity
@Table(name= "Stock")
public class StockEntity {
	@Id
	private String codUbicacion;
	private int cantidadReal;
	private int cantidadReservada;
	private String estado;
	
	public StockEntity(){}
	
	public StockEntity(String codUbicacion, int cantidadReal, int cantidadReservada, String estado) {
		this.codUbicacion = codUbicacion;
		this.cantidadReal = cantidadReal;
		this.cantidadReservada = cantidadReservada;
		this.estado = estado;
	}
	
	public String getCodUbicacion() {
		return codUbicacion;
	}


	public void setCodUbicacion(String codUbicacion) {
		this.codUbicacion = codUbicacion;
	}


	public int getCantidadReal() {
		return cantidadReal;
	}


	public void setCantidadReal(int cantidadReal) {
		this.cantidadReal = cantidadReal;
	}


	public int getCantidadReservada() {
		return cantidadReservada;
	}


	public void setCantidadReservada(int cantidadReservada) {
		this.cantidadReservada = cantidadReservada;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
