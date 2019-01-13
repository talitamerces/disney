package br.uesb.dovic.controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.event.FileUploadEvent;

import br.uesb.dovic.beans.Lexico;
import br.uesb.dovic.beans.PaginaDocumento;
import br.uesb.dovic.entidades.Autor;
import br.uesb.dovic.entidades.Colaborador;
import br.uesb.dovic.entidades.DocumentoMacro;
import br.uesb.dovic.entidades.DocumentoMicro;
import br.uesb.dovic.entidades.Imagem;
import br.uesb.dovic.entidades.TipoDocumentoMicro;
import br.uesb.dovic.entidades.TrabalhoCorpus;
import br.uesb.dovic.enums.OperacaoCRUD;
import br.uesb.dovic.enums.TipoImagem;
import br.uesb.dovic.enums.TipoTrabalhoCorpus;
import br.uesb.dovic.modelo.DAOAutor;
import br.uesb.dovic.modelo.DAOColaborador;
import br.uesb.dovic.modelo.DAODocumentoMacro;
import br.uesb.dovic.modelo.DAODocumentoMicro;
import br.uesb.dovic.modelo.DAOImagem;
import br.uesb.dovic.modelo.DAOTipoMicro;
import br.uesb.dovic.modelo.DAOTrabalhoCorpus;
import br.uesb.dovic.servicos.FileService;
import br.uesb.dovic.servicos.SyntaxToXML;
import br.uesb.dovic.servicos.ViewService;
import br.uesb.dovic.servicos.XMLService;
import br.uesb.dovic.util.ImagemServlet;
import br.uesb.dovic.util.UtilErros;
import br.uesb.dovic.util.UtilMensagens;

@SuppressWarnings("serial")
@ManagedBean(name = "controleDocumentoMicro")
@ViewScoped
public class ControleDocumentoMicro implements Serializable {

	// @ManagedProperty(value="#{param.idMacroSelected}")
	private Integer selectedMacroId;
	private Integer selectedMicroId;
	private DocumentoMacro selectedMacro;
	private OperacaoCRUD operacaoCRUD;
	private List<DocumentoMicro> documentosMicro;
	private DAODocumentoMicro<DocumentoMicro> dao;
	private DAODocumentoMacro<DocumentoMacro> daoMacro;
	private DAOTipoMicro<TipoDocumentoMicro> daoTipoMicro;
	private DAOImagem<Imagem> daoImagem;
	private DAOColaborador<Colaborador> daoColaborador;
	private DAOAutor<Autor> daoAutor;
	private DAOTrabalhoCorpus<TrabalhoCorpus> daoTrabalho;

	private DocumentoMicro objeto;
	private boolean edicao;
	private List<TipoDocumentoMicro> listaTipos;
	private List<Imagem> imagens;
	private List<Imagem> imagensMacro;
	private List<Colaborador> colaboradoresEdicao;
	private List<Colaborador> colaboradoresTranscricao;
	private List<Colaborador> colaboradoresRevisaoM;
	private List<Colaborador> colaboradoresRevisaoS;
	private List<Colaborador> colaboradoresCadastroW;
	private TrabalhoCorpus trabalhoEdicao;
	private TrabalhoCorpus trabalhoTranscricao;
	private TrabalhoCorpus trabalhoRevisaoM;
	private TrabalhoCorpus trabalhoRevisaoS;
	private TrabalhoCorpus trabalhoCadastroW;
	private TipoTrabalhoCorpus tipoTrabalho;
	private List<Colaborador> colaboradoresTable;

	private List<InputStream> arquivosImagens;

	private List<SelectItem> listaTipoImagem;
	private TipoDocumentoMicro selectedTipoMicro;

	private String caminhoArquivoXML;
	private InputStream arquivoXML;
	private String caminhoArquivoPSD;
	private InputStream arquivoPSD;
	private String caminhoImagemCapa;
	private InputStream arquivoImagemCapa;
	private String caminhoArquivoImagem;
	private InputStream arquivoImagem;
	private TipoImagem selectedTipoImagem;
	private ViewService viewService;
	private SyntaxToXML syntaxService;
	private XMLService xmlService;
	private Integer anoDocumento;
	private Integer anoPublicacao;
	private boolean comCapa;
	private boolean adicionarImagem;
	private boolean checkGerarXML;
	private Integer tabAtiva;
	private Integer nrPagina;
	private Integer nrFolha;
	private boolean fecharStream = true;
	private Date inicioEdicao;
	private Date fimEdicao;
	private Date inicioTranscricao;
	private Date fimTranscricao;
	private Date inicioRevisaoM;
	private Date fimRevisaoM;
	private Date inicioRevisaoS;
	private Date fimRevisaoS;
	private Date inicioCadastroW;
	private Date fimCadastroW;

	private static final String SEPARATOR = File.separator;
	private static final String HOME_FOLDER = System.getProperty("user.home");
	private static String UPLOAD_FOLDER = HOME_FOLDER + SEPARATOR + "websinc"
			+ SEPARATOR + "uploads" + SEPARATOR;
	private static String IMAGENS_FOLDER = HOME_FOLDER + SEPARATOR + "websinc"
			+ SEPARATOR + "imagens" + SEPARATOR;

