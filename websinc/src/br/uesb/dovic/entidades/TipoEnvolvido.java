package br.uesb.dovic.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tipoenvolvido")
public class TipoEnvolvido implements Serializable {
	@Id
	@Column(name="idTipoEnvolvido")
	@SequenceGenerator(name="SEQ_TIPO_ENVOLVIDO", sequenceName="SEQ_TIPO_ENVOLVIDO_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TIPO_ENVOLVIDO")
	private Integer id;
	
	@NotEmpty(message="A descrição do tipo  não pode ser nula")
	@Length(max=200, message="A descrição não pode ultrapassar {max} caracteres")
	@Column(name="descricaoTipoEnvolvido", length=200, nullable=false)
	private String descricaoTipoEnvolvido;
	
	public TipoEnvolvido(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoTipoEnvolvido() {
		return descricaoTipoEnvolvido;
	}

	public void setDescricaoTipoEnvolvido(String descricaoTipoEnvolvido) {
		this.descricaoTipoEnvolvido = descricaoTipoEnvolvido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((descricaoTipoEnvolvido == null) ? 0
						: descricaoTipoEnvolvido.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TipoEnvolvido other = (TipoEnvolvido) obj;
		if (descricaoTipoEnvolvido == null) {
			if (other.descricaoTipoEnvolvido != null)
				return false;
		} else if (!descricaoTipoEnvolvido.equals(other.descricaoTipoEnvolvido))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
