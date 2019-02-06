package br.uesb.dovic.controle;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;

import br.uesb.dovic.entidades.Colaborador;
import br.uesb.dovic.entidades.DocumentoMacro;
import br.uesb.dovic.entidades.Imagem;
import br.uesb.dovic.entidades.TipoDocumentoMicro;
import br.uesb.dovic.enums.LadoImagem;
import br.uesb.dovic.enums.TipoImagem;
import br.uesb.dovic.modelo.DAODocumentoMacro;
import br.uesb.dovic.modelo.DAOImagem;
import br.uesb.dovic.servicos.FileService;
import br.uesb.dovic.servicos.SyntaxToXML;
import br.uesb.dovic.servicos.ViewService;
import br.uesb.dovic.servicos.XMLService;
import br.uesb.dovic.util.ImagemServlet;

@SuppressWarnings({ "serial", "unused" })
@ManagedBean(name = "controleImagem")
@ViewScoped
public class ControleImagem implements Serializable {

	private Integer selectedMacroId;
	private DocumentoMacro selectedMacro;

	private DAOImagem<Imagem> dao;
	private DAODocumentoMacro<DocumentoMacro> daoMacro;
	private List<Imagem> imagensMacro;
	private Imagem objeto;
	private Imagem selectedImagem;
	private Integer numeroPagina;
	private Integer numeroFolha;
	private Integer numeroSequencia;
	private Integer numeroColeta;
	private String codigoImagem;
	private List<SelectItem> listaTipoImagem;
	private List<SelectItem> listaLadoImagem;
	private TipoImagem selectedTipoImagem;
	private LadoImagem selectedLadoImagem;
	private boolean edicao;
	private ViewService viewService;
	private String caminhoImagem;
	private InputStream arquivoImagem;
	private boolean naoSalvar;

	public ControleImagem() {
		edicao = false;
		naoSalvar = true;
		dao = new DAOImagem<Imagem>();
		daoMacro = new DAODocumentoMacro<DocumentoMacro>();
		viewService = new ViewService();
		selectedTipoImagem = TipoImagem.FOLHA;
		selectedLadoImagem = LadoImagem.FRENTE;
		listaTipoImagem = viewService.getSelectItensFromArrayWithoutEmpty(
				TipoImagem.values(), "toString");
		listaLadoImagem = viewService.getSelectItensFromArrayWithoutEmpty(
				LadoImagem.values(), "toString");
		selectedImagem = new Imagem();

	}

	@PostConstruct
	public void init() {

		String idMacro = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("idMacroSelected");
		selectedMacroId = Integer.parseInt(idMacro);
		selectedMacro = daoMacro.getEm().find(DocumentoMacro.class,
				selectedMacroId);
		imagensMacro = dao.getByDocumentoMacro(selectedMacroId);
	}

	public void gravar() {
		String arquivoAntigo = new String();
		boolean gravou = false;
		objeto.setTipoImagem(selectedTipoImagem);
		objeto.setLadoImagem(selectedLadoImagem);
		objeto.setNumeroFolha(numeroFolha);
		objeto.setNumeroPagina(numeroPagina);
		objeto.setNumeroSequencia(numeroSequencia);
		objeto.setCodigoImagem(codigoImagem);

		if (arquivoImagem != null) {
			arquivoAntigo = objeto.getEnderecoImagem();
			objeto.setEnderecoImagem(caminhoImagem);
		}
		if (objeto.getId() == null)
			gravou = dao.persist(objeto);

		else
			gravou = dao.merge(objeto);

		if (gravou) {
			imagensMacro.add(objeto);
			edicao = false;
			if (arquivoImagem != null)
				FileService.copyFile(caminhoImagem, arquivoImagem);
			if (arquivoAntigo != null)
				if (!arquivoAntigo.equals(""))
					FileService.deleteFile(arquivoAntigo);

		}
	}

