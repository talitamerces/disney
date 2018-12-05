package br.uesb.dovic.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="estado")
@NamedQueries({
	@NamedQuery(name="findByPais", query = "SELECT e FROM Estado e WHERE e.pais.id=:idPais")
})



public class Estado implements Serializable {
	@Id
	@Column(name="idestado")
	@SequenceGenerator(name="SEQ_ESTADO", sequenceName="SEQ_ESTADO_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ESTADO")
	private Integer id;
	
	@NotEmpty(message="O nome do estado deve ser informado")
	@Length(max=100, message="O nome não pode ultrapassar {max} caracteres")
	@Column(name="nomeestado", length=100, nullable=false)
	private String nomeEstado;
	
	@ManyToOne
	@JoinColumn(name="idPais", nullable=true)
	private Pais pais;
	
	public Estado(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idEstado) {
		this.id = idEstado;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomeEstado == null) ? 0 : nomeEstado.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
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
		Estado other = (Estado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeEstado == null) {
			if (other.nomeEstado != null)
				return false;
		} else if (!nomeEstado.equals(other.nomeEstado))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estado [idEstado=" + id + ", nomeEstado=" + nomeEstado
				+ ", pais=" + pais + "]";
	}
	
	
	
	

}
