package br.uesb.dovic.controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.uesb.dovic.entidades.Cor;
import br.uesb.dovic.entidades.DocumentoMacro;
import br.uesb.dovic.entidades.DocumentoMicro;
import br.uesb.dovic.entidades.Imagem;
import br.uesb.dovic.entidades.LocalDeposito;
import br.uesb.dovic.entidades.MaterialCapaForro;
import br.uesb.dovic.entidades.TipoDocumentoMacro;
import br.uesb.dovic.enums.LadoImagem;
import br.uesb.dovic.enums.TipoData;
import br.uesb.dovic.enums.TipoImagem;
import br.uesb.dovic.modelo.DAOCor;
import br.uesb.dovic.modelo.DAODocumentoMacro;
import br.uesb.dovic.modelo.DAODocumentoMicro;
import br.uesb.dovic.modelo.DAOImagem;
import br.uesb.dovic.modelo.DAOLocalDeposito;
import br.uesb.dovic.modelo.DAOMaterialCapaForro;
import br.uesb.dovic.modelo.DAOTipoMacro;
import br.uesb.dovic.servicos.ViewService;
import br.uesb.dovic.util.ImagemServlet;

@SuppressWarnings("serial")
@ManagedBean(name = "controleDocumentoMacro")
@SessionScoped
@ViewScoped
public class ControleDocumentoMacro implements Serializable {

	private DAODocumentoMacro<DocumentoMacro> dao;
	private List<DocumentoMacro> filteredDocs;

	private DAODocumentoMicro<DocumentoMicro> daoMicro;
	private DAOTipoMacro<TipoDocumentoMacro> daoTipoMacro;
	private DAOMaterialCapaForro<MaterialCapaForro> daoMaterial;
	private DAOLocalDeposito<LocalDeposito> daoLocal;
	private DAOImagem<Imagem> daoImagem;
	private DAOCor<Cor> daoCor;
	private TipoDocumentoMacro selectedTipoMacro;
	private MaterialCapaForro selectedMaterialCapa;
	private MaterialCapaForro selectedMaterialForro;
	private MaterialCapaForro removedMaterialForro;
	private Cor selectedCorCapa;
	private LocalDeposito selectedLocalDeposito;

	private DocumentoMacro objeto;
	private boolean edicao;
	private List<TipoDocumentoMacro> listaTipos;
	private List<MaterialCapaForro> listaMateriais;
	private List<MaterialCapaForro> forroList;
	private List<LocalDeposito> listaLocaisDeposito;
	private List<Cor> listaCores;
	private boolean comCapa;
	private boolean temCapa;
	private boolean temContraCapa;
	private boolean temLombada;
	private boolean temAbertura;
	private boolean temFechamento;
	private boolean comForro;
	private int tabAtiva;

	private String caminhoImagemCapa;
	private InputStream arquivoImagemCapa;
	private String caminhoImagemContraCapa;

	private String caminhoImagemAbertura;

	private String caminhoImagemFechamento;

	private String caminhoImagemLombada;
	private InputStream arquivoImagemLombada;

	private List<SelectItem> listaTipoData;

	private TipoData selectedTipoData;

	private ViewService viewService;

	private String text;
	private Integer anoInicio;
	private Integer anoFim;
	private static final String SEPARATOR = File.separator;
	private static final String HOME_FOLDER = System.getProperty("user.home");
	private static String IMAGENS_FOLDER = HOME_FOLDER + SEPARATOR + "websinc"
			+ SEPARATOR + "imagens" + SEPARATOR;
	private String caminho;

	String teste;

	public String getImagensCatalogo(TipoImagem tipoImagem, LadoImagem lado) {

		List<Imagem> catalogo = daoImagem.getByDocumentoMacro(objeto.getId());
		for (Imagem img : catalogo) {
			if (img.getTipoImagem() == tipoImagem
					&& img.getLadoImagem() == lado) {
				teste = ImagemServlet.getURL(img.getEnderecoImagem());
				return ImagemServlet.getURL(img.getEnderecoImagem());
			}
		}
		return null;
	}

	public String getImagensCatalogo(TipoImagem tipoImagem) {

		List<Imagem> catalogo = daoImagem.getByDocumentoMacro(objeto.getId());
		for (Imagem img : catalogo) {
			if (img.getTipoImagem() == tipoImagem) {
				teste = ImagemServlet.getURL(img.getEnderecoImagem());
				return ImagemServlet.getURL(img.getEnderecoImagem());
			}

		}
		return null;
	}

