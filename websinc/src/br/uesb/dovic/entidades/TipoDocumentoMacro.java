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
@Table(name="tipodocumentomacro")
public class TipoDocumentoMacro implements Serializable {
	@Id
	@Column(name="idTipoMacro")
	@SequenceGenerator(name="SEQ_TIPO_MACRO", sequenceName="SEQ_TIPO_MACRO_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TIPO_MACRO")
	private Integer id;
	
	@NotEmpty(message="A descrição do tipo  não pode ser nula")
	@Length(max=200, message="A descrição não pode ultrapassar {max} caracteres")
	@Column(name="descricaoTipoMacro", length=200, nullable=false)
	private String descricaoTipoMacro;
	
	public TipoDocumentoMacro(){
		
	}



	public TipoDocumentoMacro(Integer id, String descricaoTipoMacro) {
		super();
		this.id = id;
		this.descricaoTipoMacro = descricaoTipoMacro;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getDescricaoTipoMacro() {
		return descricaoTipoMacro;
	}

	public void setDescricaoTipoMacro(String descricaoTipoMacro) {
		this.descricaoTipoMacro = descricaoTipoMacro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((descricaoTipoMacro == null) ? 0 : descricaoTipoMacro
						.hashCode());
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
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
		TipoDocumentoMacro other = (TipoDocumentoMacro) obj;
		if (descricaoTipoMacro == null) {
			if (other.descricaoTipoMacro != null)
				return false;
		} else if (!descricaoTipoMacro.equals(other.descricaoTipoMacro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return descricaoTipoMacro;
	}
	
	

}
