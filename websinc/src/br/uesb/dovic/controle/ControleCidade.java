package br.uesb.dovic.controle;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.uesb.dovic.entidades.Cidade;
import br.uesb.dovic.entidades.Estado;
import br.uesb.dovic.entidades.Pais;
import br.uesb.dovic.modelo.DAOCidade;
import br.uesb.dovic.modelo.DAOEstado;
import br.uesb.dovic.modelo.DAOPais;

@SuppressWarnings("serial")
@ManagedBean(name="controleCidade")
@ViewScoped
public class ControleCidade implements Serializable{
	private DAOCidade<Cidade> dao;
	private DAOEstado<Estado> daoEstado;
	private DAOPais<Pais> daoPais;
	private Cidade objeto;
	private boolean edicao;
	
	private List<Estado> listaEstados;
	private Estado selectedEstado;
	private List<Pais> listaPaises;
	private Pais selectedPais;
	
	
	
	
	@PostConstruct
    public void init(){
		selectedEstado= new Estado();
		//listaEstados= daoEstado.getByPais(2);

				
		selectedPais= new Pais();
		listaPaises= daoPais.listarTodos();
	}
	
	public ControleCidade(){
		dao = new DAOCidade<Cidade>();
		daoEstado= new DAOEstado<Estado>();
		daoPais= new DAOPais<Pais>();
		edicao=false;
	}
	
	public void atualizaEstados(){
//		try{
//		Thread.sleep(3000);
//		} 
//		catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
		listaEstados= daoEstado.getByPais(selectedPais.getId());
		
		
	}
	public String listar(){
		return "/pages/cadastros/cidade?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new Cidade();
		selectedEstado= new Estado();
		selectedPais= new Pais();
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
		
		objeto.setEstado(selectedEstado);
		
		if (objeto.getId() == null){
			gravou = dao.persist(objeto);
		}else {
			gravou = dao.merge(objeto);
		}
		if (gravou){
			edicao = false;}
		return null;
		
		
	}
	
	public String alterar(Cidade obj){
		objeto = obj;
		
		selectedPais = obj.getEstado().getPais();
		atualizaEstados();
		selectedEstado = obj.getEstado();
		edicao=true; 
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
		return null;
	}
	
	public DAOCidade<Cidade> getDao() {
		return dao;
	}
	public void setDao(DAOCidade<Cidade> dao) {
		this.dao = dao;
	}
	public Cidade getObjeto() {
		return objeto;
	}
	public void setObjeto(Cidade objeto) {
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

	
}
	

