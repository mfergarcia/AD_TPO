package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("SV")
@Table(name="MovimientosStock")
public class MovStockVentaEntity extends MovimientoStockEntity {

	@OneToOne(cascade= CascadeType.ALL)
	@PrimaryKeyJoinColumn //Testear
	private PedidoEntity pe;

	public MovStockVentaEntity(char tipoAjuste, Date fecha, int cant, String tipoMov, PedidoEntity pe) {
		super(tipoAjuste, fecha, cant, tipoMov);
		this.setPe(pe);
	}

	public MovStockVentaEntity(){}

	public PedidoEntity getPe() {
		return pe;
	}

	public void setPe(PedidoEntity pe) {
		this.pe = pe;
	}
	
	
}