	public ControleDocumentoMicro() {
		dao = new DAODocumentoMicro<DocumentoMicro>();
		daoMacro = new DAODocumentoMacro<DocumentoMacro>();
		daoTipoMicro = new DAOTipoMicro<TipoDocumentoMicro>();
		daoAutor = new DAOAutor<Autor>();
		daoImagem = new DAOImagem<Imagem>();
		daoColaborador = new DAOColaborador<Colaborador>();
		daoTrabalho = new DAOTrabalhoCorpus<TrabalhoCorpus>();
		arquivosImagens = new ArrayList<InputStream>();
		imagens = new ArrayList<Imagem>();
		imagensMacro = new ArrayList<Imagem>();
		tipoTrabalho = TipoTrabalhoCorpus.EDICAO;
		edicao = false;
	}

	@PostConstruct
	public void init() {
		// objeto=new DocumentoMicro();
		selectedTipoMicro = new TipoDocumentoMicro();
		listaTipos = daoTipoMicro.listarTodos();
		viewService = new ViewService();
		syntaxService = new SyntaxToXML();
		xmlService = new XMLService();
		listaTipoImagem = viewService.getSelectItensFromArray(
				TipoImagem.values(), "toString");
		comCapa = false;
		adicionarImagem = false;
		selectedTipoImagem = TipoImagem.FOLHA;
		tabAtiva = 0;
		colaboradoresEdicao = new ArrayList<Colaborador>();
		colaboradoresTranscricao = new ArrayList<Colaborador>();
		colaboradoresRevisaoM = new ArrayList<Colaborador>();
		colaboradoresRevisaoS = new ArrayList<Colaborador>();
		colaboradoresCadastroW = new ArrayList<Colaborador>();
		colaboradoresTable = dao.getColaboradores();

		String idMacro = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("idMacroSelected");
		String idMicro = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("idMicroSelected");
		if (idMacro != null)
			if (!idMacro.equals("")) {

				selectedMacroId = Integer.parseInt(idMacro);
				selectedMacro = daoMacro.getEm().find(DocumentoMacro.class,
						selectedMacroId);
				documentosMicro = dao.getByDocumentoMacro(selectedMacroId);

			}
		if (idMicro != null)
			if (!idMicro.equals("")) {
				selectedMicroId = Integer.parseInt(idMicro);
				objeto = dao.getEm()
						.find(DocumentoMicro.class, selectedMicroId);

			}
	}

	public String formatarData(String data) {
		if (data != null)
			return viewService.formatarData(data);
		return "";
	}

	public Integer getSelectedMacroId() {
		return selectedMacroId;
	}

	public void setSelectedMacroId(Integer selectedMacroId) {
		this.selectedMacroId = selectedMacroId;
	}

	public List<DocumentoMicro> getDocumentosMicro() {
		return documentosMicro;
	}

	public void setDocumentosMicro(List<DocumentoMicro> documentosMicro) {
		this.documentosMicro = documentosMicro;
	}

	public List<Imagem> getImagensSelecao() {
		List<Imagem> lista = new ArrayList<Imagem>();
		for (Imagem i : imagensMacro)
			if (!imagens.contains(i)) {
				// i.setSelecionada(false);
				lista.add(i);
			}
		return lista;
	}

	public List<Colaborador> getTableTranscricao() {
		List<Colaborador> lista = new ArrayList<Colaborador>();
		for (Colaborador c : colaboradoresTable)
			if (!colaboradoresTranscricao.contains(c)) {

				lista.add(c);
			}
		return lista;
	}

	public List<Colaborador> getTableEdicao() {
		List<Colaborador> lista = new ArrayList<Colaborador>();
		for (Colaborador c : colaboradoresTable)
			if (!colaboradoresEdicao.contains(c)) {

				lista.add(c);
			}
		return lista;
	}

	public List<Colaborador> getTableRevisaoM() {
		List<Colaborador> lista = new ArrayList<Colaborador>();
		for (Colaborador c : colaboradoresTable)
			if (!colaboradoresRevisaoM.contains(c)) {

				lista.add(c);
			}
		return lista;
	}

	public List<Colaborador> getTableRevisaoS() {
		List<Colaborador> lista = new ArrayList<Colaborador>();
		for (Colaborador c : colaboradoresTable)
			if (!colaboradoresRevisaoS.contains(c)) {

				lista.add(c);
			}
		return lista;
	}

	public List<Colaborador> getTableCadastroW() {
		List<Colaborador> lista = new ArrayList<Colaborador>();
		for (Colaborador c : colaboradoresTable)
			if (!colaboradoresCadastroW.contains(c)) {

				lista.add(c);
			}
		return lista;
	}

	public String getEnderecoImagem(Imagem img) {
		if (img == null)
			return null;
		if (img.getEnderecoImagem() == null)
			return null;
		return ImagemServlet.getURL(img.getEnderecoImagem());
	}

	public List<PaginaDocumento> textoTranscrito() {
		String arquivo = "";
		if (objeto.getArquivoAnotacaoXML() != null)
			arquivo = UPLOAD_FOLDER + objeto.getArquivoAnotacaoXML();

		return xmlService.geraTranscricao(arquivo, objeto.getImagens());

	}

	public List<PaginaDocumento> textoModernizado() {
		String arquivo = "";
		if (objeto.getArquivoAnotacaoXML() != null)
			arquivo = UPLOAD_FOLDER + objeto.getArquivoAnotacaoXML();
		return xmlService.geraModernizado(arquivo, objeto.getImagens());
	}

