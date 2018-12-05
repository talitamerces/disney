package br.uesb.dovic.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="autor")
@SequenceGenerator(name="SEQ_AUTOR", sequenceName="SEQ_AUTOR_ID", allocationSize=1)
public class Autor implements Serializable {
	@Id
	@Column(name="idAutor")
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_AUTOR")
	private Integer id;
	
	@NotEmpty(message="O nome do autor deve ser informado")
	@Length(max=100, message="O nome não pode ultrapassar {max} caracteres")
	@Column(name="nomeautor", length=100, nullable=false)
	private String nomeAutor;
	
	@Column(name="dataNascimentoCompleta")
	@Temporal(TemporalType.DATE)
	private Date dataNascimentoCompleta;
	
	@Column(name="dataMorteCompleta")
	@Temporal(TemporalType.DATE)
	private Date dataMorteCompleta;
	
	@Column(name="anoNascimento")
	private Integer anoNascimento;
	
	@Column(name="anoMorte")
	private Integer anoMorte;
	
	@Column(name="observacaoAutor")
	private String observacaoAutor;

	public Autor(){
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Integer getId() {
		return id;
	}
	

	public void setId(Integer idAutor) {
		this.id = idAutor;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public Date getDataNascimentoCompleta() {
		return dataNascimentoCompleta;
	}

	public void setDataNascimentoCompleta(Date dataNascimentoCompleta) {
		this.dataNascimentoCompleta = dataNascimentoCompleta;
	}

	public Date getDataMorteCompleta() {
		return dataMorteCompleta;
	}

	public void setDataMorteCompleta(Date dataMorteCompleta) {
		this.dataMorteCompleta = dataMorteCompleta;
	}

	public Integer getAnoNascimento() {
		return anoNascimento;
	}

	public void setAnoNascimento(Integer anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public Integer getAnoMorte() {
		return anoMorte;
	}

	public void setAnoMorte(Integer anoMorte) {
		this.anoMorte = anoMorte;
	}

	public String getObservacaoAutor() {
		return observacaoAutor;
	}

	public void setObservacaoAutor(String observacaoAutor) {
		this.observacaoAutor = observacaoAutor;
	}
	
	
	
	
}
	
	