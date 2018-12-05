package br.uesb.dovic.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="documentomicro")
@NamedQueries({
	@NamedQuery(name="findImagensSelecao", 
			query = "SELECT img FROM Imagem img "
					+ "JOIN img.documentoMacro dma "
					+ "WHERE dma.id=:idMacro "
					+ "AND img.id NOT IN ("
					+ "	SELECT imgDmi.id"
					+ "	FROM DocumentoMicro dmi"
					+ " JOIN dmi.imagens imgDmi"
					+ " WHERE dmi.id=:idMicro"
					+ ") order by img.numeroSequencia"),
					
		@NamedQuery(name="findColaboradores", 
					query = "SELECT c FROM Colaborador c order by c.nomeColaborador"),
					
		@NamedQuery(name="findImagensMacro", 
					query = "SELECT img FROM Imagem img "
							+ "JOIN img.documentoMacro dma "
							+ "WHERE dma.id=:idMacro order by img.numeroSequencia"),
							
		@NamedQuery(name="findImagensMicro", 
							query = "SELECT dmi.imagens"
					+ "	FROM DocumentoMicro dmi"
					+ " WHERE dmi.id=:idMicro"),			
							
	@NamedQuery(name="findByMacro", query = "SELECT d FROM DocumentoMicro d WHERE d.documentoMacro.id=:idMacro order by d.id"),
	@NamedQuery(name="findAvulsos", query = "SELECT d FROM DocumentoMicro d WHERE d.avulso=TRUE"),
	@NamedQuery(name="findAnotadosMorfo", query = "SELECT d FROM DocumentoMicro d WHERE d.arquivoAnotacaoXML<>''"),
	@NamedQuery(name="findAnotadosSintax", query = "SELECT d FROM DocumentoMicro d WHERE d.arquivoSintaxeXML<>''"),
	@NamedQuery(name="groupByTipoMicro", query = "SELECT d.tipoMicro.descricaoTipoMicro  AS nomeTipoMicro, count(d.id) AS total FROM DocumentoMicro d GROUP BY d.tipoMicro.descricaoTipoMicro "),
	@NamedQuery(name="groupByTipoMicroDetalhado", query = "SELECT d.documentoMacro.id AS idMacro, d.documentoMacro.titulo AS nomeMacro, d.tipoMicro.descricaoTipoMicro  AS nomeTipoMicro, count(d.id) AS total FROM DocumentoMicro d GROUP BY d.documentoMacro.id, d.documentoMacro.titulo, d.tipoMicro.descricaoTipoMicro ")
})
public class DocumentoMicro implements Serializable {
	@Id
	@Column(name="idDocumentoMicro")
	@SequenceGenerator(name="SEQ_MICRO", sequenceName="SEQ_MICRO_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MICRO")
	private Integer id;
	
	
	@Column(name="idDocumentoOriginal", length=50)
	private String idDocumentoOriginal;
	
	
	@Column(name="dataPublicacaoCompleta")
	@Temporal(TemporalType.DATE)
	private Date dataPublicacaoCompleta;
	
	
	@Column(name="dataDocumentoCompleta")
	@Temporal(TemporalType.DATE)
	private Date dataDocumentoCompleta;
	
	
	
	@Column(name="titulo", length=255)
	private String titulo;
	
		
	@Column(name="observacoes")
	private String observacoes;
	
	
	@Column(name="comCapa")
	private Boolean comCapa;
	
	@Column(name="avulso")
	private Boolean avulso;
	
	@Column(name="anoPublicacao")
	private Integer anoPublicacao;
	
	@Column(name="anoDocumento")
	private Integer anoDocumento;
	
	@ManyToOne
	@JoinColumn(name="idTipoMicro")
	private TipoDocumentoMicro tipoMicro;
	
	
	@Column(name="arquivoAnotacaoXML")
	private String arquivoAnotacaoXML;
	
	
	@Column(name="arquivoPSD")
	private String arquivoPSD;
	
	@Column(name="arquivoSintaxeXML")
	private String arquivoSintaxeXML;
	
	
	@ManyToOne
	@JoinColumn(name="idTrabalhoEdicao")
	private TrabalhoCorpus edicao;
	
	
	@ManyToOne
	@JoinColumn(name="idTrabalhoTranscricao")
	private TrabalhoCorpus transcricao;
	
	
	@ManyToOne
	@JoinColumn(name="idTrabalhoRevisaoMorfologia")
	private TrabalhoCorpus revisaoMorfologia;
	
	@ManyToOne
	@JoinColumn(name="idTrabalhoRevisaoSintaxe")
	private TrabalhoCorpus revisaoSintaxe;
	
	@ManyToOne
	@JoinColumn(name="idTrabalhoCadastroWebsinc")
	private TrabalhoCorpus cadastroWebsinc;
	