	public List<Lexico> lexicoEdicoes() {
		return xmlService.lexicoEdicoes(UPLOAD_FOLDER
				+ objeto.getArquivoAnotacaoXML());
	}

	public void adicionarImagens() {
		for (Imagem i : imagensMacro) {
			if (i.isSelecionada() && !imagens.contains(i)) {
				Imagem img = daoImagem.getEm().find(Imagem.class, i.getId());
				// imagensSelecao.remove(i);
				imagens.add(img);
			}
		}
		tabAtiva = 2;

	}

	public void adicionarColaboradoresEdicao() {
		for (Colaborador c : colaboradoresTable) {
			if (c.isSelecionado() && !colaboradoresEdicao.contains(c)) {
				Colaborador u = daoColaborador.getEm().find(Colaborador.class,
						c.getId());
				colaboradoresEdicao.add(u);
				c.setSelecionado(false);
			}

		}
		tabAtiva = 4;
	}

	public void adicionarColaboradoresTranscricao() {
		for (Colaborador c : colaboradoresTable) {
			if (c.isSelecionado() && !colaboradoresTranscricao.contains(c)) {
				Colaborador u = daoColaborador.getEm().find(Colaborador.class,
						c.getId());
				colaboradoresTranscricao.add(u);
				c.setSelecionado(false);
			}

		}
		tabAtiva = 3;
	}

	public void adicionarColaboradoresRevisaoM() {
		for (Colaborador c : colaboradoresTable) {
			if (c.isSelecionado() && !colaboradoresRevisaoM.contains(c)) {
				Colaborador u = daoColaborador.getEm().find(Colaborador.class,
						c.getId());
				colaboradoresRevisaoM.add(u);
				c.setSelecionado(false);
			}

		}
		tabAtiva = 5;
	}

	public void adicionarColaboradoresRevisaoS() {
		for (Colaborador c : colaboradoresTable) {
			if (c.isSelecionado() && !colaboradoresRevisaoS.contains(c)) {
				Colaborador u = daoColaborador.getEm().find(Colaborador.class,
						c.getId());
				colaboradoresRevisaoS.add(u);
				c.setSelecionado(false);
			}

		}
		tabAtiva = 6;
	}

	public void adicionarColaboradoresCadastroW() {
		for (Colaborador c : colaboradoresTable) {
			if (c.isSelecionado() && !colaboradoresCadastroW.contains(c)) {
				Colaborador u = daoColaborador.getEm().find(Colaborador.class,
						c.getId());
				colaboradoresCadastroW.add(u);
				c.setSelecionado(false);
			}

		}
		tabAtiva = 7;
	}

	public void removerImagem(Imagem i) {

		imagens.remove(i);
		tabAtiva = 2;
	}

	public void removerEdicao(Colaborador u) {
		colaboradoresEdicao.remove(u);
		tabAtiva = 4;
	}

	public void removerTranscricao(Colaborador u) {
		colaboradoresTranscricao.remove(u);
		tabAtiva = 3;
	}

	public void removerRevisaoM(Colaborador u) {
		colaboradoresRevisaoM.remove(u);
		tabAtiva = 5;
	}

	public void removerRevisaoS(Colaborador u) {
		colaboradoresRevisaoS.remove(u);
		tabAtiva = 6;
	}

	public void removerCadastroW(Colaborador u) {
		colaboradoresCadastroW.remove(u);
		tabAtiva = 7;
	}

	public void addImagem() {

		if (caminhoArquivoImagem != null) {
			Imagem img = new Imagem();
			img.setId(null);
			img.setEnderecoImagem(caminhoArquivoImagem);
			img.setTipoImagem(selectedTipoImagem);
			img.setNumeroFolha(nrFolha);
			img.setNumeroPagina(nrPagina);
			imagens.add(img);
			arquivosImagens.add(arquivoImagem);

		}
		tabAtiva = 1;

	}

	// public List<DocumentoMicro> getByDocumentoMacro() {
	// List<DocumentoMicro> lista =
	// dao.getByDocumentoMacro(selectedMacro.getId());
	// return lista;
	// }

	public void ajaxAnoDocumento() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(objeto.getDataDocumentoCompleta());