	public void uploadArquivoImagem(FileUploadEvent event) {

		double time = System.currentTimeMillis();
		String extensao = FileService
				.getExtensao(event.getFile().getFileName());

		caminhoImagem = "IMG_" + time + "." + extensao;
		try {
			arquivoImagem = event.getFile().getInputstream();
			naoSalvar = false;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getEnderecoImagem(Imagem img) {
		if (img == null)
			return null;
		if (img.getEnderecoImagem() == null)
			return null;
		return ImagemServlet.getURL(img.getEnderecoImagem());
	}

	
	
	
	public void novo() {
		objeto = new Imagem();
		objeto.setDocumentoMacro(selectedMacro);
		selectedTipoImagem = TipoImagem.FOLHA;
		selectedLadoImagem = LadoImagem.FRENTE;
		codigoImagem = selectedMacro.getIdDocumentoFisico() + "-W-Z";
		numeroSequencia = null;
		numeroPagina = null;
		numeroColeta = null;
		numeroFolha = null;
		caminhoImagem = "";
		arquivoImagem = null;
		naoSalvar = true;
		this.edicao = true;
	}

	public void ajaxCodigoImagem() {
		codigoImagem = selectedMacro.getIdDocumentoFisico() + "-C"
				+ numeroColeta + "-" + numeroSequencia;
	}

	public void cancelar() {
		edicao = false;
		objeto = null;

	}

	public void alterar(Imagem obj) {
		objeto = obj;
		selectedMacro = objeto.getDocumentoMacro();
		selectedTipoImagem = objeto.getTipoImagem();
		selectedLadoImagem = objeto.getLadoImagem();
		numeroPagina = objeto.getNumeroPagina();
		numeroFolha = objeto.getNumeroFolha();
		numeroSequencia = objeto.getNumeroSequencia();
		codigoImagem = objeto.getCodigoImagem();
		caminhoImagem = objeto.getEnderecoImagem();
		arquivoImagem = null;
		naoSalvar = false;
		edicao = true;

	}

	public void excluir() {

		Imagem img = dao.getEm().find(Imagem.class, objeto.getId());
		dao.remove(img);
		String arquivo = objeto.getEnderecoImagem();
		FileService.deleteFile(arquivo);
		imagensMacro.remove(img);
		edicao = false;

	}

	public List<Imagem> getImagensCatalogo() {
		List<Imagem> lista = dao.getImagensCatalogo(selectedMacro.getId());
		return lista;
	}

	public DAOImagem<Imagem> getDaoImagem() {
		return dao;
	}

	public void setDaoImagem(DAOImagem<Imagem> daoImagem) {
		this.dao = daoImagem;
	}

	public Imagem getObjeto() {
		return objeto;
	}

	public void setObjeto(Imagem objeto) {
		this.objeto = objeto;
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

	public DocumentoMacro getSelectedMacro() {
		return selectedMacro;
	}

	public void setSelectedMacro(DocumentoMacro selectedMacro) {
		this.selectedMacro = selectedMacro;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public boolean isNaoSalvar() {
		return naoSalvar;
	}

	public void setNaoSalvar(boolean naoSalvar) {
		this.naoSalvar = naoSalvar;
	}

	public List<SelectItem> getListaLadoImagem() {
		return listaLadoImagem;
	}

	public void setListaLadoImagem(List<SelectItem> listaLadoImagem) {
		this.listaLadoImagem = listaLadoImagem;
	}

	public LadoImagem getSelectedLadoImagem() {
		return selectedLadoImagem;
	}

	public void setSelectedLadoImagem(LadoImagem selectedLadoImagem) {
		this.selectedLadoImagem = selectedLadoImagem;
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

	public Integer getNumeroSequencia() {
		return numeroSequencia;
	}

	public void setNumeroSequencia(Integer numeroSequencia) {
		this.numeroSequencia = numeroSequencia;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public InputStream getArquivoImagem() {
		return arquivoImagem;
	}

	public void setArquivoImagem(InputStream arquivoImagem) {
		this.arquivoImagem = arquivoImagem;
	}

	public Integer getNumeroColeta() {
		return numeroColeta;
	}

	public void setNumeroColeta(Integer numeroColeta) {
		this.numeroColeta = numeroColeta;
	}

	public String getCodigoImagem() {
		return codigoImagem;
	}

	public void setCodigoImagem(String codigoImagem) {
		this.codigoImagem = codigoImagem;
	}

	public Integer getSelectedMacroId() {
		return selectedMacroId;
	}

	public void setSelectedMacroId(Integer selectedMacroId) {
		this.selectedMacroId = selectedMacroId;
	}

	public List<Imagem> getImagensMacro() {
		return imagensMacro;
	}

	public void setImagensMacro(List<Imagem> imagensMacro) {
		this.imagensMacro = imagensMacro;
	}

	public Imagem getSelectedImagem() {
		return selectedImagem;
	}

	public void setSelectedImagem(Imagem selectedImagem) {
		this.selectedImagem = selectedImagem;
	}

}
