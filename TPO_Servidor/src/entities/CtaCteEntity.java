package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import negocio.CtaCte;
import negocio.Factura;
import negocio.Pago;

@Entity
@Table(name="CtaCte")
public class CtaCteEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idCtaCte;
	
	private float limiteCredito;
	
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name= "idCtaCte")
	private List<FacturaEntity> fe;
	
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name= "idCtaCte")
	private List<PagoEntity> pe; 

	public CtaCteEntity(float limiteCredito) {
		this.limiteCredito=limiteCredito;
		this.pe= new ArrayList<PagoEntity>();
		this.fe= new ArrayList<FacturaEntity>();		
	}
	
	public CtaCteEntity(){}
	
	public CtaCteEntity(CtaCte cc){
		if(cc.getId()!=0)
			this.setIdCtaCte(cc.getId());
		this.setLimiteCredito(cc.getLimiteCredito());
		this.cargarListFe(cc.getFacturas());
		this.cargarListPe(cc.getPagos());
	}
	
	private void cargarListFe(Collection<Factura> fe){
		List<FacturaEntity> res= new ArrayList<FacturaEntity>();
		for(Factura f: fe)
			res.add(new FacturaEntity(f));
		this.setFe(res);
	}
	
	private void cargarListPe(Collection<Pago> pe){
		List<PagoEntity> res= new ArrayList<PagoEntity>();
		for(Pago p: pe)
			res.add(new PagoEntity(p));
		this.setPe(res);
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
	public Collection<FacturaEntity> getFe() {
		return fe;
	}

	public void setFe(List<FacturaEntity> fe) {
		this.fe = fe;
	}
	public Collection<PagoEntity> getPe() {
		return pe;
	}

	public void setPe(List<PagoEntity> pe) {
		this.pe = pe;
	}

	

}
