package entities;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoMov", discriminatorType= DiscriminatorType.STRING)
public class MovimientoStockEntity {
	
	private int idMov;
	// tipoAjuste: 'A' (alta), 'B' (baja)
	private char tipoAjuste;
	private Date fecha;
	private int cant;
	// tipoMov:'SV' Stock Venta, 'SC'Stock Compra, 'SM'Stock Mantenimiento, 'SA'Stock Ajuste
	private String tipoMov;
	
	public MovimientoStockEntity(char tipoAjuste,Date fecha,int cant, String tipoMov) {
		super();
		this.tipoAjuste = tipoAjuste;
		this.fecha = fecha;
		this.cant = cant;
		this.tipoMov = tipoMov;
	}
	
	public MovimientoStockEntity(){}
	
	public int getIdMov() {
		return idMov;
	}

	public void setIdMov(int idMov) {
		this.idMov = idMov;
	}

	public char getTipoAjuste() {
		return tipoAjuste;
	}
	public void setTipoAjuste(char tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	public String getTipoMov() {
		return tipoMov;
	}
	public void setTipoMov(String tipoMov) {
		this.tipoMov = tipoMov;
	}

	

}
