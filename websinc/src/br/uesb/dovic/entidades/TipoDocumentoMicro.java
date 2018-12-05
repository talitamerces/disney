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
@Table(name="tipodocumentomicro")
public class TipoDocumentoMicro implements Serializable {
	@Id
	@Column(name="idTipoMicro")
	@SequenceGenerator(name="SEQ_TIPO_MICRO", sequenceName="SEQ_TIPO_MICRO_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TIPO_MICRO")
	private Integer id;
	
	@NotEmpty(message="A descri��o do tipo  n�o pode ser nula")
	@Length(max=200, message="A descri��o n�o pode ultrapassar {max} caracteres")
	@Column(name="descricaoTipoMicro", length=200, nullable=false)
	private String descricaoTipoMicro;
	
	public TipoDocumentoMicro(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idTipoMicro) {
		this.id = idTipoMicro;
	}

	public String getDescricaoTipoMicro() {
		return descricaoTipoMicro;
	}

	public void setDescricaoTipoMicro(String descricaoTipoMicro) {
		this.descricaoTipoMicro = descricaoTipoMicro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((descricaoTipoMicro == null) ? 0 : descricaoTipoMicro
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
		TipoDocumentoMicro other = (TipoDocumentoMicro) obj;
		if (descricaoTipoMicro == null) {
			if (other.descricaoTipoMicro != null)
				return false;
		} else if (!descricaoTipoMicro.equals(other.descricaoTipoMicro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
