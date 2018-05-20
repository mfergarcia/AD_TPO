package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CtaCte")
public class CtaCteEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idCtaCte;
	private float limiteCredito;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "idCtaCte")
	private List<FacturaEntity> fe;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "idCtaCte")
	private List<PagoEntity> pe; 

	public CtaCteEntity(float limiteCredito) {
		this.limiteCredito=limiteCredito;
		this.pe= new ArrayList<PagoEntity>();
		this.fe= new ArrayList<FacturaEntity>();
		
	}
	public CtaCteEntity(){}
	
	public Integer getIdCtaCte() {
		return idCtaCte;
	}

	public void setIdCtaCte(Integer idCtaCte) {
		this.idCtaCte = idCtaCte;
	}

	public float getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public List<FacturaEntity> getFe() {
		return fe;
	}

	public void setFe(List<FacturaEntity> fe) {
		this.fe = fe;
	}
	
	public List<PagoEntity> getPe() {
		return pe;
	}

	public void setPe(List<PagoEntity> pe) {
		this.pe = pe;
	}

	

}
