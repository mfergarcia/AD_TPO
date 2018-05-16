package entities;

import java.util.Set;
import java.util.TreeSet;

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
	/*
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "idCtaCte")
	private Set<FacturaEntity> fe= new TreeSet<FacturaEntity>(); 
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "idCtaCte")
	private Set<PagoEntity> pe= new TreeSet<PagoEntity>(); 
	*/
	public CtaCteEntity(float limiteCredito) {
		this.limiteCredito=limiteCredito;
	}

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
	/*
	public Set<FacturaEntity> getFe() {
		return fe;
	}

	public void setFe(Set<FacturaEntity> fe) {
		this.fe = fe;
	}
	
	public Set<PagoEntity> getPe() {
		return pe;
	}

	public void setPe(Set<PagoEntity> pe) {
		this.pe = pe;
	}
	*/
	

}
