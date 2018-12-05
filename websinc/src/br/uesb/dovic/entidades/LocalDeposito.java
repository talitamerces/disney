package br.uesb.dovic.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="localdeposito")
public class LocalDeposito implements Serializable {
	@Id
	@Column(name="idLocal")
	@SequenceGenerator(name="SEQ_LOCAL", sequenceName="SEQ_LOCAL_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_LOCAL")
	private Integer id;
	
	@NotEmpty(message="O nome do local deve ser informado")
	@Length(max=100, message="O nome do local não pode ultrapassar {max} caracteres")
	@Column(name="nomeLocal", length=100, nullable=false)
	private String nomeLocal;
	
	@ManyToOne
	@JoinColumn(name="idCidade")
	private Cidade cidade;
	
	public LocalDeposito(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idLocal) {
		this.id = idLocal;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomeLocal == null) ? 0 : nomeLocal.hashCode());
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
		LocalDeposito other = (LocalDeposito) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeLocal == null) {
			if (other.nomeLocal != null)
				return false;
		} else if (!nomeLocal.equals(other.nomeLocal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeLocal + " - "+ cidade;
	}
	
	
	
	

}
