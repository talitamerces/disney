package br.uesb.dovic.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Length;

import br.uesb.dovic.enums.LadoImagem;
import br.uesb.dovic.enums.TipoImagem;

@Entity
@Table(name = "imagem")
@NamedQueries({

		// @NamedQuery(name="findImagensSelecao", query =
		// "SELECT i FROM Imagem i WHERE i.documentoMacro.id=:idMacro and i not in (:lista)"),
		@NamedQuery(name = "findImagemByMacro", query = "SELECT i FROM Imagem i WHERE i.documentoMacro.id=:idMacro order by numeroSequencia"),
		@NamedQuery(name = "getImagensCatalogo", query = "SELECT i FROM Imagem i WHERE i.documentoMacro.id=:idMacro and i.tipoImagem IN (:tipos)")

})
public class Imagem implements Serializable {
	@Id
	@Column(name = "idImagem")
	@SequenceGenerator(name = "SEQ_IMAGEM", sequenceName = "SEQ_IMAGEM_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IMAGEM")
	private Integer id;

	@Length(max = 50, message = "O código não pode ultrapassar {max} caracteres")
	@Column(name = "codigoImagem", length = 50, nullable = true)
	private String codigoImagem;

	@Column(name = "enderecoImagem", nullable = true)
	private String enderecoImagem;

	@Column(name = "nrpagina", nullable = true)
	@Digits(integer = 10, fraction = 0, message = "O número da página deve ser informado com um número inteiro")
	private Integer numeroPagina;

	@Column(name = "nrfolha", nullable = true)
	@Digits(integer = 10, fraction = 0, message = "O número da folha deve ser informado com um número inteiro")
	private Integer numeroFolha;

	@Column(name = "nrsequencia", nullable = true)
	@Digits(integer = 10, fraction = 0, message = "O número de sequência deve ser informado com um número inteiro")
	private Integer numeroSequencia;

	@Enumerated
	@Column(name = "tipoImagem")
	private TipoImagem tipoImagem;

	@Enumerated
	@Column(name = "ladoImagem")
	private LadoImagem ladoImagem;

	@ManyToOne
	@JoinColumn(name = "iddocumentomacro", nullable = true)
	private DocumentoMacro documentoMacro;

	@ManyToMany(mappedBy = "imagens")
	private List<DocumentoMicro> documentos = new ArrayList<DocumentoMicro>();

	@Transient
	private boolean selecionada;

	public Imagem() {
		selecionada = false;
	}

	//
	// public List<DocumentoMicro> getDocumentos() {
	// return documentos;
	// }

	public void setDocumentos(List<DocumentoMicro> documentos) {
		this.documentos = documentos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idImagem) {
		this.id = idImagem;
	}

	public String getCodigoImagem() {
		return codigoImagem;
	}

	public void setCodigoImagem(String codigoImagem) {
		this.codigoImagem = codigoImagem;
	}

	public String getEnderecoImagem() {
		return enderecoImagem;
	}

	public void setEnderecoImagem(String enderecoImagem) {
		this.enderecoImagem = enderecoImagem;
	}

	public TipoImagem getTipoImagem() {
		return tipoImagem;
	}

	public void setTipoImagem(TipoImagem tipoImagem) {
		this.tipoImagem = tipoImagem;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public Integer getNumeroFolha() {
		return numeroFolha;
	}

	public void setNumeroFolha(Integer numeroFolha) {
		this.numeroFolha = numeroFolha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoImagem == null) ? 0 : codigoImagem.hashCode());
		result = prime * result
				+ ((enderecoImagem == null) ? 0 : enderecoImagem.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((tipoImagem == null) ? 0 : tipoImagem.hashCode());
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
		Imagem other = (Imagem) obj;
		if (codigoImagem == null) {
			if (other.codigoImagem != null)
				return false;
		} else if (!codigoImagem.equals(other.codigoImagem))
			return false;
		if (enderecoImagem == null) {
			if (other.enderecoImagem != null)
				return false;
		} else if (!enderecoImagem.equals(other.enderecoImagem))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipoImagem != other.tipoImagem)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Imagem [idImagem=" + id + ", codigoImagem=" + codigoImagem
				+ ", enderecoImagem=" + enderecoImagem + ", tipoImagem="
				+ tipoImagem + "]";
	}

	public Integer getNumeroSequencia() {
		return numeroSequencia;
	}

	public void setNumeroSequencia(Integer numeroSequencia) {
		this.numeroSequencia = numeroSequencia;
	}

	public DocumentoMacro getDocumentoMacro() {
		return documentoMacro;
	}

	public void setDocumentoMacro(DocumentoMacro documentoMacro) {
		this.documentoMacro = documentoMacro;
	}

	public boolean isSelecionada() {
		return selecionada;
	}

	public void setSelecionada(boolean selecionada) {
		this.selecionada = selecionada;
	}

	public LadoImagem getLadoImagem() {
		return ladoImagem;
	}

	public void setLadoImagem(LadoImagem ladoImagem) {
		this.ladoImagem = ladoImagem;
	}

}
