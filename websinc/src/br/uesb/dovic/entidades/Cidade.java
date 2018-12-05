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
@Table(name="cidade")
@NamedQueries({
	@NamedQuery(name="findByEstado", query = "SELECT c FROM Cidade c WHERE c.estado.id=:idEstado")
})

public class Cidade implements Serializable {
	@Id
	@Column(name="idcidade")
	@SequenceGenerator(name="SEQ_CIDADE", sequenceName="SEQ_CIDADE_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CIDADE")
	private Integer id;
	
	@NotEmpty(message="O nome da cidade deve ser informado")
	@Length(max=100, message="O nome da cidade não pode ultrapassar {max} caracteres")
	@Column(name="nomecidade", length=100, nullable=false)
	private String nomeCidade;
	
	@ManyToOne
	@JoinColumn(name="idEstado", nullable=false)
	private Estado estado;
	
	
	public Cidade(){
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer idCidade) {
		this.id = idCidade;
	}


	public String getNomeCidade() {
		return nomeCidade;
	}


	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomeCidade == null) ? 0 : nomeCidade.hashCode());
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
		Cidade other = (Cidade) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeCidade == null) {
			if (other.nomeCidade != null)
				return false;
		} else if (!nomeCidade.equals(other.nomeCidade))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return  nomeCidade ;
	}
	
	
	
}
	
