package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("SC")
@Table(name="MoviemientosStock")
public class MovStockCompraEntity extends MovimientoStockEntity{
	@OneToOne(cascade= CascadeType.ALL)
	@PrimaryKeyJoinColumn //Testear
	private OrdenDeCompraEntity oce;

	public MovStockCompraEntity() {
		super();
	}

	public MovStockCompraEntity(char tipoAjuste, Date fecha, int cant, String tipoMov, OrdenDeCompraEntity oce) {
		super(tipoAjuste, fecha, cant, tipoMov);
		this.oce= oce;
	}

	public OrdenDeCompraEntity getOce() {
		return oce;
	}

	public void setOce(OrdenDeCompraEntity oce) {
		this.oce = oce;
	}
	
	
}