		anoDocumento = calendar.get(Calendar.YEAR);

	}

	public void ajaxAnoPublicacao() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(objeto.getDataPublicacaoCompleta());

		anoPublicacao = calendar.get(Calendar.YEAR);

	}

	public void processChecked(AjaxBehaviorEvent event) {
		comCapa = !((SelectBooleanCheckbox) event.getSource()).isSelected();
	}

	public String listar() {
		edicao = false;
		return "/pages/corpus/inserirDocumentoAvulso?faces-redirect=true";
	}

	public String select() {

		return "/pages/corpus/detalhesDocumentoFilho?faces-redirect=true";
	}

	public void removerImagem() {

	}

	public void novo(boolean avulso) {
		// selectedMacro= daoMacro.getEm().find(DocumentoMacro.class,
		// selectedMacroId);
		objeto = new DocumentoMicro();
		objeto.setAvulso(new Boolean(avulso));
		selectedTipoMicro = new TipoDocumentoMicro();
		objeto.setDocumentoMacro(selectedMacro);
		anoDocumento = null;
		anoPublicacao = null;

		imagens = new ArrayList<Imagem>();
		imagensMacro = dao.getImagensMacro(selectedMacro.getId());
		colaboradoresTable = dao.getColaboradores();

		// está buscando todos os colaboradoresTableEdicao na tabela
		caminhoImagemCapa = "";
		caminhoArquivoImagem = "";
		caminhoArquivoXML = "";
		caminhoArquivoPSD = "";

		inicioEdicao = null;
		inicioTranscricao = null;
		inicioRevisaoM = null;
		inicioRevisaoS = null;
		inicioCadastroW = null;
		fimEdicao = null;
		fimTranscricao = null;
		fimRevisaoM = null;
		fimRevisaoS = null;
		fimCadastroW = null;

		colaboradoresEdicao = new ArrayList<Colaborador>();
		colaboradoresTranscricao = new ArrayList<Colaborador>();
		colaboradoresRevisaoM = new ArrayList<Colaborador>();
		colaboradoresRevisaoS = new ArrayList<Colaborador>();
		colaboradoresCadastroW = new ArrayList<Colaborador>();

		trabalhoEdicao = new TrabalhoCorpus();
		trabalhoTranscricao = new TrabalhoCorpus();
		trabalhoRevisaoM = new TrabalhoCorpus();
		trabalhoRevisaoS = new TrabalhoCorpus();
		trabalhoCadastroW = new TrabalhoCorpus();
		trabalhoEdicao.setTipoTrabalhoCorpus(TipoTrabalhoCorpus.EDICAO);
		trabalhoTranscricao
				.setTipoTrabalhoCorpus(TipoTrabalhoCorpus.TRANSCRICAO);
		trabalhoRevisaoM
				.setTipoTrabalhoCorpus(TipoTrabalhoCorpus.REVISAO_MORFOLOGIA);
		trabalhoRevisaoS
				.setTipoTrabalhoCorpus(TipoTrabalhoCorpus.REVISAO_SINTAXE);
		trabalhoCadastroW
				.setTipoTrabalhoCorpus(TipoTrabalhoCorpus.CADASTRO_WEBSINC);
		this.edicao = true;
		operacaoCRUD = OperacaoCRUD.INSERIR;

	}

	public String cancelar() {
		edicao = false;
		objeto = null;
		return null;

	}

	public void gravar() {

		objeto.setTipoMicro(selectedTipoMicro);

		if (anoDocumento != null)
			if (anoDocumento != 0)
				objeto.setAnoDocumento(anoDocumento);
		if (anoPublicacao != null)
			if (anoPublicacao != 0)
				objeto.setAnoPublicacao(anoPublicacao);
		if (arquivoXML != null) {
			objeto.setArquivoAnotacaoXML(caminhoArquivoXML);
			copyFile(caminhoArquivoXML, arquivoXML);
		}

		if (arquivoPSD != null) {

			objeto.setArquivoPSD(caminhoArquivoPSD);
			fecharStream = false;
			copyFile(caminhoArquivoPSD, arquivoPSD);

			if (checkGerarXML == true) {

				File file = new File(UPLOAD_FOLDER + caminhoArquivoPSD);
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}

				String removeExtension = caminhoArquivoPSD.substring(0,
						caminhoArquivoPSD.length() - 3);
				String arquivoSintaxeXML = UPLOAD_FOLDER + "SIN_"
						+ removeExtension + "xml";
				syntaxService.xmlTransform(br, arquivoSintaxeXML);
				objeto.setArquivoSintaxeXML("SIN_" + removeExtension + "xml");

			}
		}

		if (!imagens.isEmpty()) {
			objeto.setImagens(imagens);
		}

		// Grava Edição
		if ((inicioEdicao != null) || (fimEdicao != null)
				|| (!colaboradoresEdicao.isEmpty())) {
			if (trabalhoEdicao == null) {
				trabalhoEdicao = new TrabalhoCorpus();
				trabalhoEdicao.setTipoTrabalhoCorpus(TipoTrabalhoCorpus.EDICAO);
			}
			trabalhoEdicao.setDataInicioTrabalho(inicioEdicao);
			trabalhoEdicao.setDataFimTrabalho(fimEdicao);
			objeto.setEdicao(trabalhoEdicao);
			objeto.getEdicao().setColaboradores(colaboradoresEdicao);
			if (trabalhoEdicao.getIdTrabalhoCorpus() == null)
				daoTrabalho.persist(trabalhoEdicao);
			else
				daoTrabalho.merge(trabalhoEdicao);
		}

		// Grava Transcrição
		if ((inicioTranscricao != null) || (fimTranscricao != null)
				|| (!colaboradoresTranscricao.isEmpty())) {
			if (trabalhoTranscricao == null) {
				trabalhoTranscricao = new TrabalhoCorpus();
				trabalhoTranscricao
						.setTipoTrabalhoCorpus(TipoTrabalhoCorpus.TRANSCRICAO);
			}

			trabalhoTranscricao.setDataInicioTrabalho(inicioTranscricao);
			trabalhoTranscricao.setDataFimTrabalho(fimTranscricao);
			objeto.setTranscricao(trabalhoTranscricao);
			objeto.getTranscricao().setColaboradores(colaboradoresTranscricao);
			if (trabalhoTranscricao.getIdTrabalhoCorpus() == null)
				daoTrabalho.persist(trabalhoTranscricao);
			else
				daoTrabalho.merge(trabalhoTranscricao);

		}

		// Grava Revisão da Morfologia
		if ((inicioRevisaoM != null) || (fimRevisaoM != null)
				|| (!colaboradoresRevisaoM.isEmpty())) {
			if (trabalhoRevisaoM == null) {
				trabalhoRevisaoM = new TrabalhoCorpus();
				trabalhoRevisaoM
						.setTipoTrabalhoCorpus(TipoTrabalhoCorpus.REVISAO_MORFOLOGIA);
			}
			trabalhoRevisaoM.setDataInicioTrabalho(inicioRevisaoM);
			trabalhoRevisaoM.setDataFimTrabalho(fimRevisaoM);
			objeto.setRevisaoMorfologia(trabalhoRevisaoM);
			objeto.getRevisaoMorfologia().setColaboradores(
					colaboradoresRevisaoM);
			if (trabalhoRevisaoM.getIdTrabalhoCorpus() == null)
				daoTrabalho.persist(trabalhoRevisaoM);
			else
				daoTrabalho.merge(trabalhoRevisaoM);
		}

		// Grava Revisão da Sintaxe
		if ((inicioRevisaoS != null) || (fimRevisaoS != null)
				|| (!colaboradoresRevisaoS.isEmpty())) {
			if (trabalhoRevisaoS == null) {
				trabalhoRevisaoS = new TrabalhoCorpus();
				trabalhoRevisaoS
						.setTipoTrabalhoCorpus(TipoTrabalhoCorpus.REVISAO_SINTAXE);
			}
			trabalhoRevisaoS.setDataInicioTrabalho(inicioRevisaoS);
			trabalhoRevisaoS.setDataFimTrabalho(fimRevisaoS);
			objeto.setRevisaoSintaxe(trabalhoRevisaoS);
			objeto.getRevisaoSintaxe().setColaboradores(colaboradoresRevisaoS);
			if (trabalhoRevisaoS.getIdTrabalhoCorpus() == null)
				daoTrabalho.persist(trabalhoRevisaoS);
			else
				daoTrabalho.merge(trabalhoRevisaoS);
		}

		// Grava Trabalho de Cadastro no WebSinc
		if ((inicioCadastroW != null) || (fimCadastroW != null)
				|| (!colaboradoresCadastroW.isEmpty())) {
			if (trabalhoCadastroW == null) {
				trabalhoCadastroW = new TrabalhoCorpus();
				trabalhoCadastroW
						.setTipoTrabalhoCorpus(TipoTrabalhoCorpus.CADASTRO_WEBSINC);
			}
			trabalhoCadastroW.setDataInicioTrabalho(inicioCadastroW);
			trabalhoCadastroW.setDataFimTrabalho(fimCadastroW);
			objeto.setCadastroWebsinc(trabalhoCadastroW);
			objeto.getCadastroWebsinc()
					.setColaboradores(colaboradoresCadastroW);
			if (trabalhoCadastroW.getIdTrabalhoCorpus() == null)
				daoTrabalho.persist(trabalhoCadastroW);
			else
				daoTrabalho.merge(trabalhoCadastroW);
		}

		boolean gravou = false;
		if (objeto.getId() == null) {
			gravou = dao.persist(objeto);

		} else {
			gravou = dao.merge(objeto);
		}

		if (gravou) {
			if (operacaoCRUD == OperacaoCRUD.INSERIR)
				documentosMicro.add(objeto);
			edicao = false;

		}

	}

	public void alterar(DocumentoMicro obj) {
		// selectedMacro= daoMacro.getEm().find(DocumentoMacro.class,
		// selectedMacroId);
		objeto = obj;
		selectedTipoMicro = objeto.getTipoMicro();
		caminhoArquivoXML = objeto.getArquivoAnotacaoXML();
		caminhoArquivoPSD = objeto.getArquivoPSD();
		anoDocumento = objeto.getAnoDocumento();
		anoPublicacao = objeto.getAnoPublicacao();
		// imagens = objeto.getImagens();//EAGER
		imagens = dao.findImagensMicro(objeto.getId());// LAZY
		// imagensMacro é a mesma da inicialização

		// colaboradoresTable=dao.getColaboradores();

		inicioEdicao = null;
		fimEdicao = null;
		inicioTranscricao = null;
		fimTranscricao = null;
		inicioRevisaoM = null;
		fimRevisaoM = null;
		inicioRevisaoS = null;
		fimRevisaoS = null;
		inicioCadastroW = null;
		fimCadastroW = null;

		trabalhoEdicao = objeto.getEdicao();
		if (trabalhoEdicao != null) {
			inicioEdicao = trabalhoEdicao.getDataInicioTrabalho();
			fimEdicao = trabalhoEdicao.getDataFimTrabalho();
			colaboradoresEdicao = daoTrabalho.loadColaboradores(trabalhoEdicao
					.getIdTrabalhoCorpus());
			// atualizarTable(colaboradoresTableEdicao, colaboradoresEdicao);
		} else
			colaboradoresEdicao = new ArrayList<Colaborador>();

		trabalhoTranscricao = objeto.getTranscricao();
		if (trabalhoTranscricao != null) {
			inicioTranscricao = trabalhoTranscricao.getDataInicioTrabalho();
			fimTranscricao = trabalhoTranscricao.getDataFimTrabalho();
			colaboradoresTranscricao = daoTrabalho
					.loadColaboradores(trabalhoTranscricao
							.getIdTrabalhoCorpus());
		} else
			colaboradoresTranscricao = new ArrayList<Colaborador>();

		trabalhoRevisaoM = objeto.getRevisaoMorfologia();
		if (trabalhoRevisaoM != null) {
			inicioRevisaoM = trabalhoRevisaoM.getDataInicioTrabalho();
			fimRevisaoM = trabalhoRevisaoM.getDataFimTrabalho();
			colaboradoresRevisaoM = daoTrabalho
					.loadColaboradores(trabalhoRevisaoM.getIdTrabalhoCorpus());
		} else
			colaboradoresRevisaoM = new ArrayList<Colaborador>();

		trabalhoRevisaoS = objeto.getRevisaoSintaxe();
		if (trabalhoRevisaoS != null) {
			inicioRevisaoS = trabalhoRevisaoS.getDataInicioTrabalho();
			fimRevisaoS = trabalhoRevisaoS.getDataFimTrabalho();
			colaboradoresRevisaoS = daoTrabalho
					.loadColaboradores(trabalhoRevisaoS.getIdTrabalhoCorpus());
		} else
			colaboradoresRevisaoS = new ArrayList<Colaborador>();

		trabalhoCadastroW = objeto.getCadastroWebsinc();
		if (trabalhoCadastroW != null) {
			inicioCadastroW = trabalhoCadastroW.getDataInicioTrabalho();
			fimCadastroW = trabalhoCadastroW.getDataFimTrabalho();
			colaboradoresCadastroW = daoTrabalho
					.loadColaboradores(trabalhoCadastroW.getIdTrabalhoCorpus());
		} else
			colaboradoresCadastroW = new ArrayList<Colaborador>();

		imagensMacro = dao.getImagensSelecao(selectedMacro.getId(),
				objeto.getId());
		edicao = true;
		operacaoCRUD = OperacaoCRUD.ALTERAR;
		tabAtiva = 0;

	}

	public List<Colaborador> getColaboradoresTrabalho(TrabalhoCorpus trabalho) {
		if (trabalho != null)
			return daoTrabalho
					.loadColaboradores(trabalho.getIdTrabalhoCorpus());
		return null;

	}

	// public List<Imagem> atualizarListaImagensSelecao(){
	// List<Imagem> lista=new ArrayList<Imagem>();
	// for (Imagem i:imagensMacro)
	// if (!imagens.contains(i))
	// {
	// imagensMacro.add(i);
	// }
	// return lista;
	//
	//
	// }

	public void excluir() {

		DocumentoMicro dc = dao.getEm().find(DocumentoMicro.class,
				objeto.getId());
		documentosMicro.remove(dc);
		dao.remove(dc);

	}

	public TipoDocumentoMicro getSelectedTipoMicro() {
		return selectedTipoMicro;
	}

	public void setSelectedTipoMicro(TipoDocumentoMicro selectedTipoMicro) {

		this.selectedTipoMicro = selectedTipoMicro;

	}

	public List<Autor> completeAutor(String query) {
		List<Autor> allAutors = daoAutor.listarTodos();
		List<Autor> filteredAutors = new ArrayList<Autor>();

		for (int i = 0; i < allAutors.size(); i++) {
			Autor skin = allAutors.get(i);
			if (skin.getNomeAutor().toLowerCase().startsWith(query)) {
				filteredAutors.add(skin);
			}
		}

		return filteredAutors;
	}

	public void uploadArquivoCapa(FileUploadEvent event) {

		FacesMessage msg = new FacesMessage("Successo no envio do arquivo ",
				event.getFile().getFileName());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		caminhoImagemCapa = event.getFile().getFileName();
		try {
			arquivoImagemCapa = event.getFile().getInputstream();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void uploadArquivoImagem(FileUploadEvent event) {

		FacesMessage msg = new FacesMessage("Successo no envio do arquivo ",
				event.getFile().getFileName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		double time = System.currentTimeMillis();
		String extensao = FileService
				.getExtensao(event.getFile().getFileName());

		caminhoArquivoImagem = "IMG_" + time + "." + extensao;
		try {
			arquivoImagem = event.getFile().getInputstream();

		} catch (IOException e) {
			e.printStackTrace();
			UtilMensagens.mensagemErro("Erro fazer upload da imagem "
					+ UtilErros.getMensagemErro(e));
		}

		adicionarImagem = true;
	}

	public void uploadArquivoXML(FileUploadEvent event) {

		double time = System.currentTimeMillis();
		String extensao = FileService
				.getExtensao(event.getFile().getFileName());
		caminhoArquivoXML = "XML_" + time + "." + extensao;
		try {
			arquivoXML = event.getFile().getInputstream();

		} catch (IOException e) {
			e.printStackTrace();
			UtilMensagens.mensagemErro("Erro fazer upload do arquivo XML "
					+ UtilErros.getMensagemErro(e));
		}
	}

	public void uploadArquivoPSD(FileUploadEvent event) {

		FacesMessage msg = new FacesMessage("Successo no envio do arquivo ",
				event.getFile().getFileName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		double time = System.currentTimeMillis();
		String extensao = FileService
				.getExtensao(event.getFile().getFileName());
		caminhoArquivoPSD = "PSD_" + time + "." + extensao;

		try {
			arquivoPSD = event.getFile().getInputstream();

		} catch (IOException e) {
			e.printStackTrace();
			UtilMensagens.mensagemErro("Erro fazer upload do arquivo PSD"
					+ UtilErros.getMensagemErro(e));
		}
	}

	public void copyFile(String fileName, InputStream in) {

		String pasta;
		if (fileName.contains("jpg") || fileName.contains("png")
				|| fileName.contains("JPG") || fileName.contains("PNG"))
			pasta = IMAGENS_FOLDER;
		else
			pasta = UPLOAD_FOLDER;
		try {

			OutputStream out = new FileOutputStream(new File(pasta + fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			if (fecharStream == true) {
				in.close();
				fecharStream = false;
			}

			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
			UtilMensagens.mensagemErro("Erro fazer upload do arquivo "
					+ UtilErros.getMensagemErro(e));
		}
	}

	public DAOTipoMicro<TipoDocumentoMicro> getDaoTipoMicro() {
		return daoTipoMicro;
	}

	public void setDaoTipoMicro(DAOTipoMicro<TipoDocumentoMicro> daoTipoMicro) {
		this.daoTipoMicro = daoTipoMicro;
	}

	public boolean getEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public List<TipoDocumentoMicro> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<TipoDocumentoMicro> listaTipos) {
		this.listaTipos = listaTipos;
	}

	public DAODocumentoMicro<DocumentoMicro> getDao() {
		return dao;
	}

	public void setDao(DAODocumentoMicro<DocumentoMicro> dao) {
		this.dao = dao;
	}

	public DocumentoMicro getObjeto() {
		return objeto;
	}

	public void setObjeto(DocumentoMicro objeto) {
		this.objeto = objeto;

	}

	public String getCaminhoArquivoXML() {
		return caminhoArquivoXML;
	}

	public void setCaminhoArquivoXML(String caminhoArquivoXML) {
		this.caminhoArquivoXML = caminhoArquivoXML;
	}

	public InputStream getArquivoXML() {
		return arquivoXML;
	}

	public void setArquivoXML(InputStream arquivoXML) {
		this.arquivoXML = arquivoXML;
	}

	public String getCaminhoArquivoPSD() {
		return caminhoArquivoPSD;
	}

	public void setCaminhoArquivoPSD(String caminhoArquivoPSD) {
		this.caminhoArquivoPSD = caminhoArquivoPSD;
	}

	public InputStream getArquivoPSD() {
		return arquivoPSD;
	}

	public void setArquivoPSD(InputStream arquivoPSD) {
		this.arquivoPSD = arquivoPSD;
	}

	public String getCaminhoImagemCapa() {
		return caminhoImagemCapa;
	}

	public void setCaminhoImagemCapa(String caminhoImagemCapa) {
		this.caminhoImagemCapa = caminhoImagemCapa;
	}

	public InputStream getArquivoImagemCapa() {
		return arquivoImagemCapa;
	}

	public void setArquivoImagemCapa(InputStream arquivoImagemCapa) {
		this.arquivoImagemCapa = arquivoImagemCapa;
	}

	public Integer getAnoDocumento() {
		return anoDocumento;
	}

	public void setAnoDocumento(Integer anoDocumento) {
		this.anoDocumento = anoDocumento;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public boolean isComCapa() {
		return comCapa;
	}

	public void setComCapa(boolean comCapa) {
		this.comCapa = comCapa;
	}

	public String getCaminhoArquivoImagem() {
		return caminhoArquivoImagem;
	}

	public void setCaminhoArquivoImagem(String caminhoArquivoImagem) {
		this.caminhoArquivoImagem = caminhoArquivoImagem;
	}

	public InputStream getArquivoImagem() {
		return arquivoImagem;
	}

	public void setArquivoImagem(InputStream arquivoImagem) {
		this.arquivoImagem = arquivoImagem;
	}

	public List<SelectItem> getListaTipoImagem() {
		return listaTipoImagem;
	}

	public void setListaTipoImagem(List<SelectItem> listaTipoImagem) {
		this.listaTipoImagem = listaTipoImagem;
	}

	public TipoImagem getSelectedTipoImagem() {
		return selectedTipoImagem;
	}

	public void setSelectedTipoImagem(TipoImagem selectedTipoImagem) {

		this.selectedTipoImagem = selectedTipoImagem;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	public boolean isAdicionarImagem() {
		return adicionarImagem;
	}

	public void setAdicionarImagem(boolean adicionarImagem) {
		this.adicionarImagem = adicionarImagem;
	}

	public List<InputStream> getArquivosImagens() {
		return arquivosImagens;
	}

	public void setArquivosImagens(List<InputStream> arquivosImagens) {
		this.arquivosImagens = arquivosImagens;
	}

	public DocumentoMacro getSelectedMacro() {
		return selectedMacro;
	}

	public void setSelectedMacro(DocumentoMacro selectedMacro) {
		this.selectedMacro = selectedMacro;
	}

	public boolean isCheckGerarXML() {
		return checkGerarXML;
	}

	public void setCheckGerarXML(boolean checkGerarXML) {
		this.checkGerarXML = checkGerarXML;
	}

	public Integer getTabAtiva() {
		return tabAtiva;
	}

	public void setTabAtiva(Integer tabAtiva) {
		this.tabAtiva = tabAtiva;
	}

	public Integer getNrPagina() {
		return nrPagina;
	}

	public void setNrPagina(Integer nrPagina) {
		this.nrPagina = nrPagina;
	}

	public Integer getNrFolha() {
		return nrFolha;
	}

	public void setNrFolha(Integer nrFolha) {
		this.nrFolha = nrFolha;
	}

	public List<Imagem> getImagensMacro() {
		return imagensMacro;
	}

	public void setImagensMacro(List<Imagem> imagensMacro) {
		this.imagensMacro = imagensMacro;
	}

	public List<Colaborador> getColaboradoresEdicao() {
		return colaboradoresEdicao;
	}

	public void setColaboradoresEdicao(List<Colaborador> colaboradoresEdicao) {
		this.colaboradoresEdicao = colaboradoresEdicao;
	}

	public List<Colaborador> getColaboradoresTableEdicao() {
		return colaboradoresTable;
	}

	public void setColaboradoresTableEdicao(List<Colaborador> colaboradores) {
		this.colaboradoresTable = colaboradores;
	}

	public Date getInicioEdicao() {
		return inicioEdicao;
	}

	public void setInicioEdicao(Date inicioEdicao) {
		this.inicioEdicao = inicioEdicao;
	}

	public Date getFimEdicao() {
		return fimEdicao;
	}

	public void setFimEdicao(Date fimEdicao) {
		this.fimEdicao = fimEdicao;
	}

	public List<Colaborador> getColaboradoresTranscricao() {
		return colaboradoresTranscricao;
	}

	public void setColaboradoresTranscricao(
			List<Colaborador> colaboradoresTranscricao) {
		this.colaboradoresTranscricao = colaboradoresTranscricao;
	}

	public List<Colaborador> getColaboradoresRevisaoM() {
		return colaboradoresRevisaoM;
	}

	public void setColaboradoresRevisaoM(List<Colaborador> colaboradoresRevisaoM) {
		this.colaboradoresRevisaoM = colaboradoresRevisaoM;
	}

	public List<Colaborador> getColaboradoresRevisaoS() {
		return colaboradoresRevisaoS;
	}

	public void setColaboradoresRevisaoS(List<Colaborador> colaboradoresRevisaoS) {
		this.colaboradoresRevisaoS = colaboradoresRevisaoS;
	}

	public List<Colaborador> getColaboradoresCadastroW() {
		return colaboradoresCadastroW;
	}

	public void setColaboradoresCadastroW(
			List<Colaborador> colaboradoresCadastroW) {
		this.colaboradoresCadastroW = colaboradoresCadastroW;
	}

	public TrabalhoCorpus getTrabalhoEdicao() {
		return trabalhoEdicao;
	}

	public void setTrabalhoEdicao(TrabalhoCorpus trabalhoEdicao) {
		this.trabalhoEdicao = trabalhoEdicao;
	}

	public TrabalhoCorpus getTrabalhoTranscricao() {
		return trabalhoTranscricao;
	}

	public void setTrabalhoTranscricao(TrabalhoCorpus trabalhoTranscricao) {
		this.trabalhoTranscricao = trabalhoTranscricao;
	}

	public TrabalhoCorpus getTrabalhoRevisaoM() {
		return trabalhoRevisaoM;
	}

	public void setTrabalhoRevisaoM(TrabalhoCorpus trabalhoRevisaoM) {
		this.trabalhoRevisaoM = trabalhoRevisaoM;
	}

	public TrabalhoCorpus getTrabalhoRevisaoS() {
		return trabalhoRevisaoS;
	}

	public void setTrabalhoRevisaoS(TrabalhoCorpus trabalhoRevisaoS) {
		this.trabalhoRevisaoS = trabalhoRevisaoS;
	}

	public TrabalhoCorpus getTrabalhoCadastroW() {
		return trabalhoCadastroW;
	}

	public void setTrabalhoCadastroW(TrabalhoCorpus trabalhoCadastroW) {
		this.trabalhoCadastroW = trabalhoCadastroW;
	}

	public Date getInicioTranscricao() {
		return inicioTranscricao;
	}

	public void setInicioTranscricao(Date inicioTranscricao) {
		this.inicioTranscricao = inicioTranscricao;
	}

	public Date getFimTranscricao() {
		return fimTranscricao;
	}

	public void setFimTranscricao(Date fimTranscricao) {
		this.fimTranscricao = fimTranscricao;
	}

	public Date getInicioRevisaoM() {
		return inicioRevisaoM;
	}

	public void setInicioRevisaoM(Date inicioRevisaoM) {
		this.inicioRevisaoM = inicioRevisaoM;
	}

	public Date getFimRevisaoM() {
		return fimRevisaoM;
	}

	public void setFimRevisaoM(Date fimRevisaoM) {
		this.fimRevisaoM = fimRevisaoM;
	}

	public Date getInicioRevisaoS() {
		return inicioRevisaoS;
	}

	public void setInicioRevisaoS(Date inicioRevisaoS) {
		this.inicioRevisaoS = inicioRevisaoS;
	}

	public Date getFimRevisaoS() {
		return fimRevisaoS;
	}

	public void setFimRevisaoS(Date fimRevisaoS) {
		this.fimRevisaoS = fimRevisaoS;
	}

	public Date getInicioCadastroW() {
		return inicioCadastroW;
	}

	public void setInicioCadastroW(Date inicioCadastroW) {
		this.inicioCadastroW = inicioCadastroW;
	}

	public Date getFimCadastroW() {
		return fimCadastroW;
	}

	public void setFimCadastroW(Date fimCadastroW) {
		this.fimCadastroW = fimCadastroW;
	}

	public TipoTrabalhoCorpus getTipoTrabalho() {
		return tipoTrabalho;
	}

	public void setTipoTrabalho(TipoTrabalhoCorpus tipoTrabalho) {
		this.tipoTrabalho = tipoTrabalho;
	}

}
