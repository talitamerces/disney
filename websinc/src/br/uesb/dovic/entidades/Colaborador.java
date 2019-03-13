package br.uesb.dovic.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.uesb.dovic.enums.CategoriaColaborador;

@Entity
@Table(name = "colaborador")
@NamedQueries({ @NamedQuery(name = "findByCategoria", query = "SELECT c FROM Colaborador c "
		+ "WHERE c.categoriaColaborador=:categoria order by c.nomeColaborador asc") })
@SequenceGenerator(name = "SEQ_COLABORADOR", sequenceName = "SEQ_COLABORADOR_ID", allocationSize = 1)
public class Colaborador implements Serializable {
	@Id
	@Column(name = "idcolaborador")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COLABORADOR")
	private Integer id;

	@NotEmpty(message = "O nome do colaborador deve ser informado")
	@Length(max = 100, message = "O nome não pode ultrapassar {max} caracteres")
	@Column(name = "nome", length = 100, nullable = false)
	private String nomeColaborador;

	@Column(name = "biografia")
	private String biografia;

	@Column(name = "linkLattes")
	private String linkLattes;

	@Column(name = "enderecoFoto")
	private String enderecoFoto;

	@Column(name = "linkHomePage")
	private String linkHomePage;

	@Enumerated
	@Column(name = "categoria")
	private CategoriaColaborador categoriaColaborador;

	@Transient
	private boolean selecionado;

	public Colaborador() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CategoriaColaborador getCategoriaColaborador() {
		return categoriaColaborador;
	}

	public void setCategoriaColaborador(
			CategoriaColaborador categoriaColaborador) {
		this.categoriaColaborador = categoriaColaborador;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getLinkLattes() {
		return linkLattes;
	}

	public void setLinkLattes(String linkLattes) {
		this.linkLattes = linkLattes;
	}

	public String getEnderecoFoto() {
		return enderecoFoto;
	}

	public void setEnderecoFoto(String enderecoFoto) {
		this.enderecoFoto = enderecoFoto;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public String getLinkHomePage() {
		return linkHomePage;
	}

	public void setLinkHomePage(String linkHomePage) {
		this.linkHomePage = linkHomePage;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((biografia == null) ? 0 : biografia.hashCode());
		result = prime * result
				+ ((enderecoFoto == null) ? 0 : enderecoFoto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((linkLattes == null) ? 0 : linkLattes.hashCode());
		result = prime * result
				+ ((nomeColaborador == null) ? 0 : nomeColaborador.hashCode());
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
		Colaborador other = (Colaborador) obj;
		if (biografia == null) {
			if (other.biografia != null)
				return false;
		} else if (!biografia.equals(other.biografia))
			return false;
		if (enderecoFoto == null) {
			if (other.enderecoFoto != null)
				return false;
		} else if (!enderecoFoto.equals(other.enderecoFoto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (linkLattes == null) {
			if (other.linkLattes != null)
				return false;
		} else if (!linkLattes.equals(other.linkLattes))
			return false;
		if (nomeColaborador == null) {
			if (other.nomeColaborador != null)
				return false;
		} else if (!nomeColaborador.equals(other.nomeColaborador))
			return false;
		return true;
	}

}
