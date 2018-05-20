package entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("SM")
@Table(name="MovimientosStock")
public class MovStockMantenimientoEntity extends MovimientoStockEntity {
		
		private String usuarioRegistrado;
		private String autorizante;
		private String destinoFinal;
		
		
		public MovStockMantenimientoEntity() {
			super();
		}
		public MovStockMantenimientoEntity(char tipoAjuste, Date fecha, int cant, String tipoMov, String usuarioRegistrado, String autorizante, String destinoFinal) {
			super(tipoAjuste, fecha, cant, tipoMov);
			this.setAutorizante(autorizante);
			this.setAutorizante(autorizante);
			this.setDestinoFinal(destinoFinal);
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
		
		
		
}