	@ManyToOne
	@JoinColumn(name="idDocumentoMacro")
	private DocumentoMacro documentoMacro;
	
	@Column(name="localEscrito")
	private String localEscrito;
	
	@Column(name="localDeposito")
	private String localDeposito;
	
    //@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL )
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="documentoimagem", joinColumns={@JoinColumn(name="iddocumentoMicro")},
	inverseJoinColumns={@JoinColumn(name="idimagem")})
	private List<Imagem> imagens = new ArrayList<Imagem>();
	
	public DocumentoMicro(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idDocumentoMicro) {
		this.id = idDocumentoMicro;
	}

	public String getIdDocumentoOriginal() {
		return idDocumentoOriginal;
	}

	public void setIdDocumentoOriginal(String idDocumentoOriginal) {
		this.idDocumentoOriginal = idDocumentoOriginal;
	}

	public Date getDataPublicacaoCompleta() {
		return dataPublicacaoCompleta;
	}

	public void setDataPublicacaoCompleta(Date dataPublicacaoCompleta) {
		this.dataPublicacaoCompleta = dataPublicacaoCompleta;
	}

	public Date getDataDocumentoCompleta() {
		return dataDocumentoCompleta;
	}

	public void setDataDocumentoCompleta(Date dataDocumentoCompleta) {
		this.dataDocumentoCompleta = dataDocumentoCompleta;
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



	public Boolean getComCapa() {
		return comCapa;
	}

	public void setComCapa(Boolean comCapa) {
		this.comCapa = comCapa;
	}

	public Boolean getAvulso() {
		return avulso;
	}

	public void setAvulso(Boolean avulso) {
		this.avulso = avulso;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public Integer getAnoDocumento() {
		return anoDocumento;
	}

	public void setAnoDocumento(Integer anoDocumento) {
		this.anoDocumento = anoDocumento;
	}

	public TipoDocumentoMicro getTipoMicro() {
		return tipoMicro;
	}

	public void setTipoMicro(TipoDocumentoMicro tipoMicro) {
		this.tipoMicro = tipoMicro;
	}

	public String getArquivoAnotacaoXML() {
		return arquivoAnotacaoXML;
	}

	public void setArquivoAnotacaoXML(String arquivoAnotacaoXML) {
		this.arquivoAnotacaoXML = arquivoAnotacaoXML;
	}

	public String getArquivoSintaxeXML() {
		return arquivoSintaxeXML;
	}

	public void setArquivoSintaxeXML(String arquivoSintaxeXML) {
		this.arquivoSintaxeXML = arquivoSintaxeXML;
	}

	public TrabalhoCorpus getEdicao() {
		return edicao;
	}

	public void setEdicao(TrabalhoCorpus edicao) {
		this.edicao = edicao;
	}

	public TrabalhoCorpus getTranscricao() {
		return transcricao;
	}

	public void setTranscricao(TrabalhoCorpus transcricao) {
		this.transcricao = transcricao;
	}



	public TrabalhoCorpus getRevisaoMorfologia() {
		return revisaoMorfologia;
	}

	public void setRevisaoMorfologia(TrabalhoCorpus revisaoMorfologia) {
		this.revisaoMorfologia = revisaoMorfologia;
	}



	public String getLocalEscrito() {
		return localEscrito;
	}

	public void setLocalEscrito(String localEscrito) {
		this.localEscrito = localEscrito;
	}

	public String getLocalDeposito() {
		//return localDeposito;
		return documentoMacro.getLocalEncontrado().getNomeLocal();
	}

	public void setLocalDeposito(String localDeposito) {
		this.localDeposito = localDeposito;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	
	

	public String getArquivoPSD() {
		return arquivoPSD;
	}

	public void setArquivoPSD(String arquivoPSD) {
		this.arquivoPSD = arquivoPSD;
	}


	public DocumentoMacro getDocumentoMacro() {
		return documentoMacro;
	}

	public void setDocumentoMacro(DocumentoMacro documentoMacro) {
		this.documentoMacro = documentoMacro;
	}
	
	

	public TrabalhoCorpus getRevisaoSintaxe() {
		return revisaoSintaxe;
	}

	public void setRevisaoSintaxe(TrabalhoCorpus revisaoSintaxe) {
		this.revisaoSintaxe = revisaoSintaxe;
	}

	public TrabalhoCorpus getCadastroWebsinc() {
		return cadastroWebsinc;
	}

	public void setCadastroWebsinc(TrabalhoCorpus cadastroWebsinc) {
		this.cadastroWebsinc = cadastroWebsinc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anoDocumento == null) ? 0 : anoDocumento.hashCode());
		result = prime * result
				+ ((anoPublicacao == null) ? 0 : anoPublicacao.hashCode());
		result = prime
				* result
				+ ((arquivoAnotacaoXML == null) ? 0 : arquivoAnotacaoXML
						.hashCode());
		result = prime * result
				+ ((arquivoPSD == null) ? 0 : arquivoPSD.hashCode());
		result = prime
				* result
				+ ((arquivoSintaxeXML == null) ? 0 : arquivoSintaxeXML
						.hashCode());
		
		result = prime * result + ((avulso == null) ? 0 : avulso.hashCode());
		result = prime * result + ((comCapa == null) ? 0 : comCapa.hashCode());
		
		result = prime
				* result
				+ ((dataDocumentoCompleta == null) ? 0 : dataDocumentoCompleta
						.hashCode());
		result = prime
				* result
				+ ((dataPublicacaoCompleta == null) ? 0
						: dataPublicacaoCompleta.hashCode());
		result = prime * result
				+ ((documentoMacro == null) ? 0 : documentoMacro.hashCode());
		result = prime * result + ((edicao == null) ? 0 : edicao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((idDocumentoOriginal == null) ? 0 : idDocumentoOriginal
						.hashCode());
		
		result = prime * result + ((imagens == null) ? 0 : imagens.hashCode());
		result = prime * result
				+ ((localDeposito == null) ? 0 : localDeposito.hashCode());
		result = prime * result
				+ ((localEscrito == null) ? 0 : localEscrito.hashCode());
		result = prime * result
				+ ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime
				* result
				+ ((revisaoMorfologia == null) ? 0 : revisaoMorfologia
						.hashCode());
		
		result = prime * result
				+ ((tipoMicro == null) ? 0 : tipoMicro.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result
				+ ((transcricao == null) ? 0 : transcricao.hashCode());
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
		DocumentoMicro other = (DocumentoMicro) obj;
		if (anoDocumento == null) {
			if (other.anoDocumento != null)
				return false;
		} else if (!anoDocumento.equals(other.anoDocumento))
			return false;
		if (anoPublicacao == null) {
			if (other.anoPublicacao != null)
				return false;
		} else if (!anoPublicacao.equals(other.anoPublicacao))
			return false;
		if (arquivoAnotacaoXML == null) {
			if (other.arquivoAnotacaoXML != null)
				return false;
		} else if (!arquivoAnotacaoXML.equals(other.arquivoAnotacaoXML))
			return false;
		if (arquivoPSD == null) {
			if (other.arquivoPSD != null)
				return false;
		} else if (!arquivoPSD.equals(other.arquivoPSD))
			return false;
		if (arquivoSintaxeXML == null) {
			if (other.arquivoSintaxeXML != null)
				return false;
		} else if (!arquivoSintaxeXML.equals(other.arquivoSintaxeXML))
			return false;
		
		if (avulso == null) {
			if (other.avulso != null)
				return false;
		} else if (!avulso.equals(other.avulso))
			return false;
		if (comCapa == null) {
			if (other.comCapa != null)
				return false;
		} else if (!comCapa.equals(other.comCapa))
			return false;
		
		if (dataDocumentoCompleta == null) {
			if (other.dataDocumentoCompleta != null)
				return false;
		} else if (!dataDocumentoCompleta.equals(other.dataDocumentoCompleta))
			return false;
		if (dataPublicacaoCompleta == null) {
			if (other.dataPublicacaoCompleta != null)
				return false;
		} else if (!dataPublicacaoCompleta.equals(other.dataPublicacaoCompleta))
			return false;
		if (documentoMacro == null) {
			if (other.documentoMacro != null)
				return false;
		} else if (!documentoMacro.equals(other.documentoMacro))
			return false;
		if (edicao == null) {
			if (other.edicao != null)
				return false;
		} else if (!edicao.equals(other.edicao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idDocumentoOriginal == null) {
			if (other.idDocumentoOriginal != null)
				return false;
		} else if (!idDocumentoOriginal.equals(other.idDocumentoOriginal))
			return false;
		
		if (imagens == null) {
			if (other.imagens != null)
				return false;
		} else if (!imagens.equals(other.imagens))
			return false;
		if (localDeposito == null) {
			if (other.localDeposito != null)
				return false;
		} else if (!localDeposito.equals(other.localDeposito))
			return false;
		if (localEscrito == null) {
			if (other.localEscrito != null)
				return false;
		} else if (!localEscrito.equals(other.localEscrito))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		
		if (revisaoMorfologia == null) {
			if (other.revisaoMorfologia != null)
				return false;
		} else if (!revisaoMorfologia.equals(other.revisaoMorfologia))
			return false;
		
		if (tipoMicro == null) {
			if (other.tipoMicro != null)
				return false;
		} else if (!tipoMicro.equals(other.tipoMicro))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (transcricao == null) {
			if (other.transcricao != null)
				return false;
		} else if (!transcricao.equals(other.transcricao))
			return false;
		return true;
	}

	

}
