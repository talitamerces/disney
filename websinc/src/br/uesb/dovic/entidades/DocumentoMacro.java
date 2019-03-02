package br.uesb.dovic.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.uesb.dovic.enums.TipoData;

@Entity
@Table(name="documentomacro")
public class DocumentoMacro implements Serializable {
	@Id
	@Column(name="idDocumentoMacro")
	@SequenceGenerator(name="SEQ_MACRO", sequenceName="SEQ_MACRO_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MACRO")
	private Integer id;
	
	
	@Column(name="idDocumentoFisico", length=50)
	private String idDocumentoFisico;
	
	
	@Column(name="dataInicioDocumento")
	@Temporal(TemporalType.DATE)
	private Date dataInicioDocumento;
	
	
	@Column(name="dataFimDocumento")
	@Temporal(TemporalType.DATE)
	private Date dataFimDocumento;
	
	
	@NotEmpty(message="O título não pode ser vazio")
	@Length(max=255, message="O título não pode ultrapassar {max} caracteres")
	@Column(name="titulo", length=255)
	private String titulo;
	
	@Column(name="observacoes")
	private String observacoes;
	
	@Column(name="descricaoVolume")
	private String descricaoVolume;
	
	
	@Column(name="alturaDocumento")
	@Digits(fraction=2, integer=5, 
	message="A altura deve ser informada com no máximo {integer} dígitos na parte interna e {fraction} dígitos na parte fracionária" ) 
	private Double alturaDocumento;
	
	@Column(name="larguraDocumento")
	@Digits(fraction=2, integer=5, 
	message="A largura deve ser informada com no máximo {integer} dígitos na parte interna e {fraction} dígitos na parte fracionária" )
	private Double larguraDocumento;
	
	@Column(name="profundidadeDocumento")
	@Digits(fraction=2, integer=5, 
	message="A profundidade deve ser informada com no máximo {integer} dígitos na parte interna e {fraction} dígitos na parte fracionária" )
	private Double profundidadeDocumento;
	
	@Column(name="forroDocumento")
	private Boolean forroDocumento;
	
	@Column(name="comCapa")
	private Boolean comCapa;
	
	@Column(name="capaOriginal")
	private Boolean capaOriginal;
	
	@Column(name="dataAproximada")
	private Boolean dataAproximada;
	
	@Column(name="anoInicioDocumento")
	@Digits(fraction = 0, integer = 4, message ="Apenas inteiros com 4 dígitos")
	private Integer anoInicioDocumento;
	
	@Column(name="anoFimDocumento")
	@Digits(fraction = 0, integer = 4, message ="Apenas inteiros com 4 dígitos")
	private Integer anoFimDocumento;
	
	@ManyToOne
	@JoinColumn(name="idTipoMacro")
	private TipoDocumentoMacro tipoMacro;
	
	@ManyToOne
	@JoinColumn(name="idMaterialCapa")
	private MaterialCapaForro materialCapa;
	
	@ManyToOne
	@JoinColumn(name="idCorCapa")
	private Cor corCapa;
	
	
	@ManyToOne
	@JoinColumn(name="idLocalEncontrado")
	private LocalDeposito localEncontrado;
	
	@Enumerated
	@Column(name="tipoDataDocumento")
	private TipoData tipoData;
	
	@ManyToOne
	@JoinColumn(name="idTrabalhoCaptura")
	private TrabalhoCorpus captura;
	
	@ManyToOne
	@JoinColumn(name="idTrabalhoCatalogacao")
	private TrabalhoCorpus catalogacao;
	
	
		
	@ManyToMany(fetch=FetchType.EAGER)
//	@ManyToMany
	@JoinTable(name="materialforrodocumento", joinColumns={@JoinColumn(name="iddocumentomacro")},
	inverseJoinColumns={@JoinColumn(name="idmaterialcapa")})
	private List<MaterialCapaForro> materiaisForro = new ArrayList<MaterialCapaForro>();
	
	public DocumentoMacro(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idDocumentoMacro) {
		this.id = idDocumentoMacro;
	}

	public String getIdDocumentoFisico() {
		return idDocumentoFisico;
	}

	public void setIdDocumentoFisico(String idDocumentoFisico) {
		this.idDocumentoFisico = idDocumentoFisico;
	}

	public TrabalhoCorpus getCaptura() {
		return captura;
	}

	public void setCaptura(TrabalhoCorpus captura) {
		this.captura = captura;
	}

