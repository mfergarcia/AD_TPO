package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("SA")
@Table(name="MovimientosStock")
public class MovStockAjusteEntity extends MovimientoStockEntity {
		
		@OneToOne(cascade= CascadeType.ALL)
		@PrimaryKeyJoinColumn 
		private ArticuloEnStockEntity aee;

		public MovStockAjusteEntity() {
			super();
		}

		public MovStockAjusteEntity(char tipoAjuste, Date fecha, int cant, String tipoMov, ArticuloEnStockEntity aee) {
			super(tipoAjuste, fecha, cant, tipoMov);
			this.aee= aee;
		}

		public ArticuloEnStockEntity getAee() {
			return aee;
		}

		public void setAee(ArticuloEnStockEntity aee) {
			this.aee = aee;
		}
		
		
}
