package br.uesb.dovic.controle;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.uesb.dovic.entidades.Cidade;
import br.uesb.dovic.entidades.Estado;
import br.uesb.dovic.entidades.LocalDeposito;
import br.uesb.dovic.entidades.Pais;
import br.uesb.dovic.modelo.DAOCidade;
import br.uesb.dovic.modelo.DAOEstado;
import br.uesb.dovic.modelo.DAOLocalDeposito;
import br.uesb.dovic.modelo.DAOPais;

@SuppressWarnings("serial")
@ManagedBean(name="controleLocalDeposito")
@ViewScoped
public class ControleLocalDeposito implements Serializable{
	private DAOLocalDeposito<LocalDeposito> dao;
	private DAOEstado<Estado> daoEstado;
	private DAOPais<Pais> daoPais;
	private DAOCidade<Cidade> daoCidade;
	private LocalDeposito objeto;
	private boolean edicao;
	
	private List<Estado> listaEstados;
	private Estado selectedEstado;
	private List<Pais> listaPaises;
	private Pais selectedPais;
	private List<Cidade> listaCidades;
	private Cidade selectedCidade;
	
	
	
	
	@PostConstruct
    public void init(){
		selectedEstado= new Estado();
		selectedPais= new Pais();
		selectedCidade= new Cidade();
		listaPaises= daoPais.listarTodos();
		
	}
	
	public ControleLocalDeposito(){
		dao = new DAOLocalDeposito<LocalDeposito>();
		daoEstado= new DAOEstado<Estado>();
		daoPais= new DAOPais<Pais>();
		daoCidade= new DAOCidade<Cidade>();
		edicao=false;
	}
	
	public void atualizaEstados(){
		listaEstados= daoEstado.getByPais(selectedPais.getId());
	
		
	}
	
	
	public void atualizaCidades(){
		listaCidades= daoCidade.getByEstado(selectedEstado.getId());
	
		
	}
	
	public String listar(){
		return "/pages/cadastros/localDeposito?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new LocalDeposito();
		selectedEstado= new Estado();
		selectedPais= new Pais();
		selectedCidade= new Cidade();
		this.edicao = true;
		return null;		
	}
	

	public boolean getEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public String cancelar(){
		edicao = false;
		objeto=null;
		return null;
	}
	
	public String gravar(){
		
		boolean gravou = false;
		
		objeto.setCidade(selectedCidade);
		
		if (objeto.getId() == null){
			gravou = dao.persist(objeto);
		}else {
			gravou = dao.merge(objeto);
		}
		if (gravou){
			edicao = false;}
		return null;
		
		
	}
	
	public String alterar(LocalDeposito obj){
		objeto = obj;
		selectedPais = obj.getCidade().getEstado().getPais();
		atualizaEstados();
		selectedEstado = obj.getCidade().getEstado();
		atualizaCidades();
		selectedCidade = obj.getCidade();
		edicao=true; 
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
		return null;
	}
	
	public DAOLocalDeposito<LocalDeposito> getDao() {
		return dao;
	}
	public void setDao(DAOLocalDeposito<LocalDeposito> dao) {
		this.dao = dao;
	}
	public LocalDeposito getObjeto() {
		return objeto;
	}
	public void setObjeto(LocalDeposito objeto) {
		this.objeto = objeto;
	}

	public DAOEstado<Estado> getDaoEstado() {
		return daoEstado;
	}

	public void setDaoEstado(DAOEstado<Estado> daoEstado) {
		this.daoEstado = daoEstado;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public Estado getSelectedEstado() {
		return selectedEstado;
	}

	public void setSelectedEstado(Estado selectedEstado) {
		this.selectedEstado = selectedEstado;
	}

	public DAOPais<Pais> getDaoPais() {
		return daoPais;
	}

	public void setDaoPais(DAOPais<Pais> daoPais) {
		this.daoPais = daoPais;
	}

	public List<Pais> getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(List<Pais> listaPaises) {
		this.listaPaises = listaPaises;
	}

	public Pais getSelectedPais() {
		return selectedPais;
	}

	public void setSelectedPais(Pais selectedPais) {
		this.selectedPais = selectedPais;
	}

	public DAOCidade<Cidade> getDaoCidade() {
		return daoCidade;
	}

	public void setDaoCidade(DAOCidade<Cidade> daoCidade) {
		this.daoCidade = daoCidade;
	}

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public Cidade getSelectedCidade() {
		return selectedCidade;
	}

	public void setSelectedCidade(Cidade selectedCidade) {
		this.selectedCidade = selectedCidade;
	}

	
}