	public TrabalhoCorpus getCatalogacao() {
		return catalogacao;
	}

	public void setCatalogacao(TrabalhoCorpus catalogacao) {
		this.catalogacao = catalogacao;
	}
	
	public Date getDataInicioDocumento() {
		return dataInicioDocumento;
	}

	public void setDataInicioDocumento(Date dataInicioDocumento) {
		this.dataInicioDocumento = dataInicioDocumento;
	}

	public Date getDataFimDocumento() {
		return dataFimDocumento;
	}


	public void setMateriaisForro(List<MaterialCapaForro> materiaisForro) {
		this.materiaisForro = materiaisForro;
	}
	
	

	public List<MaterialCapaForro> getMateriaisForro() {
		return materiaisForro;
	}

	public void setDataFimDocumento(Date dataFimDocumento) {
		this.dataFimDocumento = dataFimDocumento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	public Double getAlturaDocumento() {
		return alturaDocumento;
	}

	public void setAlturaDocumento(Double alturaDocumento) {
		this.alturaDocumento = alturaDocumento;
	}

	public Double getLarguraDocumento() {
		return larguraDocumento;
	}

	public void setLarguraDocumento(Double larguraDocumento) {
		this.larguraDocumento = larguraDocumento;
	}

	public Double getProfundidadeDocumento() {
		return profundidadeDocumento;
	}

	public void setProfundidadeDocumento(Double profundidadeDocumento) {
		this.profundidadeDocumento = profundidadeDocumento;
	}

	public Boolean getForroDocumento() {
		return forroDocumento;
	}

	public void setForroDocumento(Boolean forroDocumento) {
		this.forroDocumento = forroDocumento;
	}

	public Boolean getComCapa() {
		return comCapa;
	}

	public void setComCapa(Boolean comCapa) {
		this.comCapa = comCapa;
	}

	public Boolean getCapaOriginal() {
		return capaOriginal;
	}

	public void setCapaOriginal(Boolean capaOriginal) {
		this.capaOriginal = capaOriginal;
	}

	public Boolean getDataAproximada() {
		return dataAproximada;
	}

	public void setDataAproximada(Boolean dataAproximada) {
		this.dataAproximada = dataAproximada;
	}

	public Integer getAnoInicioDocumento() {
		return anoInicioDocumento;
	}

	public void setAnoInicioDocumento(Integer anoInicioDocumento) {
		this.anoInicioDocumento = anoInicioDocumento;
	}

	public Integer getAnoFimDocumento() {
		return anoFimDocumento;
	}

	public void setAnoFimDocumento(Integer anoFimDocumento) {
		this.anoFimDocumento = anoFimDocumento;
	}

	public TipoDocumentoMacro getTipoMacro() {
		return tipoMacro;
	}

	public void setTipoMacro(TipoDocumentoMacro tipoMacro) {
		this.tipoMacro = tipoMacro;
	}

	

	public MaterialCapaForro getMaterialCapa() {
		return materialCapa;
	}

	public void setMaterialCapa(MaterialCapaForro materialCapa) {
		this.materialCapa = materialCapa;
	}

	public LocalDeposito getLocalEncontrado() {
		return localEncontrado;
	}

	public void setLocalEncontrado(LocalDeposito localEncontrado) {
		this.localEncontrado = localEncontrado;
	}

	public TipoData getTipoData() {
		return tipoData;
	}

	public void setTipoData(TipoData tipoData) {
		this.tipoData = tipoData;
	}
	
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alturaDocumento == null) ? 0 : alturaDocumento.hashCode());
		result = prime * result
				+ ((anoFimDocumento == null) ? 0 : anoFimDocumento.hashCode());
		result = prime
				* result
				+ ((anoInicioDocumento == null) ? 0 : anoInicioDocumento
						.hashCode());
		result = prime * result
				+ ((capaOriginal == null) ? 0 : capaOriginal.hashCode());
		result = prime * result + ((captura == null) ? 0 : captura.hashCode());
		result = prime * result
				+ ((catalogacao == null) ? 0 : catalogacao.hashCode());
		result = prime * result + ((comCapa == null) ? 0 : comCapa.hashCode());
		result = prime * result
				+ ((dataAproximada == null) ? 0 : dataAproximada.hashCode());
		result = prime
				* result
				+ ((dataFimDocumento == null) ? 0 : dataFimDocumento.hashCode());
		result = prime
				* result
				+ ((dataInicioDocumento == null) ? 0 : dataInicioDocumento
						.hashCode());
		result = prime * result
				+ ((forroDocumento == null) ? 0 : forroDocumento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((idDocumentoFisico == null) ? 0 : idDocumentoFisico
						.hashCode());
		
		result = prime
				* result
				+ ((larguraDocumento == null) ? 0 : larguraDocumento.hashCode());
		result = prime * result
				+ ((localEncontrado == null) ? 0 : localEncontrado.hashCode());
		result = prime * result
				+ ((materiaisForro == null) ? 0 : materiaisForro.hashCode());
		result = prime * result
				+ ((materialCapa == null) ? 0 : materialCapa.hashCode());
		result = prime * result
				+ ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime
				* result
				+ ((profundidadeDocumento == null) ? 0 : profundidadeDocumento
						.hashCode());
		result = prime * result
				+ ((tipoData == null) ? 0 : tipoData.hashCode());
		result = prime * result
				+ ((tipoMacro == null) ? 0 : tipoMacro.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		DocumentoMacro other = (DocumentoMacro) obj;
		if (alturaDocumento == null) {
			if (other.alturaDocumento != null)
				return false;
		} else if (!alturaDocumento.equals(other.alturaDocumento))
			return false;
		if (anoFimDocumento == null) {
			if (other.anoFimDocumento != null)
				return false;
		} else if (!anoFimDocumento.equals(other.anoFimDocumento))
			return false;
		if (anoInicioDocumento == null) {
			if (other.anoInicioDocumento != null)
				return false;
		} else if (!anoInicioDocumento.equals(other.anoInicioDocumento))
			return false;
		if (capaOriginal == null) {
			if (other.capaOriginal != null)
				return false;
		} else if (!capaOriginal.equals(other.capaOriginal))
			return false;
		if (captura == null) {
			if (other.captura != null)
				return false;
		} else if (!captura.equals(other.captura))
			return false;
		if (catalogacao == null) {
			if (other.catalogacao != null)
				return false;
		} else if (!catalogacao.equals(other.catalogacao))
			return false;
		if (comCapa == null) {
			if (other.comCapa != null)
				return false;
		} else if (!comCapa.equals(other.comCapa))
			return false;
		if (dataAproximada == null) {
			if (other.dataAproximada != null)
				return false;
		} else if (!dataAproximada.equals(other.dataAproximada))
			return false;
		if (dataFimDocumento == null) {
			if (other.dataFimDocumento != null)
				return false;
		} else if (!dataFimDocumento.equals(other.dataFimDocumento))
			return false;
		if (dataInicioDocumento == null) {
			if (other.dataInicioDocumento != null)
				return false;
		} else if (!dataInicioDocumento.equals(other.dataInicioDocumento))
			return false;
		if (forroDocumento == null) {
			if (other.forroDocumento != null)
				return false;
		} else if (!forroDocumento.equals(other.forroDocumento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idDocumentoFisico == null) {
			if (other.idDocumentoFisico != null)
				return false;
		} else if (!idDocumentoFisico.equals(other.idDocumentoFisico))
			return false;
		
		if (larguraDocumento == null) {
			if (other.larguraDocumento != null)
				return false;
		} else if (!larguraDocumento.equals(other.larguraDocumento))
			return false;
		if (localEncontrado == null) {
			if (other.localEncontrado != null)
				return false;
		} else if (!localEncontrado.equals(other.localEncontrado))
			return false;
		if (materiaisForro == null) {
			if (other.materiaisForro != null)
				return false;
		} else if (!materiaisForro.equals(other.materiaisForro))
			return false;
		if (materialCapa == null) {
			if (other.materialCapa != null)
				return false;
		} else if (!materialCapa.equals(other.materialCapa))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (profundidadeDocumento == null) {
			if (other.profundidadeDocumento != null)
				return false;
		} else if (!profundidadeDocumento.equals(other.profundidadeDocumento))
			return false;
		
		if (tipoData != other.tipoData)
			return false;
		if (tipoMacro == null) {
			if (other.tipoMacro != null)
				return false;
		} else if (!tipoMacro.equals(other.tipoMacro))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	public String getDescricaoVolume() {
		return descricaoVolume;
	}

	public void setDescricaoVolume(String descricaoVolume) {
		this.descricaoVolume = descricaoVolume;
	}

	public Cor getCorCapa() {
		return corCapa;
	}

	public void setCorCapa(Cor corCapa) {
		this.corCapa = corCapa;
	}
}
