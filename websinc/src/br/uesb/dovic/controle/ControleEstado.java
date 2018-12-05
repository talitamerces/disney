package br.uesb.dovic.controle;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.uesb.dovic.entidades.Estado;
import br.uesb.dovic.entidades.Pais;
import br.uesb.dovic.modelo.DAOEstado;
import br.uesb.dovic.modelo.DAOPais;

@SuppressWarnings("serial")
@ManagedBean(name="controleEstado")
@ViewScoped
public class ControleEstado implements Serializable{
	private DAOEstado<Estado> dao;
	private DAOPais<Pais> daoPais;
	private Estado objeto;
	private boolean edicao;
	
	private List<Pais> listaPaises;
	private Pais selectedPais;
	
	
	@PostConstruct
    public void init(){
		selectedPais= new Pais();
		listaPaises= daoPais.listarTodos();
	}
	
	public ControleEstado(){
		dao = new DAOEstado<Estado>();
		daoPais= new DAOPais<Pais>();
		edicao=false;
	}
	
	public String listar(){
		return "/pages/cadastros/estado?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new Estado();
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
		
		objeto.setPais(selectedPais);
		
		if (objeto.getId() == null){
			gravou = dao.persist(objeto);
		}else {
			gravou = dao.merge(objeto);
		}
		if (gravou){
			edicao = false;}
		return null;
		
		
	}
	
	public String alterar(Estado obj){
		objeto = obj;
		selectedPais = obj.getPais();
		edicao=true; 
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
		return null;
	}
	
	public DAOEstado<Estado> getDao() {
		return dao;
	}
	public void setDao(DAOEstado<Estado> dao) {
		this.dao = dao;
	}
	public Estado getObjeto() {
		return objeto;
	}
	public void setObjeto(Estado objeto) {
		this.objeto = objeto;
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

	public DAOPais<Pais> getDaoPais() {
		return daoPais;
	}

	public void setDaoPais(DAOPais<Pais> daoPais) {
		this.daoPais = daoPais;
	}
	
	

}