	public String getEnderecoCapaFrente() {
		return getImagensCatalogo(TipoImagem.CAPA, LadoImagem.FRENTE);
	}

	public String getEnderecoCapaVerso() {
		return getImagensCatalogo(TipoImagem.CAPA, LadoImagem.VERSO);
	}

	public String getEnderecoLombada() {
		return getImagensCatalogo(TipoImagem.LOMBADA);
	}

	public String getEnderecoTermoAbertura() {
		return getImagensCatalogo(TipoImagem.ABERTURA);
	}

	public String getEnderecoTermoFechamento() {
		return getImagensCatalogo(TipoImagem.FECHAMENTO);
	}

	public void ajaxAnoInicio() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(objeto.getDataInicioDocumento());
		anoInicio = calendar.get(Calendar.YEAR);

	}

	public void ajaxAnoFim() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(objeto.getDataFimDocumento());
		anoFim = calendar.get(Calendar.YEAR);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@PostConstruct
	public void init() {
		selectedTipoMacro = new TipoDocumentoMacro();
		tabAtiva = 0;
		selectedLocalDeposito = new LocalDeposito();
		selectedCorCapa = new Cor();
		listaTipos = daoTipoMacro.listarTodos();
		listaMateriais = daoMaterial.listarTodos();
		listaCores = daoCor.listarTodos();
		listaLocaisDeposito = daoLocal.listarTodos();
		forroList = new ArrayList<MaterialCapaForro>();
		viewService = new ViewService();
		listaTipoData = viewService.getSelectItensFromArray(TipoData.values(),
				"toString");
		caminho = IMAGENS_FOLDER;
	}

	public ControleDocumentoMacro() {
		dao = new DAODocumentoMacro<DocumentoMacro>();
		daoMicro = new DAODocumentoMicro<DocumentoMicro>();
		daoTipoMacro = new DAOTipoMacro<TipoDocumentoMacro>();
		daoMaterial = new DAOMaterialCapaForro<MaterialCapaForro>();
		daoImagem = new DAOImagem<Imagem>();
		daoCor = new DAOCor<Cor>();
		daoLocal = new DAOLocalDeposito<LocalDeposito>();
		edicao = false;
		comCapa = false;
		comForro = false;
		text = "teste";

	}

	public String getTipoEscolhido() {
		return selectedTipoMacro != null && selectedTipoMacro.getId() != null ? selectedTipoMacro
				.toString() : "Tipo não escolhido";
	}

	public List<DocumentoMicro> getDocumentosMicro() {
		List<DocumentoMicro> documentos = new ArrayList<DocumentoMicro>();
		documentos = daoMicro.getByDocumentoMacro(objeto.getId());
		return documentos;

	}

	public String inserir() {
		return "/pages/corpus/inserirDocumentoMacro?faces-redirect=true";
	}

	// criando método para chamar o tcc
	public String inserirTcc() {
		return "/pages/corpus/inserirDocumentoMacroTcc?faces-redirect=true";
	}

	public String listar() {
		edicao = false;
		objeto = null;
		// forroList.addAll(listaMateriais);
		return "/pages/corpus/listarDocumentoMacro?faces-redirect=true";
	}

	public String select() {

		return "/pages/corpus/detalhesDocumentoMacro?faces-redirect=true";
	}

	public String inserirFilho() {

		return "/pages/corpus/inserirDocumentoFilho?faces-redirect=true";
	}

	public String inserirFilhoTcc() {

		return "/pages/corpus/inserirDocumentoFilhoTcc?faces-redirect=true";
	}

	public String catalogoMicro() {

		return "/pages/corpus/detalhesDocumentoFilho?faces-redirect=true";
	}

	public String inserirImagem() {

		return "/pages/corpus/inserirImagem?faces-redirect=true";
	}

	// tcc
	public String inserirImagemTcc() {

		return "/pages/corpus/inserirImagemTcc?faces-redirect=true";
	}

	public String novo() {
		objeto = new DocumentoMacro();
		selectedTipoMacro = new TipoDocumentoMacro();
		selectedMaterialForro = new MaterialCapaForro();
		selectedCorCapa = new Cor();
		selectedLocalDeposito = new LocalDeposito();
		caminhoImagemCapa = "";
		temCapa = false;
		temContraCapa = false;
		temAbertura = false;
		temFechamento = false;
		temLombada = false;
		caminhoImagemContraCapa = "";
		caminhoImagemLombada = "";
		caminhoImagemAbertura = "";
		caminhoImagemFechamento = "";
		anoInicio = null;
		anoFim = null;
		comCapa = false;
		comForro = false;
		tabAtiva = 0;
		forroList.clear();
		copiarElementos(listaMateriais, forroList);
		this.edicao = true;
		return "/pages/corpus/inserirDocumentoMacro?faces-redirect=true";
	}

	public String novoTcc() {
		objeto = new DocumentoMacro();
		selectedTipoMacro = new TipoDocumentoMacro();
		selectedMaterialForro = new MaterialCapaForro();
		selectedCorCapa = new Cor();
		selectedLocalDeposito = new LocalDeposito();
		caminhoImagemCapa = "";
		temCapa = false;
		temContraCapa = false;
		temAbertura = false;
		temFechamento = false;
		temLombada = false;
		caminhoImagemContraCapa = "";
		caminhoImagemLombada = "";
		caminhoImagemAbertura = "";
		caminhoImagemFechamento = "";
		anoInicio = null;
		anoFim = null;
		comCapa = false;
		comForro = false;
		tabAtiva = 0;
		forroList.clear();
		copiarElementos(listaMateriais, forroList);
		this.edicao = true;
		return "/pages/corpus/inserirDocumentoMacroTcc?faces-redirect=true";
	}

	private void copiarElementos(List<MaterialCapaForro> origem,
			List<MaterialCapaForro> destino) {
		for (MaterialCapaForro mcf : origem) {
			MaterialCapaForro m = new MaterialCapaForro();
			m.setId(mcf.getId());
			m.setNomeMaterialCapa(mcf.getNomeMaterialCapa());
			m.setSelecionado(false);
			destino.add(m);
		}
	}

	public String cancelar() {
		edicao = false;
		objeto = null;
		forroList.clear();
		// forroList.addAll(listaMateriais);
		return null;
	}

	public String cancelarTcc() {
		edicao = false;
		objeto = null;

		return "/pages/corpus/inserirDocumentoMacroTcc?faces-redirect=true";
	}

	public String inicio() {
		return "/index?faces-redirect=true";
	}

	public void gravar() {

		if (anoFim != 0)
			objeto.setAnoFimDocumento(anoFim);
		if (anoInicio != 0)
			objeto.setAnoInicioDocumento(anoInicio);
		objeto.setTipoMacro(selectedTipoMacro);
		if (selectedMaterialCapa != null) {
			objeto.setMaterialCapa(selectedMaterialCapa);
			objeto.setCorCapa(selectedCorCapa);

		}

		objeto.setTipoData(selectedTipoData);
		objeto.setLocalEncontrado(selectedLocalDeposito);
		objeto.setComCapa(comCapa);

		if (comForro == true) {
			MaterialCapaForro mf;
			List<MaterialCapaForro> novaLista = new ArrayList<MaterialCapaForro>();

			for (MaterialCapaForro m : forroList) {
				if (m.isSelecionado()) {
					mf = daoMaterial.getEm().find(MaterialCapaForro.class,
							m.getId());
					novaLista.add(mf);
				}

			}

			objeto.setMateriaisForro(novaLista);

		}

		else {
			forroList.clear();
			forroList.addAll(listaMateriais);
		}

		boolean gravou = false;
		if (objeto.getId() == null) {
			gravou = dao.persist(objeto);

		} else {
			gravou = dao.merge(objeto);
		}
		if (gravou) {
			edicao = false;
		}

	}

	public String alterar(DocumentoMacro obj) {
		tabAtiva = 0;
		objeto = obj;
		selectedTipoMacro = objeto.getTipoMacro();
		selectedMaterialCapa = objeto.getMaterialCapa();
		selectedCorCapa = objeto.getCorCapa();
		selectedTipoData = objeto.getTipoData();
		selectedLocalDeposito = objeto.getLocalEncontrado();

		// Lista de materiais salvar para o documento:
		List<MaterialCapaForro> listaForro = objeto.getMateriaisForro();
		MaterialCapaForro mcf;

		forroList.clear();

		for (MaterialCapaForro mf : listaMateriais) {
			mcf = new MaterialCapaForro();
			mcf.setId(mf.getId());
			mcf.setNomeMaterialCapa(mf.getNomeMaterialCapa());
			if (listaForro.contains(mf))
				mcf.setSelecionado(true);
			else
				mcf.setSelecionado(false);
			forroList.add(mcf);
		}

		edicao = true;
		anoInicio = objeto.getAnoInicioDocumento();
		anoFim = objeto.getAnoFimDocumento();
		comCapa = objeto.getComCapa();
		if (forroList.isEmpty())
			comForro = false;
		else
			comForro = true;
		return "/pages/corpus/inserirDocumentoMacro?faces-redirect=true";
	}

	// tcc
	public String alterarTcc(DocumentoMacro obj) {
		tabAtiva = 0;
		objeto = obj;
		selectedTipoMacro = objeto.getTipoMacro();
		selectedMaterialCapa = objeto.getMaterialCapa();
		selectedCorCapa = objeto.getCorCapa();
		selectedTipoData = objeto.getTipoData();
		selectedLocalDeposito = objeto.getLocalEncontrado();

		// Lista de materiais salvar para o documento:
		List<MaterialCapaForro> listaForro = objeto.getMateriaisForro();
		MaterialCapaForro mcf;

		forroList.clear();

		for (MaterialCapaForro mf : listaMateriais) {
			mcf = new MaterialCapaForro();
			mcf.setId(mf.getId());
			mcf.setNomeMaterialCapa(mf.getNomeMaterialCapa());
			if (listaForro.contains(mf))
				mcf.setSelecionado(true);
			else
				mcf.setSelecionado(false);
			forroList.add(mcf);
		}

		edicao = true;
		anoInicio = objeto.getAnoInicioDocumento();
		anoFim = objeto.getAnoFimDocumento();
		comCapa = objeto.getComCapa();
		if (forroList.isEmpty())
			comForro = false;
		else
			comForro = true;
		return "/pages/corpus/inserirDocumentoMacroTcc?faces-redirect=true";
	}

	public String excluir() {

		dao.remove(objeto);
		edicao = false;
		return null;
	}

	public TipoDocumentoMacro getSelectedTipoMacro() {
		return selectedTipoMacro;
	}

	public void setSelectedTipoMacro(TipoDocumentoMacro selectedTipoMacro) {

		this.selectedTipoMacro = selectedTipoMacro;

	}

	public void copyFile(String fileName, InputStream in) {

		try {

			OutputStream out = new FileOutputStream(new File(IMAGENS_FOLDER
					+ fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DAOTipoMacro<TipoDocumentoMacro> getDaoTipoMacro() {
		return daoTipoMacro;
	}

	public void setDaoTipoMacro(DAOTipoMacro<TipoDocumentoMacro> daoTipoMacro) {
		this.daoTipoMacro = daoTipoMacro;
	}

	public InputStream getArquivoImagemCapa() {
		return arquivoImagemCapa;
	}

	public void setArquivoImagemCapa(InputStream arquivoImagemCapa) {
		this.arquivoImagemCapa = arquivoImagemCapa;
	}

	public String getCaminhoImagemLombada() {
		return caminhoImagemLombada;
	}

	public void setCaminhoImagemLombada(String caminhoImagemLombada) {
		this.caminhoImagemLombada = caminhoImagemLombada;
	}

	public InputStream getArquivoImagemLombada() {
		return arquivoImagemLombada;
	}

	public void setArquivoImagemLombada(InputStream arquivoImagemLombada) {
		this.arquivoImagemLombada = arquivoImagemLombada;
	}

	public String getCaminhoImagemCapa() {

		return caminhoImagemCapa;
	}

	public void setCaminhoImagemCapa(String caminhoImagemCapa) {
		this.caminhoImagemCapa = caminhoImagemCapa;
	}

	public boolean getEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public List<TipoDocumentoMacro> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<TipoDocumentoMacro> listaTipos) {
		this.listaTipos = listaTipos;
	}

	public DAODocumentoMacro<DocumentoMacro> getDao() {
		return dao;
	}

	public void setDao(DAODocumentoMacro<DocumentoMacro> dao) {
		this.dao = dao;
	}

	public DocumentoMacro getObjeto() {
		return objeto;
	}

	public void setObjeto(DocumentoMacro objeto) {
		this.objeto = objeto;

	}

	public List<SelectItem> getListaTipoData() {
		return listaTipoData;
	}

	public void setListaTipoData(List<SelectItem> listaTipoData) {
		this.listaTipoData = listaTipoData;
	}

	public TipoData getSelectedTipoData() {
		return selectedTipoData;
	}

	public void setSelectedTipoData(TipoData selectedTipoData) {
		this.selectedTipoData = selectedTipoData;
	}

	public MaterialCapaForro getSelectedMaterialCapa() {
		return selectedMaterialCapa;
	}

	public void setSelectedMaterialCapa(MaterialCapaForro selectedMaterialCapa) {
		this.selectedMaterialCapa = selectedMaterialCapa;
	}

	public List<MaterialCapaForro> getListaMateriais() {
		return listaMateriais;
	}

	public void setListaMateriais(List<MaterialCapaForro> listaMateriais) {
		this.listaMateriais = listaMateriais;
	}

	public List<MaterialCapaForro> getForroList() {
		return forroList;
	}

	public void setForroList(List<MaterialCapaForro> forroList) {
		this.forroList = forroList;
	}

	public MaterialCapaForro getSelectedMaterialForro() {
		return selectedMaterialForro;
	}

	public void setSelectedMaterialForro(MaterialCapaForro selectedMaterialForro) {
		this.selectedMaterialForro = selectedMaterialForro;
	}

	public MaterialCapaForro getReomvedMaterialForro() {
		return removedMaterialForro;
	}

	public void setReomvedMaterialForro(MaterialCapaForro reomvedMaterialForro) {
		this.removedMaterialForro = reomvedMaterialForro;
	}

	public String getCaminhoImagemContraCapa() {
		return caminhoImagemContraCapa;
	}

	public void setCaminhoImagemContraCapa(String caminhoImagemContraCapa) {
		this.caminhoImagemContraCapa = caminhoImagemContraCapa;
	}

	public String getCaminhoImagemAbertura() {
		return caminhoImagemAbertura;
	}

	public void setCaminhoImagemAbertura(String caminhoImagemAbertura) {
		this.caminhoImagemAbertura = caminhoImagemAbertura;
	}

	public String getCaminhoImagemFechamento() {
		return caminhoImagemFechamento;
	}

	public void setCaminhoImagemFechamento(String caminhoImagemFechamento) {
		this.caminhoImagemFechamento = caminhoImagemFechamento;
	}

	public LocalDeposito getSelectedLocalDeposito() {
		return selectedLocalDeposito;
	}

	public void setSelectedLocalDeposito(LocalDeposito selectedLocalDeposito) {
		this.selectedLocalDeposito = selectedLocalDeposito;
	}

	public List<LocalDeposito> getListaLocaisDeposito() {
		return listaLocaisDeposito;
	}

	public void setListaLocaisDeposito(List<LocalDeposito> listaLocaisDeposito) {
		this.listaLocaisDeposito = listaLocaisDeposito;
	}

	public Integer getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(Integer anoInicio) {
		this.anoInicio = anoInicio;
	}

	public Integer getAnoFim() {
		return anoFim;
	}

	public void setAnoFim(Integer anoFim) {
		this.anoFim = anoFim;
	}

	public boolean isComCapa() {
		return comCapa;
	}

	public void setComCapa(boolean comCapa) {
		this.comCapa = comCapa;
	}

	public boolean isComForro() {
		return comForro;
	}

	public void setComForro(boolean comForro) {
		this.comForro = comForro;
	}

	public MaterialCapaForro getRemovedMaterialForro() {
		return removedMaterialForro;
	}

	public void setRemovedMaterialForro(MaterialCapaForro removedMaterialForro) {
		this.removedMaterialForro = removedMaterialForro;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public boolean isTemCapa() {
		return temCapa;
	}

	public void setTemCapa(boolean temCapa) {
		this.temCapa = temCapa;
	}

	public boolean isTemContraCapa() {
		return temContraCapa;
	}

	public void setTemContraCapa(boolean temContraCapa) {
		this.temContraCapa = temContraCapa;
	}

	public boolean isTemLombada() {
		return temLombada;
	}

	public void setTemLombada(boolean temLombada) {
		this.temLombada = temLombada;
	}

	public boolean isTemAbertura() {
		return temAbertura;
	}

	public void setTemAbertura(boolean temAbertura) {
		this.temAbertura = temAbertura;
	}

	public boolean isTemFechamento() {
		return temFechamento;
	}

	public void setTemFechamento(boolean temFechamento) {
		this.temFechamento = temFechamento;
	}

	public int getTabAtiva() {
		return tabAtiva;
	}

	public void setTabAtiva(int tabAtiva) {
		this.tabAtiva = tabAtiva;
	}

	public Cor getSelectedCorCapa() {
		return selectedCorCapa;
	}

	public void setSelectedCorCapa(Cor selectedCorCapa) {
		this.selectedCorCapa = selectedCorCapa;
	}

	public List<Cor> getListaCores() {
		return listaCores;
	}

	public void setListaCores(List<Cor> listaCores) {
		this.listaCores = listaCores;
	}

	public List<DocumentoMacro> getFilteredDocs() {
		return filteredDocs;
	}

	public void setFilteredDocs(List<DocumentoMacro> filteredDocs) {
		this.filteredDocs = filteredDocs;
	}
}
