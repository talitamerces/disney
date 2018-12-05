package br.uesb.dovic.entidades;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.*;

@Entity
@Table(name="cor")
public class Cor implements Serializable {
	@Id
	@Column(name="idcor")
	@SequenceGenerator(name="SEQ_COR", sequenceName="SEQ_COR_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_COR")
	
	private Integer id;
	
	@NotEmpty(message="O nome da cor não pode ser nulo")
	@Length(max=100, message="O nome não pode ultrapassar {max} caracteres")
	@Column(name="nomecor", length=100, nullable=false)
	private String nomeCor;
	
	
	public Cor(){
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer idCor) {
		this.id = idCor;
	}


	public String getNomeCor() {
		return nomeCor;
	}


	public void setNomeCor(String nomeCor) {
		this.nomeCor = nomeCor;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomeCor == null) ? 0 : nomeCor.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cor other = (Cor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeCor == null) {
			if (other.nomeCor != null)
				return false;
		} else if (!nomeCor.equals(other.nomeCor))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Cor [idCor=" + id + ", nomeCor=" + nomeCor + "]";
	}
	
	

}
