package br.uesb.dovic.entidades;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.*;

@Entity
@Table(name="pais")
public class Pais implements Serializable {
	@Id
	@Column(name="idpais")
	@SequenceGenerator(name="SEQ_PAIS", sequenceName="SEQ_PAIS_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PAIS")
	private Integer id;
	
	@NotEmpty(message="O nome do país não pode ser nulo")
	@Length(max=100, message="O nome não pode ultrapassar {max} caracteres")
	@Column(name="nomepais", length=100, nullable=false)
	private String nomePais;
	
	
	public Pais(){
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer idPais) {
		this.id = idPais;
	}


	public String getNomePais() {
		return nomePais;
	}


	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomePais == null) ? 0 : nomePais.hashCode());
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
		Pais other = (Pais) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomePais == null) {
			if (other.nomePais != null)
				return false;
		} else if (!nomePais.equals(other.nomePais))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Pais [idPais=" + id + ", nomePais=" + nomePais + "]";
	}
	
	

}
